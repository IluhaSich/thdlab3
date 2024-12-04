package mongodb.demo.mongodemo.services;

import mongodb.demo.mongodemo.models.News;
import mongodb.demo.mongodemo.models.NewsDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

public interface NewsService {
    NewsDto getById(String id);

    void deleteById(String id);

    void save(NewsDto news);

    void updateById(String id, NewsDto newsDto);

    List<String> getComments();

    void saveAll(List<News> news);

    Page<NewsDto> getNews(Pageable pageable);
}
//    Page<NewsDto> getByTitle(String title);
//    Page<NewsDto> getByContent(String content);
//    Page<NewsDto> getByAuthor(String author);
//    Page<NewsDto> getByViews(Long views);
//    Page<NewsDto> findByPublicationDateAfter(LocalDate date);
//}
