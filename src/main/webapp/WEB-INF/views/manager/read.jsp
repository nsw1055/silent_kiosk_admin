<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="../includes/header.jsp"%>

<div class="modal1 modal" tabindex="-1">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title">Modal title</h5>
				<button type="button" class="btn-close" data-bs-dismiss="modal"
					aria-label="Close"></button>
			</div>
			<div class="modal-body">
				<p>${manager.enabled == false ? "등록" :" 삭제" }하시겠습니까?</p>
			</div>
			<div class="modal-footer">
				<button type="button" class="delCancel btn btn-secondary"
					data-bs-dismiss="modal">Close</button>
				<button type="button" class="delAgree btn btn-primary">${manager.enabled == false ? "등록" :" 삭제" }</button>
			</div>
		</div>
	</div>
</div>

<div class="modal2 modal" tabindex="-1">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title">Modal title</h5>
			</div>
			<div class="modal-body">
				<p>${manager.enabled == false ? "등록" :" 삭제" }하였습니다.</p>
			</div>
			<div class="modal-footer">
				<button type="button" class="delCommit btn btn-primary">확인</button>
			</div>
		</div>
	</div>
</div>

<div class="modal3 modal" tabindex="-1">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title">Modal title</h5>
			</div>
			<div class="modal-body">
				<p>가입 승인되었습니다</p>
			</div>
			<div class="modal-footer">
				<button type="button" class="appCommit btn btn-primary">확인</button>
			</div>
		</div>
	</div>
</div>

<div class="modal4 modal" tabindex="-1">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title">Modal title</h5>
				<button type="button" class="btn-close" data-bs-dismiss="modal"
					aria-label="Close"></button>
			</div>
			<div class="modal-body">
				<p>삭제 하시겠습니까?</p>
			</div>
			<div class="modal-footer">
				<button type="button" class="delCancel2 btn btn-secondary"
					data-bs-dismiss="modal">Close</button>
				<button type="button" class="delDoc btn btn-primary">삭제</button>
			</div>
		</div>
	</div>
</div>

<div class="modal5 modal" tabindex="-1">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title">Modal title</h5>
			</div>
			<div class="modal-body">
				<p>삭제되었습니다</p>
			</div>
			<div class="modal-footer">
				<button type="button" class="delCommit2 btn btn-primary">확인</button>
			</div>
		</div>
	</div>
</div>




<div class="content">

	<div class="container-fluid">
		<div class="row">
			<div class="col-md-8">
				<div class="card">
					<div class="cardHeaderStyle card-header card-header-primary">
						<div>
							<h4 class="card-title">Edit Profile</h4>
							<p class="card-category">Complete your profile</p>
						</div>

						<div class="cardHeaderBtn approvalBtn">
							<c:if test="${manager.approval == false}">
								<button type="submit" class="btn btn-primary pull-right"
									style="background-color: #ffffff; color: #ee6d09;">가입
									승인</button>
							</c:if>
						</div>

					</div>
					<div class="card-body">
						<form>
							<div class="row">
								
								<div class="col-md-6">
									<div class="form-group">
										<label class="bmd-label-floating">ID</label> <input
											type="text" class="form-control" readonly="readonly"
											name='mid' value=${manager.mid }>
									</div>
								</div>

								<div class="col-md-6">
									<div class="form-group">
										<label class="bmd-label-floating">Phone</label> <input
											type="text" class="form-control" readonly="readonly"
											value=${manager.phone }>
									</div>
								</div>
								<div class="col-md-6">
									<div class="form-group">
										<label class="bmd-label-floating">Email address</label> <input
											type="email" class="form-control" readonly="readonly"
											value=${manager.email }>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-md-12">
									<div class="form-group">
										<label class="bmd-label-floating">가입일</label> <input
											type="text" class="form-control" readonly="readonly"
											value=${manager.regdate }>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-md-6">
									<div class="form-group">

										<label class="bmd-label-floating">enabled</label> <input
											type="text" class="form-control" readonly="readonly"
											value=${manager.enabled == false ? "승인되지&nbsp않음" :" 승인됨" }>

									</div>
								</div>
								<div class="col-md-6">
									<div class="form-group">
										<label class="bmd-label-floating">approval</label> <input
											type="text" class="form-control" readonly="readonly"
											value=${manager.approval == false ? "승인되지&nbsp않음" :" 승인됨" }>
									</div>
								</div>
							</div>
							<button type="submit"
								class="delBtn btn btn-${manager.enabled == false ? 'primary' : 'danger' } btn-round pull-right">${manager.enabled == false ? "등록" : "삭제" }</button>
							<button type="submit"
								class="modBtn btn btn-primary btn-round pull-right">수정</button>

							<div class="clearfix"></div>
						</form>
					</div>
				</div>
			</div>
			<div class="col-md-4">
				<div class="card card-profile">
					<div class="card-avatar">
						<a href="javascript:;"> <img class="img"
							src="../resources/assets/img/faces/marc.jpg" />
						</a>
					</div>
					<div class="card-body">
						<h4 class="card-title">storeName</h4>
						<p class="card-description">storeAddress</p>
						<div class="docDiv">
							<%-- <c:forEach items="${store.docFiles }" var="doc">
								<div class="doctest" style="display: flex; flex-direction: row;">
									<p class="docFile card-description"
										data-href="${doc.muploadPath }/${doc.muuid }_${doc.mfileName }">${doc.mfileName }</p>
									<button rel="tooltip" title="Remove"
										class="fileDelete btn btn-danger btn-link btn-sm"
										value="${doc.muuid }">
										<i class="material-icons" style="margin-bottom: auto;">close</i>
									</button>
								</div>
							</c:forEach> --%>
						</div>


						<a href="javascript:;" class="btn btn-primary btn-round">Follow</a>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>


