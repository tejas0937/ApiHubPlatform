package com.apihub.model;

import java.time.LocalDateTime;

public class ApiUsageLog {

    private long id;
    private long apiKeyId;
    private String apiName;
    private LocalDateTime createdAt;

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public long getApiKeyId() { return apiKeyId; }
    public void setApiKeyId(long apiKeyId) { this.apiKeyId = apiKeyId; }

    public String getApiName() { return apiName; }
    public void setApiName(String apiName) { this.apiName = apiName; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
