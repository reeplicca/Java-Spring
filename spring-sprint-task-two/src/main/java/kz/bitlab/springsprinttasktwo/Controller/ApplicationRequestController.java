package kz.bitlab.springsprinttasktwo.Controller;

import kz.bitlab.springsprinttasktwo.entities.ApplicationRequest;
import kz.bitlab.springsprinttasktwo.Repository.ApplicationRequestRepository;
import kz.bitlab.springsprinttasktwo.services.ApplicationRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/request")
public class ApplicationRequestController {
    @Autowired
    ApplicationRequestService applicationRequestService;

    @GetMapping(value = "/home")
    public String openHome(Model model) {
        List<ApplicationRequest> applicationRequests = applicationRequestService.getALLRequests();
        model.addAttribute("requests", applicationRequests);
        return "home";
    }

    @GetMapping(value = "/add-request")
    public String openAddRequest() {
        return "add-request";
    }

    @PostMapping(value = "/add-request")
    public String addRequestPost(@RequestParam(name = "request-username") String username,
                                 @RequestParam(name = "request-coursename") String coursename,
                                 @RequestParam(name = "request-phone") String phone,
                                 @RequestParam(name = "request-commentary") String commentary){
        String redirect = "/request/add-request?error";

        ApplicationRequest applicationRequest = new ApplicationRequest();
        applicationRequest.setUserName(username);
        applicationRequest.setCourseName(coursename);
        applicationRequest.setPhone(phone);
        applicationRequest.setCommentary(commentary);
        applicationRequest.setHandled(false);

        if (applicationRequestService.addRequest(applicationRequest) != null) {
            redirect = "/request/home";
        }

        return "redirect:" + redirect;
    }

    @GetMapping(value = "/details/{id}")
    public String openDetails(@PathVariable(name = "id") Long id, Model model) {
        ApplicationRequest applicationRequest = applicationRequestService.getRequest(id);
        model.addAttribute("request", applicationRequest);
        return "details";
    }

    @PostMapping(value = "/update")
    public String updatePost(@RequestParam(name = "request-id") Long id,
                             @RequestParam(name = "request-username") String username,
                             @RequestParam(name = "request-coursename") String coursename,
                             @RequestParam(name = "request-phone") String phone,
                             @RequestParam(name = "request-commentary") String commentary) {
        String redirect = "/request/update?error";

        ApplicationRequest applicationRequest = applicationRequestService.getRequest(id);
        applicationRequest.setUserName(username);
        applicationRequest.setCourseName(coursename);
        applicationRequest.setPhone(phone);
        applicationRequest.setCommentary(commentary);
        applicationRequest.setHandled(true);

        if (applicationRequestService.updateRequest(applicationRequest) != null) {
            redirect = "/request/home";
        }

        return "redirect:" + redirect;
    }

    @PostMapping(value = "/delete")
    public String deletePost(@RequestParam(name = "request-id") Long id) {
        applicationRequestService.deleteRequest(id);

        return "redirect:home";
    }

    @GetMapping(value = "/new-request")
    public String openNewRequest(Model model) {
        List<ApplicationRequest> applicationRequests = applicationRequestService.getAllByHandled(false);
        model.addAttribute("requests",applicationRequests);
        return "new-request";
    }

    @GetMapping(value = "/processed-request")
    public String newProcessedRequest(Model model) {
        List<ApplicationRequest> applicationRequests = applicationRequestService.getAllByHandled(true);
        model.addAttribute("requests",applicationRequests);
        return "processed-request";
    }

    @GetMapping(value = "/search")
    public String openTasks(@RequestParam(name = "search-str") String name, Model model) {
        List<ApplicationRequest> applicationRequests = applicationRequestService.getAllByUserName(name);
        model.addAttribute("requests", applicationRequests);
        return "home";
    }
}
