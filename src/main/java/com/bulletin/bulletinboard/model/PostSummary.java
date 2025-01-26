package com.bulletin.bulletinboard.model;

import java.time.Instant;
import java.util.Objects;

public class PostSummary {
    private Long id;
    private String title;
    private String titleEn;
    private String author;
    private Long views;
    private Instant createdDate;
    private Instant modifiedDate;

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

    public String getTitleEn() {
        return titleEn;
    }

    public void setTitleEn(String titleEn) {
        this.titleEn = titleEn;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        PostSummary that = (PostSummary) o;
        return Objects.equals(id, that.id) && Objects.equals(title, that.title) && Objects.equals(titleEn, that.titleEn) && Objects.equals(author, that.author) && Objects.equals(views, that.views) && Objects.equals(createdDate, that.createdDate) && Objects.equals(modifiedDate, that.modifiedDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, titleEn, author, views, createdDate, modifiedDate);
    }

    @Override
    public String toString() {
        return "PostSummary{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", titleEn='" + titleEn + '\'' +
                ", author='" + author + '\'' +
                ", views=" + views +
                ", createdDate=" + createdDate +
                ", modifiedDate=" + modifiedDate +
                '}';
    }
}
