package it.rdev.blog.api.dao;
import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import it.rdev.blog.api.dao.entity.Tag;

@Repository
public interface TagDao extends CrudRepository<Tag,Long> {
	
	@Query("Select t From Tag t")
	Set<Tag> findAll();
	
	Tag findByNometag(String nome);
}
