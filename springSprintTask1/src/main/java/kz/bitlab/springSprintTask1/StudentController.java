package kz.bitlab.springSprintTask1;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
@RequestMapping(value = "/student")
public class StudentController {
    @GetMapping(value = "/add-student")
    public String openAddStudent() {
        return "add-student";
    }

    @PostMapping(value = "/add-student")
    public String addStudentPost(@RequestParam(name = "student-name")String name,
                                 @RequestParam(name = "student-surname")String surname,
                                 @RequestParam(name = "student-exam")int exam) {
        String redirect = "/student/add-student?error";
        Student student = new Student();
        student.setName(name);
        student.setSurname(surname);
        student.setExam(exam);

        if (exam >= 90) {
            student.setMark("A");
        } else if (exam >= 75) {
            student.setMark("B");
        } else if (exam >= 60) {
            student.setMark("C");
        } else if (exam >= 50) {
            student.setMark("D");
        } else {
            student.setMark("F");
        }

        if (DBManager.addStudent(student) == true) {
            redirect = "/student/home";
        }

        return "redirect:" + redirect;
    }

    @GetMapping(value = "/home")
    public String openHome(Model model) {
        ArrayList<Student> students = DBManager.getAllStudents();
        model.addAttribute("students",students);
        return "home";
    }

    @GetMapping(value = "/details")
    public String openDetails(@RequestParam(name = "id")Long id, Model model) {
        Student student = DBManager.getStudent(id);
        model.addAttribute("student",student);
        return "details";
    }

    @PostMapping(value = "/update")
    public String updatePost(@RequestParam(name = "student-name") String name,
                             @RequestParam(name = "student-surname") String surname,
                             @RequestParam(name = "student-exam") int exam,
                             @RequestParam(name = "student-id") Long id) {
        String redirect = "/student/update?error";
        Student updStudent = new Student();
        updStudent.setId(id);
        updStudent.setName(name);
        updStudent.setSurname(surname);
        updStudent.setExam(exam);

        if (exam >= 90) {
            updStudent.setMark("A");
        } else if (exam >= 75) {
            updStudent.setMark("B");
        } else if (exam >= 60) {
            updStudent.setMark("C");
        } else if (exam >= 50) {
            updStudent.setMark("D");
        } else {
            updStudent.setMark("F");
        }

        if (DBManager.updateStudent(updStudent)) {
            redirect = "/student/home";
        }

        return "redirect:" + redirect;
    }

    @PostMapping(value = "/delete")
    public String deletePost(@RequestParam(name = "student-id") Long id) {
        String redirect = "/student/delete?error";

        if (DBManager.deleteStudent(id)) {
            redirect = "/student/home";
        }

        return "redirect:" + redirect;
    }
}
