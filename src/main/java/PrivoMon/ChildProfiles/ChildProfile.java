package PrivoMon.ChildProfiles;

import PrivoMon.Users.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

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

}