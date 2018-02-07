package com.zhang.colas.blog.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhang.colas.blog.entity.BlogArticle;
import com.zhang.colas.blog.mapper.BlogArticleMapper;
import com.zhang.colas.blog.service.ArticleService;
import com.zhang.colas.common.SimpleResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private BlogArticleMapper articleMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return articleMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(BlogArticle record) {
        return articleMapper.insert(record);
    }

    @Override
    public int insertSelective(BlogArticle record) {
        record.setCreateTime(new Date());
        return articleMapper.insertSelective(record);
    }

    @Override
    public BlogArticle selectByPrimaryKey(Integer id) {
        return articleMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(BlogArticle record) {
        return articleMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKeyWithBLOBs(BlogArticle record) {
        return articleMapper.updateByPrimaryKeyWithBLOBs(record);
    }

    @Override
    public int updateByPrimaryKey(BlogArticle record) {
        return articleMapper.updateByPrimaryKey(record);
    }

    @Override
    public SimpleResult save(BlogArticle blogArticle) {

        if (blogArticle.getId() != null) {
            articleMapper.updateByPrimaryKeySelective(blogArticle);
        } else {
            blogArticle.setCreateBy(-1);
            blogArticle.setPublishTime(new Date());
            //todo type和status 以后使用枚举
            blogArticle.setArticleType(1);
            blogArticle.setArticleStatus(1);
            articleMapper.insertSelective(blogArticle);
        }
        return SimpleResult.responseOk("ok");
    }

    @Override
    public PageInfo<BlogArticle> selectArticleListPage(int pageNum, int pageSize) {
        PageInfo<BlogArticle> pageInfo = PageHelper
                .startPage(pageNum,pageSize)
                .doSelectPageInfo(()-> articleMapper.queryList());
        return pageInfo;
    }
}
