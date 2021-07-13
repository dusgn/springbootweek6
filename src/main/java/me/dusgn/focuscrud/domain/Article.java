package me.dusgn.focuscrud.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import me.dusgn.focuscrud.dto.ArticleCreateRequestDto;
import me.dusgn.focuscrud.dto.ArticleUpdateRequestDto;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Article extends Timestamped{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    public Article(ArticleCreateRequestDto requestDto) {
        this.userId = requestDto.getUserId();
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
    }

    public void update(ArticleUpdateRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
    }
}
