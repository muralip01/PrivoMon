package PrivoMon.ChildProfiles;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChildProfileService {

    private final ChildProfileRepository childProfileRepository;

    public ChildProfileService(ChildProfileRepository childProfileRepository) {
        this.childProfileRepository = childProfileRepository;
    }

    public ChildProfile addChildProfile(ChildProfile childProfile) {
        // Save the child profile in the database
        return childProfileRepository.save(childProfile);
    }

    public ChildProfile updateChildProfile(Long childId, ChildProfile updatedChildProfile) {
        ChildProfile existingChildProfile = childProfileRepository.findById(childId)
                .orElseThrow(() -> new IllegalArgumentException("Child profile not found"));

        // Update the existing child profile with the new information
        existingChildProfile.setName(updatedChildProfile.getName());
        existingChildProfile.setAge(updatedChildProfile.getAge());

        // Save the updated child profile in the database
        return childProfileRepository.save(existingChildProfile);
    }

    public void deleteChildProfile(Long childId) {
        childProfileRepository.deleteById(childId);
    }

    public List<ChildProfile> getAllChildProfiles() {
        return childProfileRepository.findAll();
    }

    public Optional<ChildProfile> getChildProfileById(Long childId) {
        return childProfileRepository.findById(childId);
    }

    public ChildProfile setMonitoringSettings(Long childId, MonitoringSettings monitoringSettings) {
        ChildProfile childProfile = childProfileRepository.findById(childId)
                .orElseThrow(() -> new IllegalArgumentException("Child profile not found"));

        // Set the monitoring settings for the child profile
        childProfile.setMonitoringSettings(monitoringSettings);

        // Save the updated child profile in the database
        return childProfileRepository.save(childProfile);
    }
}