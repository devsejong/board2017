package net.chandol.study.board.article.dto;

import lombok.Data;
import lombok.experimental.Tolerate;

@Data
public class ArticleCreateRequest {
    private String title;
    private String content;
    private Long userId;

    @Tolerate
    public ArticleCreateRequest(String title, String content, Long userId) {
        this.userId = userId;
        this.title = title;
        this.content = content;
    }
}
