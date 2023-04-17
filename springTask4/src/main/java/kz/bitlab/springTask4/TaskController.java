package kz.bitlab.springTask4;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
@RequestMapping(value = "/task")
public class TaskController {
    @GetMapping(value = "/home")
    public String openHome(Model model) {
        ArrayList<Task> tasks = DBManager.getAllTask();
        model.addAttribute("tasks", tasks);
        return "home";
    }

    @PostMapping(value = "/add-task")
    public String addTaskPost(@RequestParam(name = "task-name") String name,
                              @RequestParam(name = "task-description") String description,
                              @RequestParam(name = "task-deadlinedate") String deadlinedate) {
        String redirect = "/task/add-task?error";
        Task task = new Task();
        task.setName(name);
        task.setDescription(description);
        task.setDeadlineDate(deadlinedate);
        task.setCompleted(false);

        if (DBManager.addTask(task)) {
            redirect = "/task/home";
        }

        return "redirect:" + redirect;
    }

    @GetMapping(value = "details")
    public String openDetails(@RequestParam(name = "id") Long id, Model model) {
        Task task = DBManager.getTask(id);
        model.addAttribute("task",task);
        return "home";
    }

    @PostMapping(value = "/update")
    public String updatePost(@RequestParam(name = "task-name")String name,
                             @RequestParam(name = "task-description")String description,
                             @RequestParam(name = "task-deadlinedate")String deadlinedate,
                             @RequestParam(name = "task-iscompleted")boolean iscompleted,
                             @RequestParam(name = "task-id") Long id) {
        String redirect = "/task/update?error";
        Task updTask = new Task();
        updTask.setId(id);
        updTask.setName(name);
        updTask.setDescription(description);
        updTask.setDeadlineDate(deadlinedate);

        if (iscompleted) {
            updTask.setCompleted(true);
        } else {
            updTask.setCompleted(false);
        }

        if (DBManager.updateTask(updTask) == true) {
            redirect = "/task/home";
        }

        return "redirect:" + redirect;
    }

    @PostMapping(value = "/delete")
    public String deletePost(@RequestParam(name = "task-id") Long id) {
        String redirect = "/task/delete?error";

        if (DBManager.deleteTask(id)) {
            redirect = "/task/home";
        }

        return "redirect:" + redirect;
    }
}
