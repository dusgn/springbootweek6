package me.dusgn.focuscrud.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ArticleCreateRequestDto {
    private String title;
    private String content;
    /*
    setter 필요할 것 같음 아니었음... NoArgsConstructort와 AllArgsConstructor
     */

}
