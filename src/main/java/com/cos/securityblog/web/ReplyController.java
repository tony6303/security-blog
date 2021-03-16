package com.cos.securityblog.web;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.securityblog.config.auth.PrincipalDetails;
import com.cos.securityblog.domain.post.Post;
import com.cos.securityblog.domain.post.PostRepository;
import com.cos.securityblog.domain.reply.Reply;
import com.cos.securityblog.domain.reply.ReplyRepository;
import com.cos.securityblog.service.ReplyService;
import com.cos.securityblog.web.dto.CMRespDto;
import com.cos.securityblog.web.reply.dto.ReplySaveReqDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class ReplyController {
	// 테스트 컨트롤러
	private final ReplyRepository replyRepository;
	private final PostRepository postRepository;
	private final ReplyService replyService;
	
	// 게시글 상세보기 user, post , reply
	@GetMapping("/test/post/{id}")
	public CMRespDto<?> test(@PathVariable int id) {
      Post postEntity= postRepository.findById(id).get();
      
      return new CMRespDto<>(1,postEntity);   
	}

	
	@DeleteMapping("/reply/{id}") 
	public CMRespDto<?> deleteReply(@PathVariable int id, @AuthenticationPrincipal PrincipalDetails principalDetails){
		// 모든 컨트롤러의 삭제,수정은 동일 인물이 로그인 했는지 확인해야함 = principaldetails
		
		
		int result = replyService.삭제하기(id, principalDetails.getUser().getId());
		return new CMRespDto<>(result, null);
	}
	
	@PostMapping("/reply/post/{boardId}") // Reply 모델에 맞게 잘 저장해주면 됨
	// ajax로 보낸 let data가 @RequestBody 에 들어오게 됨
	public CMRespDto<?> replySave(@PathVariable int boardId,@RequestBody ReplySaveReqDto replySaveReqDto, @AuthenticationPrincipal PrincipalDetails principalDetails){
		System.out.println(boardId);
		System.out.println(replySaveReqDto);
		Reply reply = replySaveReqDto.toEntity();  // reply 에 content 넣음
		
		reply.setUser(principalDetails.getUser()); // reply 에 User 넣음
		
		// content 랑 user 들어가있는 Entity 를 서비스에 보냄
		replyService.댓글저장하기(boardId, reply); // reply 에 Post 넣음 + DB에 저장함
		
		
		//replyService.댓글저장하기(boardId, replySaveReqDto);
		return new CMRespDto<>(1,null);
	}
}
