package com.example.demo.controller;

import com.example.demo.req.CategoryQueryReq;
import com.example.demo.req.CategorySaveReq;
import com.example.demo.resp.CategoryQueryResp;
import com.example.demo.resp.CommonResp;
import com.example.demo.resp.PageResp;
import com.example.demo.service.CategoryService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
	
	@Resource
	private CategoryService categoryService;
	
	@GetMapping("/list")
	public CommonResp<PageResp<CategoryQueryResp>> list(@Valid CategoryQueryReq req) {
		
		CommonResp<PageResp<CategoryQueryResp>> response = new CommonResp<>();
		PageResp<CategoryQueryResp> list = categoryService.list(req);
		response.setContent(list);
		
		return response;
	}
	
	@GetMapping("/all")
	public CommonResp<List<CategoryQueryResp>> all() {
		
		CommonResp<List<CategoryQueryResp>> response = new CommonResp<>();
		List<CategoryQueryResp> list = categoryService.all();
		response.setContent(list);
		
		return response;
	}
	
	@PostMapping("/save")
	public CommonResp<Object> save(@Valid @RequestBody CategorySaveReq req) {
		
		CommonResp<Object> response = new CommonResp<>();
		categoryService.save(req);
		return response;
	}
	
	@DeleteMapping("/delete/{id}")
	public CommonResp<Object> delete(@PathVariable Long id) {
		
		CommonResp<Object> response = new CommonResp<>();
		categoryService.delete(id);
		return response;
	}
	
}
