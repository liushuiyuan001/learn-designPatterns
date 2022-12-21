package com.example.demo.service;

import com.example.demo.domain.Ebook;
import com.example.demo.domain.EbookExample;
import com.example.demo.mapper.EbookMapper;
import com.example.demo.req.EbookReq;
import com.example.demo.req.PageReq;
import com.example.demo.resp.EbookResp;
import com.example.demo.resp.PageResp;
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
public class EbookService {
	
	private static final Logger LOG = LoggerFactory.getLogger(EbookService.class);
	
	@Resource
	private EbookMapper ebookMapper;
	
	public PageResp<EbookResp> list(EbookReq req) {
		EbookExample example = new EbookExample();
		EbookExample.Criteria criteria =  example.createCriteria();
		if(!ObjectUtils.isEmpty(req.getName())) {
			criteria.andNameLike("%" + req.getName() + "%");
		}
		PageHelper.startPage(req.getPage(), req.getSize());
		List<Ebook> ebookList = ebookMapper.selectByExample(example);
		
		PageInfo<Ebook> pageInfo = new PageInfo<>(ebookList);
		
		LOG.info("总行数：{}", pageInfo.getTotal());
		LOG.info("总页数：{}", pageInfo.getPages());
		
		pageInfo.getTotal();
		pageInfo.getPages();
		
		List<EbookResp> respList = new ArrayList<>();
		for(Ebook ebook : ebookList) {
			EbookResp ebookReq = new EbookResp();
			BeanUtils.copyProperties(ebook, ebookReq);
			respList.add(ebookReq);
		}
		
		PageResp<EbookResp> pageResp = new PageResp<>();
		pageResp.setList(respList);
		pageResp.setTotal(pageInfo.getTotal());
		return pageResp;
	}
}
