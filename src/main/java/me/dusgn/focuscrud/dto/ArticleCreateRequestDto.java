package me.dusgn.focuscrud.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ArticleCreateRequestDto {
    private String userId;
    private String title;
    private String content;
    /*
    setter 필요할 것 같음
     */

}
