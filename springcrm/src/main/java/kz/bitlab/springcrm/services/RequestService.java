package kz.bitlab.springcrm.services;

import kz.bitlab.springcrm.entities.Request;

import java.util.List;

public interface RequestService {
    Request addRequest(Request request);
    List<Request> getAllRequest();
    Request getRequest(Long id);
    Request updateRequest(Request request);
    void deleteRequest(Long id);
}
