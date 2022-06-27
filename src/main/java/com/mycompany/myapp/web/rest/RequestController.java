package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.request.PendingRequest;
import com.mycompany.myapp.domain.request.SubmittedRequest;
import com.mycompany.myapp.service.RequestService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="v2/request")
public class RequestController {
    private final RequestService requestService;

    public RequestController(RequestService requestService) {
        this.requestService = requestService;
    }

    /*Post methods*/
    @PostMapping("/fetch")
    public void receiveRequest(@RequestParam(name="customerId") Long customerId) {
        requestService.receiveFrom(customerId);
    }

    @PostMapping("/submit")
    public void submitRequest(@RequestParam(name="customerId") Long customerId) {
        requestService.submitFrom(customerId);
    }

    @PostMapping("/release")
    public void releaseRequest(@RequestParam(name="customerId") Long customerId) {
        requestService.removeRequestInPendingFrom(customerId);
    }

    @GetMapping("/pending/all")
    public List<PendingRequest> getAllPendingRequests() {
        return requestService.findAllPendingRequests();
    }

    @GetMapping("/submitted/all")
    public List<SubmittedRequest> getAllSubmittedRequests() {
        return requestService.findAllSubmittedRequests();
    }
}
