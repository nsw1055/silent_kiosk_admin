<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="../includes/header.jsp"%>
<%
request.setCharacterEncoding("UTF-8");
String sno = request.getParameter("sno");
%>

<div class="modModal modal" tabindex="-1">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title">Modal title</h5>
			</div>
			<form action="/admin/store/menuModify" class="actionForm"
				method="post">
				<div class="modal-body">
					<div class="card card-stats">
						<div class="card-header card-header-warning card-header-icon">
							<div class="card-icon imgDiv ">
								<img class='menu-img modal-img  ' src='/admin/resources/assets/img/image.png'>
							</div>

							<div class="form-group">
								<input type="hidden" class="form-control" name='tno' value="" >
							</div>

							<div class="form-group">
								<label class="bmd-label-floating"></label> <input type="text"
									class="form-control" name='tname' value="" placeholder="토핑이름">
							</div>
							<div class="form-group">
								<label class="bmd-label-floating"></label> <input type="text"
									class="form-control" name='tprice' value="" placeholder="가격">
							</div>
							<div class="form-group">
								
							</div>
							 <div style = "margin-bottom: 10px">                        
                           		   <input type="file" name="timg" data-fileName="" class="form-control" id="inputGroupFile02" >
                          		 </div>
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



<div class="commitModal modal" tabindex="-1">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title">Modal title</h5>
			</div>
			<div class="modal-body">
				<p>수정되었습니다</p>
			</div>
			<div class="modal-footer">
				<button type="button" class="modConfirm btn btn-primary">확인</button>
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
			<div class="col-md-12">
				<div class="card">
					<div class="cardHeaderStyle card-header card-header-primary">
						<div>
							<h4 class="card-title ">${sname }</h4>
							<p class="card-category">Here is a subtitle for this table</p>
						</div>
					</div>

					<div class="card-body">

						<ul class="navCat nav nav-pills nav-fill">
							<li class="nav-item"><a class="catNav nav-link "
								data-value="1">메인메뉴</a></li>
							<li class="nav-item"><a class="catNav nav-link "
								data-value="2">사이드메뉴</a></li>
							<li class="nav-item"><a class="catNav nav-link "
								data-value="3">음료</a></li>
							<li class="nav-item"><a class="navTopp nav-link active"
								data-value="topp">토핑</a></li>
						</ul>

						<div class="row">
							<c:forEach items="${topping }" var="topping">

								<div class="col-lg-3 col-md-6 col-sm-6">
									<div class="menuInfo card card-stats">
										<div class="card-header card-header-warning card-header-icon">
											<div class="card-icon imgDiv ">
												<img class ="menu-img" src="/admin/common/topping/view?link=<%=sno %>/${topping.tno }/${topping.timg}" />
											</div>
											<h5 class="tname card-title">${topping.tname }</h5>
											<p class="tprice card-category">${topping.tprice }</p>
											<input type="hidden" class="timg" value=${topping.timg }/>
										</div>
										<div class="card-footer">
											<div class="menuBtn stats" data-mno="${topping.tno }">
												<button type="submit"
													class="modBtn btn btn-primary pull-right"
													style="padding: 5px;" value="/admin/store/toppingModify">수정</button>
												<button type="submit"
													class="delBtn btn btn-danger pull-right"
													style="padding: 5px;">삭제</button>
												
											</div>
										</div>
									</div>
								</div>
							</c:forEach>
								<div class="col-lg-3 col-md-6 col-sm-6">
									<div class="card card-stats">
										<div class="card-header card-header-warning card-header-icon">
											
											<h5 class="card-title"> </h5>
											<p class="card-category"> </p>
											<p class="card-category"> </p>
											<p class="card-category"> </p>

										</div>
										<div class="card-footer">
											<div class="stats" >
												<button type="submit"
													class="regBtn btn btn-primary pull-right"
													style="" value="/admin/store/toppingRegister">등록</button>
											</div>
										</div>
									</div>
								</div>


						</div>
					</div>
				</div>

			</div>
		</div>
	</div>

	<form class="actionForm2" action="/admin/store/menuList" method="get">
		<input type="hidden" name="sno" value="<%= sno %>"> <input
			type="hidden" name="cno" value="">

	</form>


	<script type="text/javascript" src="../resources/service.js"></script>

	<script>
const actionForm = document.querySelector(".actionForm")
const actionForm2 = document.querySelector(".actionForm2")
const sno = document.querySelector("input[name='sno']").value
const csrfTokenValue = "${_csrf.token}";

