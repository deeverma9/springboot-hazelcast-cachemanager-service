package com.example.demo.service;

import java.util.List;

import com.example.demo.model.ProjectRequest;
import com.example.demo.model.ProjectResponse;

public interface ProjectService {
	
	ProjectResponse getProject(Integer id);
	
	ProjectResponse createProject(ProjectRequest projectRequest);

	String insertRecordsToCache();

	List<String> fetchRecordsFromCache();
	
}
