package kz.bitlab.springtrello.services;

import kz.bitlab.springtrello.entities.Folder;
import kz.bitlab.springtrello.entities.Task;
import kz.bitlab.springtrello.entities.TaskCategories;

import java.util.List;

public interface TaskService {
    Task addTask(Task task);
    List<Task> getAllTask(Folder folder);
    Task getTask(Long id);
    Task updateTask(Task task);
    void deleteTask(Long id);
    void deleteAllTask(Folder folder);

}
