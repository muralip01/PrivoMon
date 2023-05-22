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

    public ChildProfileController(ChildProfileService childProfileService) {
        this.childProfileService = childProfileService;
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
}