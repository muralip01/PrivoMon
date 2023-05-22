package PrivoMon.VideoHandler;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.Map;

@Controller
public class VideoController {
    private Map<String, VideoActionMessage> videoStates = new HashMap<>();

    @MessageMapping("/video")
    @SendTo("/topic/video")
    public VideoActionMessage handleVideoAction(VideoActionMessage message) {
        videoStates.put(message.getVideoUrl(), message);
        return message;
    }

    @MessageMapping("/getVideoState")
    @SendTo("/topic/videoState")
    public VideoActionMessage getVideoState(String videoUrl) {
        return videoStates.get(videoUrl);
    }
}