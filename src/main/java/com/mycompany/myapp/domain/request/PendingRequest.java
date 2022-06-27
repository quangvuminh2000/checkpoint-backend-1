package com.mycompany.myapp.domain.request;


import com.mycompany.myapp.domain.AbstractAuditingEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;

/*
 * A class to store pending customer request information
 * */
@Entity
@Table(name = "jhi_pending_request")
public class PendingRequest extends AbstractAuditingEntity implements Serializable {
    @Id
    @SequenceGenerator(sequenceName = "pending_request_sequence", name = "pending_request_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pending_request_sequence")
    private Long id;

    @Column(name = "customer_id")
    private Long customerId;

    @Column(name = "status")
    private boolean status;

    @Transient
    private final Long threshold = 15l;

    public PendingRequest(Long customerId) {
        this.customerId = customerId;
        this.status = true;
    }

    public PendingRequest() { }

    public Long getId() {
        return id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public boolean getStatus() {
        this.status = Instant.now().minusSeconds(15).isBefore(this.getCreatedDate());

        return this.status;
    }

    public Long getThreshold() {
        return threshold;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
}
