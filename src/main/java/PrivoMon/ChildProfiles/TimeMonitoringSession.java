package PrivoMon.ChildProfiles;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class TimeMonitoringSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    @ManyToOne
    private ChildProfile childProfile;

    public TimeMonitoringSession() {
    }

    public TimeMonitoringSession(LocalDateTime startTime, LocalDateTime endTime, ChildProfile childProfile) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.childProfile = childProfile;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public ChildProfile getChildProfile() {
        return childProfile;
    }

    public void setChildProfile(ChildProfile childProfile) {
        this.childProfile = childProfile;
    }
}