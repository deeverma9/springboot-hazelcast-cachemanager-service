package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.ProjectRequest;
import com.example.demo.model.ProjectResponse;
import com.example.demo.service.ProjectService;

@RestController
@RequestMapping("/project")
public class ProjectController {
	
	@Autowired
	private ProjectService projectService;
	
	@PostMapping
	public ProjectResponse createProject(@RequestBody ProjectRequest projectRequest) {
		
		return projectService.createProject(projectRequest);
	}

	@GetMapping("/{id}")
	public ProjectResponse getProject(@PathVariable Integer id) {
		
		return projectService.getProject(id);
	}
	
	@GetMapping("/cache/insert")
	public String insertDataToCache() {
		
		return projectService.insertRecordsToCache();
	}

	@GetMapping("/cache/retrieve")
	public List<String> retrieveDataFromCache() {
		
		return projectService.fetchRecordsFromCache();
	}
}
