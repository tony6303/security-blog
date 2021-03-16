package com.cos.securityblog.web.reply.dto;

import com.cos.securityblog.domain.post.Post;
import com.cos.securityblog.domain.reply.Reply;
import com.cos.securityblog.domain.user.User;

import lombok.Data;

@Data
public class ReplySaveReqDto {

	private String content;
	
	public Reply toEntity() {
		return Reply.builder()
			.content(content)
			.build();
	}
}
