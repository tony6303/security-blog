package com.cos.securityblog.domain.post;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import com.cos.securityblog.domain.reply.Reply;
import com.cos.securityblog.domain.user.RoleType;
import com.cos.securityblog.domain.user.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
public class Post {
	// Post는 User 한명이 여러개 쓸 수 있다. -> User(one) - Post(many)
	
	@Id //Primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 프로젝트에서 연결된 DB의 넘버링 전략을 따라간다.
	private Integer id; // 시퀀스, auto_increment
	 
	@Column(nullable = false, length = 100) 
	private String title; // 아이디
	
	@Lob //(대용량 데이터)
	private String content;
	
	@ColumnDefault("0")
	private int count;
	
	@ManyToOne // 기본 EAGER , (fetch = FetchType.LAZY)  FK의 주인인 곳에서 적어야 됨. User(1) - Post(N)
	@JoinColumn(name = "userId")
	private User user;
	
	// JPA 양방향 매핑.  post - reply
	// "post" = reply의 변수명, 기본키로 삽입하지 않게 한다 ?
	// LAZY 하면? = Reply가 필요할때 에만 select
	// 게시글이 삭제되면 댓글도 함께 삭제 = cascade
	@OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE , fetch = FetchType.LAZY)
	@JsonIgnoreProperties({"post"}) // 양방향 매핑이 되면 무한루프에 빠지는 것 방지. (서로서로 Entity 를 getter 하게됨)
	@OrderBy("id desc")
	private List<Reply> replys;
	
	@CreationTimestamp
	private Timestamp createDate;

}
