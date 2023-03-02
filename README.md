# springboot-hazelcast-cachemanager-service details

### Description
This service performs below operations:

* POST /project
	- endpoint to create projects
* GET /project/{id}
	- endpoint to get project by id
* GET /project/cache/insert
	- endpoint to insert entire Project table DB data to cache
* GET /project/cache/retrieve
	- endpoint to retrieve entire data from cache
	
### Other Details

* This service uses H2 DB 
* Postman collection with all requests is available in same repo to hit endpoints 

### How to test?

1. Use first endpoint to create project data
2. Use second endpoint to get project data by id, first time it will read from DB but next time it will read from cache. This can be verified from console logs. First time it will print log "Reading record from Database", for subsequent hits to this endpoint for same id will not print any log
3. Create more projects using first endpoint
4. Use third endpoint to insert all records to cache in one go
5. Use fourth endpoint to fetch all records from cache in one go

### 	

