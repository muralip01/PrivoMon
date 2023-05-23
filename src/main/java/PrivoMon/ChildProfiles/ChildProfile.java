package PrivoMon.ChildProfiles;

import PrivoMon.Users.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class ChildProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int age;
    @Embedded
    private MonitoringSettings monitoringSettings;

    @ManyToOne
    private User parent;

    @OneToMany(mappedBy = "childProfile", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TimeMonitoringSession> timeMonitoringSessions = new ArrayList<>();

    @OneToMany(mappedBy = "childProfile", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<WindowActivity> windowActivities = new ArrayList<>();

    public ChildProfile() { }

    public ChildProfile(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public MonitoringSettings getMonitoringSettings() {
        return monitoringSettings;
    }

    public void setMonitoringSettings(MonitoringSettings monitoringSettings) {
        this.monitoringSettings = monitoringSettings;
    }

    public User getParent() {
        return parent;
    }

    public void setParent(User parent) {
        this.parent = parent;
    }

    public List<TimeMonitoringSession> getTimeMonitoringSessions() {
        return timeMonitoringSessions;
    }

    public void setTimeMonitoringSessions(List<TimeMonitoringSession> timeMonitoringSessions) {
        this.timeMonitoringSessions = timeMonitoringSessions;
    }

    public void addTimeMonitoringSession(TimeMonitoringSession session) {
        timeMonitoringSessions.add(session);
        session.setChildProfile(this);
    }

    public void removeTimeMonitoringSession(TimeMonitoringSession session) {
        timeMonitoringSessions.remove(session);
        session.setChildProfile(null);
    }

    public List<WindowActivity> getWindowActivities() {
        return windowActivities;
    }

    public void setWindowActivities(List<WindowActivity> windowActivities) {
        this.windowActivities = windowActivities;
    }

    public void addWindowActivity(WindowActivity windowActivity) {
        windowActivities.add(windowActivity);
        windowActivity.setChildProfile(this);
    }

    public void removeWindowActivity(WindowActivity windowActivity) {
        windowActivities.remove(windowActivity);
        windowActivity.setChildProfile(null);
    }

}