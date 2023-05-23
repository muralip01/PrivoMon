package PrivoMon.ChildProfiles;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TimeMonitoringSessionRepository extends JpaRepository<TimeMonitoringSession, Long> {
    List<TimeMonitoringSession> findAllByChildProfile(ChildProfile childProfile);
}