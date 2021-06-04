package com.manageuser.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import com.manageuser.dao.User;
import com.manageuser.exception.NotFoundException;
import com.manageuser.model.dto.UserBodyDTO;
import com.manageuser.model.dto.UserNoPassDTO;
import com.manageuser.model.mapper.UserMapper;
import com.manageuser.repositories.UserRepository;

@Component
public class UserServiceImpl implements UserService {// đánh dấu để inject vào controller

	@Autowired
	UserRepository userRepository;
	private List<User> listUsers;

	@Override
	public List<UserNoPassDTO> getListUsers(Integer page) {
		List<User> listUser;
		if(page!=null) {
			Page<User> pageUser = userRepository.findAll(PageRequest.of(page.intValue(), 5));
			listUser = pageUser.getContent();
		}else {
			listUser = userRepository.findAll();
		}
		List<UserNoPassDTO> result = new ArrayList<>();
		for (User user : listUser) {
			result.add(UserMapper.toUserDTO(user));
		}
		return result;
	}

	@Override
	public UserNoPassDTO getUserById(int id) {
		Optional<User> user = userRepository.findById(id);
		if (user != null) {
			return UserMapper.toUserDTO(user.get());
		}
		throw new NotFoundException("Không tồn tại user này!");
	}

	@Override
	public List<UserNoPassDTO> searchUser(String keyword) {
		listUsers = userRepository.findAll();
		List<UserNoPassDTO> list = new ArrayList<>();
		for (User user : listUsers) {
			if (user.getName().contains(keyword)) {
				list.add(UserMapper.toUserDTO(user));
			}
		}
		if (list.size() == 0) {
			throw new NotFoundException("Không tồn tại user này!");
		}
		return list;
	}

	public int getPosition(int id) {
		for (int i = 0; i < listUsers.size(); i++) {
			if (listUsers.get(i).getId() == id) {
				return i;
			}
		}

		return -1;
	}

	@Override
	public UserNoPassDTO createUser(UserBodyDTO user) {
		User userDAO = userRepository.findByName(user.getName());
		if (userDAO != null) {
			throw new NotFoundException("Duplicate username");
		}
		User entity = UserMapper.toUserDAO(user);
		User newAcc = userRepository.save(entity);
		return UserMapper.toUserDTO(newAcc);
	}

	@Override
	public UserNoPassDTO updateUser(int id, UserBodyDTO user) {

		Optional<User> useOptional = userRepository.findById(id);

		if (!useOptional.isPresent()) {
			throw new NotFoundException("Not found username");
		}
		User userDAO = useOptional.get();
		userDAO.setAvatar(user.getAvatar());
		userDAO.setEmail(user.getEmail());
		userDAO.setPassword(user.getPassword());
		userDAO.setPhone(user.getPhone());
		User newUpdateAcc = userRepository.save(userDAO);
		return UserMapper.toUserDTO(newUpdateAcc);
	}

	@Override
	public UserNoPassDTO deleteUser(int id) {
		Optional<User> useOptional = userRepository.findById(id);

		if (!useOptional.isPresent()) {
			throw new NotFoundException("Not found username");
		}
		userRepository.deleteById(id);
		return UserMapper.toUserDTO(useOptional.get());
	}

	

}
