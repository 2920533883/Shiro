package com.itzhang.controller;

import com.itzhang.entity.Article;
import com.itzhang.entity.LeaveMsg;
import com.itzhang.entity.MyQuery;
import com.itzhang.entity.R;
import com.itzhang.service.ArticleService;
import com.itzhang.service.LeaveMsgService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "文章模块")
@RestController
public class ArticleController {
    @Autowired
    ArticleService articleService;

    @ApiOperation(value = "获取所有文章", notes = "若要获取所有文章，则不需要传topic")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "topic", value = "题目关键词", paramType = "string"),
            @ApiImplicitParam(name = "pageNum", value = "当前页数", paramType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "每页信息数", paramType = "int")
    })
    @GetMapping("/articles")
    public R getArticles(@RequestParam(required = false) String topic, @RequestParam Integer pageNum, @RequestParam Integer pageSize) {
        List<Article> articles = articleService.getArticles(new MyQuery(topic, (pageNum - 1) * pageSize, pageSize));
        return new R(200, "获取成功！", articles);
    }

    @ApiOperation(value = "添加文章", notes = "需要权限 article:insert")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "topic", value = "题目", paramType = "string"),
            @ApiImplicitParam(name = "code", value = "代码", paramType = "string")
    })
    @PostMapping("/article")
    @RequiresPermissions("article:insert")
    public R addArticle(@RequestParam String topic, @RequestParam String code) {
        Article article = new Article(null, topic, code);
        articleService.insertArticle(article);
        return new R(200, "添加成功！", article);
    }

    @ApiOperation(value = "删除文章", notes = "需要权限 article:delete")
    @ApiImplicitParam(name = "article_id", value = "文章ID", paramType = "string")
    @RequiresPermissions("article:delete")
    @DeleteMapping("/article/{article_id}")
    public R deleteArticle(@PathVariable String article_id) {
        articleService.deleteArticleById(article_id);
        return new R(200, "删除成功！", null);
    }

    @ApiOperation(value = "修改文章", notes = "需要权限 article:update")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "article_id", value = "文章ID", paramType = "string"),
            @ApiImplicitParam(name = "topic", value = "题目", paramType = "string"),
            @ApiImplicitParam(name = "code", value = "代码", paramType = "string")

    })
    @RequiresPermissions("article:update")
    @PutMapping("/article/{article_id}")
    public R updateArticle(@PathVariable String article_id, @RequestParam String topic, @RequestParam String code) {
        Article article = new Article(article_id, topic, code);
        articleService.updateArticleById(article);
        return new R(200, "修改成功！", article);
    }

}
