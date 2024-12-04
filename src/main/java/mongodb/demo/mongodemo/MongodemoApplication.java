package mongodb.demo.mongodemo;

import mongodb.demo.mongodemo.repos.NewsRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.Collections;

@SpringBootApplication
@EnableMongoRepositories(basePackageClasses = {NewsRepository.class}, repositoryImplementationPostfix = "Impl")
public class MongodemoApplication {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(MongodemoApplication.class);
        app.setDefaultProperties(Collections.singletonMap("spring.profiles.default", "mongodb"));
        app.run(args);
    }
}
