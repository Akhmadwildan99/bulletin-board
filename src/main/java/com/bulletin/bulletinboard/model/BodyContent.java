package com.bulletin.bulletinboard.model;

import org.hibernate.annotations.processing.Exclude;
import org.springframework.data.annotation.CreatedDate;

import java.time.Instant;
import java.util.Objects;


public class BodyContent {
    private Long id;
    private String content;
    private Instant createdDate;
    private Instant modifiedDate;
    private Post post;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public Instant getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Instant modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        BodyContent that = (BodyContent) o;
        return Objects.equals(id, that.id) && Objects.equals(content, that.content) && Objects.equals(createdDate, that.createdDate) && Objects.equals(modifiedDate, that.modifiedDate) && Objects.equals(post, that.post);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, content, createdDate, modifiedDate, post);
    }

    @Override
    public String toString() {
        return "BodyContent{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", createdDate=" + createdDate +
                ", modifiedDate=" + modifiedDate +

                '}';
    }
}
