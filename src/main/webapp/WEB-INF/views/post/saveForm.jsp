<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp" %>
<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>


<div class="container">
 <form action="/post" method="post">
  <div class="form-group">
    <input type="text" class="form-control" placeholder="Enter title"  name="title"/>
  </div>
  <div class="form-group">
    <textarea rows="" cols="5" class="form-control" name="content" id="summernote"></textarea>
  </div>
  <button type="submit" class="btn btn-primary">글쓰기 완료</button>
</form> 
</div>

<script>
   $('#summernote').summernote({
        placeholder: '글을 쓰세요.',
        tabsize: 2,
        height: 400
      });
  </script>



<%@ include file="../layout/footer.jsp" %>