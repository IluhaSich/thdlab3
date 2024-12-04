package mongodb.demo.mongodemo.services.impl;

import org.modelmapper.ModelMapper;
import mongodb.demo.mongodemo.models.News;
import mongodb.demo.mongodemo.models.NewsDto;
import mongodb.demo.mongodemo.repos.NewsRepository;
import mongodb.demo.mongodemo.services.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class NewsServicesImpl implements NewsService {
    private final NewsRepository newsRepository;
    ModelMapper modelMapper = new ModelMapper();

    @Autowired
    public NewsServicesImpl(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    @Override
    public NewsDto getById(String id) {
        News news = newsRepository.getById(id);
        return mapToDto(news);
    }

    @Override
    public void deleteById(String id) {
        newsRepository.deleteById(id);
    }

    @Override
    public void updateById(String id, NewsDto newsDto) {
        News news = newsRepository.getById(id);
        List<Map<String, String>> authors = new ArrayList<>();
        for (String i : newsDto.getAuthor().split(";")) {
            Map<String, String> map = new HashMap();
            String[] author = i.split(",");
            map.put("comments", author[0]);
            map.put("department", author[1]);
            authors.add(map);
        }

        List<Map<String, String>> comments = new ArrayList<>();
        for (String i : newsDto.getComments().split(";")) {
            Map<String, String> map = new HashMap();
            String[] comment = i.split(",");
            map.put("name", comment[0]);
            map.put("content", comment[1]);
            comments.add(map);
        }
        List<String> tags = new ArrayList<>(Arrays.asList(newsDto.getTags().split(",")));
        news.setId(id);
        news.setAuthor(authors);
        news.setComments(comments);
        news.setTags(tags);
        news.setContent(newsDto.getContent());
        news.setViews(newsDto.getViews());
        news.setTitle(newsDto.getTitle());
        news.setPublicationDate(newsDto.getPublicationDate());
        newsRepository.save(news);
    }


    public List<String> getComments() {
        List<News> newsList = newsRepository.findAll();
        return newsList.stream()
                .flatMap(news -> news.getComments().stream()
                        .map(comment -> comment.get("content"))
                        .filter(Objects::nonNull))
                .collect(Collectors.toList());
    }



    public void save(NewsDto newsDto) {
        newsRepository.save(mapToEntity(newsDto));
    }

    public void saveAll(List<News> news) {
        newsRepository.saveAll(news);
    }

    @Override
    public Page<NewsDto> getNews(Pageable pageable) {
        Page<News> newsDtos = newsRepository.findAll(pageable);
        return newsDtos.map(this::mapToDto);
    }
    public NewsDto mapToDto(News news) {
        String author = "";
        for (Map<String, String> authors : news.getAuthor()) {
            author += authors.get("name") + ",";
            author += authors.get("department") + ";";
        }
        String comment = "";
        for (Map<String, String> comments : news.getComments()) {
            comment += comments.get("comments") + ",";
            comment += comments.get("content") + ";";
        }
        String tags = "";
        for (String tag : news.getTags()) {
            tags += tag + ",";
        }
        NewsDto newsDto = new NewsDto(
                news.getId(),
                news.getTitle(),
                news.getContent(),
                author,
                news.getViews(),
                tags,
                comment,
                news.getPublicationDate()
        );return newsDto;
    }

    public News mapToEntity(NewsDto newsDto) {
        List<Map<String, String>> authors = new ArrayList<>();
        for (String i : newsDto.getAuthor().split(";")) {
            Map<String, String> map = new HashMap();
            String[] author = i.split(",");
            map.put("comments", author[0]);
            map.put("department", author[1]);
            authors.add(map);
        }

        List<Map<String, String>> comments = new ArrayList<>();
        for (String i : newsDto.getComments().split(";")) {
            Map<String, String> map = new HashMap();
            String[] comment = i.split(",");
            map.put("name", comment[0]);
            map.put("content", comment[1]);
            comments.add(map);
        }
        List<String> tags = new ArrayList<>(Arrays.asList(newsDto.getTags().split(",")));

        News news = new News(
                newsDto.getId(),
                newsDto.getTitle(),
                newsDto.getContent(),
                authors,
                newsDto.getViews(),
                tags,
                comments,
                newsDto.getPublicationDate()
        );
        return news;
    }
}


////ВЫВЕСТИ ТОЛЬКО КОММЕНТАРИИ (ТОЛЬКО КОНТЕНТ)
//    public String getComments(News news) {
//        String comment = "";
//        for (Map<String, String> comments : news.getComments()) {
//            comment += comments.get("content") + ";";
//        }
//
////        newsDtos.map(this::mapToDto);
//        return comment;
//    }

//    public String getComments() {
//        List<News> news = newsRepository.findAll();
//        String content = "";
//        content += news.stream().map(news1 -> news1.getComments().stream().map(comments -> comments.get("content")));
//        System.out.println(content);
//        return content;
//    }