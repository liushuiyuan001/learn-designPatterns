package com.example.demo.service;

import com.example.demo.domain.Ebook;
import com.example.demo.domain.EbookExample;
import com.example.demo.domain.User;
import com.example.demo.domain.UserExample;
import com.example.demo.mapper.EbookMapper;
import com.example.demo.mapper.UserMapper;
import com.example.demo.req.EbookQueryReq;
import com.example.demo.req.EbookSaveReq;
import com.example.demo.req.UserQueryReq;
import com.example.demo.req.UserSaveReq;
import com.example.demo.resp.EbookQueryResp;
import com.example.demo.resp.PageResp;
import com.example.demo.resp.UserQueryResp;
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
public class UserService {
	
	private static final Logger LOG = LoggerFactory.getLogger(UserService.class);
	
	@Resource
	private UserMapper userMapper;
	
	@Resource
	private SnowFlake snowFlake;
	
	public PageResp<UserQueryResp> list(UserQueryReq req) {
		UserExample example = new UserExample();
		UserExample.Criteria criteria =  example.createCriteria();
		if(!ObjectUtils.isEmpty(req.getLoginName())) {
			criteria.andLoginNameLike("%" + req.getLoginName() + "%");
		}

		PageHelper.startPage(req.getPage(), req.getSize());
		List<User> userList = userMapper.selectByExample(example);
		
		PageInfo<User> pageInfo = new PageInfo<>();
		
		LOG.info("总行数：{}", pageInfo.getTotal());
		LOG.info("总页数：{}", pageInfo.getPages());
		
		
		List<UserQueryResp> respList = new ArrayList<>();
		for(User user : userList) {
			UserQueryResp userResq = new UserQueryResp();
			BeanUtils.copyProperties(user, userResq);
			respList.add(userResq);
		}
		
		PageResp<UserQueryResp> pageResp = new PageResp<>();
		pageResp.setList(respList);
		pageResp.setTotal(pageInfo.getTotal());
		return pageResp;
	}
	
	public void save(UserSaveReq req) {
		User user = new User();
		BeanUtils.copyProperties(req, user);
		if(ObjectUtils.isEmpty(req.getId())) {
			// 新增
			user.setId(snowFlake.nextId());
			userMapper.insert(user);
		} else {
			userMapper.updateByPrimaryKey(user);
		}
	}
	
	public void delete(Long id) {
		userMapper.deleteByPrimaryKey(id);
	}
}
