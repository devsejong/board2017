package net.chandol.study.board.article.dto;

import lombok.Data;
import lombok.experimental.Tolerate;

@Data
public class CommentCreateRequest {
    private Long articleId;
    private Long userId;
    private String body;

    @Tolerate
    public CommentCreateRequest(Long articleId, Long userId, String body) {
        this.articleId = articleId;
        this.userId = userId;
        this.body = body;
    }
}
