package com.example.demo.controller;

import com.example.demo.domain.Demo;
import com.example.demo.domain.Ebook;
import com.example.demo.mapper.EbookMapper;
import com.example.demo.req.EbookReq;
import com.example.demo.resp.CommonResp;
import com.example.demo.resp.EbookResp;
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
	public CommonResp list(EbookReq req) {
		
		CommonResp<List<EbookResp>> response = new CommonResp<>();
		
		response.setContent(ebookService.list(req));
		
		return response;
	}
}
