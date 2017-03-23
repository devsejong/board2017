package net.chandol.study.board.article.dto;

import lombok.Data;
import lombok.experimental.Tolerate;

@Data
public class ArticleCreateRequest {
    private String title;
    private String author;
    private String content;

    @Tolerate
    public ArticleCreateRequest(String title, String author, String content) {
        this.title = title;
        this.author = author;
        this.content = content;
    }
}
