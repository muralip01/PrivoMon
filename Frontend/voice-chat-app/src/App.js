import React from 'react';
import VoiceChat from './VoiceChat';

function App() {
    return (
        <div>
            <VoiceChat roomId={1} userId={1} />
            <VoiceChat roomId={1} userId={2} />
        </div>
    );
}

export default App;