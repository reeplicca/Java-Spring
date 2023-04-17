package kz.bitlab.springsprinttasktwo.services;

import kz.bitlab.springsprinttasktwo.entities.ApplicationRequest;

import java.util.List;

public interface ApplicationRequestService {

    ApplicationRequest addRequest(ApplicationRequest applicationRequest);
    List<ApplicationRequest> getALLRequests();
    ApplicationRequest getRequest(Long id);
    ApplicationRequest updateRequest(ApplicationRequest applicationRequest);
    void deleteRequest(Long id);
    List<ApplicationRequest> getAllByHandled(boolean bool);
    List<ApplicationRequest> getAllByUserName(String name);
}
