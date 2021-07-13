package me.dusgn.focuscrud.dto;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ArticleUpdateRequestDto {
    private String title;
    private String content;
}
