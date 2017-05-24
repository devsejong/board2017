package net.chandol.study.board.article.dto;

import lombok.Data;
import lombok.experimental.Tolerate;

@Data
public class ArticleModifyRequest {
    private String title;
    private String body;

    @Tolerate
    public ArticleModifyRequest(String title, String body) {
        this.title = title;
        this.body = body;
    }
}
