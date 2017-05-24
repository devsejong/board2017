package net.chandol.study.board.article.model;

import lombok.Getter;
import lombok.ToString;
import net.chandol.study.board.user.User;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Entity
@Getter @ToString
@EntityListeners(value = {AuditingEntityListener.class})
public class Comment {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    @JoinColumn(name = "ARTICLE_ID")
    private Article article;
    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;
    @Column(columnDefinition = "TEXT")
    private String body;
    @CreatedDate
    private OffsetDateTime created;
    @LastModifiedDate
    private OffsetDateTime modified;

    protected Comment() {
    }

    public Comment(Article article, User user, String body) {
        this.setArticle(article);
        this.user = user;
        this.body = body;
    }

    public void setArticle(Article article) {
        this.article = article;
        article.addComment(this);
    }
}
