package kz.bitlab.springtrello.services;

import kz.bitlab.springtrello.entities.TaskCategories;

import java.util.List;

public interface CategoriesService {
    TaskCategories addCategories(TaskCategories taskCategories);
    List<TaskCategories> getAllTaskCategories();
    TaskCategories getTaskCategories(Long id);
    TaskCategories updateTaskCategories(TaskCategories taskCategories);
    void deleteTaskCategories(Long id);
}
