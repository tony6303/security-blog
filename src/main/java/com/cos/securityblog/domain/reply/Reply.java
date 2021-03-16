package com.cos.securityblog.domain.reply;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;

import com.cos.securityblog.domain.post.Post;
import com.cos.securityblog.domain.user.RoleType;
import com.cos.securityblog.domain.user.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder // 빌더 패턴!!
//ORM -> Java(다른언어) Object -> 테이블로 매핑해주는 기술
@Entity // User 클래스가 MySQL에 테이블이 생성이 된다.
// @DynamicInsert // insert시에 null인 필드를 제외시켜준다.
public class Reply {
	@Id //Primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 프로젝트에서 연결된 DB의 넘버링 전략을 따라간다.
	private Integer id; // 시퀀스, auto_increment
	
	@Column(nullable = false, length = 200)
	private String content;
	
	// 자식이 외래키의 주인이다 ? < 외워도 됨
	
	@ManyToOne // User(1) - Reply(N)
	@JoinColumn(name = "userId")
	private User user;
	
	@ManyToOne // Post(1) - Reply(N)
	@JoinColumn(name = "postId")
	private Post post;
	
	@CreationTimestamp
	private Timestamp createDate;
}
