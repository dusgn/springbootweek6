package me.dusgn.focuscrud.dto;

import lombok.Getter;
import me.dusgn.focuscrud.domain.Article;

@Getter
public class ArticleReadOneResponseDto {
    private String userId;
    private String title;
    private String content;

    public ArticleReadOneResponseDto(Article article) {
        this.userId = article.getUserId();
        this.title = article.getTitle();
        this.content = article.getContent();
    }
}
