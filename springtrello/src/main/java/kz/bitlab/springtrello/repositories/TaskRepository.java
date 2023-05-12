package kz.bitlab.springtrello.repositories;

import kz.bitlab.springtrello.entities.Folder;
import kz.bitlab.springtrello.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface TaskRepository extends JpaRepository<Task,Long> {
    Task findAllById(Long id);
    List<Task> findAllByFolder(Folder folder);

    void deleteAllByFolder(Folder folder);
}
