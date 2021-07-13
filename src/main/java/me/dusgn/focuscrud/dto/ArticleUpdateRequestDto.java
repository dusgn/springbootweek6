package me.dusgn.focuscrud.dto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ArticleUpdateRequestDto {
    private String title;
    private String content;
}
