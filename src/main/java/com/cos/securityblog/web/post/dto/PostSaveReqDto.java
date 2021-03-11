package com.cos.securityblog.web.post.dto;


import com.cos.securityblog.domain.post.Post;

import lombok.Data;

@Data
public class PostSaveReqDto {
	private String title;
	private String content;
	
	public Post toEntity() {
		return Post.builder()
			.title(title)
			.content(content)
			.build();
	}
}