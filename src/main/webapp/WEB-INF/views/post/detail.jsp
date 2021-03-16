<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>


<div class="container">

<div>
<button class="btn btn-secondary" onClick="history.go(-1)">뒤로가기</button>
<!-- principal은 header에서 받았음, -->
 <c:if test="${post.user.id == principal.user.id}"> 
  <a href="/post/${post.id }/updateForm" class="btn btn-warning" >수정</a>
  <button id="btn-delete" class="btn btn-danger" value="${post.id }">삭제</button>
 </c:if>
 
 <br/>
 <div class="d-flex-justify-content-between">
 <span>글번호 : ${post.id }</span> <span>작성자: ${post.user.username }</span>
 </div>
 <br/>
 <div>
  <h3>${post.title }</h3>
 </div>
 <div>
  <div>${post.content }</div>
 </div>
</div>

<!-- 댓글 시작 -->
 <div class="card">
     <form>
     <input type="hidden" name="userId" value="${principal.user.id }"/>
     <input type="hidden" name="boardId" value="${post.id }"/>
   <div class="card-body">
     <textarea id="reply-content" class="form-control" rows="1"></textarea>
   </div>
   <div class="card-footer">
    <button type="button" onClick="replySave(${post.id } )" id="btn-reply-save" class="btn btn-primary">등록</button>
   </div>
  </form>
 </div>
 <br />
 
 <div class="card">
  <div class="card-header">댓글 리스트</div>
  <ul id="reply-box" class="list-group">
  
   <c:forEach var="reply" items="${post.replys }"> <!-- Post가 List<Reply> replys 를 갖고있음 -->
   
    <li id="reply-${reply.id }" class="list-group-item d-flex justify-content-between">
     <div>${reply.content }</div> <!-- LAZY로딩 시작. 이유는 getter 호출이 되니까.. open in view 모드에서만! -->
     <div class="d-flex">
      <div class="font-italic">작성자 : ${reply.user.username } &nbsp;</div> <!-- test/post 에서 json 보면 알거임 -->
      <button onClick="deleteReply(${reply.id})" class="badge">삭제</button>
      <!-- delete는 onClick이 낫습니다~ -->  
     </div>
    </li>
    
   </c:forEach>
    
  </ul>
 </div>
 <!-- 댓글 끝 -->


</div> <!--  container end -->
<script>
  
  function deleteReply(replyId){
	 $.ajax({
	    type: "DELETE",
	    url: "/reply/" + replyId,
	    dataType: "json"
	   }).done(res=>{
     if(res.code === 1){
      $("#reply-"+replyId).remove();
      
     }else{
         alert("작성자가 아니면 삭제할 수 없습니다.");
     }
   })
  }

  function replySave(boardId){
	  var data = {
				content : $("#reply-content").val()
			}
            console.log(data);
			$.ajax({
				type : "post",
				url : "/reply/post/" + boardId,
				data : JSON.stringify(data),
				contentType : "application/json; charset=utf-8",
				dataType : "json"
			}).done(function(result) {
				if (result.code === 1) {
					console.log(result);
					$("#reply-content").val("");
                    location.reload();
				} else {
     //done , fail 로 구분하는듯
					alert("댓글쓰기 실패");
				}
			});
	}

  
</script>

<%@ include file="../layout/footer.jsp"%>