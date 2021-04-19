<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="../includes/header.jsp"%>
<div class="content">
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-12">
				<div class="card">
					<div class="card-header card-header-primary">
						<h4 class="card-title">공지사항 등록</h4>
					</div>
					<div class="card-body">
						<form>
							<div class="row">
								<div class="col-md-6">
									<div class="form-group bmd-form-group">
										<label class="bmd-label-floating">제목</label> <input
											type="text" name="title" class="form-control"
											value="${notice.title}">
									</div>
								</div>
								<div class="col-md-3">
									<div class="form-group bmd-form-group">
										<label class="bmd-label-floating">작성자</label> <input
											type="text" name="writer" class="form-control"
											value="${notice.writer}" readonly="readonly">
									</div>
								</div>
								<div class="col-md-3">
									<div class="form-group bmd-form-group">
										<select style='width: px;' class="selectCate custom-select">
											<option ${notice.category == "안내"? "selected" :"" }
												value="안내">안내</option>
											<option ${notice.category == "긴급"? "selected" :"" }
												value="긴급">긴급</option>
											<option ${notice.category == "이벤트"? "selected" :"" }
												value="이벤트">이벤트</option>
										</select>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-md-12">
									<div class="form-group">
										<label>내용</label>
										<div class="form-group bmd-form-group">
											<textarea class="form-control" name="content" rows="20"><c:out
													value="${notice.content}" /></textarea>
										</div>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-md-12">
									<input style="height: 10vh;" type="file" multiple="multiple"
										name="files">
								</div>
							</div>
							<div class="row">
								<ul class="fileUl">
								</ul>
							</div>
							<hr />
							<div class="btnContainer">
								<div class="checkbox"
									style="display: flex; flex-direction: row;">
									<h5>공지사항 고정</h5>
									<input style="margin-left: 10px; margin-top: 5px;"
										type="checkbox" class="checkShowed" name="showed"
										${notice.showed==true?"checked":"" }>
								</div>
								<button class="btn btn-primary btn-round modifyBtn">수정</button>
								<button class="btn btn-primary btn-round cancelBtn">수정
									취소</button>
							</div>
						</form>
					</div>
				</div>
			</div>
			<div class="col-md-4">
				<div class="card card-profile"></div>
			</div>
		</div>
	</div>
</div>


<div class="modal" id="modifyModal" tabindex="-1" role="dialog">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title">수정 확인</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<p>수정하시겠습니까?</p>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-primary modalModifyBtn">수정</button>
				<button type="button" class="btn btn-secondary" data-dismiss="modal">닫기</button>
			</div>
		</div>
	</div>
</div>

<div class="modal" id="checkModal" tabindex="-1" role="dialog">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title">등록 확인</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body checkModalBody"></div>
			<div class="modal-footer">
				<button type="button" class="btn btn-primary checkBtn" >확인</button>
			</div>
		</div>
	</div>
</div>


<form action="/admin/notice/read" class="actionForm">
	<input type="hidden" name="page" value="${pageDTO.page }">
	<input type="hidden" name="perSheet" value="${pageDTO.perSheet }">
	<input type="hidden" name="type" value="${pageDTO.type }">
	<input type="hidden" name="keyword" value="${pageDTO.keyword }">
</form>


<script src="/admin/resources/service.js"></script>
<script>


   const csrfTokenValue = "${_csrf.token}";

   const actionForm = document.querySelector(".actionForm")
   
   function modalHide() {
			$("#checkModal").modal("hide")
	}
   
   function modifySuccess(){
	   
	  		 actionForm.innerHTML += "<input type='hidden' name='nno' value='${notice.nno}'>"
		      
		     actionForm.setAttribute("action", "/admin/notice/read")
		      
		     actionForm.submit()
   }

   document.querySelector(".modifyBtn").addEventListener("click", function(e) {

   e.preventDefault();
      
    $("#modifyModal").modal("show")

   }, false)
   
   const arr = []

   var category = "${notice.category}"
   
   document.querySelector(".modalModifyBtn").addEventListener("click", function(e) {
	   
      const title = document.querySelector("input[name='title']").value
      
      const writer = document.querySelector("input[name='writer']").value
      
      const content = document.querySelector("textarea[name='content']").value
      
  	  const checkShowed = document.querySelector(".checkShowed")
      
      const obj = {nno:${notice.nno}, title:title, category:category, writer:writer, content:content, showed:checkShowed.checked, list:arr}
      
      service.sendJson("/admin/notice/modify",obj,csrfTokenValue).then(result => 
      {
  			console.dir(result)
  			
  		if(result[0]){
  			
  			document.querySelector(".checkModalBody").innerHTML = "<p>"+result[0].defaultMessage+"</p>"
  			
  			$("#modifyModal").modal("hide")
  			
  			$("#checkModal").modal("show")
  			
  			document.querySelector(".checkBtn").setAttribute("onclick", "modalHide()")
  			
  		}else{
  			
	  		document.querySelector(".checkModalBody").innerHTML = "<p>수정완료</p>"
	  		
	  		document.querySelector(".checkBtn").setAttribute("onclick", "modifySuccess()")
	  		
	  		$("#modifyModal").modal("hide")
	  		
	  		$("#checkModal").modal("show")
	  	}
	  })
      
   }, false)
   
   const sCate =  document.querySelector(".selectCate")
   
  sCate.addEventListener("change",function(){
	   
	   const cateIdx = sCate.selectedIndex
	   
	   category = sCate[cateIdx].value
	   
   },false)
   
