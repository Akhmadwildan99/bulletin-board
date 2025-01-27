package com.bulletin.bulletinboard.model;

import java.time.Instant;
import java.util.Objects;

public class PostCredential {
    private Long id;
    private String updatedKey;
    private Instant updatedKeyDate;
    private String password;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUpdatedKey() {

        return updatedKey;
    }

    public void setUpdatedKey(String updatedKey) {
        this.updatedKey = updatedKey;
    }

    public Instant getUpdatedKeyDate() {
        return updatedKeyDate;
    }

    public void setUpdatedKeyDate(Instant updatedKeyDate) {
        this.updatedKeyDate = updatedKeyDate;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "PostCredential{" +
                "id=" + id +
                ", updatedKey='" + updatedKey + '\'' +
                ", updatedKeyDate='" + updatedKeyDate + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        PostCredential that = (PostCredential) o;
        return Objects.equals(id, that.id) && Objects.equals(updatedKey, that.updatedKey) && Objects.equals(updatedKeyDate, that.updatedKeyDate) && Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, updatedKey, updatedKeyDate, password);
    }
}
