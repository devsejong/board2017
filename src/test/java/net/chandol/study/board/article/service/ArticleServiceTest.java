package net.chandol.study.board.article.service;

import net.chandol.study.board.article.dto.ArticleCreateRequest;
import net.chandol.study.board.article.dto.ArticleModifyRequest;
import net.chandol.study.board.article.model.Article;
import net.chandol.study.board.article.repository.ArticleRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@RunWith(SpringRunner.class)
public class ArticleServiceTest {
    @Autowired ArticleService articleService;
    @Autowired ArticleRepository articleRepository;

    @Test
    public void createArticleTest() {
        // given
        ArticleCreateRequest request = new ArticleCreateRequest("제목", "작가", "본문");

        // when
        Article article = articleService.createArticle(request);

        // then
        assertThat(article.getId()).hasNoNullFieldsOrProperties();
        assertThat(article.getTitle()).isEqualTo(request.getTitle());
        assertThat(article.getAuthor()).isEqualTo(request.getAuthor());
        assertThat(article.getContent()).isEqualTo(request.getContent());
        assertThat(article.getCreated()).hasNoNullFieldsOrProperties();
    }

    @Test
    public void readArticleTest(){
        // given
        ArticleCreateRequest request = new ArticleCreateRequest("제목", "작가", "본문");
        Article article = articleService.createArticle(request);

        // when
        Article readArticle = articleService.getArticle(article.getId());

        //then
        assertThat(readArticle.getId()).hasNoNullFieldsOrProperties();
        assertThat(readArticle.getTitle()).isEqualTo(request.getTitle());
        assertThat(readArticle.getAuthor()).isEqualTo(request.getAuthor());
        assertThat(readArticle.getContent()).isEqualTo(request.getContent());
        assertThat(readArticle.getCreated()).hasNoNullFieldsOrProperties();
    }

    @Test
    public void modifyArticleTest(){
        // given
        ArticleCreateRequest request = new ArticleCreateRequest("제목", "작가", "본문");
        Article article = articleService.createArticle(request);

        //when
        ArticleModifyRequest modifyRequest = new ArticleModifyRequest("title", "author", "content");
        Article modifiedArticle = articleService.modifyArticle(article.getId(), modifyRequest);

        //then
        assertThat(modifiedArticle.getId()).isEqualTo(article.getId());
        assertThat(modifiedArticle.getTitle()).isEqualTo("title");
        assertThat(modifiedArticle.getAuthor()).isEqualTo("author");
        assertThat(modifiedArticle.getContent()).isEqualTo("content");
    }

    @Test
    public void removeArticleTest(){
        // given
        ArticleCreateRequest request = new ArticleCreateRequest("제목", "작가", "본문");
        Article article = articleService.createArticle(request);
        assertThat(articleRepository.count()).isEqualTo(1L);

        //when
        articleService.removeArticle(article.getId());

        //then
        assertThat(articleRepository.count()).isEqualTo(0L);
    }

}