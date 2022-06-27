package com.mycompany.myapp.domain.request;


import com.mycompany.myapp.domain.AbstractAuditingEntity;

import javax.persistence.*;
import java.io.Serializable;

/*
 * A class to store submitted customer request information
 * */
@Entity
@Table(name = "jhi_submitted_request")
public class SubmittedRequest extends AbstractAuditingEntity implements Serializable {
    @Id
    @SequenceGenerator(sequenceName = "submitted_request_sequence", name = "submitted_request_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "submitted_request_sequence")
    private Long id;

    @Column(name = "customer_id")
    private Long customerId;

    public SubmittedRequest(Long customerId) {
        this.customerId = customerId;
    }

    public SubmittedRequest() { }

    public Long getId() {
        return id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
}
