package com.example.demo.controller;

import com.example.demo.req.UserQueryReq;
import com.example.demo.req.UserSaveReq;
import com.example.demo.resp.CommonResp;
import com.example.demo.resp.PageResp;
import com.example.demo.resp.UserQueryResp;
import com.example.demo.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Resource
	private UserService userService;
	
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
	
}
