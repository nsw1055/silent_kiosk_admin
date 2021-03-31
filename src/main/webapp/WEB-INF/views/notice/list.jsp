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
						<h4 class="card-title">Simple Table</h4>
						<p class="card-category">Here is a subtitle for this table</p>

					</div>
					<div class="card-body">
						<div class="listTop">

							<div class="selectType">
								<select class="selectTypeVal custom-select sType">
									<option value="t" ${pageDTO.type == 't'?"selected" : ""} >제목</option>
									<option value="c"${pageDTO.type == 'c'?"selected" : ""} >내용</option>
									<option value="y"${pageDTO.type == 'y'?"selected" : ""}>카테고리</option>
									<option value="tc"${pageDTO.type == 'tc'?"selected" : ""}>제목/내용</option>
								</select>
							</div>

							<div class="searchList input-group no-border">
								<input name="skeyword" 
								type="text" value="${pageDTO.keyword}" class="searchValue form-control" placeholder="Search...">
								<button type="submit" class="searchBtn btn btn-white btn-round btn-just-icon">
									<i class="material-icons">search</i>
									<div class="ripple-container"></div>
								</button>
							</div>

							<div class="selectPerSheet">
								<select class="selectPerSheet custom-select">
									<option ${10 == pageDTO.perSheet ? "selected" : "" } value="10">10개씩</option>
									<option ${20 == pageDTO.perSheet ? "selected" : "" } value="20">20개씩</option>
									<option ${30 == pageDTO.perSheet ? "selected" : "" } value="30">30개씩</option>
								</select>
							</div>
						</div>
					
					
						<div class="table-responsive">
							<table class="table">
								<thead class=" text-primary">
									<th>Nno</th>
									<th>Title</th>
									<th>Content</th>
									<th>Writer</th>
									<th>regDate</th>
									<th>updateDate</th>
								</thead>
								<tbody class="tList">
								<c:forEach items="${list}" var="notice" >
								<tr data-nno="${notice.nno }">
								<td>${notice.nno }</td>
								<td>${notice.title }</td>
								<td>${notice.content }</td>
								<td>${notice.writer }</td>
								<td>${notice.regdate }</td>
								<td>${notice.updatedate }</td>
								</tr>
								</c:forEach>
								</tbody>
							</table>
						</div>
					<div class="btnContainer">
						<button class="btn btn-primary btn-round registerBtn">등록하기</button>
					</div>

						<div>
							<ul class="pagination justify-content-center">
								<c:if test="${pageMaker.prev }">
									<li class="page-item"><a class="page-link"
										href="${pageMaker.start-1 }" tabindex="-1"
										aria-disabled="true">Previous</a></li>
								</c:if>
								<c:forEach begin="${pageMaker.start}" end="${pageMaker.end }"
									var="num">
									<li class="page-item ${pageDTO.page == num? "active" : ""}  "><a
										class="page-link" href="${num }">${num }</a></li>
								</c:forEach>
								<c:if test="${pageMaker.next }">
									<li class="page-item"><a class="page-link"
										href="${pageMaker.end+1 }">Next</a></li>
								</c:if>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>


<form action="/admin/notice/list" class="actionForm">
	<input type="hidden" name="page" value="${pageDTO.page }">
	<input type="hidden" name="perSheet" value="${pageDTO.perSheet }"> 
	<input type="hidden" name="type" value="${pageDTO.type }">
	<input type="hidden" name="keyword" value="${pageDTO.keyword }"> 
</form>

<script>
 const actionForm = document.querySelector(".actionForm")

 const ul =  document.querySelector(".tList")
  
 const pUl = document.querySelector(".pagination")

pUl.addEventListener("click",function(e){
	  
  e.preventDefault()
	  
  const target = e.target
	  
  if(target == pUl){
	  return;
  }
	  
  console.log(target)

  const pageNum = target.getAttribute("href")
	  
  document.querySelector("input[name='page']").value = pageNum
	
  actionForm.submit()

	  
},false)
  
  
  
const sPerSheet = document.querySelector(".selectPerSheet")
  
sPerSheet.addEventListener("change", function(e){
	  
  const idx = sPerSheet.selectedIndex
		
  const perSheet = sPerSheet[idx].value
  
  console.log(perSheet)
		
  actionForm.querySelector("input[name='perSheet']").value = perSheet
	  
  actionForm.submit()
	   
}, false)


document.querySelector("input[name='skeyword']").addEventListener("keypress", function(e){
    
	if (e.key === 'Enter') {
	const skeyword = document.querySelector("input[name='skeyword']").value
	
	const stype = document.querySelector(".sType")
	
	const sidx = stype.selectedIndex
	
	const type = stype[sidx].value
	
	actionForm.querySelector("input[name='keyword']").value = skeyword
	
	actionForm.querySelector("input[name='type']").value = type
	
	actionForm.querySelector("input[name='page']").value = 1
	
	actionForm.submit()
	
    }
	
	
}, false)

document.querySelector(".searchBtn").addEventListener("click", function(e){
    
	const skeyword = document.querySelector("input[name='skeyword']").value
	
	const stype = document.querySelector(".sType")
	
	const sidx = stype.selectedIndex
	
	const type = stype[sidx].value
	
	actionForm.querySelector("input[name='keyword']").value = skeyword
	
	actionForm.querySelector("input[name='type']").value = type
	
	actionForm.querySelector("input[name='page']").value = 1
	
	actionForm.submit()
	
}, false)


document.querySelector(".tList").addEventListener("click", function(e){
	
	const nno = e.target.parentNode.getAttribute("data-nno")
	
	console.log(nno)
	
	actionForm.innerHTML += "<input type='hidden' name='nno' value='"+ nno +"'>"

	actionForm.setAttribute("action","/admin/notice/read")
	 
	actionForm.submit()
	 
}, false)

document.querySelector(".registerBtn").addEventListener("click", function(e){
	
	actionForm.setAttribute("action","/admin/notice/register")
	
	actionForm.submit()
	
	
},false)
  
</script>

<%@ include file="../includes/footer.jsp"%>