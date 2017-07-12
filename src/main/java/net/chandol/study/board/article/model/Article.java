package net.chandol.study.board.article.model;

import lombok.Getter;
import net.chandol.study.board.user.User;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

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
    @OneToMany(mappedBy = "article", fetch = FetchType.LAZY)
    private List<Comment> comments = new ArrayList<>();
    @Column(columnDefinition = "TEXT")
    private String body;
    @CreatedDate
    private OffsetDateTime created;
    @LastModifiedDate
    private OffsetDateTime modified;

    protected Article() {
    }

    public Article(String title, String body, User user) {
        this.title = title;
        this.body = body;
        this.user = user;
    }

    public void modifyArticle(String title, String body, User user) {
        this.title = title;
        this.body = body;
        this.user = user;
    }

    void addComment(Comment comment) {
        comments.add(comment);
    }
}
