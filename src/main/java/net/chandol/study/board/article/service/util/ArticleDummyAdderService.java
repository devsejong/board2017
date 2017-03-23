package net.chandol.study.board.article.service.util;

import net.chandol.study.board.article.dto.ArticleCreateRequest;
import net.chandol.study.board.article.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.stream.IntStream;

@Service
@Profile("local")
public class ArticleDummyAdderService {
    @Autowired ArticleService service;

    @PostConstruct
    @Transactional
    public void addDummyArticles() {
        IntStream.rangeClosed(1, 128).forEach(i -> {
            ArticleCreateRequest request = new ArticleCreateRequest("제목 " + i, "작가 " + i, "본문 " + i);
            service.createArticle(request);
        });
    }
}
