package me.dusgn.focuscrud.service;

import me.dusgn.focuscrud.domain.Article;
import me.dusgn.focuscrud.dto.ArticleCreateRequestDto;
import me.dusgn.focuscrud.dto.ArticleUpdateRequestDto;
import me.dusgn.focuscrud.repository.ArticleRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ArticleServiceTest {

    @Mock
    ArticleRepository articleRepository;

    @Test
    @DisplayName("uqdateArticles()를 통해 id에 맞는 제목과 내용이 수정되는지 확인")
    void updateArticles() {
        // given
        Long id = 100L;
        String userId = "hello";
        String title = "changedtitle";
        String content = "This is changed content";

        ArticleCreateRequestDto createrequestDto = new ArticleCreateRequestDto("hello","hello","hello");
        Article article = new Article(createrequestDto);

        ArticleService articleService = new ArticleService(articleRepository);
        when(articleRepository.findById(id))
                .thenReturn(Optional.of(article));

        ArticleUpdateRequestDto updateRequestDto = new ArticleUpdateRequestDto(title, content);

        // when
        Article result = articleService.updateArticles(id, updateRequestDto);
        // then
        assertEquals(title, article.getTitle());
        assertEquals(content, article.getContent());
    }
}