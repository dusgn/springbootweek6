package me.dusgn.focuscrud.repository;

import me.dusgn.focuscrud.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {
}
