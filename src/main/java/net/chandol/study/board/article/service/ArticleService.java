package net.chandol.study.board.article.service;

import net.chandol.study.board.article.dto.ArticleCreateRequest;
import net.chandol.study.board.article.dto.ArticleModifyRequest;
import net.chandol.study.board.article.model.Article;
import net.chandol.study.board.article.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.data.domain.Sort.Direction.*;

@Service
public class ArticleService {
    @Autowired ArticleRepository repository;
    private final int DEFAULT_PAGE_SIZE = 10;

    @Transactional
    public Article createArticle(ArticleCreateRequest request) {
        Article article = new Article(request.getTitle(), request.getAuthor(), request.getContent());
        return repository.save(article);
    }

    @Transactional
    public Page<Article> getArticlePage(int page) {
        PageRequest pageRequest = new PageRequest(
                page, DEFAULT_PAGE_SIZE, new Sort(DESC, "id"));

        return repository.findAll(pageRequest);
    }

    @Transactional
    public Article getArticle(Long id) {
        return repository.getOne(id);
    }

    @Transactional
    public Article modifyArticle(Long id, ArticleModifyRequest request) {
        Article article = getArticle(id);
        article.modifyArticle(request.getTitle(), request.getAuthor(), request.getContent());
        return article;
    }

    @Transactional
    public void removeArticle(Long id) {
        repository.delete(id);
    }

}
