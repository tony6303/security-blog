package com.cos.securityblog.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.securityblog.domain.post.Post;
import com.cos.securityblog.domain.post.PostRepository;
import com.cos.securityblog.domain.reply.Reply;
import com.cos.securityblog.domain.reply.ReplyRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ReplyService {

	private final ReplyRepository replyRepository;
	private final PostRepository postRepository;
	
	public List<Reply> 댓글전체보기(int boardId){
		
		return null;		
	}
	
	@Transactional
	public int 삭제하기(int id, int userId) {
		Reply replyEntity = replyRepository.findById(id).get(); // 컨트롤 어드바이스 익셉션 ???
		if(replyEntity.getUser().getId() == userId) {
			replyRepository.deleteById(id);
			return 1;
		}
		return -1;		
	}
	
	@Transactional    //boardId 18, user랑 content 담긴 entity     
	public Reply 댓글저장하기(int id, Reply reply){
		// 
		//ReplySaveReqDto / 1. boardid, content 
		
		//1. boardid => 어떤 게시글인지 찾기
		//2. 그 게시글에 댓글에 content넣기
		
		//reply 빈 객체 -> 니가 받아온데이터 3개를 넣어
		//현재 reply : userid, content,///// 분리 int id =boardID)
		
		// replyRepository.save(replyEntity);
		
		// Post post = postRepository.findById(id).get();
		
		//현재 reply : userid, content,///// 분리 post 완성)
		reply.setPost(postRepository.findById(id).get()); // ajax url에 있는 boardId로 post 를 찾음
		Reply replyEntity =  replyRepository.save(reply);
		
		
		return replyEntity; 
	}
}
