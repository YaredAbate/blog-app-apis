package com.codewithyad.blog.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codewithyad.blog.entities.Catagory;
import com.codewithyad.blog.entities.User;
import com.codewithyad.blog.execeptions.ResourceNotFoundExeception;
import com.codewithyad.blog.payloads.CatagoryDto;
import com.codewithyad.blog.payloads.UserDto;
import com.codewithyad.blog.repositores.CatagoryRepositories;
import com.codewithyad.blog.services.CatagoryService;
@Service
public class CatagoryServicImpl implements CatagoryService {
	@Autowired
	private CatagoryRepositories catagoryRepo;
	@Autowired
	private ModelMapper modelMapper;
	@Override
	public CatagoryDto createCatagory(CatagoryDto catagoryDto) {
		Catagory catagory=this.dtoToCatagory(catagoryDto);
		Catagory savedCatagory=this.catagoryRepo.save(catagory);
		return this.catagoryToDto(savedCatagory);
	}

	@Override
	public CatagoryDto updateCatagory(CatagoryDto catagoryDto,Integer catagoryId) {
		Catagory catagory=this.catagoryRepo.findById(catagoryId).orElseThrow(()->
		new ResourceNotFoundExeception("user","Id",catagoryId));
		catagory.setCatagoryTitle(catagoryDto.getCatagoryTitle());
		catagory.setCatagoryDescription(catagoryDto.getCatagoryDescription());
		Catagory savedCatagory=this.catagoryRepo.save(catagory);
		CatagoryDto dto=this.catagoryToDto(savedCatagory);
		return dto;
	}

	@Override
	public CatagoryDto getCatagoryById(Integer catagoryId) {
		Catagory catagory=this.catagoryRepo.findById(catagoryId).orElseThrow(()->
		new ResourceNotFoundExeception("user","Id",catagoryId));
		return this.catagoryToDto(catagory);
	}

	@Override
	public List<CatagoryDto> getAllCatagory() {
		List<Catagory> catagories=this.catagoryRepo.findAll();
		List<CatagoryDto> catagoryDtos=catagories.stream().map(catagory->
		this.catagoryToDto(catagory)).collect(Collectors.toList());
		return catagoryDtos;
	}

	@Override
	public void deleteCatagory(Integer catagoryId) {
		Catagory catagory=this.catagoryRepo.findById(catagoryId).orElseThrow(()->
		new ResourceNotFoundExeception("user","Id",catagoryId));
		this.catagoryRepo.delete(catagory);
}
	private Catagory dtoToCatagory(CatagoryDto catagoryDto) {
		Catagory catagory=this.modelMapper.map(catagoryDto,Catagory.class);
		return catagory;
	}
	private CatagoryDto catagoryToDto(Catagory catagory) {
		CatagoryDto catagoryDto=this.modelMapper.map(catagory,CatagoryDto.class);
		return catagoryDto;
	}

}
