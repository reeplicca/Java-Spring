package kz.bitlab.springcrm.services.impl;

import kz.bitlab.springcrm.entities.Request;
import kz.bitlab.springcrm.repositories.RequestRepository;
import kz.bitlab.springcrm.services.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RequestServiceImpl implements RequestService {
    @Autowired
    private RequestRepository requestRepository;

    @Override
    public Request addRequest(Request request) {
        return requestRepository.save(request);
    }

    @Override
    public List<Request> getAllRequest() {
        return requestRepository.findAll();
    }

    @Override
    public Request getRequest(Long id) {
        return requestRepository.findAllById(id);
    }

    @Override
    public Request updateRequest(Request request) {
        return requestRepository.save(request);
    }

    @Override
    public void deleteRequest(Long id) {
        requestRepository.deleteById(id);
    }
}
