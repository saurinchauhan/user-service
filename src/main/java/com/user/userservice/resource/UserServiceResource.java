package com.user.userservice.resource;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.user.userservice.model.User;

@RestController
@RequestMapping("/rest/user")
public class UserServiceResource {

	@Autowired
	private RestTemplate restTemplate;

	private String DB_SERVICE_URL = "http://db-service/rest/db/";

	@GetMapping("/{username}")
	public User getUserByUserName(@PathVariable("username") final String userName) {
		ResponseEntity<User> response = restTemplate.exchange(DB_SERVICE_URL + userName, HttpMethod.GET, null,
				User.class);
		return response.getBody();
	}

	@GetMapping("/all")
	public List<User> findAllUsers() {
		ResponseEntity<ArrayList> response = restTemplate.exchange(DB_SERVICE_URL + "all", HttpMethod.GET, null,
				ArrayList.class);
		return response.getBody();
	}

	@PostMapping("/add")
	public User addUser(@RequestBody final User user) {
		ResponseEntity<User> response = restTemplate.exchange(DB_SERVICE_URL + "add", HttpMethod.POST, null, User.class,
				user);
		return response.getBody();
	}

	@PostMapping("/update")
	public User updateUser(@RequestBody final User user) {
		ResponseEntity<User> response = restTemplate.exchange(DB_SERVICE_URL + "update", HttpMethod.POST, null,
				User.class, user);
		return response.getBody();
	}

	@DeleteMapping("/delete/{username}")
	public User deleteUser(@PathVariable("username") final String userName) {
		ResponseEntity<User> response = restTemplate.exchange(DB_SERVICE_URL + "delete/" + userName, HttpMethod.DELETE,
				null, User.class);
		return response.getBody();
	}

}
