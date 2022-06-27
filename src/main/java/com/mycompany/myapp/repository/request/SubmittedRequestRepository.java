package com.mycompany.myapp.repository.request;

import com.mycompany.myapp.domain.request.SubmittedRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubmittedRequestRepository extends JpaRepository<SubmittedRequest, Long> { }
