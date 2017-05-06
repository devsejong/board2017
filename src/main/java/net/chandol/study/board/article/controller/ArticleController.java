package net.chandol.study.board.article.controller;

import net.chandol.study.board.article.dto.ArticleCreateRequest;
import net.chandol.study.board.article.model.Article;
import net.chandol.study.board.article.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ArticleController {
    @Autowired ArticleService articleService;

    @GetMapping("/articles")
    public String getArticles(Model model,
            @RequestParam(defaultValue = "1") Integer page) {

        Page<Article> articlePage = articleService.getArticlePage(page - 1);
        model.addAttribute("articlePage", articlePage);

        return "pages/article/articles";
    }

    @GetMapping("/article/{articleId}")
    public String getArticle(Model model,
            @PathVariable(value="articleId") Article article) {

        model.addAttribute("articlePage", article);
        return "pages/article/article";
    }

    @GetMapping("/articles/write")
    public String writeArticle(Model model) {
        return "pages/article/writeArticle";
    }

    @PostMapping("/articles/write")
    public String writeArticle(
            @ModelAttribute ArticleCreateRequest request) {

        articleService.createArticle(request);
        return "redirect:/articles";
    }

}
