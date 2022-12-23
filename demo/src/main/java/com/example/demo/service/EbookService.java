package com.example.demo.service;

import com.example.demo.domain.Ebook;
import com.example.demo.domain.EbookExample;
import com.example.demo.mapper.EbookMapper;
import com.example.demo.req.EbookQueryReq;
import com.example.demo.req.EbookSaveReq;
import com.example.demo.resp.EbookQueryResp;
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
public class EbookService {
	
	private static final Logger LOG = LoggerFactory.getLogger(EbookService.class);
	
	@Resource
	private EbookMapper ebookMapper;
	
	@Resource
	private SnowFlake snowFlake;
	
	public PageResp<EbookQueryResp> list(EbookQueryReq req) {
		EbookExample example = new EbookExample();
		EbookExample.Criteria criteria =  example.createCriteria();
		if(!ObjectUtils.isEmpty(req.getName())) {
			criteria.andNameLike("%" + req.getName() + "%");
		}
		if(!ObjectUtils.isEmpty(req.getCategory2Id())) {
			criteria.andCategory2IdEqualTo(req.getCategory2Id());
		}
		PageHelper.startPage(req.getPage(), req.getSize());
		List<Ebook> ebookList = ebookMapper.selectByExample(example);
		
		PageInfo<Ebook> pageInfo = new PageInfo<>(ebookList);
		
		LOG.info("总行数：{}", pageInfo.getTotal());
		LOG.info("总页数：{}", pageInfo.getPages());
		
		
		List<EbookQueryResp> respList = new ArrayList<>();
		for(Ebook ebook : ebookList) {
			EbookQueryResp ebookReq = new EbookQueryResp();
			BeanUtils.copyProperties(ebook, ebookReq);
			respList.add(ebookReq);
		}
		
		PageResp<EbookQueryResp> pageResp = new PageResp<>();
		pageResp.setList(respList);
		pageResp.setTotal(pageInfo.getTotal());
		return pageResp;
	}
	
	public void save(EbookSaveReq req) {
		Ebook ebook = new Ebook();
		BeanUtils.copyProperties(req, ebook);
		if(ObjectUtils.isEmpty(req.getId())) {
			// 新增
			ebook.setId(snowFlake.nextId());
			ebookMapper.insert(ebook);
		} else {
			ebookMapper.updateByPrimaryKey(ebook);
		}
	}
	
	public void delete(Long id) {
		ebookMapper.deleteByPrimaryKey(id);
	}
}
