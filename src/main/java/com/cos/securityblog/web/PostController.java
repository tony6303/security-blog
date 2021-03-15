package com.cos.securityblog.web;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.cos.securityblog.config.auth.PrincipalDetails;
import com.cos.securityblog.domain.post.Post;
import com.cos.securityblog.service.PostService;
import com.cos.securityblog.web.post.dto.PostSaveReqDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class PostController {

	private final PostService postService;
	
	
	@GetMapping("/")
	public String findAll(Model model, @PageableDefault(sort = "id", direction = Sort.Direction.DESC, size=5)Pageable pageable,
			@AuthenticationPrincipal PrincipalDetails principalDetails) { // 스프링이 제공해주는 라이브러리 Model
		//List<Post> posts = postService.전체찾기(pageable);
		
		Page<Post> posts = postService.전체찾기(pageable);
		
		model.addAttribute("posts",posts); // requestDispatcher 에 담고 포워딩 한거랑 같다.
		return "post/list";
	}
//	
//	@GetMapping("/")
//	public String index() {
//		return "index";
//	}
	
	@GetMapping("/post/saveForm")
	public String saveForm() {
		return "post/saveForm";
	}
	
//	@PostMapping("/post")
//	public Post post(Post post) {
//		return postService.글저장하기(post);
//	}
	
	@PostMapping("/post") //                               시큐리티가 낚아챔 
	public String save(PostSaveReqDto postSaveReqDto, @AuthenticationPrincipal PrincipalDetails principalDetails) {
		Post post = postSaveReqDto.toEntity(); // 외부에서 받았음 (영속화 안됨)
		post.setUser(principalDetails.getUser()); // Post 에 UserId 필드를 여기서 넣어줌
		Post postEntity = postService.글저장하기(post); // 영속화 되었음
		
		if(postEntity == null) {
			return "post/saveForm"; // 글쓰기 실패 >> 원상태로 돌림
		}else {
			return "redirect:/"; // 성공 >> 리스트 페이지로 이동
		}
	}
	
	
}
