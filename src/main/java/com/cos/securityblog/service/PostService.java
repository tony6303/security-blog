package com.cos.securityblog.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.securityblog.domain.post.Post;
import com.cos.securityblog.domain.post.PostRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PostService {

	private final PostRepository postRepository;
	
	public List<Post> 전체찾기(){
		return postRepository.findAll();
	}
	
	@Transactional
	public Post 글저장하기(Post post) {
		return postRepository.save(post);
	}
}
