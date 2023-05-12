package kz.bitlab.springtrello.services.impl;

import kz.bitlab.springtrello.entities.TaskCategories;
import kz.bitlab.springtrello.repositories.CategoriesRepository;
import kz.bitlab.springtrello.services.CategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriesServiceImpl implements CategoriesService {
    @Autowired
    private CategoriesRepository categoriesRepository;
    @Override
    public TaskCategories addCategories(TaskCategories taskCategories) {
        return categoriesRepository.save(taskCategories);
    }

    @Override
    public List<TaskCategories> getAllTaskCategories() {
        return categoriesRepository.findAll();
    }

    @Override
    public TaskCategories getTaskCategories(Long id) {
        return categoriesRepository.findAllById(id);
    }

    @Override
    public TaskCategories updateTaskCategories(TaskCategories taskCategories) {
        return categoriesRepository.save(taskCategories);
    }

    @Override
    public void deleteTaskCategories(Long id) {
        categoriesRepository.deleteAllById(id);
    }
}
