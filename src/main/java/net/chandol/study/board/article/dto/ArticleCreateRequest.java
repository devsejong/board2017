package net.chandol.study.board.article.dto;

import lombok.Data;
import lombok.experimental.Tolerate;

@Data
public class ArticleCreateRequest {
    private String title;
    private String body;
    private Long userId;

    @Tolerate
    public ArticleCreateRequest(String title, String body, Long userId) {
        this.userId = userId;
        this.title = title;
        this.body = body;
    }
}
