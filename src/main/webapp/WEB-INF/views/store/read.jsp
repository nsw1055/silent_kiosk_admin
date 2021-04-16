<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="../includes/header.jsp"%>

<div class="modModal modal" tabindex="-1">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title">Modal title</h5>
			</div>
			<form action="/admin/manager/menuModify" class="actionForm"
				method="post">
				<div class="modal-body">
					<div class="card card-stats">
						<div class="card-header card-header-warning card-header-icon">
							

							<div class="form-group is-filled">
								<input type="text"
									class="form-control" name='mid' value="${manager.mid }" readonly="readonly">
							</div>
							<div class="form-group is-filled">
								 <input type="text"
									class="form-control" name='phone' value="" placeholder="phone">
							</div>
							<div class="form-group is-filled">
								 <input type="text"
									class="form-control" name='email' value="" placeholder="email">
							</div>
							 <!-- <div class="row">
								<div class="col-md-12">
									<div class="form-group">
									</div>
									<div style = "margin-bottom: 10px">
									<label class="bmd-label-floating">매장명</label>                       
                             		 <input type="file" name="cdn" data-fileName="" class="form-control" placeholder="cdn" readonly="readonly">
                           			</div>
								</div>
							</div>
							<div class="row">
								<div class="col-md-12">
									<div class="form-group">
									</div>
									<div style = "margin-bottom: 10px">                        
                             			 <input type="file" name="health" data-fileName="" class="form-control" placeholder="health" readonly="readonly" >
                           			</div>
								</div>
							</div>
							<div class="row">
								<div class="col-md-12">
									<div class="form-group">
									</div>
									<div style = "margin-bottom: 10px">                        
                             			 <input type="file" name="hygiene" data-fileName="" class="form-control" placeholder="hygiene" readonly="readonly">
                           			</div>
								</div>
							</div>
							<div class="row">
								<div class="col-md-12">
									<div class="form-group">
									</div>
									<div style = "margin-bottom: 10px">                        
                             			 <input type="file" name="license" data-fileName="" class="form-control" placeholder="license" readonly="readonly" >
                           			</div>
								</div>
							</div> -->

					</div>
				</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="modCommit btn btn-primary">확인</button>
					<button type="button" class="modClose btn btn-danger">취소</button>
				</div>
			</form>
		</div>
	</div>
</div>

<div class="modConfirm modal" tabindex="-1">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title">Modal title</h5>
			</div>
			<div class="modal-body">
				<p>수정 되었습니다.</p>
			</div>
			<div class="modal-footer">
				<button type="button" class="modConBtn btn btn-primary">확인</button>
			</div>
		</div>
	</div>
</div>


<div class="delModal modal" tabindex="-1">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title">Modal title</h5>
				<button type="button" class="btn-close" data-bs-dismiss="modal"
					aria-label="Close"></button>
			</div>
			<div class="modal-body">
				<p>메뉴를 삭제 하시겠습니까?</p>
			</div>
			<div class="modal-footer">
				<button type="button" class="delCancel btn btn-secondary"
					data-bs-dismiss="modal">Close</button>
				<button type="button" class="delAgree btn btn-primary">삭제</button>
			</div>
		</div>
	</div>
</div>
<div class="delModalCon modal" tabindex="-1">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title">Modal title</h5>
			</div>
			<div class="modal-body">
				<p>삭제하였습니다.</p>
			</div>
			<div class="modal-footer">
				<button type="button" class="delCommit btn btn-primary">확인</button>
			</div>
		</div>
	</div>
</div>




