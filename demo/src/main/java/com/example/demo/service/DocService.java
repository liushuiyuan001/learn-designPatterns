package com.example.demo.service;

import com.example.demo.domain.Content;
import com.example.demo.domain.Doc;
import com.example.demo.domain.DocExample;
import com.example.demo.mapper.ContentMapper;
import com.example.demo.mapper.DocMapper;
import com.example.demo.mapper.MyDocMapper;
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
	private MyDocMapper myDocMapper;
	
	@Resource
	private ContentMapper contentMapper;
	
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
	
	public List<DocQueryResp> all(Long ebookId) {
		DocExample example = new DocExample();
		DocExample.Criteria criteria = example.createCriteria();
		criteria.andEbookIdEqualTo(ebookId);
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
		Content content = new Content();
		BeanUtils.copyProperties(req, doc);
		BeanUtils.copyProperties(req, content);
		if(ObjectUtils.isEmpty(req.getId())) {
			// 新增
			long uid = snowFlake.nextId();
			
			doc.setId(uid);
			content.setId(uid);
			
			doc.setViewCount(0);
			doc.setVoteCount(0);
			docMapper.insert(doc);
			contentMapper.insert(content);
		} else {
			docMapper.updateByPrimaryKey(doc);
			int count = contentMapper.updateByPrimaryKeyWithBLOBs(content);
			if(count == 0) {
				contentMapper.insert(content);
			}
		}
	}
	
	public void delete(String ids) {
		DocExample example = new DocExample();
		DocExample.Criteria criteria = example.createCriteria();
		String[] idList = ids.split(",");
		List<Long> idNumList = new ArrayList<>();
		for(String id : idList) {
			idNumList.add(Long.parseLong(id));
		}
		criteria.andIdIn(idNumList);
		docMapper.deleteByExample(example);
	}
	
	public String queryContent(Long id) {
		
		Content content = contentMapper.selectByPrimaryKey(id);
		myDocMapper.updateViewCount(id);
		if(ObjectUtils.isEmpty(content)) {
			return "";
		}
		return content.getContent();
	}
	
	public void vote(Long id) {
		myDocMapper.updateVoteCount(id);
	}
	
	public void updateEbookInfo() {
		myDocMapper.updateEbookInfo();
	}
}
