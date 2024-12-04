package mongodb.demo.mongodemo.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Document(collection = "news")
public class News {
    private String id;
    private String title;
    private String content;
    private List<Map<String,String>> author;
    private Long views;
    private List<String> tags;
    private List<Map<String,String>> comments;
    private LocalDate publicationDate;

    public News(String id, String title, String content, List<Map<String, String>> author, Long views, List<String> tags, List<Map<String, String>> comments, LocalDate publicationDate) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.author = author;
        this.views = views;
        this.tags = tags;
        this.comments = comments;
        this.publicationDate = publicationDate;
    }

    public News(String title, String content, List<Map<String, String>> author, Long views, List<String> tags, List<Map<String, String>> comments, LocalDate publicationDate) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.views = views;
        this.tags = tags;
        this.comments = comments;
        this.publicationDate = publicationDate;
    }

    public News() {
    }

    @Id
    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public List<Map<String, String>> getAuthor() {
        return author;
    }

    public Long getViews() {
        return views;
    }

    public List<String> getTags() {
        return tags;
    }

    public List<Map<String, String>> getComments() {
        return comments;
    }

    @Field(name="publication_date")
    public LocalDate getPublicationDate() {
        return publicationDate;
    }
//
    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setAuthor(List<Map<String, String>> author) {
        this.author = author;
    }

    public void setViews(Long views) {
        this.views = views;
    }

    public void setTags(List<String > tags) {
        this.tags = tags;
    }

    public void setComments(List<Map<String, String>> comments) {
        this.comments = comments;
    }

    public void setPublicationDate(LocalDate publicationDate) {
        this.publicationDate = publicationDate;
    }
}
