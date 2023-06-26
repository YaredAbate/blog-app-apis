package com.codewithyad.blog.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codewithyad.blog.payloads.ApiResponse;
import com.codewithyad.blog.payloads.CatagoryDto;
import com.codewithyad.blog.services.CatagoryService;

@RestController
@RequestMapping("/api/catagories")
public class CatagoryController {
 @Autowired
 private CatagoryService catService;
 @PostMapping("/")
 public ResponseEntity<CatagoryDto> createCatagory(@Valid @RequestBody CatagoryDto catagoryDto){
	 CatagoryDto createCatagoryDto=this.catService.createCatagory(catagoryDto);
	 return new ResponseEntity<>(createCatagoryDto,HttpStatus.CREATED);
 }
 @PutMapping("/{catagoryId}")
 public ResponseEntity<CatagoryDto> updateCatagory(@Valid @RequestBody CatagoryDto catagoryDto,@PathVariable("catagoryId") Integer cid){
	 CatagoryDto updateCatagoryDto=this.catService.updateCatagory(catagoryDto, cid);
	 return ResponseEntity.ok(updateCatagoryDto);
 }
 @GetMapping("/{catagoryId}")
 public ResponseEntity<CatagoryDto> getCatagoryById(@PathVariable("catagoryId") Integer cid){
	 return ResponseEntity.ok(this.catService.getCatagoryById(cid));
 }
 @GetMapping("/")
 public ResponseEntity<List<CatagoryDto>> getAllCatagory(){
	 return ResponseEntity.ok(this.catService.getAllCatagory());
 }
 @DeleteMapping("/{catagoryId}")
 public ResponseEntity<ApiResponse> deleteUser(@PathVariable("catagoryId") Integer cid){
	 this.catService.deleteCatagory(cid);
	 return new ResponseEntity(new ApiResponse("Catagory deleted succesfully",true),HttpStatus.OK);
}}
