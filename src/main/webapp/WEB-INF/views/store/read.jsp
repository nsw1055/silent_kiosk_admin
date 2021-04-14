<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="../includes/header.jsp"%>

<div class="content">

	<div class="container-fluid">
		<div class="row">
			<div class="col-md-8">
				<div class="card card-profile">
					<div class="card-avatar">
						<a href="javascript:;"> <img class="img"
							src="../resources/assets/img/domino.jpg" />
						</a>	
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
					</div>
				</div>
			</div>		
		
		
		
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

							<div class="clearfix"></div>
						</form>
					</div>
				</div>
			</div>
			
			
			
		</div>
	</div>
</div>

<%@ include file="../includes/footer.jsp"%>