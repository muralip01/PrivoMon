package PrivoMon.ChildProfiles;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TimeMonitoringSessionService {

    private final TimeMonitoringSessionRepository timeMonitoringSessionRepository;

    public TimeMonitoringSessionService(TimeMonitoringSessionRepository timeMonitoringSessionRepository) {
        this.timeMonitoringSessionRepository = timeMonitoringSessionRepository;
    }

    public TimeMonitoringSession startTimeMonitoringSession(ChildProfile childProfile) {
        LocalDateTime startTime = LocalDateTime.now();
        TimeMonitoringSession session = new TimeMonitoringSession(startTime, null, childProfile);
        return timeMonitoringSessionRepository.save(session);
    }

    public TimeMonitoringSession endTimeMonitoringSession(Long sessionId) {
        Optional<TimeMonitoringSession> optionalSession = timeMonitoringSessionRepository.findById(sessionId);
        if (optionalSession.isPresent()) {
            TimeMonitoringSession session = optionalSession.get();
            session.setEndTime(LocalDateTime.now());
            return timeMonitoringSessionRepository.save(session);
        }
        throw new IllegalArgumentException("Time monitoring session not found");
    }

    public List<TimeMonitoringSession> getTimeMonitoringSessionsByChildProfile(ChildProfile childProfile) {
        return timeMonitoringSessionRepository.findAllByChildProfile(childProfile);
    }
}