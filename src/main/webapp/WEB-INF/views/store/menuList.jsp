<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="../includes/header.jsp"%>

<%
request.setCharacterEncoding("UTF-8");
String cno = request.getParameter("cno");
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
							<div class="card-icon">
								<i class="material-icons">content_copy</i>
							</div>
							
							<div class="selectPerSheet form-group" style ="z-index: 10000;">
								<select class="catSelect custom-select">
									<option value="1">메인메뉴</option>
									<option value="2">사이드메뉴</option>
									<option value="3">음료</option>
								</select>
							</div>
							
							<div class="form-group">
								<input type="hidden" class="form-control" name='mno' value="">
							</div>

							<div class="form-group is-filled">
								<input type="text"
									class="form-control" name='menuName' value="" placeholder="메뉴이름">
							</div>
							<div class="form-group is-filled">
								 <input type="text"
									class="form-control" name='content' value="" placeholder="메뉴설명">
							</div>
							<div class="form-group is-filled">
								 <input type="text"
									class="form-control" name='mprice' value="" placeholder="가격">
							</div>
							<div class="form-group is-filled">
								 <input type="text"
									class="form-control" name='mimg' value="" placeholder="이미지">
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
				<p>처리되었습니다</p>
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
							<li class="nav-item"><a class="navTopp nav-link "
								data-value="topp">토핑</a></li>
						</ul>

						<div class="row">
							<c:forEach items="${menu }" var="menu">

								<div class="col-lg-3 col-md-6 col-sm-6">
									<div class="menuInfo card card-stats">
										<div class="card-header card-header-warning card-header-icon">
											<div class="card-icon">
												<i class="material-icons">content_copy</i>
											</div>
											<h5 class="menuName card-title">${menu.menuName }</h5>
											<p class="menuContent card-category">${menu.content }</p>
											<p class="menuPrice card-category">${menu.mprice }</p>
											<p class="menuImg card-category">${menu.mimg }</p>

										</div>
										<div class="card-footer">
											<div class="menuBtn stats" data-mno="${menu.mno }">
												<button type="submit"
													class="modBtn btn btn-primary pull-right"
													style="padding: 5px;" value="/admin/store/menuModify">수정</button>
												<button type="submit"
													class="delBtn btn btn-danger pull-right"
													style="padding: 5px;">삭제</button>
												<c:if test= "${menu.cno == 1}">
												<button type="submit"
													class="btn btn-info pull-right"
													style="padding: 5px;">토핑</button>
													</c:if>

											</div>
										</div>
									</div>
								</div>
							</c:forEach>
							<div class="col-lg-3 col-md-6 col-sm-6">
									<div class="card card-stats">
										<div class="card-header card-header-warning card-header-icon">
											

										</div>
										<div class="card-footer">
											<div class="stats" >
												<button type="submit"
													class="regBtn btn btn-primary pull-right"
													style="" value="/admin/store/menuRegister">등록</button>
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
		<input type="hidden" name="sno" value="<%=sno%>"> <input
			type="hidden" name="cno" value="<%=cno%>">

	</form>


	<script type="text/javascript" src="../resources/service.js"></script>

	<script>
	
	const actionForm = document.querySelector(".actionForm")
	const actionForm2 = document.querySelector(".actionForm2")
    const cnoAct = document.querySelector("input[name='cno']").value
    //category
        window.onload = function(){

            document.querySelectorAll(".catNav").forEach(value => {

                if(cnoAct == value.dataset.value){

                    console.log(value.className)
                    value.classList.add('active')
                }

            })

        }

        document.querySelector(".navCat").addEventListener("click", function(e){

            e.preventDefault()

            cno = e.target.dataset.value

            document.querySelector("input[name='cno']").value = cno
            
            if (cno == "topp"){
            	
            	actionForm2.action ="/admin/store/toppingList"
            	
            }

         	actionForm2.submit()

        }, false)
	
	
// MenuRegister        
        
        document.querySelector(".regBtn").addEventListener("click", function(e){
        	actionForm.setAttribute("action" , document.querySelector(".regBtn").value)
        	document.querySelector("input[name='mno']").value = ""
			document.querySelector("input[name='menuName']").value = ""
			document.querySelector("input[name='content']").value = ""
			document.querySelector("input[name='mprice']").value = ""
			document.querySelector("input[name='mimg']").value = ""
        	
			$(".modModal").modal("show")
        })
        
// MenuModify
	
	document.querySelectorAll(".menuInfo").forEach(event => {
		event.addEventListener("click", function(e){
			
			const mno = e.currentTarget.querySelector(".menuBtn").getAttribute("data-mno")
			const menuName = e.currentTarget.querySelector(".menuName").innerHTML
			const content = e.currentTarget.querySelector(".menuContent").innerHTML
			const mprice = e.currentTarget.querySelector(".menuPrice").innerHTML
			const mimg = e.currentTarget.querySelector(".menuImg").innerHTML
			
			if(e.target == e.currentTarget.querySelector(".modBtn")){
				
				console.log(actionForm)
				actionForm.setAttribute("action" , document.querySelector(".modBtn").value)
				
				document.querySelector("input[name='mno']").value = mno
				document.querySelector("input[name='menuName']").value = menuName
				document.querySelector("input[name='content']").value = content
				document.querySelector("input[name='mprice']").value = mprice
				document.querySelector("input[name='mimg']").value = mimg
				
				$(".modModal").modal("show")
			}else if(e.target == e.currentTarget.querySelector(".delBtn")){
				$(".delModal").modal("show")
				
				document.querySelector(".delAgree").addEventListener("click" , function(e){
            
            fetch("/admin/store/menuDelete" , {
               method : 'post' , 
               headers : {"Content-Type" : "application/json"} ,
               body : JSON.stringify(mno)
            }).then(res => res.text()).then(result => {
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
		
		const menuName = document.querySelector("input[name='menuName']").value
		const content = document.querySelector("input[name='content']").value
		const mprice = document.querySelector("input[name='mprice']").value
		const mimg = document.querySelector("input[name='mimg']").value
		const mno = document.querySelector("input[name='mno']").value
		const sno = document.querySelector("input[name='sno']").value
		const category = document.querySelector(".catSelect").value
		document.querySelector("input[name='cno']").value = category
		const menuDTO = {mno:mno, sno:sno , menuName:menuName , content:content , mprice : mprice , mimg:mimg , cno:category}
		console.log(mprice)
		console.log(mimg)
		console.log(JSON.stringify(menuDTO))
		const path = actionForm.getAttribute("action")
		fetch(path , {
			method : 'post',
			headers : {"Content-Type" : "application/json;"} ,
			body : JSON.stringify(menuDTO)
		}).then(res => res.text()).then(result => {
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
   
   document.querySelector(".modClose").addEventListener("click" , function(e){
	   $(".modModal").modal("hide")
   })
   


	
	</script>


	<%@ include file="../includes/footer.jsp"%>