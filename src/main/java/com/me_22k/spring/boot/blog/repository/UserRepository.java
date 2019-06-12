package com.me_22k.spring.boot.blog.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.me_22k.spring.boot.blog.domain.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

/**
 * 用户仓库.
 *
*/
public interface UserRepository extends JpaRepository<User, Long>{

	/**
	 * 根据用户名分页查询用户列表
	 * @param name
	 * @param pageable
	 * @return
	 */
	Page<User> findByNameLike(String name, Pageable pageable);
	/**
	 * 根据名称查询
	 * @param username
	 * @return
	 */
	User findByUsername(String username);
	/**
	 * 根据名称列表查询
	 * @param usernames
	 * @return
	 */
	List<User> findByUsernameIn(Collection<String> usernames);

	/**
	 * 查询邮箱激活状态码
	 */
	@Query(value = "SELECT status FROM user WHERE username =?",nativeQuery = true)
	 String findByStatus(String username);

	/**
	 * 更改邮箱激活状态
	 */
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(value = "UPDATE user set status = ? WHERE email = ?",nativeQuery =true)
	int updateStatus(String status,String email);
}
