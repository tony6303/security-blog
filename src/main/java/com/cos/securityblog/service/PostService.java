package com.cos.securityblog.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.securityblog.domain.post.Post;
import com.cos.securityblog.domain.post.PostRepository;
import com.cos.securityblog.web.post.dto.PostSaveReqDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PostService {

	private final PostRepository postRepository;
	
	// import domain걸로
	@Transactional(readOnly = true)
	public Page<Post> 전체찾기(Pageable pageable){
		return postRepository.findAll(pageable);
	}
	
	@Transactional
	public Post 글저장하기(Post post) {
		return postRepository.save(post);
	}

	@Transactional(readOnly = true)
	public Post 상세보기(int id) {
		return postRepository.findById(id).get(); 
	}
	
	public void 삭제하기(int id) {
		postRepository.deleteById(id);
	}

	public void 수정하기(int id, PostSaveReqDto postSaveReqDto) {
		// 영속화
		Post postEntity = postRepository.findById(id).get();
		
		postEntity.setTitle(postSaveReqDto.getTitle());
		postEntity.setContent(postSaveReqDto.getContent());
		
	}// 더티체킹
}
