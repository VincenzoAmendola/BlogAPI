package it.rdev.blog.api.dao;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import it.rdev.blog.api.dao.entity.Article;

@Repository
public interface ArticleDao extends CrudRepository<Article, Long> {
		
	
		Set<Article> findByAutore(String username);
		
		
		Set<Article> findByCategoria(String categoria);
		
		
		
		@Query("SELECT a From Article a Where a.titolo like :titolo OR a.sottotitolo like :titolo OR a.testo like :titolo")
		Set<Article> findByKeywords(@Param("titolo") String ricerca);
		
		
		
		@Query("SELECT a from Article a")
		Set<Article> findAll();
		
		Set<Article> findByTags(String s);
		
		Optional<Article> findById(Long id);
		
		Set<Article> findByStato(Boolean b);
}
