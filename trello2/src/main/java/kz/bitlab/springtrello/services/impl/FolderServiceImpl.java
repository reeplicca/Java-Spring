package kz.bitlab.springtrello.services.impl;

import kz.bitlab.springtrello.entities.Folder;
import kz.bitlab.springtrello.entities.TaskCategories;
import kz.bitlab.springtrello.repositories.FolderRepository;
import kz.bitlab.springtrello.services.FolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FolderServiceImpl implements FolderService {
    @Autowired
    private FolderRepository folderRepository;

    @Override
    public Folder addFolder(Folder folder) {
        return folderRepository.save(folder);
    }

    @Override
    public List<Folder> getAllFolder() {
        return folderRepository.findAll();
    }

    @Override
    public Folder getFolder(Long id) {
        return folderRepository.findAllById(id);
    }

    @Override
    public Folder updateFolder(Folder folder) {
        return folderRepository.save(folder);
    }

    @Override
    public void deleteFolder(Long id) {
        folderRepository.deleteById(id);
    }

    @Override
    public List<Folder> getFolderByCategories(TaskCategories taskCategories) {
        return folderRepository.findAllByCategories(taskCategories);
    }

    @Override
    public List<Folder> addAllFolder(List<Folder> folders) {
        return folderRepository.saveAll(folders);
    }

}
