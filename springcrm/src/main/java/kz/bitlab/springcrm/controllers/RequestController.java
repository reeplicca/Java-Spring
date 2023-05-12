package kz.bitlab.springcrm.controllers;

import kz.bitlab.springcrm.entities.Course;
import kz.bitlab.springcrm.entities.Request;
import kz.bitlab.springcrm.services.CourseService;
import kz.bitlab.springcrm.services.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping(value = "/request")
public class RequestController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private RequestService requestService;

    @GetMapping(value = "/add-request")
    public String openAddRequest(Model model) {
        List<Course> courses = courseService.getAllCourse();
        model.addAttribute("courses",courses);
        return "add-request";
    }

    @PostMapping(value = "/add-request")
    public String addRequest(@RequestParam(name = "request-name") String name,
                             @RequestParam(name = "request-course-id") Long courseId,
                             @RequestParam(name = "request-phone") String phone,
                             @RequestParam(name = "request-commentary") String commentary) {
        String redirect = "/request/add-request?error";

        Request request = Request.builder()
                .fullName(name)
                .phone(phone)
                .commentary(commentary)
                .course(Course.builder()
                        .id(courseId)
                        .build())
                .build();

        if (requestService.addRequest(request) != null) {
            redirect = "/request/add-request?success";
        }

        return "redirect:" + redirect;
    }

    @GetMapping(value = "/add-course")
    public String openAddCourse() {
        return "add-course";
    }

    @PostMapping(value = "/add-course")
    public String addCourse(@RequestParam(name = "course-name") String name,
                            @RequestParam(name = "course-description") String description,
                            @RequestParam(name = "course-price") int price) {

        String redirect = "/request/add-course?error";

        Course course = Course.builder()
                .name(name)
                .description(description)
                .price(price)
                .build();

        if (courseService.addCourse(course) != null) {
            redirect = "/request/add-course?success";
        }

        return "redirect:" + redirect;

    }
}
