<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="../includes/header.jsp"%>

<div class="topModal modal" tabindex="-1">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title">Modal title</h5>
			</div>
			<div class="modal-body">
				<p>수정되었습니다</p>
			</div>
			<div class="modal-footer">
				<button type="button" class="topCommit btn btn-primary">확인</button>
			</div>
		</div>
	</div>
</div>

<div class="col-lg-3 col-md-6 col-sm-6">
								<form action="/admin/store/menuModify" class="actionForm" method="post">
									<div class="card card-stats">
										<div class="card-header card-header-warning card-header-icon">
											<div class="card-icon">
												<i class="material-icons">content_copy</i>
											</div>
											
											<div class="form-group">
												<label class="bmd-label-floating"></label> 
												<input type="text" class="form-control" name='tName' value=${topping.tname }>
											</div>
											<div class="form-group">
												<label class="bmd-label-floating"></label> 
												<input type="text" class="form-control" name='tPrice' value=${topping.tprice }>
											</div>	
											<div class="form-group">
												<label class="bmd-label-floating"></label> 
												<input type="text" class="form-control" name='tImg' value=${topping.timg }>
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
		
		const tname = document.querySelector("input[name='tName']").value
		const tprice = document.querySelector("input[name='tPrice']").value
		const timg = document.querySelector("input[name='tImg']").value
		const tno = ${topping.tno}
	
		const toppingDTO = {tno:tno , tname:tname , tprice:tprice ,  timg : timg}
		console.log(tname)
		console.log(tprice)
		console.log(timg)
		console.log(JSON.stringify(toppingDTO))
		fetch("/admin/store/toppingModify" , {
			method : 'post',
			headers : {"Content-Type" : "application/json;"} ,
			body : JSON.stringify(toppingDTO)
		}).then(res => res.text()).then(result => {$(".topModal").modal("show")})
		
	} , false) 
	
// toppingModifyConfirm
	document.querySelector(".topCommit").addEventListener("click" , function(e){
		location.href = "/admin/store/toppingList?sno="+${topping.sno}
	} , false)
	
	</script>
	


	<%@ include file="../includes/footer.jsp"%>