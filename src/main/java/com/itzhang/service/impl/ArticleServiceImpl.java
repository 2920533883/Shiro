package com.itzhang.service.impl;

import com.itzhang.entity.Article;
import com.itzhang.entity.MyQuery;
import com.itzhang.mapper.ArticleMapper;
import com.itzhang.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Transactional(isolation = Isolation.READ_UNCOMMITTED)
@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    ArticleMapper mapper;

    @Override
    public List<Article> getArticles(MyQuery query) {
        return mapper.getArticles(query);
    }

    @Override
    public int insertArticle(Article article) {
        return mapper.insertArticle(article);
    }

    @Override
    public int deleteArticleById(String id) {
        return mapper.deleteArticleById(id);
    }

    @Override
    public int updateArticleById(Article article) {
        return mapper.updateArticleById(article);
    }
}
