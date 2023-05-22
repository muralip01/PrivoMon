package PrivoMon.ChildProfiles;

import javax.persistence.Embeddable;

@Embeddable
public class MonitoringSettings {

    private boolean trackTimeActive;
    private boolean trackWindowActivity;
    private boolean trackBrowsingActivity;

    public boolean isTrackTimeActive() {
        return trackTimeActive;
    }

    public void setTrackTimeActive(boolean trackTimeActive) {
        this.trackTimeActive = trackTimeActive;
    }

    public boolean isTrackWindowActivity() {
        return trackWindowActivity;
    }

    public void setTrackWindowActivity(boolean trackWindowActivity) {
        this.trackWindowActivity = trackWindowActivity;
    }

    public boolean isTrackBrowsingActivity() {
        return trackBrowsingActivity;
    }

    public void setTrackBrowsingActivity(boolean trackBrowsingActivity) {
        this.trackBrowsingActivity = trackBrowsingActivity;
    }
}