package me.dusgn.focuscrud.service;

import lombok.RequiredArgsConstructor;
import me.dusgn.focuscrud.domain.Article;
import me.dusgn.focuscrud.dto.ArticleCreateRequestDto;
import me.dusgn.focuscrud.dto.ArticleReadOneResponseDto;
import me.dusgn.focuscrud.dto.ArticleReadResponseDto;
import me.dusgn.focuscrud.dto.ArticleUpdateRequestDto;
import me.dusgn.focuscrud.repository.ArticleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ArticleService {

    private final ArticleRepository articleRepository;

    @Transactional
    public Long createArticle(ArticleCreateRequestDto requestDto) {
        Article article = new Article(requestDto);
        return articleRepository.save(article).getId();
    }
    // dto <-> Entity
    // ModelMapper 도 사용해보기기
   @Transactional(readOnly = true)
    public List<ArticleReadResponseDto> readArticle() {
        return articleRepository.findAll()
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
    public Long updateArticles(Long id, ArticleUpdateRequestDto requestDto) {
        Article article = articleRepository.findById(id).orElseThrow(
                () -> new NullPointerException("아이디가 존재하지 않습니다.")
        );
        article.update(requestDto);
        return article.getId();
    }

    @Transactional
    public Long deleteArticles(Long id) {
        Long deleteid = articleRepository.findById(id).orElseThrow(
                () -> new NullPointerException("아이디가 존재하지 않습니다.")
        ).getId();
        articleRepository.deleteById(deleteid);
        return deleteid;
    }
}
