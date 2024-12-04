package mongodb.demo.mongodemo.runner;

import com.github.javafaker.Faker;
import mongodb.demo.mongodemo.models.News;
import mongodb.demo.mongodemo.services.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.concurrent.TimeUnit;

// Раскоментировать аннотацию для генерации записей
//@Component
public class GenerateNews implements CommandLineRunner {
    private static final int LIST_SIZE = 1000;
    private static final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    @Autowired
    private NewsService newsService;

    @Override
    public void run(String... args) throws Exception {
        newsService.saveAll(generateBooks());
    }

    private List<News> generateBooks() throws ParseException {
        Faker faker = new Faker();
        List<News> news = new ArrayList<>(LIST_SIZE);
        for (int j=0; j<LIST_SIZE; j++) {
            Random random= new Random();
            List<String> tags = new ArrayList<>();
            for (int i = 0; i < random.nextInt(1,4); i++){
                tags.add(faker.job().title());
            }

            List<Map<String,String>> comments = new ArrayList<>();
            for (int i = 0; i < random.nextInt(0,10); i++){
                Map<String,String> comment = new LinkedHashMap<>();
                comment.put("name", faker.name().username());
                comment.put("content", faker.lorem().characters(1,5));
                comments.add(comment);
            }

            List<Map<String,String>> authors = new ArrayList<>();
            Map<String,String> author = new LinkedHashMap<>();
            author.put("name", faker.name().username());
            author.put("department", faker.job().title());
            authors.add(author);
            news.add(new News(
                    faker.lorem().characters(10, 50),
                    faker.lorem().characters(10, 50),
                    authors,
                    (long) faker.number().numberBetween(300, 10000),
                    tags,
                    comments,
                    toLocalDate(faker.date().past(1000, TimeUnit.DAYS))
                    )
            );
        }
        return news;
    }

    private LocalDate toLocalDate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }
}