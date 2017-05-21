package net.chandol.study.board;

import net.chandol.study.board.article.dto.ArticleCreateRequest;
import net.chandol.study.board.article.model.Article;
import net.chandol.study.board.article.service.ArticleService;
import net.chandol.study.board.user.User;
import net.chandol.study.board.user.UserCreateRequest;
import net.chandol.study.board.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TestData {
    private static TestData instnace;
    private static List<User> users;
    private static List<Article> articles;

    public static User user(String username){
        return users.stream()
                .filter(u->u.getUsername().equals(username))
                .collect(Collectors.toList()).get(0);
    }

    public static Article article(int index){
        return articles.get(index);
    }


    // 초기화 로직!!
    @Autowired TestDataInitializer initializer;

    @PostConstruct
    void initData() {
        users = initializer.initUsers();
        articles = initializer.initArticles();
        instnace = this;
    }

    @Component
    class TestDataInitializer {
        @Autowired UserService userService;
        @Autowired ArticleService articleService;

        @Transactional
        List<User> initUsers() {
            return Arrays.asList(
                    userService.createUser(new UserCreateRequest("admin", "admin@chandol.net", "password")),
                    userService.createUser(new UserCreateRequest("user1", "user1@chandol.net", "password1")),
                    userService.createUser(new UserCreateRequest("user2", "user2@chandol.net", "password2"))
            );
        }

        @Transactional
        List<Article> initArticles() {
            User user = userService.loadUserByUsername("user1");

            return Arrays.asList(
                    articleService.createArticle(new ArticleCreateRequest("title1", "content1", user.getId())),
                    articleService.createArticle(new ArticleCreateRequest("title2", "content2", user.getId())),
                    articleService.createArticle(new ArticleCreateRequest("title3", "content3", user.getId()))
            );
        }
    }
}
