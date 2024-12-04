package mongodb.demo.mongodemo.repos;

import mongodb.demo.mongodemo.models.News;
import org.springframework.data.domain.Page;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface NewsRepository extends MongoRepository<News, String> {
    News getById(String id);
}

//    Optional<News> getByTitle(String title);
//    Page<News> getByContent(String content);
//    Page<News> getByAuthor(String author);
//    Page<News> getByViews(Long views);
//    Page<News> findByPublicationDateAfter(LocalDate date);
//}
