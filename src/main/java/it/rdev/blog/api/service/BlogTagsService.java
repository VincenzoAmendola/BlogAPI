package it.rdev.blog.api.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.rdev.blog.api.controller.dto.TagDTO;
import it.rdev.blog.api.dao.TagDao;
import it.rdev.blog.api.dao.entity.Tag;
;

@Service
public class BlogTagsService {
	@Autowired
	private TagDao tagDao;
	
	public Set<TagDTO> getAll(){
		Set<Tag> tag =  tagDao.findAll();
		Set<TagDTO> tagsDTO = new HashSet<>();
		for(Tag t : tag) {
			TagDTO tDTO = new TagDTO();
			tDTO.setNomeTag(t.getNometag());;
			tagsDTO.add(tDTO);
		}
		return tagsDTO;
		}
}
