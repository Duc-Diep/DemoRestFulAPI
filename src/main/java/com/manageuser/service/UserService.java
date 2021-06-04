package com.manageuser.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.manageuser.dao.User;
import com.manageuser.model.dto.UserBodyDTO;
import com.manageuser.model.dto.UserNoPassDTO;

@Service
public interface UserService {//giảm sự phụ thuộc của controller vào service
	public List<UserNoPassDTO> getListUsers(Integer page);
	public UserNoPassDTO getUserById(int id);
	public List<UserNoPassDTO> searchUser(String keyword);
	public UserNoPassDTO createUser(UserBodyDTO user);
	public UserNoPassDTO updateUser(int id,UserBodyDTO user);
	public UserNoPassDTO deleteUser(int id);
}
