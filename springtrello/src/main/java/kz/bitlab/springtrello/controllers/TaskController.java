package kz.bitlab.springtrello.controllers;

import kz.bitlab.springtrello.entities.Folder;
import kz.bitlab.springtrello.entities.Task;
import kz.bitlab.springtrello.entities.TaskCategories;
import kz.bitlab.springtrello.services.CategoriesService;
import kz.bitlab.springtrello.services.FolderService;
import kz.bitlab.springtrello.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/trello")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private FolderService folderService;

    @Autowired
    private CategoriesService categoriesService;

    @GetMapping(value = "/add-task/{folder_id}")
    public String openAddTask(@PathVariable(name = "folder_id") Long folderId, Model model) {
        Folder folder = folderService.getFolder(folderId);
        model.addAttribute("folder",folder);
        return "add-task";
    }

    @PostMapping(value = "/add-task")
    public String addTask(@RequestParam(name = "task-title") String title,
                          @RequestParam(name = "task-description") String description,
                          @RequestParam(name = "folder-id") Long folderId) {

        String redirect = "/trello/add-task?error";

        Folder folder = folderService.getFolder(folderId);

        Task task = new Task();
        task.setTitle(title);
        task.setDescription(description);
        task.setFolder(folder);
        task.setStatus(0);

        if (taskService.addTask(task) != null) {
            redirect = "/trello/task/";
        }

        return "redirect:" + redirect + folderId;

    }

    @GetMapping(value = "/task/{folder_id}")
    public String openHome(Model model, @PathVariable(name = "folder_id") Long folderId) {
        Folder folder = folderService.getFolder(folderId);
        model.addAttribute("folder",folder);

        List<Task> tasks = taskService.getAllTask(folder);
        model.addAttribute("tasks", tasks);

        List<TaskCategories> taskCategories = folder.getCategories();
        model.addAttribute("taskCategories",taskCategories);

        return "task";
    }

    @GetMapping(value = "/details/{id}")
    public String openDetails(@PathVariable(name = "id") Long id, Model model) {
        Task task = taskService.getTask(id);
        model.addAttribute("task",task);

        model.addAttribute("fold",task.getFolder());
        return "details";
    }

    @PostMapping(value = "/update")
    public String updateTask(@RequestParam(name = "fold-id") Long foldId,
                             @RequestParam(name = "task-id") Long id,
                             @RequestParam(name = "task-title") String title,
                             @RequestParam(name = "task-description") String description,
                             @RequestParam(name = "task-status") int status) {

        String redirect = "/trello/update?error";

        Task task = taskService.getTask(id);
        task.setTitle(title);
        task.setDescription(description);
        task.setStatus(status);

        if (taskService.updateTask(task) != null) {
            redirect = "/trello/task/";
        }

        return "redirect:" + redirect + foldId;

    }

    @PostMapping(value = "/delete")
    public String deleteTask(@RequestParam(name = "fold-id") Long foldId,
                             @RequestParam(name = "task-id") Long id) {

        taskService.deleteTask(id);

        return "redirect:/trello/task/" + foldId;
    }

    @GetMapping(value = "/folder")
    public String openFolder(Model model) {

        List<Folder> folders = folderService.getAllFolder();
        model.addAttribute("folders",folders);

        return "folder";
    }

    @GetMapping(value = "/add-folder")
    public String openAddFolder() {
        return "add-folder";
    }

    @PostMapping(value = "/add-folder")
    public String addFolder(@RequestParam(name = "folder-name") String name) {
        String redirect = "/trello/add-folder?error";

        Folder folder = new Folder();
        folder.setName(name);

        if (folderService.addFolder(folder) != null) {
            redirect = "/trello/folder";
        }

        return "redirect:" + redirect;
    }

    @GetMapping(value = "/add-categories")
    public String openAddCategories(Model model) {
        List<TaskCategories> taskCategories = categoriesService.getAllTaskCategories();
        model.addAttribute("taskcategories",taskCategories);

        return "add-categories";
    }

    @PostMapping(value = "/add-categories")
    public String AddCategories(@RequestParam(name = "categories-name") String name) {
        String redirect = "/trello/add-categories?error";

        TaskCategories categories = new TaskCategories();
        categories.setName(name);

        if (categoriesService.addCategories(categories) != null) {
            redirect = "/trello/folder";
        }

        return "redirect:" + redirect;
    }

    @GetMapping(value = "/new-categories/{id}")
    public String openNewCategories(Model model,@PathVariable(name = "id") Long folderId) {

        Folder folder = folderService.getFolder(folderId);
        model.addAttribute("folder",folder);

        List<TaskCategories> taskCategories = categoriesService.getAllTaskCategories();
        taskCategories.removeAll(folder.getCategories());
        model.addAttribute("taskCategories",taskCategories);

        return "new-categories";
    }

    @PostMapping(value = "/assign-categories")
    public String assignCategories(@RequestParam(name = "categories-id") Long categoriesId,
                                   @RequestParam(name = "folder-id") Long folderId) {

         TaskCategories taskCategories = categoriesService.getTaskCategories(categoriesId);

        Folder folder = folderService.getFolder(folderId);
        List<TaskCategories> taskCategoriesList = folder.getCategories();
        taskCategoriesList.add(taskCategories);
        folder.setCategories(taskCategoriesList);
        folderService.addFolder(folder);

        return "redirect:/trello/new-categories/" + folderId;
    }

    @PostMapping(value = "/unassign-categories")
    public String unAssignCategories(@RequestParam(name = "categories-id") Long categoriesId,
                                   @RequestParam(name = "folder-id") Long folderId) {

        TaskCategories taskCategories = categoriesService.getTaskCategories(categoriesId);

        Folder folder = folderService.getFolder(folderId);
        List<TaskCategories> taskCategoriesList = folder.getCategories();
        taskCategoriesList.remove(taskCategories);
        folder.setCategories(taskCategoriesList);

        folderService.addFolder(folder);

        return "redirect:/trello/new-categories/" + folderId;
    }

    @PostMapping(value = "/delete-folder")
    public String deleteFolder(@RequestParam(name = "folder-id") Long folderId) {
        Folder folder = folderService.getFolder(folderId);

        taskService.deleteAllTask(folder);

        folderService.deleteFolder(folderId);

        return "redirect:/trello/folder";
    }

    @GetMapping(value = "/category-details/{id}")
    public String openCategoryDetails(@PathVariable(name = "id") Long id, Model model) {
        TaskCategories taskCategories = categoriesService.getTaskCategories(id);
        model.addAttribute("categories",taskCategories);

        return "category-details";
    }

    @PostMapping(value = "/update-category")
    public String updateCategory(@RequestParam(name = "categories-name") String name,
                                 @RequestParam(name = "categories-id") Long id) {

        String redirect = "/trello/updateCategory?error";

        TaskCategories taskCategories = categoriesService.getTaskCategories(id);
        taskCategories.setName(name);

        if (categoriesService.updateTaskCategories(taskCategories) != null) {
            redirect = "/trello/add-categories";
        }

        return "redirect:" + redirect;

    }

    @PostMapping(value = "/delete-category")
    public String deleteCategory(@RequestParam(name = "categories-id") Long id) {

        TaskCategories taskCategories = categoriesService.getTaskCategories(id);

        List<Folder> folders = folderService.getFolderByCategories(taskCategories);
        for (int i = 0; i < folders.size(); i++) {
            List<TaskCategories> taskCategoriesList = folders.get(i).getCategories();
            taskCategoriesList.remove(taskCategories);
            folders.get(i).setCategories(taskCategoriesList);

            folderService.addAllFolder(folders);
        }

        categoriesService.deleteTaskCategories(id);

        return "redirect:/trello/add-categories";
    }

}
