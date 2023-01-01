package com.example.demo.controller;

import com.example.demo.req.DocQueryReq;
import com.example.demo.req.DocSaveReq;
import com.example.demo.resp.CommonResp;
import com.example.demo.resp.DocQueryResp;
import com.example.demo.resp.PageResp;
import com.example.demo.service.DocService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/doc")
public class DocController {
	
	@Resource
	private DocService docService;
	
	@GetMapping("/list")
	public CommonResp<PageResp<DocQueryResp>> list(@Valid DocQueryReq req) {
		
		CommonResp<PageResp<DocQueryResp>> response = new CommonResp<>();
		PageResp<DocQueryResp> list = docService.list(req);
		response.setContent(list);
		
		return response;
	}
	
	@GetMapping("/all/{ebookId}")
	public CommonResp<List<DocQueryResp>> all(@PathVariable Long ebookId) {
		
		CommonResp<List<DocQueryResp>> response = new CommonResp<>();
		List<DocQueryResp> list = docService.all(ebookId);
		response.setContent(list);
		
		return response;
	}
	
	@GetMapping("/queryContent/{id}")
	public CommonResp<String> queryContent(@PathVariable Long id) {
		
		CommonResp<String> response = new CommonResp<>();
		String content = docService.queryContent(id);
		response.setContent(content);
		
		return response;
	}
	
	@GetMapping("/vote/{id}")
	public CommonResp<String> vote(@PathVariable Long id) {
		
		CommonResp<String> response = new CommonResp<>();
		docService.vote(id);
		
		return response;
	}
	
	
	@PostMapping("/save")
	public CommonResp<Object> save(@Valid @RequestBody DocSaveReq req) {
		
		CommonResp<Object> response = new CommonResp<>();
		docService.save(req);
		return response;
	}
	
	@DeleteMapping("/delete/{id}")
	public CommonResp<Object> delete(@PathVariable String id) {
		
		CommonResp<Object> response = new CommonResp<>();
		docService.delete(id);
		return response;
	}
	
}
