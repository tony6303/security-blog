<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>

<h1>회원정보수정 페이지입니다</h1>
 <form onsubmit=""> <!-- form , method 를 자바스크립트로 구현 -->
  <input type="hidden" id="id" value="${id }"/>
  <input type="text" value="${principal.user.username } " placeholder="Username" id="username" readonly="readonly"/> <br/>
  <input type="password" placeholder="Password" id="password" /> <br/>
  <input type="email" value="${principal.user.email } " placeholder="Email" id="email" /> <br/>
  <Button id="btn-update">회원가입</Button>
 </form>
 <br/>

<script>
//Listener
$("#btn-update").on("click", (e) => {
  e.preventDefault();
  let data={
    username: $("#username").val(),
    password: $("#password").val(),
    email: $("#email").val()          
  };

  console.log(data);

  let id = $("#id").val();
  
  $.ajax({
      type: "PUT",
      url: "/user/" + id,
      data: JSON.stringify(data),
      contentType: "application/json; charset=utf-8",
      dataType: "json"
    }).done((res) => {
      console.log(res);
      if(res.code===1){
        alert("수정 성공");
        //location.href = "/";  
      } else{
        alert("수정 실패"); 
      }
    });
   
})

</script>

<%@ include file="../layout/footer.jsp"%>