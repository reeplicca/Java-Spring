package kz.bitlab.springtrello.repositories;

import jakarta.transaction.Transactional;
import kz.bitlab.springtrello.entities.TaskCategories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
@Transactional
public interface CategoriesRepository extends JpaRepository<TaskCategories,Long> {

    TaskCategories findAllById(Long id);
    void deleteAllById(Long id);
}
