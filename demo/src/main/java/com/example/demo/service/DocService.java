package com.example.demo.service;

import com.example.demo.domain.Doc;
import com.example.demo.domain.DocExample;
import com.example.demo.mapper.DocMapper;
import com.example.demo.req.DocQueryReq;
import com.example.demo.req.DocSaveReq;
import com.example.demo.resp.DocQueryResp;
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
public class DocService {
	
	private static final Logger LOG = LoggerFactory.getLogger(DocService.class);
	
	@Resource
	private DocMapper docMapper;
	
	@Resource
	private SnowFlake snowFlake;
	
	public PageResp<DocQueryResp> list(DocQueryReq req) {
		DocExample example = new DocExample();
		example.setOrderByClause("sort asc");
		
		PageHelper.startPage(req.getPage(), req.getSize());
		List<Doc> docList = docMapper.selectByExample(example);
		
		PageInfo<Doc> pageInfo = new PageInfo<>(docList);
		
		LOG.info("总行数：{}", pageInfo.getTotal());
		LOG.info("总页数：{}", pageInfo.getPages());
		
		
		List<DocQueryResp> respList = new ArrayList<>();
		for(Doc doc : docList) {
			DocQueryResp docReq = new DocQueryResp();
			BeanUtils.copyProperties(doc, docReq);
			respList.add(docReq);
		}
		
		PageResp<DocQueryResp> pageResp = new PageResp<>();
		pageResp.setList(respList);
		pageResp.setTotal(pageInfo.getTotal());
		return pageResp;
	}
	
	public List<DocQueryResp> all() {
		DocExample example = new DocExample();
		example.setOrderByClause("sort asc");
		List<Doc> docList = docMapper.selectByExample(example);
		
		List<DocQueryResp> respList = new ArrayList<>();
		for(Doc doc  : docList) {
			DocQueryResp docReq = new DocQueryResp();
			BeanUtils.copyProperties(doc, docReq);
			respList.add(docReq);
		}
		
	
		return respList;
	}
	
	public void save(DocSaveReq req) {
		Doc doc = new Doc();
		BeanUtils.copyProperties(req, doc);
		if(ObjectUtils.isEmpty(req.getId())) {
			// 新增
			doc.setId(snowFlake.nextId());
			docMapper.insert(doc);
		} else {
			docMapper.updateByPrimaryKey(doc);
		}
	}
	
	public void delete(Long id) {
		docMapper.deleteByPrimaryKey(id);
	}
}
