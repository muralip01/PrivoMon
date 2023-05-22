import React, { useEffect, useRef, useState } from 'react';
import Peer from 'simple-peer';

const VoiceChat = ({ roomId, userId }) => {
    const peers = useRef({});
    const [stream, setStream] = useState(null);
    const [ws, setWs] = useState(null); 

    useEffect(() => {
        const websocket = new WebSocket(`ws://localhost:8080/chat/${roomId}/${userId}`);
        setWs(websocket);

        return () => {
            websocket.close();
        };
    }, [roomId, userId]);

    // Get audio stream from the user's microphone when the component mounts
    useEffect(() => {
        navigator.mediaDevices.getUserMedia({ audio: true }).then(stream => {
            setStream(stream);
        });
    }, []);

    useEffect(() => {
        if (ws) {
            ws.onmessage = (event) => {
                const data = JSON.parse(event.data);

                switch (data.type) {
                    case 'USER_JOINED':
                        if (data.userId < userId) { // Only create a Peer if the other user is the initiator
                            createPeer(data.userId, data.signal);
                        }
                        break;
                    case 'USER_LEFT':
                        delete peers.current[data.userId];
                        break;
                    case 'SIGNAL':
                        if (peers.current[data.userId]) {
                            peers.current[data.userId].signal(data.signal);
                        } else {
                            createPeer(data.userId, data.signal);
                        }
                        break;
                    case 'SIGNAL_TO_INITIATOR':
                        if (peers.current[data.userId]) {
                            peers.current[data.userId].signal(data.signal);
                        }
                        break;
                    default:
                        break;
                }
            };
        }

        return () => {
            Object.values(peers.current).forEach(peer => peer.destroy());
            if (stream) {
                stream.getTracks().forEach(track => track.stop());
            }
        };
    }, [stream, ws]);

    const createPeer = (userId, initiatorSignal) => {
        const peer = new Peer({
            initiator: true,
            trickle: false,
            stream,
        });

        peer.on('signal', signal => {
            ws.send(JSON.stringify({
                type: 'SIGNAL',
                userId,
                signal,
            }));
        });

        peer.signal(initiatorSignal);
        peers.current[userId] = peer;
    };

    return (
        <div>
            <h1>Voice Chat Room: {roomId}</h1>
        </div>
    );
};

export default VoiceChat;