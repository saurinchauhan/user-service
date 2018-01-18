package com.user.userservice.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.user.userservice.model.User;

@RestController
@RequestMapping("/rest/user")
public class UserServiceResource {

	@Autowired
	private RestTemplate restTemplate;

	@GetMapping("/{username}")
	public User getUserByUserName(@PathVariable("username") final String userName) {
		ResponseEntity<User> response = restTemplate.getForEntity("http://db-service/rest/db/" + userName, User.class);
		return response.getBody();
	}

}
