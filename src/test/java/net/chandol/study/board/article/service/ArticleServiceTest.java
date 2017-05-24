package net.chandol.study.board.article.service;

import net.chandol.study.board.article.dto.ArticleCreateRequest;
import net.chandol.study.board.article.dto.ArticleModifyRequest;
import net.chandol.study.board.article.model.Article;
import net.chandol.study.board.article.repository.ArticleRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static net.chandol.study.board.TestData.user;
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
        ArticleCreateRequest request = new ArticleCreateRequest("제목", "본문", user("user1").getId());

        // when
        Article article = articleService.createArticle(request);

        // then
        assertThat(article.getId()).hasNoNullFieldsOrProperties();
        assertThat(article.getTitle()).isEqualTo(request.getTitle());
        assertThat(article.getBody()).isEqualTo(request.getBody());
    }

    @Test
    public void readArticleTest() {
        // given
        ArticleCreateRequest request = new ArticleCreateRequest("제목", "본문", user("user1").getId());
        Article article = articleService.createArticle(request);

        // when
        Article readArticle = articleService.getArticle(article.getId());

        //then
        assertThat(readArticle.getId()).hasNoNullFieldsOrProperties();
        assertThat(readArticle.getTitle()).isEqualTo(request.getTitle());
        assertThat(readArticle.getBody()).isEqualTo(request.getBody());
    }

    @Test
    public void modifyArticleTest() {
        // given
        ArticleCreateRequest request = new ArticleCreateRequest("제목", "본문", user("user1").getId());
        Article article = articleService.createArticle(request);

        //when
        ArticleModifyRequest modifyRequest = new ArticleModifyRequest("title", "body");
        Article modifiedArticle = articleService.modifyArticle(article.getId(), modifyRequest, null);

        //then
        assertThat(modifiedArticle.getId()).isEqualTo(article.getId());
        assertThat(modifiedArticle.getTitle()).isEqualTo("title");
        assertThat(modifiedArticle.getBody()).isEqualTo("body");
    }

    @Test
    public void removeArticleTest() {
        // given
        ArticleCreateRequest request = new ArticleCreateRequest("제목", "본문", user("user1").getId());
        Article article = articleService.createArticle(request);
        Long counts = articleRepository.count();

        //when
        articleService.removeArticle(article.getId());

        //then
        assertThat(articleRepository.count()).isEqualTo(counts - 1);
    }

    @Test
    public void readArticlePageTest() {
        // given
        for (int idx = 0; idx < 30; idx++) {
            articleService.createArticle(new ArticleCreateRequest("제목" + idx, "본문", user("user1").getId()));
        }

        // when
        Page<Article> articlePage = articleService.getArticlePage(0);

        // then
        assertThat(articlePage.getSize()).isEqualTo(10);
        Article firstArticle = articlePage.getContent().get(0);
        assertThat(firstArticle.getTitle()).isEqualTo("제목29");
    }

}