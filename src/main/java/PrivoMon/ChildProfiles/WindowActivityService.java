package PrivoMon.ChildProfiles;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WindowActivityService {

    private final WindowActivityRepository windowActivityRepository;

    public WindowActivityService(WindowActivityRepository windowActivityRepository) {
        this.windowActivityRepository = windowActivityRepository;
    }

    public WindowActivity saveWindowActivity(WindowActivity windowActivity) {
        return windowActivityRepository.save(windowActivity);
    }

    public List<WindowActivity> getWindowActivitiesByChildProfile(ChildProfile childProfile) {
        return windowActivityRepository.findByChildProfile(childProfile);
    }

    // Other methods as per your requirements
}