//category
document.querySelector(".navCat").addEventListener("click", function(e){

    e.preventDefault()

    cno = e.target.dataset.value

    document.querySelector("input[name='cno']").value = cno
    
    if (cno == "topp"){
    	
    	actionForm2.action ="/admin/store/toppingList"
    	
    }

 	actionForm2.submit()

}, false)

//fileUpload
document.querySelector("input[name='timg']").addEventListener("change" , function(e){

	  	e.preventDefault()
	    const fd = new FormData() 
	    const files = e.target.files
	    fd.append("files", files[0])
	    fd.append("value", e.target.name)
	    service.sendUpload(fd,csrfTokenValue).then(result => {
	    	console.dir(result[0])
	    	e.target.setAttribute("data-fileName" , result[0].sfileName)
	    }) 
	   
	    service.sendUploadThumb(fd,csrfTokenValue)
	    
} , false)  


// toppingRegister
	document.querySelector(".regBtn").addEventListener("click", function(e){
        	actionForm.setAttribute("action" , document.querySelector(".regBtn").value)
        	
        	document.querySelector("input[name='tno']").value = ""
			document.querySelector("input[name='tname']").value = ""
			document.querySelector("input[name='tprice']").value = ""
			document.querySelector("input[name='timg']").value = ""
			document.querySelector(".modal-img").setAttribute("src" , "/admin/resources/assets/img/image.png")
			$(".modModal").modal("show")
        })

//toppingModify
	
	document.querySelectorAll(".menuInfo").forEach(event => {
		event.addEventListener("click", function(e){
			
			const tno = e.currentTarget.querySelector(".menuBtn").getAttribute("data-mno")
			const tname = e.currentTarget.querySelector(".tname").innerHTML
			const tprice = e.currentTarget.querySelector(".tprice").innerHTML
			const timg = e.currentTarget.querySelector(".timg").value
		
			
			if(e.target == e.currentTarget.querySelector(".modBtn")){
				
				actionForm.setAttribute("action" , document.querySelector(".modBtn").value)
				
				document.querySelector("input[name='tno']").value = tno
				document.querySelector("input[name='tname']").value = tname
				document.querySelector("input[name='tprice']").value = tprice
				document.querySelector("input[name='timg']").dataset.filename = timg
				
				document.querySelector(".modal-img").setAttribute("src" , "/admin/common/topping/view?link="+sno+"/"+tno+"/"+timg+"")
				
				$(".modModal").modal("show")
			}else if(e.target == e.currentTarget.querySelector(".delBtn")){
				$(".delModal").modal("show")
				
				document.querySelector(".delAgree").addEventListener("click" , function(e){
            const path = "/admin/store/toppingDelete" 
           service.sendRegister(tno,path,csrfTokenValue).then(result => {
            	$(".delModal").modal("hide")
            	$(".delModalCon").modal("show")
               console.log("삭제")
         } , false)
			})
			
			
	}
		
	})
	})
	


	 document.querySelector(".modCommit").addEventListener("click" , function(e){
		e.preventDefault()
		
		const tname = document.querySelector("input[name='tname']").value
		const tprice = document.querySelector("input[name='tprice']").value
		const timg = document.querySelector("input[name='timg']").dataset.filename
		const tno = document.querySelector("input[name='tno']").value
	console.dir(document.querySelector("input[name='timg']"))
		const toppingDTO = {tno:tno ,sno:sno, tname:tname , tprice : tprice , timg:timg}
		console.log(tprice)
		console.log(timg)
		console.log(JSON.stringify(toppingDTO))
		
		const path = actionForm.getAttribute("action")
		
		service.sendRegister(toppingDTO, path, csrfTokenValue).then(result => {
			$(".modModal").modal("hide")
			$(".commitModal").modal("show")})
		
	} , false)
	
	document.querySelector(".modConfirm").addEventListener("click" , function(e){
		location.reload()
	} , false)
	   document.querySelector(".delCancel").addEventListener("click" , function(e){
		   $(".delModal").modal("hide")
   } , false)
      
   document.querySelector(".delCommit").addEventListener("click" , function(e){
	   location.reload()
   } , false)
// modClose
    document.querySelector(".modClose").addEventListener("click" , function(e){
    	document.querySelector("input[name='timg']").value = ""
	   $(".modModal").modal("hide")
   })
   
</script>


	<%@ include file="../includes/footer.jsp"%>