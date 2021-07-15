package me.dusgn.focuscrud.controller;

import lombok.RequiredArgsConstructor;
import me.dusgn.focuscrud.domain.User;
import me.dusgn.focuscrud.dto.ArticleCreateRequestDto;
import me.dusgn.focuscrud.dto.ArticleReadOneResponseDto;
import me.dusgn.focuscrud.dto.ArticleReadResponseDto;
import me.dusgn.focuscrud.dto.ArticleUpdateRequestDto;
import me.dusgn.focuscrud.security.UserDetailsImpl;
import me.dusgn.focuscrud.service.ArticleService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ArticleController {

    private final ArticleService articleService;

    // 새 게시물 생성 요청
    @PostMapping("/api/articles")
    public Long createArticle(@RequestBody ArticleCreateRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        Long userId = userDetails.getUser().getId();
        return articleService.createArticle(requestDto, userId);
    }

    // 전체 게시물 조회 요청
    @GetMapping("api/articles")
    public List<ArticleReadResponseDto> readArticle(@RequestParam("page") int page,
                                                    @RequestParam("size") int size,
                                                    @RequestParam("sortBy") String sortBy,
                                                    @RequestParam("isAsc") boolean isAsc) {
        page = page - 1;
        return articleService.readArticle(page , size, sortBy, isAsc);
    }

    // 단일 게시물 조회 요청
    @GetMapping("/api/articles/{id}")
    public ArticleReadOneResponseDto readOneArticles(@PathVariable Long id) {
        return articleService.readOneArticles(id);
    }

    // 게시물 수정 요청
    @PutMapping("/api/articles/{id}")
    public Long updateArticles(@PathVariable Long id,
                               @RequestBody ArticleUpdateRequestDto requestDto,
                               @AuthenticationPrincipal UserDetailsImpl userDetails) {
        Long userId = userDetails.getUser().getId();
        return articleService.updateArticles(id, requestDto, userId).getId();
    }

    // 게시물 삭제 요청
    @DeleteMapping("/api/articles/{id}")
    public Long deleteArticles(@PathVariable Long id,
                               @AuthenticationPrincipal UserDetailsImpl userDetails) {
        Long userId = userDetails.getUser().getId();
        return articleService.deleteArticles(id, userId);
    }

}
