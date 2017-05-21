package net.chandol.study.board.article.model;

import lombok.Getter;
import net.chandol.study.board.user.User;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Entity
@Getter
@EntityListeners(value = { AuditingEntityListener.class })
public class Article {
    @Id
    @GeneratedValue
    private Long id;
    private String title;
    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;
    @Column(columnDefinition = "TEXT")
    private String content;
    @CreatedDate
    private OffsetDateTime created;
    @LastModifiedDate
    private OffsetDateTime modified;

    protected Article() {
    }

    public Article(String title, String content, User user) {
        this.title = title;
        this.content = content;
        this.user = user;
    }

    public void modifyArticle(String title, String content, User user) {
        this.title = title;
        this.content = content;
        this.user = user;
    }
}
