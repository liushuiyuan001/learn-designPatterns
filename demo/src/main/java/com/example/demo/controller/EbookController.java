package com.example.demo.controller;

import com.example.demo.req.EbookQueryReq;
import com.example.demo.req.EbookSaveReq;
import com.example.demo.resp.CommonResp;
import com.example.demo.resp.EbookQueryResp;
import com.example.demo.resp.PageResp;
import com.example.demo.service.EbookService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@RequestMapping("/ebook")
public class EbookController {
	
	@Resource
	private EbookService ebookService;
	
	@GetMapping("/list")
	public CommonResp<PageResp<EbookQueryResp>> list(@Valid EbookQueryReq req) {
		
		CommonResp<PageResp<EbookQueryResp>> response = new CommonResp<>();
		PageResp<EbookQueryResp> list = ebookService.list(req);
		response.setContent(list);
		
		return response;
	}
	
	@PostMapping("/save")
	public CommonResp<Object> save(@Valid @RequestBody EbookSaveReq req) {
		
		CommonResp<Object> response = new CommonResp<>();
		ebookService.save(req);
		return response;
	}
	
	@DeleteMapping("/delete/{id}")
	public CommonResp<Object> delete(@PathVariable Long id) {
		
		CommonResp<Object> response = new CommonResp<>();
		ebookService.delete(id);
		return response;
	}
	
}
