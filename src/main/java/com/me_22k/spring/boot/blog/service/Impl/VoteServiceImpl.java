package com.me_22k.spring.boot.blog.service.Impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.me_22k.spring.boot.blog.domain.Vote;
import com.me_22k.spring.boot.blog.repository.VoteRepository;
import com.me_22k.spring.boot.blog.service.VoteService;

/**
 * Vote 服务.
 * 
 */
@Service
public class VoteServiceImpl implements VoteService {

	@Autowired
	private VoteRepository voteRepository;
	
	@Override
	@Transactional
	public void removeVote(Long id) {
		voteRepository.delete(id);
	}
	@Override
	public Vote getVoteById(Long id) {
		return voteRepository.findOne(id);
	}

}
