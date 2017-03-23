package net.chandol.study.board.article.dto;

import lombok.Data;
import lombok.experimental.Tolerate;

@Data
public class ArticleModifyRequest {
    private String title;
    private String author;
    private String content;

    @Tolerate
    public ArticleModifyRequest(String title, String author, String content) {
        this.title = title;
        this.author = author;
        this.content = content;
    }
}
