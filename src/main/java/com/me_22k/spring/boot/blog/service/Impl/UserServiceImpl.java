package com.me_22k.spring.boot.blog.service.Impl;

import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.me_22k.spring.boot.blog.domain.User;
import com.me_22k.spring.boot.blog.repository.UserRepository;
import com.me_22k.spring.boot.blog.service.UserService;

/**
 * User 服务.
 * 
 */
@CacheConfig(cacheNames = "userService")
@Service
public class UserServiceImpl implements UserService, UserDetailsService {

	@Autowired
	private UserRepository userRepository;


	@Transactional
	@Override
	public User saveUser(User user) {
		return userRepository.save(user);
	}

	@Transactional
	@Override
	public void removeUser(Long id) {
		userRepository.delete(id);
	}

	@Transactional
	@Override
	public void removeUsersInBatch(List<User> users) {
		userRepository.deleteInBatch(users);
	}

	@Transactional
	@Override
	public User updateUser(User user) {
		return userRepository.save(user);
	}


	@Override
	public User getUserById(Long id) {
		return userRepository.getOne(id);
	}

	@Cacheable(value = "listUsers",keyGenerator="UserKeyGenerator")
	@Override
	public List<User> listUsers() {
		return userRepository.findAll();
	}

	@Cacheable(value = "listUsersByNameLike",keyGenerator="UserKeyGenerator")
	@Override
	public Page<User> listUsersByNameLike(String name, Pageable pageable) {
		// 模糊查询
		name = "%" + name + "%";
		Page<User> users = userRepository.findByNameLike(name, pageable);
		return users;
	}

	//表单登录的验证
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return userRepository.findByUsername(username);
	}



	@Override
	public List<User> listUsersByUsernames(Collection<String> usernames) {

		return userRepository.findByUsernameIn(usernames);
	}

	@Transactional
	@Override
	public void SaveStatus(String status,String email) {
		userRepository.updateStatus(status,email);
	}

}
