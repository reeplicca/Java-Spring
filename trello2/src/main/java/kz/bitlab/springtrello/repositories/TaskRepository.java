package kz.bitlab.springtrello.repositories;

import jakarta.transaction.Transactional;
import kz.bitlab.springtrello.entities.Folder;
import kz.bitlab.springtrello.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public interface TaskRepository extends JpaRepository<Task,Long> {
    Task findAllById(Long id);
    List<Task> findAllByFolder(Folder folder);

    void deleteAllByFolder(Folder folder);
}
