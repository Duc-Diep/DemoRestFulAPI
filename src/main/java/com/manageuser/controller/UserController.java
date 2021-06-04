package com.manageuser.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.manageuser.dao.User;
import com.manageuser.model.dto.UserBodyDTO;
import com.manageuser.model.dto.UserNoPassDTO;
import com.manageuser.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {//xử lí request từ client
	
	@Autowired
	private UserService userService;

	
	//Pagination
	@GetMapping("")
	public ResponseEntity<?> getListUser(@RequestParam(value = "page",required = false)Integer page) {
		List<UserNoPassDTO> list = userService.getListUsers(page);
		if(list.size()==0) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		return ResponseEntity.status(HttpStatus.OK).body(list);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getUserByID(@PathVariable int id) {
		UserNoPassDTO result = userService.getUserById(id);
		return ResponseEntity.ok(result);
	}
	
	
	
	
	@GetMapping("/search")
	public ResponseEntity<?> searchUser(@RequestParam(name="keyword",required = false,defaultValue = "") String name){
		List<UserNoPassDTO> result = userService.searchUser(name);
		return ResponseEntity.ok(result);
	}
	
	@PostMapping("")
	public ResponseEntity<?> createAUser(@RequestBody UserBodyDTO user) {
		UserNoPassDTO userDTO = userService.createUser(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(userDTO);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateAUser(@PathVariable int id,@RequestBody UserBodyDTO user) {
		userService.updateUser(id,user);
		return ResponseEntity.ok().body(user);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteAUser(@PathVariable int id) {
		UserNoPassDTO user = userService.deleteUser(id);
		return ResponseEntity.ok().body(user);
	}
}
