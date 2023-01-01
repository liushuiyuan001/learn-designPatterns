package com.example.demo.mapper;

import org.apache.ibatis.annotations.Param;

public interface MyDocMapper {
	
	public void updateViewCount(@Param("id") Long id);
	
	public void updateVoteCount(@Param("id") Long id);
	
	public void updateEbookInfo();
}
