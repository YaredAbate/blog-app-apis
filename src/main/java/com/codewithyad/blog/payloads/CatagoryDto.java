package com.codewithyad.blog.payloads;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CatagoryDto {
	private Integer catagoryId;
	@NotBlank
	@Size(min=4,message="min size of catagoryTitle is 4")
	private String catagoryTitle;
	@NotBlank
	@Size(min=10,message="min size of catagoryDescription is 10")
	private String catagoryDescription;
}
