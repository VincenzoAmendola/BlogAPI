package it.rdev.blog.api.dao;
import java.util.Optional;
import java.util.Set;

import javax.persistence.criteria.Root;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import it.rdev.blog.api.dao.entity.Article;

@Repository
public interface ArticleDao extends PagingAndSortingRepository<Article, Long> {
		
	
		Set<Article> findByAutore(String username);
		
		
		Set<Article> findByCategoria(String categoria);
		
		
		
		@Query("SELECT a From Article a Where a.titolo like :titolo OR a.sottotitolo like :titolo OR a.testo like :titolo")
		Set<Article> findByKeywords(@Param("titolo") String ricerca);
		
		// Query used to find a page of articles to an authorized user
		@Query("SELECT a from Article a Where a.stato = true OR a.autore = :autore")
		Page<Article> findAuthorized(@Param("autore") Long id, Pageable pagea);
		
		@Query("SELECT a from Article a")
		Set<Article> findAll();
		
		Set<Article> findByTags(String s);
		
		Optional<Article> findById(Long id);
		
		Set<Article> findByStato(Boolean b);
		
		Page<Article> findAll(Pageable pageable);
}
