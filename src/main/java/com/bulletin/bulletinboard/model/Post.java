package com.bulletin.bulletinboard.model;


import java.time.Instant;
import java.util.Objects;

public class Post {
    private Long id;
    private String title;
    private String titleEn;
    private String author;
    private String password;
    private Long views;
    private Instant createdDate;
    private Instant modifiedDate;
    private BodyContent bodyContent;

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

    public BodyContent getBodyContent() {
        return bodyContent;
    }

    public void setBodyContent(BodyContent bodyContent) {
        this.bodyContent = bodyContent;
    }

    public String getTitleEn() {
        return titleEn;
    }

    public void setTitleEn(String titleEn) {
        this.titleEn = titleEn;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return Objects.equals(id, post.id) && Objects.equals(title, post.title) && Objects.equals(titleEn, post.titleEn) && Objects.equals(author, post.author) && Objects.equals(password, post.password) && Objects.equals(views, post.views) && Objects.equals(createdDate, post.createdDate) && Objects.equals(modifiedDate, post.modifiedDate) && Objects.equals(bodyContent, post.bodyContent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, titleEn, author, password, views, createdDate, modifiedDate, bodyContent);
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", titleEn='" + titleEn + '\'' +
                ", author='" + author + '\'' +
                ", password='" + password + '\'' +
                ", views=" + views +
                ", createdDate=" + createdDate +
                ", modifiedDate=" + modifiedDate +
                ", bodyContent=" + bodyContent +
                '}';
    }
}
