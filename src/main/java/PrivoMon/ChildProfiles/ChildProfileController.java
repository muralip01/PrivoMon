package PrivoMon.ChildProfiles;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/child")
public class ChildProfileController {

    private final ChildProfileService childProfileService;
    private final TimeMonitoringSessionService timeMonitoringSessionService;
    private final WindowActivityService windowActivityService;

    public ChildProfileController(ChildProfileService childProfileService, WindowActivityService windowActivityService, TimeMonitoringSessionService timeMonitoringSessionService) {
        this.childProfileService = childProfileService;
        this.timeMonitoringSessionService = timeMonitoringSessionService;
        this.windowActivityService = windowActivityService;
    }

    @PostMapping
    public ResponseEntity<ChildProfile> addChildProfile(@RequestBody ChildProfile childProfile) {
        ChildProfile addedChildProfile = childProfileService.addChildProfile(childProfile);
        return ResponseEntity.status(HttpStatus.CREATED).body(addedChildProfile);
    }

    @PutMapping("/{childId}")
    public ResponseEntity<ChildProfile> updateChildProfile(
            @PathVariable Long childId,
            @RequestBody ChildProfile updatedChildProfile) {
        ChildProfile updatedProfile = childProfileService.updateChildProfile(childId, updatedChildProfile);
        return ResponseEntity.ok(updatedProfile);
    }

    @DeleteMapping("/{childId}")
    public ResponseEntity<Void> deleteChildProfile(@PathVariable Long childId) {
        childProfileService.deleteChildProfile(childId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<ChildProfile>> getAllChildProfiles() {
        List<ChildProfile> childProfiles = childProfileService.getAllChildProfiles();
        return ResponseEntity.ok(childProfiles);
    }

    @GetMapping("/{childId}")
    public ResponseEntity<ChildProfile> getChildProfileById(@PathVariable Long childId) {
        Optional<ChildProfile> childProfile = childProfileService.getChildProfileById(childId);
        return childProfile.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/{childId}/monitoringSettings")
    public ResponseEntity<ChildProfile> setMonitoringSettings(@PathVariable Long childId, @RequestBody MonitoringSettings monitoringSettings) {
        ChildProfile updatedChildProfile = childProfileService.setMonitoringSettings(childId, monitoringSettings);
        return ResponseEntity.ok(updatedChildProfile);
    }
    @PostMapping("/{childId}/startTimeMonitoring")
    public ResponseEntity<TimeMonitoringSession> startTimeMonitoringSession(@PathVariable Long childId) {
        Optional<ChildProfile> optionalChildProfile = childProfileService.getChildProfileById(childId);
        if (optionalChildProfile.isPresent()) {
            ChildProfile childProfile = optionalChildProfile.get();
            TimeMonitoringSession session = timeMonitoringSessionService.startTimeMonitoringSession(childProfile);
            return ResponseEntity.status(HttpStatus.CREATED).body(session);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/{childId}/endTimeMonitoring/{sessionId}")
    public ResponseEntity<TimeMonitoringSession> endTimeMonitoringSession(
            @PathVariable Long childId,
            @PathVariable Long sessionId) {
        Optional<ChildProfile> optionalChildProfile = childProfileService.getChildProfileById(childId);
        if (optionalChildProfile.isPresent()) {
            TimeMonitoringSession session = timeMonitoringSessionService.endTimeMonitoringSession(sessionId);
            return ResponseEntity.ok(session);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{childId}/timeMonitoringSessions")
    public ResponseEntity<List<TimeMonitoringSession>> getTimeMonitoringSessionsByChildProfile(@PathVariable Long childId) {
        Optional<ChildProfile> optionalChildProfile = childProfileService.getChildProfileById(childId);
        if (optionalChildProfile.isPresent()) {
            ChildProfile childProfile = optionalChildProfile.get();
            List<TimeMonitoringSession> sessions = timeMonitoringSessionService.getTimeMonitoringSessionsByChildProfile(childProfile);
            return ResponseEntity.ok(sessions);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/{childId}/windowActivity")
    public ResponseEntity<WindowActivity> saveWindowActivity(
            @PathVariable Long childId,
            @RequestBody WindowActivity windowActivity) {
        Optional<ChildProfile> optionalChildProfile = childProfileService.getChildProfileById(childId);
        if (optionalChildProfile.isPresent()) {
            ChildProfile childProfile = optionalChildProfile.get();
            windowActivity.setChildProfile(childProfile);
            WindowActivity savedWindowActivity = windowActivityService.saveWindowActivity(windowActivity);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedWindowActivity);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{childId}/window-activity")
    public ResponseEntity<List<WindowActivity>> getWindowActivitiesByChildProfile(@PathVariable Long childId) {
        Optional<ChildProfile> optionalChildProfile = childProfileService.getChildProfileById(childId);
        if (optionalChildProfile.isPresent()) {
            ChildProfile childProfile = optionalChildProfile.get();
            List<WindowActivity> windowActivities = windowActivityService.getWindowActivitiesByChildProfile(childProfile);
            return ResponseEntity.ok(windowActivities);
        }
        return ResponseEntity.notFound().build();
    }
}