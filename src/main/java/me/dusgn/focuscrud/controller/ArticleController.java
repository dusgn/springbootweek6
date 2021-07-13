package me.dusgn.focuscrud.controller;

import lombok.RequiredArgsConstructor;
import me.dusgn.focuscrud.domain.Article;
import me.dusgn.focuscrud.dto.ArticleCreateRequestDto;
import me.dusgn.focuscrud.dto.ArticleReadOneResponseDto;
import me.dusgn.focuscrud.dto.ArticleReadResponseDto;
import me.dusgn.focuscrud.dto.ArticleUpdateRequestDto;
import me.dusgn.focuscrud.service.ArticleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ArticleController {

    private final ArticleService articleService;

    // 새 게시물 생성 요청
    @PostMapping("/api/articles")
    public Long createArticle(@RequestBody ArticleCreateRequestDto requestDto) {
        return articleService.createArticle(requestDto);
    }

    // 전체 게시물 조회 요청
    @GetMapping("api/articles")
    public List<ArticleReadResponseDto> readArticle() {
        return articleService.readArticle();
    }

    // 단일 게시물 조회 요청
    @GetMapping("/api/articles/{id}")
    public ArticleReadOneResponseDto readOneArticles(@PathVariable Long id) {
        return articleService.readOneArticles(id);
    }

    // 게시물 수정 요청
    @PutMapping("/api/articles/{id}")
    public Long updateArticles(@PathVariable Long id, @RequestBody ArticleUpdateRequestDto requestDto) {
        return articleService.updateArticles(id, requestDto).getId();
    }

    // 게시물 삭제 요청
    @DeleteMapping("/api/articles/{id}")
    public Long deleteArticles(@PathVariable Long id) {
        return articleService.deleteArticles(id);
    }

}
