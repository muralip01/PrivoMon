package com.watchtime.VideoHandler;

public class VideoActionMessage {

    private String type;
    private double time;
    private String videoUrl;

    public VideoActionMessage() {}

    public VideoActionMessage(String type, double time, String videoUrl) {
        this.type = type;
        this.time = time;
        this.videoUrl = videoUrl;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

}