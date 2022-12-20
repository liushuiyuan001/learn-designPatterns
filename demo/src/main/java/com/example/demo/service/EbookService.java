package com.example.demo.service;

import com.example.demo.domain.Demo;
import com.example.demo.domain.DemoExample;
import com.example.demo.domain.Ebook;
import com.example.demo.domain.EbookExample;
import com.example.demo.mapper.DemoMapper;
import com.example.demo.mapper.EbookMapper;
import com.example.demo.req.EbookReq;
import com.example.demo.resp.EbookResp;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class EbookService {
	
	@Resource
	private EbookMapper ebookMapper;
	
	public List<EbookResp> list(EbookReq req) {
		EbookExample example = new EbookExample();
		EbookExample.Criteria criteria =  example.createCriteria();
		if(!ObjectUtils.isEmpty(req.getName())) {
			criteria.andNameLike("%" + req.getName() + "%");
		}
		List<Ebook> ebookList = ebookMapper.selectByExample(example);
		
		List<EbookResp> respList = new ArrayList<>();
		for(Ebook ebook : ebookList) {
			EbookResp ebookReq = new EbookResp();
			BeanUtils.copyProperties(ebook, ebookReq);
			respList.add(ebookReq);
		}
		return respList;
	}
}
