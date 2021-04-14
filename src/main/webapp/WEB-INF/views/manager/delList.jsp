<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="../includes/header.jsp"%>

<div class="content">
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-12">
				<div class="card">
						<div class="cardHeaderStyle card-header card-header-primary">
						<div>
							<h4 class="card-title ">Simple Table</h4>
							<p class="card-category">Here is a subtitle for this table</p>
						</div>
						<div class="cardHeaderBtn">
							<button type="submit" class="allListBtn btn btn-primary pull-right" style = "background-color: #ffffff; color: #ee6d09;">List</button>
						</div>
					</div>
					<div class="card-body">
						<div class="listTop">
						
						<div class="selectType">
								<select class="selectTypeVal custom-select">
									<option ${"m" == pageDTO.type ? "selected" : "" } value="m">ID</option>
									<option ${"s" == pageDTO.type ? "selected" : "" } value="s">email</option>
									<option ${"c" == pageDTO.type ? "selected" : "" } value="c">phone</option>
								</select>
							</div>
						
							<div class="searchList input-group no-border">
								<input type="text" value="" class="searchValue form-control" placeholder="Search...">
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
									<th>ID</th>
									<th>Email</th>
									<th>Phone</th>
									<th>Enabled</th>
									<th>Approval</th>
									<th>RegDate</th>
									<th>UpdateDate</th>
								</thead>
								<tbody class="tableList">
								<c:forEach items="${list }" var="manager">
								<tr class="readBtn" data-mid = ${manager.mid }>
								<td>${manager.mid}</td>
								<td>${manager.email}</td>
								<td>${manager.phone}</td>
								<td>${manager.enabled}</td>
								<td>${manager.approval}</td>
								<td>${manager.regdate}</td>
								<td>${manager.updatedate}</td>
								</tr>
								</c:forEach>
								
								
								</tbody>
							</table>



							<!-- pagination -->
							<div>
								<ul class="pagination justify-content-center">
									<c:if test="${pageMaker.prev}">
										<li class="page-item"><a class="page-link"
											href="${pageMaker.start -1}" tabindex="-1"
											aria-disabled="true">Previous</a></li>
									</c:if>
									<c:forEach begin="${pageMaker.start}" end="${pageMaker.end }"
										var="num">
										<li class="page-item ${num == pageDTO.page ? 'active' : ''}"><a
											class="page-link" href="${num }">${num }</a></li>

									</c:forEach>

									<c:if test="${pageMaker.next}">
										<li class="page-item"><a class="page-link"
											href="${pageMaker.end +1}">Next</a></li>
									</c:if>
								</ul>
							</div>

						</div>
					</div>
				</div>
			</div>

		</div>
	</div>
</div>


<form class="actionForm" action="/admin/manager/delList" method="get">
	<input type="hidden" name="page" value="${pageDTO.page}"> 
	<input type="hidden" name="perSheet" value="${pageDTO.perSheet}">
	<input type="hidden" name="type" value="${pageDTO.type}">
	<input type="hidden" name="keyword" value="${pageDTO.keyword}">
</form>


<script type="text/javascript" src="/resources/service.js"></script>

<script>

const actionForm = document.querySelector(".actionForm")

// list

const tableList = document.querySelector(".tableList")

/* .then(result => {
	for (let resultElement of result) {
		
		tableList.innerHTML += "<tr><td><img src= '"+resultElement.logoImg+"'></td>" +
		"<td onclick='sendRead("+JSON.stringify(resultElement.mid)+")'>"+resultElement.mid+"</td>" +
		"<td>"+resultElement.email+"</td>" +
		"<td>"+resultElement.phone+"</td>" +
		"<td>"+resultElement.sname+"</td>" +
		"<td>"+resultElement.enabled+"</td>" +
		"<td>"+resultElement.approval+"</td>" +
		"<td>"+resultElement.regdate+"</td>" +
		"<td>"+resultElement.updatedate+"</td></tr>"
	}
	})
	 */
	


// pagination


document.querySelector(".pagination").addEventListener("click" , function(e){
	
	e.preventDefault()
	
	const pageNum = e.target.getAttribute("href")
	
	console.log(pageNum)
	
	if(pageNum == null){
		return
	}
	
	document.querySelector("input[name='page']").value = pageNum
	
	actionForm.submit()
	
} , false)

// persheet

document.querySelector(".selectPerSheet").addEventListener("change" , function(e){

	console.log(e.target.value)
	
	perSheetNum = e.target.value
	
	document.querySelector("input[name='perSheet']").value = perSheetNum
	
	actionForm.submit()
	
} , false)


//search

document.querySelector(".searchBtn").addEventListener("click", function(e){
	
	e.preventDefault()
	
	console.log(e.currentTarget)
	
	searchValue = document.querySelector(".searchValue").value
	typeValue = document.querySelector(".selectTypeVal").value
	console.log(searchValue)
	
	document.querySelector("input[name='keyword']").value = searchValue
	document.querySelector("input[name='type']").value = typeValue
	document.querySelector("input[name='page']").value = 1
	

	
	actionForm.submit()
}, false)


 document.querySelector(".searchValue").addEventListener("keypress", function(e){

	if (e.key === "Enter") {
		 e.preventDefault()
    	console.log("Enter......")
    	
    	searchValue = document.querySelector(".searchValue").value
    	typeValue = document.querySelector(".selectTypeVal").value
    	console.log(searchValue)
    	
    	document.querySelector("input[name='keyword']").value = searchValue
    	document.querySelector("input[name='type']").value = typeValue
    	document.querySelector("input[name='page']").value = 1
    	
    	actionForm.submit()
    }
	
	
	
}, false) 

// read

document.querySelectorAll(".readBtn").forEach(function(event){
	event.addEventListener("click", function(e){
		console.log(e.currentTarget)
		
		const mid = e.currentTarget.getAttribute("data-mid")
		
		console.log(mid)
		
		actionForm.innerHTML += "<input name='mid' value='"+mid+"'>"
		actionForm.setAttribute("action" , "/admin/manager/read?" + mid)
		actionForm.submit()
		
	} , false)
})

//allList
document.querySelector(".allListBtn").addEventListener("click", function(e){
	e.preventDefault()
	
	location.href="/admin/manager/list"
})


</script>


<%@ include file="../includes/footer.jsp"%>