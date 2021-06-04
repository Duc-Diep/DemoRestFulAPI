package com.manageuser.model.mapper;

import com.manageuser.dao.User;
import com.manageuser.model.dto.UserBodyDTO;
import com.manageuser.model.dto.UserNoPassDTO;

public class UserMapper {
	public static UserNoPassDTO toUserDTO(User user) {
		UserNoPassDTO tempDto = new UserNoPassDTO();
		tempDto.setId(user.getId());
		tempDto.setName(user.getName());
		tempDto.setEmail(user.getEmail());
		tempDto.setPhone(user.getPhone());
		tempDto.setAvatar(user.getAvatar());
		return tempDto;
	}
	public static User toUserDAO(UserBodyDTO user) {
		User temp = new User();
		temp.setName(user.getName());
		temp.setEmail(user.getEmail());
		temp.setPhone(user.getPhone());
		temp.setAvatar(user.getAvatar());
		temp.setPassword(user.getPassword());
		return temp;
	}
}
