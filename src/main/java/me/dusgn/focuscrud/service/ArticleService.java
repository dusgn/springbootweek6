package me.dusgn.focuscrud.service;

import lombok.RequiredArgsConstructor;
import me.dusgn.focuscrud.domain.Article;
import me.dusgn.focuscrud.dto.ArticleCreateRequestDto;
import me.dusgn.focuscrud.dto.ArticleReadOneResponseDto;
import me.dusgn.focuscrud.dto.ArticleReadResponseDto;
import me.dusgn.focuscrud.dto.ArticleUpdateRequestDto;
import me.dusgn.focuscrud.repository.ArticleRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ArticleService {

    private final ArticleRepository articleRepository;

    @Transactional
    public Long createArticle(ArticleCreateRequestDto requestDto, Long userId) {
        Article article = new Article(requestDto, userId);
        return articleRepository.save(article).getId();
    }

    // dto <-> Entity
    // ModelMapper 도 사용해보기기
    @Transactional(readOnly = true)
    public List<ArticleReadResponseDto> readArticle(int page, int size, String sortBy, boolean isAsc) {
        Sort.Direction direction = isAsc ? Sort.Direction.ASC : Sort.Direction.DESC;
        Sort sort = Sort.by(direction, sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);
        return articleRepository.findAll(pageable)
                .stream()
                .map(ArticleReadResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ArticleReadOneResponseDto readOneArticles(Long id) {
        Article article = articleRepository.findById(id).orElseThrow(
                () -> new NullPointerException("아이디가 존재하지 않습니다.")
        );
        ArticleReadOneResponseDto responseDto = new ArticleReadOneResponseDto(article);
        return responseDto;
    }

    @Transactional
    public Article updateArticles(Long id, ArticleUpdateRequestDto requestDto, Long userId) {
        Article article = articleRepository.findById(id).orElseThrow(
                () -> new NullPointerException("해당 게시글이 존재하지 않습니다.")
        );

        articleRepository.findById(userId).orElseThrow(
                () -> new NullPointerException("게시글 수정 권한이 없습니다.")
        );

        article.update(requestDto);
        return article;
    }

    @Transactional
    public Long deleteArticles(Long id, Long userId) {
        Long deleteid = articleRepository.findById(id).orElseThrow(
                () -> new NullPointerException("게시글이 존재하지 않습니다.")
        ).getId();
        articleRepository.findById(userId).orElseThrow(
                () -> new NullPointerException("게시글 삭제 권한이 없습니다.")
        );
        articleRepository.deleteById(deleteid);
        return deleteid;
    }
}
