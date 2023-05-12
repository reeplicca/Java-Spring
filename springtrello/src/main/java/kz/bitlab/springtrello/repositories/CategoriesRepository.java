package kz.bitlab.springtrello.repositories;

import kz.bitlab.springtrello.entities.TaskCategories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface CategoriesRepository extends JpaRepository<TaskCategories,Long> {

    TaskCategories findAllById(Long id);
    void deleteAllById(Long id);
}
