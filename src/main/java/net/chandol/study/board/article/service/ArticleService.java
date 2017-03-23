package net.chandol.study.board.article.service;

import net.chandol.study.board.article.dto.ArticleCreateRequest;
import net.chandol.study.board.article.model.Article;
import net.chandol.study.board.article.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ArticleService {
    @Autowired ArticleRepository repository;

    @Transactional
    public Article createArticle(ArticleCreateRequest request){
        Article article = new Article(request.getTitle(), request.getAuthor(), request.getContent());
        return repository.save(article);
    }

    @Transactional
    public Article getArticle(Long id) {
        return repository.getOne(id);
    }
}
