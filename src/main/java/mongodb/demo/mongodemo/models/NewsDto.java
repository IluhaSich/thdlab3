package mongodb.demo.mongodemo.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class NewsDto {
    private String id;
    private String title;
    private String content;
    private String author;
    private Long views;
    private String tags;
    private String comments;
    private LocalDate publicationDate;

    public NewsDto() {
    }

    public NewsDto(String id, String title, String content, String author, Long views, String tags, String comments, LocalDate publicationDate) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.author = author;
        this.views = views;
        this.tags = tags;
        this.comments = comments;
        this.publicationDate = publicationDate;
    }

    public NewsDto(String title, String content, String author, Long views, String tags, String comments, LocalDate publicationDate) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.views = views;
        this.tags = tags;
        this.comments = comments;
        this.publicationDate = publicationDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public LocalDate getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(LocalDate publicationDate) {
        this.publicationDate = publicationDate;
    }
}
