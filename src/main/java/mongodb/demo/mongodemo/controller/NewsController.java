package mongodb.demo.mongodemo.controller;

import mongodb.demo.mongodemo.models.News;
import mongodb.demo.mongodemo.models.NewsDto;
import mongodb.demo.mongodemo.services.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@CrossOrigin
public class NewsController {
    private final NewsService newsService;

    @Autowired
    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    @GetMapping("/news")
    public Page<NewsDto> getNewsPage(
            @RequestParam(defaultValue = "0") Integer offset,              // Номер страницы
            @RequestParam(defaultValue = "10") Integer limit
    ){
        return newsService.getNews(PageRequest.of(offset,limit));
    }

    @GetMapping("/news/{id}")
    public NewsDto findById(@PathVariable String id){
        return newsService.getById(id);
    }

    @GetMapping("/getComments")
    public List<String> getComments(){
        return newsService.getComments();
    }

    @PostMapping("/news")
    public void addNews(@RequestBody NewsDto newsDto){
        newsService.save(newsDto);
    }

    @PutMapping("/news/{id}")
    public void updateById(@PathVariable String id,@RequestBody NewsDto newsDto){
        newsService.updateById(id,newsDto);
    }

    @DeleteMapping("/news/{id}")
    public void delete(@PathVariable String id){
        newsService.deleteById(id);
    }




//    @GetMapping("/getByTitle/{title}")
//    public Page<News> getByTitle(@PathVariable String title) {
//        return newsService.getByTitle(title);
//    }
//
//    @GetMapping("/news")
//    public Page<News> findAll() {
//        return newsService.findAll();
//    }
//
//    @GetMapping("/getByContent/{title}")
//    public Page<News> getByContent(@PathVariable String content) {
//        return newsService.getByContent(content);
//    }
//
//    @GetMapping("/getByAuthor/{author}")
//    public Page<News> getByAuthor(@PathVariable String author) {
//        return newsService.getByAuthor(author);
//    }
//
//    @GetMapping("/getByViews/{views}")
//    public Page<News> getByViews(@PathVariable Long views) {
//        return newsService.getByViews(views);
//    }
//
//    @GetMapping("/getAfter/{date}")
//    public Page<News> findByPublicationDateAfter(@PathVariable LocalDate date) {
//        return newsService.findByPublicationDateAfter(date);
//    }
}