<div class="content">

	<div class="container-fluid">
		<div class="row">
		<div class="col-md-4">
				<div class="card">
					<div class="cardHeaderStyle card-header card-header-primary">
						<div>
							<h4 class="card-title">Edit Profile</h4>
							<p class="card-category">Complete your profile</p>
						</div>
					</div>
					<div class="card-body">
						<form>
							<div class="row">
								<div class="col-md-6">
									<div class="form-group">
										<label class="bmd-label-floating">ID</label> <input
											type="text" class="form-control" readonly="readonly"
											name='mid' value="${manager.mid }" >
									</div>
								</div>

								<div class="col-md-6">
									<div class="form-group">
										<label class="bmd-label-floating">Phone</label> <input
											type="text" class="form-control" readonly="readonly" value="${manager.phone }"
											>
									</div>
								</div>
								</div>
								<div class="row">
								<div class="col-md-6">
									<div class="form-group">
										<label class="bmd-label-floating">Email address</label> <input
											type="email" class="form-control" readonly="readonly" value="${manager.email }"
											>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-md-12">
									<div class="form-group">
										<label class="bmd-label-floating">가입일</label> <input
											type="text" class="form-control" readonly="readonly" value="${manager.regdate }"
											>
									</div>
								</div>
							</div>
							
							<button type="submit" class="modBtn btn btn-primary btn-round pull-right">수정</button>
							<a href="/admin/store/register?mid=${manager.mid }" class="btn btn-primary btn-round">매장등록</a>

							<div class="clearfix"></div>
						</form>
					</div>
				</div>
			</div>
		<c:forEach items="${store }" var="store">
			<div class="col-md-8">
				<div class="card card-profile">
					<div class="card-avatar" >
						 <img class="imgLogo" style="width: 130px; height: 130px"
							src="/admin/common/logo/view?link=${store.sno }/${store.logoImg}" />
							
					</div>
					
					<div class="card-body" style="text-align: left;">
						<div class="row" style="text-align: center;">
								<div class="col-md-12">
									<p>${store.sname }<p> 
								</div>
						</div>
						<div class="row">
								<div class="col-md-6">
									<div class="form-group">
										<label class="bmd-label-floating">카테고리</label> <input
											type="text" class="form-control" readonly="readonly"
											name='category' value="${store.category }" >
									</div>
								</div>
								<div class="col-md-6">
									<div class="form-group">
										<label class="bmd-label-floating">Kiosk Serial Number</label> <input
											type="text" class="form-control" readonly="readonly"
											name='kiosk' >
									</div>
								</div>
								<div class="col-md-12">
									<div class="form-group">
										<label class="bmd-label-floating">주소</label> <input
											type="text" class="form-control" readonly="readonly"
											name='addr' value="${store.address }">
									</div>
								</div>
						</div>
						


						<a href="/admin/store/menuList?sno=${store.sno }&cno=1" class="btn btn-primary btn-round">메뉴관리</a>
						<a href="/admin/store/modify?mid=${store.mid }&sno=${store.sno}" class="btn btn-primary btn-round">수정</a>
						<a href="" data-value=${store.sno } class="delBtn btn btn-primary btn-round">삭제</a>
					</div>
				</div>
			</div>	
			<div class="col-md-4">
			</div>	
		</c:forEach>
		
		
				
		</div>
	</div>
</div>

<script>
const csrfTokenValue = "${_csrf.token}"

// 수정
document.querySelector(".modBtn").addEventListener("click" , function(e){
	e.preventDefault()
	$(".modModal").modal("show")
}, false)

// 수정 보내기
document.querySelector(".modCommit").addEventListener("click" , function(e){
	
	e.preventDefault()
	const mid = document.querySelector("input[name='mid']").value
	const phone = document.querySelector("input[name='phone']").value
	const email = document.querySelector("input[name='email']").value
	const managerDTO = {mid:mid , phone:phone , email:email}
	
	const path : "/admin/manager/modifyMan"
	
	service.sendRegister(managerDTO, path, csrfTokenValue).then(result => {$(".modConfirm").modal("show")})
	
	document.querySelector(".modConBtn").addEventListener("click" , function(e){
	location.reload()
} , false)

} , false)


// 수정 취소
document.querySelector(".modClose").addEventListener("click" , function(e){
	$(".modModal").modal("hide")
} , false)

document.querySelectorAll(".delBtn").forEach(event => {
	event.addEventListener("click", function(e){
		e.preventDefault()
		const sno = e.target.dataset.value
		$(".delModal").modal("show")
		
		document.querySelector(".delAgree").addEventListener("click" , function(e){

	const path = "/admin/store/delete"
	service.sendRegister(sno, path, csrfTokenValue)then(res => res.text()).then(result => {
		$(".delModal").modal("hide")
		$(".delModalCon").modal("show")
	   console.log("삭제")
	} , false)
	})
}) 
})

document.querySelector(".delCancel").addEventListener("click" , function(e){
		   $(".delModal").modal("hide")
   } , false)
      
   document.querySelector(".delCommit").addEventListener("click" , function(e){
	   location.reload()
   } , false)

</script>


<%@ include file="../includes/footer.jsp"%>