package com.itzhang.service;

import com.itzhang.entity.Article;
import com.itzhang.entity.MyQuery;

import java.util.List;

public interface ArticleService {
    List<Article> getArticles(MyQuery query);

    int insertArticle(Article article);

    int deleteArticleById(String id);

    int updateArticleById(Article article);
}
