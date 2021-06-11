package it.rdev.blog.api.dao;

import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.rdev.blog.api.dao.entity.Category;

@Repository
public interface CategoryDao extends CrudRepository<Category,Long> {
	
	@Query("Select c From Category c")
	Set<Category> findAll();
	
}
