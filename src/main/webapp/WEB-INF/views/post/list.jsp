<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp" %>


 <div class="container">
 
 <c:forEach var="post" items="${posts}">
  <div class="card">
   <div class="card-body">
    <h4 class="card-title">${posts.title }</h4>
    <a href="/post/${posts.id}" class="btn btn-primary">${posts.content }</a>
   </div>
  </div>
  <br>
 </c:forEach>

</div>
  
<%@ include file="../layout/footer.jsp" %>