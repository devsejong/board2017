package net.chandol.study.board.article.model;

import lombok.Getter;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Entity
@Getter
public class Article {
    @Id @GeneratedValue
    private Long id;
    private String title;
    private String author;
    @Column(columnDefinition = "TEXT")
    private String content;
    @Temporal(TemporalType.TIMESTAMP)
    private OffsetDateTime created;

    protected Article() {
    }

    public Article(String title, String author, String content) {
        this.title = title;
        this.author = author;
        this.content = content;
        this.created = OffsetDateTime.now();
    }

    public void modifyArticle(String title, String author, String content){
        this.title = title;
        this.author = author;
        this.content = content;
    }
}
