/**
 * 
 */
package com.me_22k.spring.boot.blog.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.me_22k.spring.boot.blog.domain.Authority;
import com.me_22k.spring.boot.blog.repository.AuthorityRepository;
import com.me_22k.spring.boot.blog.service.AuthorityService;

/**
 * Authority 服务.
 * 
 */
@Service
public class AuthorityServiceImpl  implements AuthorityService {
	
	@Autowired
	private AuthorityRepository authorityRepository;
	
	@Override
	public Authority getAuthorityById(Long id) {
		return authorityRepository.findOne(id);
	}

}
