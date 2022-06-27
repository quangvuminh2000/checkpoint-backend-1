package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.Customer;
import com.mycompany.myapp.domain.request.PendingRequest;
import com.mycompany.myapp.domain.request.SubmittedRequest;
import com.mycompany.myapp.repository.CustomerRepository;
import com.mycompany.myapp.repository.request.PendingRequestRepository;
import com.mycompany.myapp.repository.request.SubmittedRequestRepository;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RequestService {
    private final CustomerRepository customerRepository;
    private final PendingRequestRepository pendingRequestRepository;
    private final SubmittedRequestRepository submittedRequestRepository;

    public RequestService(CustomerRepository customerRepository, PendingRequestRepository pendingRequestRepository, SubmittedRequestRepository submittedRequestRepository) {
        this.customerRepository = customerRepository;
        this.pendingRequestRepository = pendingRequestRepository;
        this.submittedRequestRepository = submittedRequestRepository;
    }

    public void receiveFrom(Long customerId) {
        // Check if the customer is already exist or not
        Optional<Customer> customerOptional = customerRepository.findById(customerId);

        if (!customerOptional.isPresent()) {
            throw new InvalidDataAccessResourceUsageException(String.format("Cannot find customer with id=%d", customerId));
        }

        Optional<PendingRequest> pendingRequestOptional = pendingRequestRepository.findByCustomerId(customerId);

        if (pendingRequestOptional.isPresent()) {
            throw new IllegalStateException(String.format("Customer with id=%d has already sent the request", customerId));
        }

        pendingRequestRepository.save(new PendingRequest(customerId));
    }

    public void removeRequestInPendingFrom(Long customerId) {
        Optional<PendingRequest> pendingRequestOptional = pendingRequestRepository.findByCustomerId(customerId);

        if (!pendingRequestOptional.isPresent()) {
            throw new InvalidDataAccessResourceUsageException(String.format("Request from customer with id=%d is no longer in the pending request", customerId));
        }
    }

    public void submitFrom(Long customerId) {
        // Check if the customer's id is already in the pending request
        Optional<PendingRequest> pendingRequestOptional = pendingRequestRepository.findByCustomerId(customerId);

        if (!pendingRequestOptional.isPresent()) {
            throw new InvalidDataAccessApiUsageException(String.format("Request for customer with id=%d is not created yet to be submitted", customerId));
        }

        // Delete pending request then create new submitted request
        pendingRequestRepository.deleteByCustomerId(customerId);
        submittedRequestRepository.save(new SubmittedRequest(customerId));
    }

    public List<PendingRequest> findAllPendingRequests() {
        return pendingRequestRepository.findAll();
    }

    public List<SubmittedRequest> findAllSubmittedRequests() {
        return submittedRequestRepository.findAll();
    }
}
