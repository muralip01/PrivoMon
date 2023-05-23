package PrivoMon.ChildProfiles;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WindowActivityRepository extends JpaRepository<WindowActivity, Long> {
    List<WindowActivity> findByChildProfile(ChildProfile childProfile);
}