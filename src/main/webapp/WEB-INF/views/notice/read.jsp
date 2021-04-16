<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="../includes/header.jsp"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<div class="content">
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-12">
				<div class="card">
					<div class="card-header card-header-primary noticeHeader">
						<h3>
							<c:out value=" ${notice.title }"></c:out>
						</h3>
						<h5>
							<c:out value=" ${notice.writer }"></c:out>
						</h5>
						<h5>
							<c:out value=" ${notice.regdate }"></c:out>
						</h5>
					</div>
					<div class="contentBox card-body noticeContent">
						<div class="fileDiv" style="text-align: center;"></div>
						<div style="white-space: pre;">
							<h4>
								<c:out value="${notice.content }"></c:out>
							</h4>
						</div>
					</div>
					<div class="btnContainer">
						<sec:authentication property="principal" var="pinfo" />
						<sec:authorize access="isAuthenticated()">
							<c:if test="${pinfo.username eq notice.writer}">
								<button class="btn btn-primary btn-round deleteBtn">삭제</button>
								<button class="btn btn-primary btn-round modifyBtn">수정</button>
							</c:if>
						</sec:authorize>
						<button class="btn btn-primary btn-round listBtn">목록으로</button>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>


<div class="modal" id="deleteModal" tabindex="-1" role="dialog">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title">삭제 확인</h5>
			</div>
			<div class="modal-body">
				<p>삭제하시겠습니까?</p>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-primary modalDeleteBtn">삭제</button>
				<button type="button" class="btn btn-secondary" data-dismiss="modal">닫기</button>
			</div>
		</div>
	</div>
</div>

<div class="modal" id="checkModal" tabindex="-1" role="dialog">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title">삭제 확인</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body checkModalBody"></div>
			<div class="modal-footer">
				<button type="button" class="btn btn-primary checkBtn">확인</button>
			</div>
		</div>
	</div>
</div>


<form action="/admin/notice/list" class="actionForm">
	<input type="hidden" name="page" value="${pageDTO.page }"> <input
		type="hidden" name="perSheet" value="${pageDTO.perSheet }"> <input
		type="hidden" name="type" value="${pageDTO.type }"> <input
		type="hidden" name="keyword" value="${pageDTO.keyword }">
</form>


<script src="/admin/resources/service.js"></script>
<script>

const csrfTokenValue = "${_csrf.token}"

const header = document.querySelector(".noticeHeader")
const content = document.querySelector(".noticeContent")
const actionForm = document.querySelector(".actionForm")

document.querySelector(".listBtn").addEventListener("click", function(e){
	
	actionForm.submit()
	
}, false)


service.getFiles(${nno}).then(res=>res.json()).then(files => {

   let str=""

	for(const file of files){
		
	if(file.image){
		str += "<li id='li"+file.uuid+"'>"+file.fileName+"<br><img style='width:30vw;' src='/admin/common/notice/view?link="+file.link+"'/></li>"
	}else{
		str += "<li id='li"+file.uuid+"'><a href='/admin/common/notice/download?link="+file.link+"'><i class='fas fa-file'></i></a>"+file.fileName+"</li>" 
	}
}
   document.querySelector(".fileDiv").innerHTML += str
   
   })

const deleteBtn = document.querySelector(".deleteBtn")

if(deleteBtn !== null){

deleteBtn.addEventListener("click", function(e){
	
	$("#deleteModal").modal("show")
	
},false)

}

document.querySelector(".modalDeleteBtn").addEventListener("click", function(e){
	
	const deleteN = service.deleteNotice(${nno},csrfTokenValue, "${notice.writer}");
	deleteN.then(result => {
		document.querySelector(".checkModalBody").innerHTML += "<p>"+result+"<p>"
	})
	$("#registerModal").modal("hide")
		
	$("#checkModal").modal("show")
	
},false)

document.querySelector(".checkBtn").addEventListener("click", function(){
		
		actionForm.submit()
		
	},false)

	const modifyBtn = document.querySelector(".modifyBtn")
	
	if(modifyBtn !== null){
	
modifyBtn.addEventListener("click", function(e){
	
	actionForm.innerHTML += "<input type='hidden' name='nno' value='${notice.nno}'>"
	
	actionForm.innerHTML += "<input type='hidden' name='writer' value='${notice.writer}'>"
	
	actionForm.setAttribute("action", "/admin/notice/modify")
	
	actionForm.submit()
}, false)

	}



</script>

<%@ include file="../includes/footer.jsp"%>