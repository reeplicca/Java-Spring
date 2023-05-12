package kz.bitlab.springtrello.repositories;

import jakarta.transaction.Transactional;
import kz.bitlab.springtrello.entities.Folder;
import kz.bitlab.springtrello.entities.TaskCategories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public interface FolderRepository extends JpaRepository<Folder,Long> {
    Folder findAllById(Long id);
    List<Folder> findAllByCategories(TaskCategories taskCategories);
}
