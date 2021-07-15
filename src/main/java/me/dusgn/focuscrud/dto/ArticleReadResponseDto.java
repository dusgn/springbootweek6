package me.dusgn.focuscrud.dto;

import lombok.Getter;
import me.dusgn.focuscrud.domain.Article;

@Getter
public class ArticleReadResponseDto {
    private Long id;
    private Long userId;
    private String title;

    public ArticleReadResponseDto(Article article) {
        this.id = article.getId();
        this.userId = article.getUserId();
        this.title = article.getTitle();
    }
}
