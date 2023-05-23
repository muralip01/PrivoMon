package PrivoMon.ChildProfiles;

import javax.persistence.*;

@Entity
public class WindowActivity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String windowTitle;

    private String applicationName;

    @ManyToOne
    private ChildProfile childProfile;

    public WindowActivity() {
    }

    public WindowActivity(String windowTitle, String applicationName, ChildProfile childProfile) {
        this.windowTitle = windowTitle;
        this.applicationName = applicationName;
        this.childProfile = childProfile;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWindowTitle() {
        return windowTitle;
    }

    public void setWindowTitle(String windowTitle) {
        this.windowTitle = windowTitle;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public ChildProfile getChildProfile() {
        return childProfile;
    }

    public void setChildProfile(ChildProfile childProfile) {
        this.childProfile = childProfile;
    }
}