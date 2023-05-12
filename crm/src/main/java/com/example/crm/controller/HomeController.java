package com.example.crm.controller;

import com.example.crm.model.ApplicationRequest;
import com.example.crm.model.Courses;
import com.example.crm.model.Operators;
import com.example.crm.repository.ApplicationRequestRepository;
import com.example.crm.repository.CoursesRepository;
import com.example.crm.repository.OperatorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private ApplicationRequestRepository applicationRequestRepository;

    @Autowired
    private CoursesRepository coursesRepository;

    @Autowired
    private OperatorsRepository operatorsRepository;

    @GetMapping(value = "/")
    private String indexPage(Model model) {
        model.addAttribute("allRequests", applicationRequestRepository.findAll());
        model.addAttribute("allCourses", coursesRepository.findAll());
        return "index";
    }

    @GetMapping(value = "/addRequest")
    private String addRequestDoGet(Model model) {
        model.addAttribute("allCourses", coursesRepository.findAll());
        return "addRequest";
    }

    @PostMapping(value = "/addNewRequest")
    public String addNewRequest(@RequestParam(name = "userName") String userName,
                             @RequestParam(name = "courseId") Long courseId,
                             @RequestParam(name = "comment") String commentary,
                             @RequestParam(name = "phone") String phone){

        Courses courses = coursesRepository.findById(courseId).orElse(null);
        if (courses!=null){
            ApplicationRequest applicationRequest = new ApplicationRequest();
            applicationRequest.setUserName(userName);
            applicationRequest.setCommentary(commentary);
            applicationRequest.setCourseName(courses);
            applicationRequest.setPhone(phone);
            applicationRequestRepository.save(applicationRequest);
        }
        return "redirect:/";
    }

    @GetMapping(value = "/handled/{handled}")
    private String handledPage(Model model,
                               @PathVariable(name = "handled") Boolean handled) {
        if (!handled) {
            model.addAttribute("allRequests", applicationRequestRepository.findAllByHandledIsFalse());
        } else {
            model.addAttribute("allRequests", applicationRequestRepository.findAllByHandledIsTrue());
        }
        model.addAttribute("allCourses", coursesRepository.findAll());
        return "handled";
    }

    @GetMapping(value = "/details/{id}")
    private String details(Model model,
                           @PathVariable(name = "id") Long id) {
        ApplicationRequest applicationRequest = applicationRequestRepository.findById(id).orElse(null);

        model.addAttribute("request", applicationRequestRepository.getReferenceById(id));
        model.addAttribute("allCourses", coursesRepository.findAll());
        model.addAttribute("operatorsByReq", applicationRequest.getOperators());
        model.addAttribute("operators", operatorsRepository.findAll());
        return "details";
    }

    @GetMapping(value = "/delete/{id}")
    private String delete(@PathVariable(name = "id") Long id) {
        applicationRequestRepository.deleteById(id);
        return "redirect:/";
    }

    @PostMapping(value = "/trueRequest/{id}")
    private String trueRequestDoPost(@PathVariable(name = "id") Long id,
                                     @RequestParam(name = "operatorId[]") Long[] operatorId) {
        if (applicationRequestRepository.existsById(id)) {
            ApplicationRequest request = applicationRequestRepository.getReferenceById(id);
            ;
            List<Operators> operators = request.getOperators();
            if (operators == null) {
                operators = new ArrayList<>();
            }

            for (Long aLong : operatorId) {
                Operators operator = operatorsRepository.findById(aLong).orElse(null);
                operators.add(operator);
            }
            request.setOperators(operators);
            request.setHandled(true);
            applicationRequestRepository.save(request);
            return "redirect:/details/" + id;
        }
        return null;
    }

    @PostMapping(value = "/unAssignOperators/{id}")
    private String unAssignOperators(@PathVariable(name = "id") Long requestId,
                                     @RequestParam(name = "operatorId") Long operatorId) {
        if (applicationRequestRepository.existsById(requestId)) {
            ApplicationRequest request = applicationRequestRepository.getReferenceById(requestId);
            List<Operators> operators = request.getOperators();
            if (operators == null) {
                operators = new ArrayList<>();
            }

            Operators operator = operatorsRepository.findById(operatorId).orElse(null);
            operators.remove(operator);
            request.setOperators(operators);
            request.setHandled(true);
            applicationRequestRepository.save(request);
        }
        return "redirect:/details/" + requestId;
    }
}
