package com.codewithyad.blog.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="catagories")
@NoArgsConstructor
@Setter
@Getter
public class Catagory {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer catagoryId;
	@Column(name="Title")
	private String CatagoryTitle;
	@Column(name="Description",length=100,nullable=false)
	private String CatagoryDescription;
	@OneToMany(mappedBy="catagory",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	private List<Post> posts=new ArrayList<>();
}
