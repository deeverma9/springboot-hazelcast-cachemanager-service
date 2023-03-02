package com.example.demo.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProjectResponse implements Serializable {

	private static final long serialVersionUID = -8525478375882983769L;
	private Integer id;
	private String projectName;
}
