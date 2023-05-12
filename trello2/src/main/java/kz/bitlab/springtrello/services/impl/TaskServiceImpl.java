package kz.bitlab.springtrello.services.impl;

import kz.bitlab.springtrello.entities.Folder;
import kz.bitlab.springtrello.entities.Task;
import kz.bitlab.springtrello.repositories.TaskRepository;
import kz.bitlab.springtrello.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    private TaskRepository taskRepository;

    @Override
    public Task addTask(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public List<Task> getAllTask(Folder folder) {
        return taskRepository.findAllByFolder(folder);
    }

    @Override
    public Task getTask(Long id) {
        return taskRepository.findAllById(id);
    }

    @Override
    public Task updateTask(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    @Override
    public void deleteAllTask(Folder folder) {
        taskRepository.deleteAllByFolder(folder);
    }

}
