<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="../includes/header.jsp"%>

<div class="col-lg-3 col-md-6 col-sm-6">
								<form action="/admin/store/menuModify" class="actionForm" method="post">
									<div class="card card-stats">
										<div class="card-header card-header-warning card-header-icon">
											<div class="card-icon">
												<i class="material-icons">content_copy</i>
											</div>
											
											<div class="form-group">
												<label class="bmd-label-floating"></label> 
												<input type="text" class="form-control" name='menuName' value=${menu.menuName }>
											</div>
											<div class="form-group">
												<label class="bmd-label-floating"></label> 
												<input type="text" class="form-control" name='content' value=${menu.content }>
											</div>	
											<div class="form-group">
												<label class="bmd-label-floating"></label> 
												<input type="text" class="form-control" name='mprice' value=${menu.mprice }>
											</div>	
											<div class="form-group">
												<label class="bmd-label-floating"></label> 
												<input type="text" class="form-control" name='mimg' value=${menu.mimg }>
											</div>	
											
										
										</div>
										<div class="card-footer">
											<div class="stats">
												<button type="submit" class="modBtn btn btn-primary pull-right" style ="padding: 5px;">수정</button>
											</div>
										</div>
									</div>
							  </form>
								</div> 



	<script type="text/javascript" src="../resources/service.js"></script>
	
	<script>
	
	
	
	
	document.querySelector(".modBtn").addEventListener("click" , function(e){
		e.preventDefault()
		
		const menuName = document.querySelector("input[name='menuName']").value
		const content = document.querySelector("input[name='content']").value
		const mprice = document.querySelector("input[name='mprice']").value
		const mimg = document.querySelector("input[name='mimg']").value
		const mno = ${menu.mno}
	
		const menuDTO = {mno:mno , menuName:menuName , content:content , mprice : mprice , mimg:mimg , category:"일식"}
		console.log(mprice)
		console.log(mimg)
		console.log(JSON.stringify(menuDTO))
		fetch("/admin/store/menuModify" , {
			method : 'post',
			headers : {"Content-Type" : "application/json;"} ,
			body : JSON.stringify(menuDTO)
		}).then(res => res.text()).then(result => console.log(result))
		
	} , false) 
	
	</script>
	


	<%@ include file="../includes/footer.jsp"%>