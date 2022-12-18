package com.example.demo.controller;

import com.example.demo.domain.Test;
import com.example.demo.service.TestService;
import com.sun.corba.se.spi.resolver.Resolver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class TestController {
	
	@Value("${test.hello:TEST}")
	private String testHello;
	@Resource
	private TestService testService;
	/**
	 * GET POST PUT DELETE
	 * /user?id=1
	 * /user/1
	 * @return str
	 */
	@GetMapping("/hello")
	public String hello() {
		return "hello World: Post " + testHello;
	}
	
	@GetMapping("/test/list")
	public List<Test> list() {
		System.out.println(testService);
		return testService.list();
	}
}
