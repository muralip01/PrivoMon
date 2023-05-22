package PrivoMon.ChildProfiles;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChildProfileRepository extends JpaRepository<ChildProfile, Long> {

}