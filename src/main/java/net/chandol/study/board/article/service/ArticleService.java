package net.chandol.study.board.article.service;

import net.chandol.study.board.article.dto.ArticleCreateRequest;
import net.chandol.study.board.article.dto.ArticleModifyRequest;
import net.chandol.study.board.article.model.Article;
import net.chandol.study.board.article.repository.ArticleRepository;
import net.chandol.study.board.user.User;
import net.chandol.study.board.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.data.domain.Sort.Direction.DESC;

@Service
public class ArticleService {
    @Autowired ArticleRepository repository;
    @Autowired UserService userService;
    private final int DEFAULT_PAGE_SIZE = 10;

    @Transactional
    public Article createArticle(ArticleCreateRequest request) {
        User user = userService.getUser(request.getUserId());
        Article article = new Article(request.getTitle(), request.getContent(), user);
        
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
    public Article modifyArticle(Long id, ArticleModifyRequest request, User user) {
        Article article = getArticle(id);
        article.modifyArticle(request.getTitle(),request.getContent(), user);
        return article;
    }

    @Transactional
    public void removeArticle(Long id) {
        repository.delete(id);
    }

}
