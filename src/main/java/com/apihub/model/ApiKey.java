package com.apihub.model;

import java.time.LocalDateTime;

public class ApiKey {

    private long id;
    private long organizationId;
    private long subscriptionId;
    private String apiKey;
    private String status;
    private LocalDateTime createdAt;

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public long getOrganizationId() { return organizationId; }
    public void setOrganizationId(long organizationId) { this.organizationId = organizationId; }

    public long getSubscriptionId() { return subscriptionId; }
    public void setSubscriptionId(long subscriptionId) { this.subscriptionId = subscriptionId; }

    public String getApiKey() { return apiKey; }
    public void setApiKey(String apiKey) { this.apiKey = apiKey; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
