package com.me_22k.spring.boot.blog.service;

import com.me_22k.spring.boot.blog.domain.Authority;

/**
 * Authority 服务接口.
 * 

 */
public interface AuthorityService {
	 
	
	/**
	 * 根据id获取 Authority
	 * @param Authority
	 * @return
	 */
	Authority getAuthorityById(Long id);
}
