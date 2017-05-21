
package net.chandol.study.board.util;

import net.chandol.study.board.article.dto.ArticleCreateRequest;
import net.chandol.study.board.article.service.ArticleService;
import net.chandol.study.board.user.User;
import net.chandol.study.board.user.UserCreateRequest;
import net.chandol.study.board.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.stream.IntStream;

@Profile("local")
@Configuration
public class LocalTestDataGenerator {
    @Autowired UserService userService;
    @Autowired ArticleService articleService;

    @PostConstruct
    @Transactional
    public void addDummyArticles() {
        User user = userService.createUser(new UserCreateRequest("admin", "email", "admin"));

        IntStream.rangeClosed(1, 128).forEach(i -> {
            ArticleCreateRequest request = new ArticleCreateRequest("제목 " + i, "본문 " + i, user.getId());
            articleService.createArticle(request);
        });
    }
}
