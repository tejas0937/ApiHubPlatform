package com.apihub.model;
import java.time.LocalDateTime;
public class User {

    private long id;
    private Long organizationId;
    private String fullName;
    private String email;
    private String password;
    private String status;
    private LocalDateTime createdAt;

    // ---------- getters & setters ----------

    public long getId() {
        return id;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    public void setId(long id) {
        this.id = id;
    }

    public Long getOrganizationId() {
        return organizationId;
    }
    

    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // 🔴 this was missing
    public String getPassword() {
        return password;
    }
    private String passwordHash;

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    // 🔴 this was missing
    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
