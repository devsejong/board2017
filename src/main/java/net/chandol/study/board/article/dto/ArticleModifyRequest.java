package net.chandol.study.board.article.dto;

import lombok.Data;
import lombok.experimental.Tolerate;

@Data
public class ArticleModifyRequest {
    private String title;
    private String content;

    @Tolerate
    public ArticleModifyRequest(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
