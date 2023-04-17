package kz.bitlab.springsprinttasktwo.services.impl;

import kz.bitlab.springsprinttasktwo.Repository.ApplicationRequestRepository;
import kz.bitlab.springsprinttasktwo.entities.ApplicationRequest;
import kz.bitlab.springsprinttasktwo.services.ApplicationRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicationRequestImpl implements ApplicationRequestService {
    @Autowired
    private ApplicationRequestRepository applicationRequestRepository;
    @Override
    public ApplicationRequest addRequest(ApplicationRequest applicationRequest) {
        return applicationRequestRepository.save(applicationRequest);
    }

    @Override
    public List<ApplicationRequest> getALLRequests() {
        return applicationRequestRepository.findAll();
    }

    @Override
    public ApplicationRequest getRequest(Long id) {
        return applicationRequestRepository.findAllById(id);
    }

    @Override
    public ApplicationRequest updateRequest(ApplicationRequest applicationRequest) {
        return applicationRequestRepository.save(applicationRequest);
    }

    @Override
    public void deleteRequest(Long id) {
        applicationRequestRepository.deleteById(id);
    }

    @Override
    public List<ApplicationRequest> getAllByHandled(boolean bool) {
        return applicationRequestRepository.findAllByHandled(bool);
    }

    @Override
    public List<ApplicationRequest> getAllByUserName(String name) {
        return applicationRequestRepository.findAllByUserNameContains(name);
    }
}
