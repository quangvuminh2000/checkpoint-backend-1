package com.mycompany.myapp.repository.request;

import com.mycompany.myapp.domain.request.PendingRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface PendingRequestRepository extends JpaRepository<PendingRequest, Long> {
    @Query("SELECT r FROM PendingRequest r WHERE r.customerId = :customerId")
    Optional<PendingRequest> findByCustomerId(Long customerId);

    @Transactional
    @Modifying
    @Query("DELETE FROM PendingRequest r WHERE r.customerId = :customerId")
    void deleteByCustomerId(Long customerId);

    @Query("SELECT r FROM PendingRequest r WHERE r.status = false")
    List<PendingRequest> findCreatedTimeSurpassThreshold();

    @Transactional
    @Modifying
    @Query("UPDATE PendingRequest r SET r.status = :newStatus WHERE r.customerId = :customerId")
    void refreshCustomerStatus(boolean newStatus, Long customerId);
}
