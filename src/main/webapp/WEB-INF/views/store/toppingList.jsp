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
					</div>

					<div class="card-body">
					 <ul class="navCat nav nav-pills nav-fill">
                            <li class="nav-item"><a class="catNav nav-link " data-value="1">메인메뉴</a></li>
                            <li class="nav-item"><a class="catNav nav-link " data-value="2">사이드메뉴</a></li>
                            <li class="nav-item"><a class="catNav nav-link " data-value="3">음료</a></li>
                            <li class="nav-item"><a class="navTopp nav-link active" data-value="topp">토핑</a></li>
                        </ul>
					
						<div class="row">
						<c:forEach items="${topping }" var ="topping">
						
            <div class="col-lg-3 col-md-6 col-sm-6">
              <div class="card card-stats">
                <div class="card-header card-header-warning card-header-icon">
                  <div class="card-icon">
                    <i class="material-icons">content_copy</i>
                  </div>
                  <h3 class="card-title">${topping.TName }  </h3>
                  <p class="card-category">${topping.TPrice }</p>
                   <p class="card-category">${topping.TImg }</p>
                </div>
                <div class="card-footer">
                  <div class="stats">
                  </div>
                </div>
              </div>
          </div>
          </c:forEach>


					</div>
				</div>
			</div>

		</div>
	</div>
</div>
<form class="actionForm" action="/admin/store/menuList" method="get">
        <input type="hidden" name="sno" value="10">
        <input type="hidden" name="cno" value="">

    </form>

<script type="text/javascript" src="/resources/service.js"></script>

<script>
const actionForm = document.querySelector(".actionForm")

document.querySelector(".navCat").addEventListener("click", function(e){

    e.preventDefault()

    cno = e.target.dataset.value

    document.querySelector("input[name='cno']").value = cno
    
    if (cno == "topp"){
    	
    	actionForm.action ="/admin/store/toppingList"
    	
    }

 	actionForm.submit()

}, false)

</script>


<%@ include file="../includes/footer.jsp"%>