package it.rdev.blog.api.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.rdev.blog.api.dao.ArticleDao;
import it.rdev.blog.api.dao.entity.Article;

@Service
public class BlogArticleService {

	@Autowired
	private ArticleDao articleDao;
	
	public Set<Article> find(){
		return articleDao.findAll();
	}
	
	
	
}
