package com.itzhang.mapper;

import com.itzhang.entity.Article;
import com.itzhang.entity.MyQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ArticleMapper {
    List<Article> getArticles(MyQuery query);

    int insertArticle(Article article);

    int deleteArticleById(String id);

    int updateArticleById(Article article);
}
