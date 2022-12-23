package com.example.demo.service;

import com.example.demo.domain.Category;
import com.example.demo.domain.CategoryExample;
import com.example.demo.mapper.CategoryMapper;
import com.example.demo.req.CategoryQueryReq;
import com.example.demo.req.CategorySaveReq;
import com.example.demo.resp.CategoryQueryResp;
import com.example.demo.resp.PageResp;
import com.example.demo.util.SnowFlake;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


@Service
public class CategoryService {
	
	private static final Logger LOG = LoggerFactory.getLogger(CategoryService.class);
	
	@Resource
	private CategoryMapper categoryMapper;
	
	@Resource
	private SnowFlake snowFlake;
	
	public PageResp<CategoryQueryResp> list(CategoryQueryReq req) {
		CategoryExample example = new CategoryExample();
		example.setOrderByClause("sort asc");
		
		PageHelper.startPage(req.getPage(), req.getSize());
		List<Category> categoryList = categoryMapper.selectByExample(example);
		
		PageInfo<Category> pageInfo = new PageInfo<>(categoryList);
		
		LOG.info("总行数：{}", pageInfo.getTotal());
		LOG.info("总页数：{}", pageInfo.getPages());
		
		
		List<CategoryQueryResp> respList = new ArrayList<>();
		for(Category category : categoryList) {
			CategoryQueryResp categoryReq = new CategoryQueryResp();
			BeanUtils.copyProperties(category, categoryReq);
			respList.add(categoryReq);
		}
		
		PageResp<CategoryQueryResp> pageResp = new PageResp<>();
		pageResp.setList(respList);
		pageResp.setTotal(pageInfo.getTotal());
		return pageResp;
	}
	
	public List<CategoryQueryResp> all() {
		CategoryExample example = new CategoryExample();
		example.setOrderByClause("sort asc");
		List<Category> categoryList = categoryMapper.selectByExample(example);
		
		List<CategoryQueryResp> respList = new ArrayList<>();
		for(Category category : categoryList) {
			CategoryQueryResp categoryReq = new CategoryQueryResp();
			BeanUtils.copyProperties(category, categoryReq);
			respList.add(categoryReq);
		}
		
	
		return respList;
	}
	
	public void save(CategorySaveReq req) {
		Category category = new Category();
		BeanUtils.copyProperties(req, category);
		if(ObjectUtils.isEmpty(req.getId())) {
			// 新增
			category.setId(snowFlake.nextId());
			categoryMapper.insert(category);
		} else {
			categoryMapper.updateByPrimaryKey(category);
		}
	}
	
	public void delete(Long id) {
		categoryMapper.deleteByPrimaryKey(id);
	}
}
