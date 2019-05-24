package com.me_22k.spring.boot.blog.service.Impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.me_22k.spring.boot.blog.domain.Comment;
import com.me_22k.spring.boot.blog.repository.CommentRepository;
import com.me_22k.spring.boot.blog.service.CommentService;

/**
 * Comment 服务.
 * 
 */
@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentRepository commentRepository;
	
	@Override
	@Transactional
	public void removeComment(Long id) {
		commentRepository.delete(id);
	}
	@Override
	public Comment getCommentById(Long id) {
		return commentRepository.findOne(id);
	}

}