/*    document.querySelector(".checkBtn").addEventListener("click", function(e){
	   
	  e.preventDefault()
      
      actionForm.innerHTML += "<input type='hidden' name='nno' value='${notice.nno }'>"
      
      actionForm.setAttribute("action", "/admin/notice/read")
      
      actionForm.submit()
      
   },false) */
   
   
   const fileUl = document.querySelector(".fileUl")
   
   document.querySelector("input[name='files']").addEventListener("change", function(e){
      
      e.preventDefault()
      
      const formdata = new FormData()
      
      const files = document.querySelector("input[name='files']").files
      
      for(var i = 0; i < files.length ; i++){
         
         formdata.append("uploadFile", files[i])
         
      }
      
      service.upload(formdata,csrfTokenValue).then(jsonObj => 
      
       { console.log(jsonObj)
         for(var i = 0 ; i< jsonObj.length; i++){
         
         var file = jsonObj[i];
         
         arr.push(file)
         
         console.log("arr: "  + arr)
         
         if(!file.image){
            
               console.log(file.link)         
               fileUl.innerHTML += "<li id='li"+file.uuid+"'><i class='fas fa-file'></i></a>"+file.fileName+"<button onclick='delTempImg(event, JSON.stringify("+file+"))'>삭제</button></li>" 
         
         }else{
         fileUl.innerHTML += "<li id='li"+file.uuid+"'>"+file.fileName+"<img src='/admin/common/notice/preview?link="+file.thumbLink+"'/><button onclick='delTempImg(event, "+JSON.stringify(file)+")'>삭제</button></li>"

         }   
      }})
      
   }, false)
   
   document.querySelector(".cancelBtn").addEventListener("click", function(e){
      
      e.preventDefault();
      
      actionForm.innerHTML += "<input type='hidden' name='nno' value='${notice.nno }'>"
      
      actionForm.submit();
      
   },false)
   
   
      function delTempImg(event, param){
      
      console.log(event)
         
      event.preventDefault()
      
      console.log(param)
       
       fileUl.querySelector("#li"+param.uuid).remove();

   } 
   
service.getAjax("/admin/common/notice/getFiles?nno=${notice.nno}").then(res => res.json()).then(files => {
	  
	   var str = ""
	   
	   for(var i =0; i<files.length; i++){
		   
		   let file = files[i]
		   
		   arr.push(file)
		   
		   if(file.image){
			   str += "<li id='li"+file.uuid+"'>"+file.fileName+"<img src = '/admin/common/notice/view?link="+file.thumbLink+"'/><button onclick='deleteImg(event,"+JSON.stringify(file)+")'>삭제</button></li>"
		   }else{
			   str +="<li id='li"+file.uuid+"'><i class='fas fa-file'></i>"+file.fileName+"<button onclick='deleteImg(event,"+JSON.stringify(file)+")'>삭제</button></li>"
		   }
		   
	   }
	   
	   fileUl.innerHTML += str
})


   
function deleteImg(param,file){
	
	const list = fileUl.querySelectorAll("li")
	
	const fileli = document.querySelector("#li"+file.uuid)
	
	param.preventDefault()
	
	console.log(file)
	
	const check = (element) => JSON.stringify(element) == JSON.stringify(file);
	
	const idx = arr.findIndex(check);
	
	console.log(idx)

	arr.splice(idx,1)
   	
   	console.dir(arr)
	
	fileli.remove()
	
}
   
   
</script>

<%@ include file="../includes/footer.jsp"%>