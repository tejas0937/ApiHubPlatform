package com.apihub.model;

import java.time.LocalDate;

public class Subscription {

    private long id;
    private long organizationId;
    private long planId;
    private LocalDate startDate;
    private String status;

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public long getOrganizationId() { return organizationId; }
    public void setOrganizationId(long organizationId) { this.organizationId = organizationId; }

    public long getPlanId() { return planId; }
    public void setPlanId(long planId) { this.planId = planId; }

    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