<script>
/* manList.sendList(${pageDTO.page} , ${pageDTO.perSheet}).then(res => res.json()).then(result => {
	for (let resultElement of result) {
		
		tableList.innerHTML += "<tr><td><img src= '"+resultElement.logoImg+"'></td>" +
		"<td onclick='sendRead("+JSON.stringify(resultElement.mid)+")'>"+resultElement.mid+"</td>" +
		"<td>"+resultElement.email+"</td>" +
		"<td>"+resultElement.phone+"</td>" +
		"<td>"+resultElement.enabled+"</td>" +
		"<td>"+resultElement.approval+"</td>" +
		"<td>"+resultElement.regdate+"</td>" +
		"<td>"+resultElement.updatedate+"</td></tr>"
	}
	
}) */

	const mid = document.querySelector("input[name='mid']").value
// delete

document.querySelector(".delBtn").addEventListener("click" , function(e){

	e.preventDefault()

	$(".modal1").modal("show")

} , false)

document.querySelector(".delAgree").addEventListener("click" , function(e){

	e.preventDefault()
	$(".modal1").modal("hide")
	

	//console.log(mid)
	const sendDelete=(
	function sendDel(){
	
 		return fetch ("/admin/manager/delete" , {
				method : 'post' ,
				headers : {'Content-Type':'application/json'} ,
				body : mid		
 		}).then(res => res.text()).then(result => $(".modal2").modal("show"))
 		
	})();
	
	//location.href = "/admin/manager/read?mid=" + mid
} , false)

// delCommit
document.querySelector(".delCommit").addEventListener("click" , function(e){

	e.preventDefault()
	
	location.href = "/admin/manager/read?mid=" + mid
} , false)

// delCancel
document.querySelector(".delCancel").addEventListener("click" , function(e){

	e.preventDefault()
	
	$(".modal1").modal("hide")
} , false)

document.querySelector(".delCancel2").addEventListener("click" , function(e){

	e.preventDefault()
	
	$(".modal4").modal("hide")
} , false)

// approval
 document.querySelector(".approvalBtn").addEventListener("click", function(e){
	
	e.preventDefault()
	function approval() {
		return fetch("/admin/manager/approval", {
			method : 'post',
			headers : {"Content-Type":"application/json"} , 
			body : mid
		}).then(res => res.text())
	}
	
	approval().then(result => $(".modal3").modal("show"))
	
	//location.href = "/admin/manager/read?mid=" + mid
}) 

// approval modal commit
document.querySelector(".appCommit").addEventListener("click" , function(e){
	
	location.reload()
	
} , false)

document.querySelector(".delCommit2").addEventListener("click" , function(e){
	
	location.reload()
	
} , false)

// fileDonwload

/* 	document.querySelectorAll(".docFile").forEach(e => {
		
		e.addEventListener("click" , function(event){
			

				const href = event.currentTarget.getAttribute("data-href")
			
				console.log(href)
	
			location.href = "/admin/common/manager/download?link=" +href
			
		})
		
	}) */
	

// deleteFile

/* document.querySelectorAll(".fileDelete").forEach(e =>{
	
		e.addEventListener("click" , function(event){
		
		const muuid = event.currentTarget.getAttribute("value")

		const hrefVal = service.getHref()
		console.log(muuid)
		console.log(hrefVAl)
	/* 	function sendDelete(){
		
			return fetch("/admin/common/manager/delete", {
				method : 'post',
				headers : {"Content-Type" : "application/json"},
				body : JSON.stringify(muuid)
			}).then(res => res.text())
		
		}
	
		sendDelete().then(result => {console.log(result)}) 
		
	} , false)
})  */

 document.querySelectorAll(".doctest").forEach(e => {

	e.addEventListener("click", function(e){
		const link = this.children[0].getAttribute("data-href")
		if(event.target.nodeName == 'I'){
			$(".modal4").modal("show")
			
			document.querySelector(".delDoc").addEventListener("click", function(e){
				function sendDelete(){
				
					return fetch("/admin/common/manager/delete", {
						method : 'post',
						headers : {"Content-Type" : "application/text; charset=utf-8"},
						body : link
					}).then(res => res.text())
					
				}
		
			sendDelete().then(result => {$(".modal4").modal("hide")
				$(".modal5").modal("show")	
			})
		})
				
			 /* function sendDelete(){
				
				return fetch("/admin/common/manager/delete", {
					method : 'post',
					headers : {"Content-Type" : "application/text; charset=utf-8"},
					body : link
				}).then(res => res.text())
			
			}
		
			sendDelete().then(result => {console.log(result)})  */
			
			
		}else{
				location.href = "/admin/common/manager/download?link=" +link
			
		}
	} , false)
}) 



</script>

<%@ include file="../includes/footer.jsp"%>