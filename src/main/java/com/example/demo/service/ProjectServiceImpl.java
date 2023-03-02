package com.example.demo.service;

import java.util.List;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.demo.exception.CustomException;
import com.example.demo.model.Project;
import com.example.demo.model.ProjectRequest;
import com.example.demo.model.ProjectResponse;
import com.example.demo.repository.ProjectRepository;
import com.hazelcast.core.HazelcastInstance;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProjectServiceImpl implements ProjectService {

	@Autowired
	private ProjectRepository projectRepository;
	
	@Autowired
	private CacheManager cacheManager;
	
	@Autowired
	private HazelcastInstance hazelcastInstance;

	@Override
	@Cacheable(value = "project-cache", key = "#id")
	public ProjectResponse getProject(Integer id) {
		ProjectResponse projectResponse = new ProjectResponse();
		if (projectRepository.findById(id).isPresent()) {
			log.info("Reading record from Database");
			BeanUtils.copyProperties(projectRepository.findById(id).get(), projectResponse);
			return projectResponse;
		} else {
			throw new CustomException("Project with id: " + id + " doesn't exist", HttpStatus.NOT_FOUND.value());
		}
	}

	@Override
	public ProjectResponse createProject(ProjectRequest projectRequest) {
		Project project = new Project();
		BeanUtils.copyProperties(projectRequest, project);
		ProjectResponse projectResponse = new ProjectResponse();
		//project = projectRepository.save(project);
		BeanUtils.copyProperties(projectRepository.save(project), projectResponse);
		return projectResponse;
	}

	@Override
	public String insertRecordsToCache() {
		Cache cache = cacheManager.getCache("project-cache");
		projectRepository.findAll().forEach(prj -> {
			ProjectResponse projectResponse = new ProjectResponse();
			BeanUtils.copyProperties(prj, projectResponse);
			cache.put(prj.getId(), projectResponse);
		});
		
		return "Inserted data to cache successfully!";
	}

	@Override
	public List<String> fetchRecordsFromCache() {
		ConcurrentMap<Integer, ProjectResponse> projectMap = hazelcastInstance.getMap("project-cache");
		
		log.info("Retrieved data from cache successfully!!");
		return projectMap.entrySet().stream().map(entry -> entry.getKey() + ": "+ entry.getValue()).collect(Collectors.toList());
	}

}
