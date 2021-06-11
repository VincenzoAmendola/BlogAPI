package it.rdev.blog.api.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.rdev.blog.api.controller.dto.CategoryDTO;
import it.rdev.blog.api.dao.CategoryDao;
import it.rdev.blog.api.dao.entity.Category;

@Service
public class BlogCategoriesService {

	@Autowired
	private CategoryDao categoryDao;
	
	public Set<CategoryDTO> getAll(){
		Set<Category> category =  categoryDao.findAll();
		Set<CategoryDTO> categoriesDTO = new HashSet<>();
		for(Category c : category) {
			CategoryDTO cDTO = new CategoryDTO();
			cDTO.setNomeCat(c.getNomeCat());
			categoriesDTO.add(cDTO);
		}
		return categoriesDTO;
		}
}
