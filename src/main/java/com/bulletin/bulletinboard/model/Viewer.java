package com.bulletin.bulletinboard.model;

import java.time.Instant;
import java.util.Objects;

public class Viewer {
    private Long id;
    private Post post;
    private Instant createDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public Instant getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Instant createDate) {
        this.createDate = createDate;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Viewer viewer = (Viewer) o;
        return Objects.equals(id, viewer.id) && Objects.equals(post, viewer.post) && Objects.equals(createDate, viewer.createDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, post, createDate);
    }

    @Override
    public String toString() {
        return "Viewer{" +
                "id=" + id +
                ", post=" + post +
                ", createDate=" + createDate +
                '}';
    }
}
