package com.codewithyad.blog.services;

import java.util.List;

import com.codewithyad.blog.payloads.CatagoryDto;

public interface CatagoryService {
	CatagoryDto createCatagory(CatagoryDto catagoryDto);
	CatagoryDto updateCatagory(CatagoryDto catagoryDto,Integer catagoryId);
	CatagoryDto getCatagoryById(Integer catagoryId);
	List<CatagoryDto> getAllCatagory();
	void deleteCatagory(Integer catagoryId);
}
