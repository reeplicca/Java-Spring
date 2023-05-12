package kz.bitlab.springtrello.services;

import kz.bitlab.springtrello.entities.Folder;
import kz.bitlab.springtrello.entities.TaskCategories;

import java.util.List;

public interface FolderService {
    Folder addFolder(Folder folder);
    List<Folder> getAllFolder();
    Folder getFolder(Long id);
    Folder updateFolder(Folder folder);
    void deleteFolder(Long id);
    List<Folder> getFolderByCategories(TaskCategories taskCategories);
    List<Folder> addAllFolder(List<Folder> folders);
}
