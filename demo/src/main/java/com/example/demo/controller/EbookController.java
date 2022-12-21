package com.example.demo.controller;

import com.example.demo.domain.Demo;
import com.example.demo.domain.Ebook;
import com.example.demo.mapper.EbookMapper;
import com.example.demo.req.EbookReq;
import com.example.demo.req.PageReq;
import com.example.demo.resp.CommonResp;
import com.example.demo.resp.EbookResp;
import com.example.demo.resp.PageResp;
import com.example.demo.service.DemoService;
import com.example.demo.service.EbookService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/ebook")
public class EbookController {
	
	@Resource
	private EbookService ebookService;
	
	@GetMapping("/list")
	public CommonResp<PageResp<EbookResp>> list(EbookReq req) {
		
		CommonResp<PageResp<EbookResp>> response = new CommonResp<>();
		PageResp<EbookResp> list = ebookService.list(req);
		response.setContent(list);
		
		return response;
	}
}
