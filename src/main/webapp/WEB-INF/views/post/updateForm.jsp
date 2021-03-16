<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp" %>
<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>


<div class="container">
 <form>
  <div class="form-group">
    <input type="hidden" id="id" value="${post.id }"/>
    <input type="text" class="form-control" placeholder="Enter title"  id="title" value="${post.title }"/>
  </div>
  <div class="form-group">
    <textarea rows="" cols="5" class="form-control" name="content" id="content">
     ${post.content }
    </textarea>
  </div>
  <button id="btn-submit" type="submit" class="btn btn-primary">글수정 완료</button>
</form> 
</div>

<script>
   $('#content').summernote({
        placeholder: '글을 쓰세요.',
        tabsize: 2,
        height: 400
      });
  </script>
  
  <script>
 $("#btn-submit").on("click", (e)=> {
   e.preventDefault();
    console.log(e.currentTarget);
  let id = $("#id").val();
  let data = {
    title: $("#title").val(),
    content: $("#content").val()
  } 
  

  $.ajax({
    type: "PUT",
    url: "/post/" + id,
    data: JSON.stringify(data),
    contentType: "application/json; charset=utf-8",
    dataType: "json"
   }).done(res=>{
     if(res.code === 1){
       console.log(res);
       alert("수정 성공");
       location.href = "/";
     }else{
    	 alert("수정 실패");  
     }
   })
    
  })
</script>


<%@ include file="../layout/footer.jsp" %>