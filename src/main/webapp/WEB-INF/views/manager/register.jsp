<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="../includes/header.jsp"%>


<div class="regModal modal" tabindex="-1">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title">Modal title</h5>
      </div>
      <div class="modal-body">
        <p>회원 가입이 완료되었습니다.</p>
      </div>
      <div class="modal-footer">
        <button type="button" class="regCommit btn btn-primary" >확인</button>
      </div>
    </div>
  </div>
</div>

<div class="content">

   <div class="container-fluid">
   
	<div class="row">
         <div class="col-md-8">
            <div class="card">
               <div class="card-header card-header-primary">
                  <h4 class="card-title">Edit Profile</h4>
                  <p class="card-category">Complete your profile</p>
               </div>
                <form class="regMan" action="/admin/manager/register" method="post" accept-charset="UTF-8"> 
               <div class="card-body">
                  
                     <div class="row">
                        <div class="col-md-6">
                           <div class="form-group">
                              <label class="bmd-label-floating">ID</label>
                              <input name="mid" type="text" class="form-control" >
                           </div>
                        </div>
                        <div class="col-md-6">
                           <div class="form-group">
                              <label class="bmd-label-floating">PassWord</label> 
                              <input name="mpw" type="text" class="form-control" >
                           </div>
                        </div>
                        </div>
                        <div class="row">
                        <div class="col-md-6">
                           <div class="form-group">
                              <label class="bmd-label-floating">Phone</label> 
                              <input name = "phone" type="text" class="form-control">
                           </div>
                        </div>
                        <div class="col-md-6">
                           <div class="form-group">
                              <label class="bmd-label-floating">Email address</label> 
                              <input name="email" type="email" class="form-control">
                           </div>
                        </div>
                        </div>
                       <!--  <div class="row">
                        <div class="col-md-12">
                           <div class="form-group is-focused" >
                              <label class="bmd-label-floating">Address</label> 
                              <input type="text" onClick="goPopup();" class="form-control" id="roadFullAddr"  >
                           </div>
                        </div>
                        </div> -->
                    <div class="docUpload">
                     <div class="row">
                        <div class="cdn col-md-6">
                           <div class="form-group bmd-form-group is-focused">
                              <label class="bmd-label-floating">사업자등록증</label> 
                              
                           </div>
                           
                           <div style = "margin-bottom: 10px">                        
                              <input type="file" name="cdn" class="form-control" id="inputGroupFile02" >
                           </div>
                           
                           <div class = "fileThumb">
                           
                           </div>
                       </div>
                       
                       <div class="health col-md-6">
                           <div class="form-group bmd-form-group is-focused">
                              <label class="bmd-label-floating">보건증</label> 
                              
                           </div>
                           
                           <div style = "margin-bottom: 10px">                        
                              <input type="file" name="health" class="form-control" id="inputGroupFile02" >
                           </div>
                           
                           <div class = "fileThumb">
                           
                           </div>
                        </div>
                           
                     </div>
                     
                     <div class="row">
                        <div class="hygiene col-md-6">
                           <div class="form-group bmd-form-group is-focused">
                              <label class="bmd-label-floating">위생확인증</label> 
                              
                           </div>
                           
                           <div style = "margin-bottom: 10px">                        
                              <input type="file" name="hygiene" class="form-control" id="inputGroupFile02" >
                           </div>
                           
                           <div class = "fileThumb">
                           
                           </div>
                        </div>
                        
                        <div class="license col-md-6">
                           <div class="form-group bmd-form-group is-focused">
                              <label class="bmd-label-floating">사업허가증</label> 
                              
                           </div>
                           
                           <div style = "margin-bottom: 10px">                        
                              <input type="file" name="license" class="form-control" id="inputGroupFile02" >
                           </div>
                           
                           <div class = "fileThumb">
                           
                           </div>
                        </div>            
                     </div>
                    </div>
                    
                    <div>
                    	<input type="hidden" name="uploadPath" class="form-control">
                    </div>
                     
                     
                     <button class="cancelBtn btn btn-primary btn-round pull-right">취소</button>
                     <button type="submit" class="regBtn btn btn-danger btn-round pull-right">등록</button>
                     
                     
                     <div class="clearfix"></div>
                 
               </div>
                </form>
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
                  <h4 class="card-title">${store.sname }</h4>
                  <p class="card-description">${store.address }</p>
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

 
document.querySelector(".docUpload").addEventListener("click" , function(e){

	e.target.addEventListener("change", function(e){
	  	e.preventDefault()
	    const fd = new FormData() 
	    const files = e.target.files
	    fd.append("files", files[0])
	    fd.append("value", e.target.name)
	    service.sendUpload(fd).then(result => {
	    	console.dir(result[0])
	    	e.target.setAttribute("data-fileName" , result[0].fileName)
	    }) 
	   
	})
	
} , false)


document.querySelector(".cancelBtn").addEventListener("click" , function(e) {
	e.preventDefault()
	location.href = "/admin/manager/list"
})

// registerPost

document.querySelector(".regBtn").addEventListener("click" , function(e) {
	e.preventDefault()
	const obj = {}
	const form = document.querySelector(".regMan")
	const input = document.querySelectorAll(".regMan input")
	
	 for (let i of input.length) {
		obj[form.elements[i].name] = form.elements[i].value
	} 
	
	obj.cdn = document.querySelector("input[name='cdn']").dataset.filename
	obj.health = document.querySelector("input[name='health']").dataset.filename
	obj.hygiene = document.querySelector("input[name='hygiene']").dataset.filename
	obj.license = document.querySelector("input[name='license']").dataset.filename
	
	
	service.sendRegister(obj, "/admin/manager/register").then(result => {$(".regModal").modal("show")}) 
	
} , false)

// regCommit
document.querySelector(".regCommit").addEventListener("click" , function(e){
	
	location.href = "/admin/manager/list"
	
} , false)

// removeFile

function sendRemove(param){
   
   alert("remove")
   console.log(param)
   fetch("/admin/common/manager/removeFile" , {
      method : 'delete' ,
      headers : {'Content-Type':'application/json'} ,
      body : param
   }).then(res => console.log(res))
}

/* //juso
function goPopup(){
   // 주소검색을 수행할 팝업 페이지를 호출합니다.
   // 호출된 페이지(jusopopup.jsp)에서 실제 주소검색URL(https://www.juso.go.kr/addrlink/addrCoordUrl.do)를 호출하게 됩니다.
   var pop = window.open("/admin/manager/jusoPopup","pop","width=570,height=420, scrollbars=yes, resizable=yes"); 
}


function jusoCallBack(roadFullAddr){
      // 팝업페이지에서 주소입력한 정보를 받아서, 현 페이지에 정보를 등록합니다.
      document.querySelector("#roadFullAddr").value = roadFullAddr;   
} */


</script>

<%@ include file="../includes/footer.jsp"%>