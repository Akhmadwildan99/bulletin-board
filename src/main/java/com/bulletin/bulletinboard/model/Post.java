package com.bulletin.bulletinboard.model;


import java.time.Instant;
import java.util.Objects;

public class Post {
    private Long id;
    private String title;
    private String author;
    private String password;
    private Long views;
    private Instant createDate;
    private Instant modifiedDate;
    private BodyContent content;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getViews() {
        return views;
    }

    public void setViews(Long views) {
        this.views = views;
    }

    public BodyContent getContent() {
        return content;
    }

    public void setContent(BodyContent content) {
        this.content = content;
    }

    public Instant getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Instant createDate) {
        this.createDate = createDate;
    }

    public Instant getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Instant modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    @Override
    public boolean equals(Object o) {

        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return Objects.equals(id, post.id) && Objects.equals(title, post.title) && Objects.equals(author, post.author) && Objects.equals(password, post.password) && Objects.equals(views, post.views) && Objects.equals(createDate, post.createDate) && Objects.equals(modifiedDate, post.modifiedDate) && Objects.equals(content, post.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, author, password, views, createDate, modifiedDate, content);
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", password='" + password + '\'' +
                ", views=" + views +
                ", createDate=" + createDate +
                ", modifiedDate=" + modifiedDate +
                ", content=" + content +
                '}';
    }
}
