package kz.bitlab.springcrm.repositories;

import jakarta.transaction.Transactional;
import kz.bitlab.springcrm.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface CourseRepository extends JpaRepository<Course,Long> {
    Course findAllById(Long id);
}
