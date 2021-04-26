<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="../includes/header.jsp"%>

<%
request.setCharacterEncoding("UTF-8");
String cno = request.getParameter("cno");
String sno = request.getParameter("sno");
String mid = request.getParameter("mid");
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

                            <div class="selectPerSheet form-group" style="z-index: 10000;">
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
                            <div class="form-group">

                            </div>
                            <div style="margin-bottom: 10px">
                                <input type="file" name="mimg" data-fileName="" class="form-control"
                                       id="inputGroupFile02">
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
                        data-bs-dismiss="modal">Close
                </button>
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


<div class="toppingModal modal" tabindex="-1">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Modal title</h5>
            </div>
            <div class="modal-body">
                <input type="hidden" name=mnoModal value=""/>
                <p>선택된 토핑 목록</p>
                <div class="select-topping">
                </div>

            </div>
            <div class="modal-body">
                <p>선택 안된 토핑 목록</p>
                <div class="unSelect-topping">
                </div>

            </div>
            <div class="modal-footer">
                <button type="button" class="toppingCommit btn btn-primary">확인</button>
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
                                            <div class="imgDiv card-icon" style="padding: 5px;">
                                                <img class="menu-img"
                                                     src="/admin/common/menu/view?link=<%=sno %>/${menu.mno }/${menu.mimg}"/>
                                            </div>
                                            <h5 class="menuName card-title">${menu.menuName }</h5>
                                            <p class="menuContent card-category">${menu.content }</p>
                                            <p class="menuPrice card-category">${menu.mprice }</p>
                                            <input type="hidden" class="menuImg" value=${menu.mimg }/>
                                        </div>
                                        <div class="card-footer">
                                            <div class="menuBtn stats" data-mno="${menu.mno }">
                                                <button type="submit"
                                                        class="modBtn btn btn-primary pull-right"
                                                        style="padding: 5px;" value="/admin/store/menuModify">수정
                                                </button>
                                                <button type="submit"
                                                        class="delBtn btn btn-danger pull-right"
                                                        style="padding: 5px;">삭제
                                                </button>
                                                <c:if test="${menu.cno == 1}">
                                                    <button type="submit"
                                                            class="topBtn btn btn-info pull-right"
                                                            style="padding: 5px;">토핑
                                                    </button>
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
                                        <div class="stats">
                                            <button type="submit"
                                                    class="regBtn btn btn-primary pull-right"
                                                    style="" value="/admin/store/menuRegister">등록
                                            </button>
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
        <input type="hidden" name="sno" value="<%=sno%>"> 
        <input type="hidden" name="cno" value="<%=cno%>">
        <input type="hidden" name="mid" value="<%=mid%>">

    </form>


    <script type="text/javascript" src="../resources/service.js"></script>

    <script>

        const actionForm = document.querySelector(".actionForm")
        const actionForm2 = document.querySelector(".actionForm2")
        const cnoAct = document.querySelector("input[name='cno']").value
        const sno = document.querySelector("input[name='sno']").value
        const csrfTokenValue = "${_csrf.token}"
        //category
        window.onload = function () {

            document.querySelectorAll(".catNav").forEach(value => {

                if (cnoAct == value.dataset.value) {

                    console.log(value.className)
                    value.classList.add('active')
                }

            })

        }

        document.querySelector(".navCat").addEventListener("click", function (e) {

            e.preventDefault()

            cno = e.target.dataset.value

            document.querySelector("input[name='cno']").value = cno

            if (cno == "topp") {

                actionForm2.action = "/admin/store/toppingList"

            }

            actionForm2.submit()

        }, false)

        //fileUpload
        document.querySelector("input[name='mimg']").addEventListener("change", function (e) {

            e.preventDefault()
            const fd = new FormData()
            const files = e.target.files
            fd.append("files", files[0])
            fd.append("value", e.target.name)
            service.sendUpload(fd, csrfTokenValue).then(result => {
                console.dir(result[0])
                e.target.setAttribute("data-fileName", result[0].sfileName)
            })
            service.sendUploadThumb(fd, csrfTokenValue)


        }, false)


        // MenuRegister        

        document.querySelector(".regBtn").addEventListener("click", function (e) {
            actionForm.setAttribute("action", document.querySelector(".regBtn").value)
            document.querySelector("input[name='mno']").value = ""
            document.querySelector("input[name='menuName']").value = ""
            document.querySelector("input[name='content']").value = ""
            document.querySelector("input[name='mprice']").value = ""

            document.querySelector(".modal-img").setAttribute("src", "/admin/resources/assets/img/image.png")

            $(".modModal").modal("show")
        })

        // MenuModify

        document.querySelectorAll(".menuInfo").forEach(event => {
            event.addEventListener("click", function (e) {

                const arr = []
                const mno = e.currentTarget.querySelector(".menuBtn").getAttribute("data-mno")
                const menuName = e.currentTarget.querySelector(".menuName").innerHTML
                const content = e.currentTarget.querySelector(".menuContent").innerHTML
                const mprice = e.currentTarget.querySelector(".menuPrice").innerHTML
                const mimg = e.currentTarget.querySelector(".menuImg").value

                if (e.target == e.currentTarget.querySelector(".modBtn")) {

                    console.log(actionForm)
                    actionForm.setAttribute("action", document.querySelector(".modBtn").value)

                    document.querySelector("input[name='mno']").value = mno
                    document.querySelector("input[name='menuName']").value = menuName
                    document.querySelector("input[name='content']").value = content
                    document.querySelector("input[name='mprice']").value = mprice
                    document.querySelector("input[name='mimg']").dataset.filename = mimg

                    document.querySelector(".modal-img").setAttribute("src", "/admin/common/menu/view?link=" + sno + "/" + mno + "/" + mimg + "")

                    $(".modModal").modal("show")
                } else if (e.target == e.currentTarget.querySelector(".delBtn")) {
                    $(".delModal").modal("show")

                    document.querySelector(".delAgree").addEventListener("click", function (e) {
                        const path = "/admin/store/menuDelete"
                        service.sendRegister(mno, path, csrfTokenValue).then(result => {
                            $(".delModal").modal("hide")
                            $(".delModalCon").modal("show")
                            console.log("삭제")
                        }, false)
                    })
                } else if (e.target == e.currentTarget.querySelector(".topBtn")) {
                    document.querySelector(".select-topping").innerHTML = ""
                    document.querySelector(".unSelect-topping").innerHTML = ""
                    document.querySelector("input[name='mnoModal']").value = mno

                    const path1 = "/admin/store/selectedTop?mno=" + mno

                    service.getAjax(path1).then(res => res.json()).then(result => result.forEach(topping => {
                        document.querySelector(".select-topping").innerHTML += "<a href='' class='btn btn-round btn-primary' data-tno=" + topping.tno + ">" + topping.tname + "</a>"
                    }))

                    const path2 = "/admin/store/unSelectTop?mno=" + mno + "&sno=" + sno

                    service.getAjax(path2).then(res => res.json()).then(result => result.forEach(topping => {
                        document.querySelector(".unSelect-topping").innerHTML += "<a href='' class='btn btn-round' data-tno=" + topping.tno + ">" + topping.tname + "</a>"
                    }))


                    $(".toppingModal").modal("show")
                }

            })
        })

        // toppingCommit
        document.querySelector(".toppingCommit").addEventListener("click", function (e) {

            e.preventDefault()
            $(".toppingModal").modal("hide")
        }, false)


        document.querySelector(".select-topping").addEventListener("click", function (e) {
            e.preventDefault()
            const tno = e.target.dataset.tno
            const mno = document.querySelector("input[name='mnoModal']").value
            const tname = e.target.innerText
            const value = {mno: mno, tno: tno}
            const path = "/admin/store/exceptTopping"

            service.sendRegister(value, path, csrfTokenValue).then(result => console.log(result))

            e.target.remove()
            document.querySelector(".unSelect-topping").innerHTML += "<a href='' class='btn btn-round' data-tno=" + tno + ">" + tname + "</a>"

        })


        document.querySelector(".unSelect-topping").addEventListener("click", function (e) {
            e.preventDefault()
            const tno = e.target.dataset.tno
            const mno = document.querySelector("input[name='mnoModal']").value
            const tname = e.target.innerText

            const value = {mno: mno, tno: tno}

            const path = "/admin/store/addTopping"

            service.sendRegister(value, path, csrfTokenValue).then(result => console.log(result))

            e.target.remove()
            document.querySelector(".select-topping").innerHTML += "<a href='' class='btn btn-round btn-primary' data-tno=" + tno + ">" + tname + "</a>"
        })


        document.querySelector(".modCommit").addEventListener("click", function (e) {
            e.preventDefault()

            const menuName = document.querySelector("input[name='menuName']").value
            const content = document.querySelector("input[name='content']").value
            const mprice = document.querySelector("input[name='mprice']").value
            const mimg = document.querySelector("input[name='mimg']").dataset.filename
            const mno = document.querySelector("input[name='mno']").value

            const category = document.querySelector(".catSelect").value
            document.querySelector("input[name='cno']").value = category
            const menuDTO = {
                mno: mno,
                sno: sno,
                menuName: menuName,
                content: content,
                mprice: mprice,
                mimg: mimg,
                cno: category
            }
            console.log(mprice)
            console.log(mimg)
            console.log(JSON.stringify(menuDTO))
            const path = actionForm.getAttribute("action")
            service.sendRegister(menuDTO, path, csrfTokenValue).then(result => {
                $(".modModal").modal("hide")
                $(".commitModal").modal("show")
            })

        }, false)

        document.querySelector(".modConfirm").addEventListener("click", function (e) {
            location.reload()
        }, false)
        document.querySelector(".delCancel").addEventListener("click", function (e) {
            $(".delModal").modal("hide")
        }, false)

        document.querySelector(".delCommit").addEventListener("click", function (e) {
            location.reload()
        }, false)

        document.querySelector(".modClose").addEventListener("click", function (e) {
            document.querySelector("input[name='mimg']").value = ""
            $(".modModal").modal("hide")
        })


    </script>


    <%@ include file="../includes/footer.jsp"%>