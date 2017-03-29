package net.chandol.study.board.article.controller;

import net.chandol.study.board.article.model.Article;
import net.chandol.study.board.article.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ArticleController {
    @Autowired ArticleService articleService;

    @GetMapping("/articles")
    public String getArticles(
            Model model,
            @RequestParam(defaultValue = "1") Integer page) {

        Page<Article> articlePage = articleService.getArticlePage(page - 1);
        model.addAttribute("articlePage", articlePage);

        return "/article/articles";
    }

    @GetMapping("/articles/write")
    public String writeArticles(Model model) {
        return "/article/writeArticle";
    }

}
