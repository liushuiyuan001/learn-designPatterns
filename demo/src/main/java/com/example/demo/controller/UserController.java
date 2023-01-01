package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.req.UserLoginReq;
import com.example.demo.req.UserQueryReq;
import com.example.demo.req.UserSaveReq;
import com.example.demo.resp.CommonResp;
import com.example.demo.resp.PageResp;
import com.example.demo.resp.UserLoginResp;
import com.example.demo.resp.UserQueryResp;
import com.example.demo.service.UserService;
import com.example.demo.util.SnowFlake;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Resource
	private UserService userService;
	
	@Resource
	private RedisTemplate<Long, UserLoginResp> redisTemplate;
	
	
	@Resource
	private SnowFlake snowFlake;
	
	@GetMapping("/list")
	public CommonResp<PageResp<UserQueryResp>> list(@Valid UserQueryReq req) {
		
		CommonResp<PageResp<UserQueryResp>> response = new CommonResp<>();
		PageResp<UserQueryResp> list = userService.list(req);
		response.setContent(list);
		
		return response;
	}
	
	@PostMapping("/save")
	public CommonResp<Object> save(@Valid @RequestBody UserSaveReq req) {
		
		CommonResp<Object> response = new CommonResp<>();
		userService.save(req);
		return response;
	}
	
	@DeleteMapping("/delete/{id}")
	public CommonResp<Object> delete(@PathVariable Long id) {
		
		CommonResp<Object> response = new CommonResp<>();
		userService.delete(id);
		return response;
	}
	
	@PostMapping("/login")
	public CommonResp<Object> login(@Valid @RequestBody UserLoginReq req) {
		
		CommonResp<Object> response = new CommonResp<>();
		UserLoginResp user = userService.login(req);
		Long token = snowFlake.nextId();
		user.setToken(token.toString());
		redisTemplate.opsForValue().set(token, user,  3600 * 24, TimeUnit.SECONDS);
		
		response.setContent(user);
		return response;
	}
	
	@GetMapping("/logout/{token}")
	public CommonResp<Object> delete(@PathVariable String token) {
		
		CommonResp<Object> response = new CommonResp<>();
		redisTemplate.delete(Long.valueOf(token));
		return response;
	}
}
