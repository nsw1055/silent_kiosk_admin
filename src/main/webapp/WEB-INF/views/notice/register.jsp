<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="../includes/header.jsp"%>
<%@ taglib prefix="sec"
uri="http://www.springframework.org/security/tags"%>

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
                                            type="text" name="title" class="form-control">
                                    </div>
                                </div>
                                <div class="col-md-3">
                                    <div class="form-group bmd-form-group">
                                        <label class="bmd-label-floating">작성자</label> <input
                                            type="text" name="writer"
                                            value='<sec:authentication property="principal.username"/>'
                                            class="form-control" readonly="readonly">
                                    </div>
                                </div>
                                <div class="col-md-3">
                                    <div class="form-group bmd-form-group">
                                        <select style='width: px;' class="selectCate custom-select">
                                            <option value="안내">안내</option>
                                            <option value="긴급">긴급</option>
                                            <option value="이벤트">이벤트</option>
                                        </select>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="form-group">
                                        <label>내용</label>
                                        <div class="form-group bmd-form-group">
                                            <textarea class="form-control" name="content" rows="20"></textarea>
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
                            <hr/>
                            <div class="btnContainer">
                                <div class="checkbox"
                                     style="display: flex; flex-direction: row;">
                                    <h5>공지사항 고정</h5>
                                    <input style="margin-left: 10px; margin-top: 5px;"
                                           type="checkbox" class="checkShowed" name="showed"
                                           ${notice.showed==true?"checked":"" }>
                                </div>
                                <button class="btn btn-primary btn-round registerBtn">등록</button>
                                <button class="btn btn-primary btn-round listBtn">등록 취소</button>
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


<div class="modal" id="registerModal" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">등록 확인</h5>
                <button type="button" class="close" data-dismiss="modal"
                        aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <p>등록하시겠습니까?</p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary modalRegisterBtn">등록</button>
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
                <button type="button" onclick='location.href="/admin/notice/list"'
                        class="btn btn-primary checkBtn">확인
                </button>
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

    const actionForm = document.querySelector(".actionForm")

    const sCate = document.querySelector(".selectCate")

    var cate = "안내";

    sCate.addEventListener("change", function () {

        const cateIdx = sCate.selectedIndex

        cate = sCate[cateIdx].value

    }, false)

    document.querySelector(".registerBtn").addEventListener("click", function (e) {

        e.preventDefault();

        $("#registerModal").modal("show")

    }, false)


    const fileUl = document.querySelector(".fileUl")

    function modalHide() {
        $("#checkModal").modal("hide")
    }

    document.querySelector(".modalRegisterBtn").addEventListener("click", function (e) {

        const title = document.querySelector("input[name='title']").value
        const writer = document.querySelector("input[name='writer']").value
        const content = document.querySelector("textarea[name='content']").value

        const arr = []

        const fileLis = fileUl.querySelectorAll("li")

        for (var fileLi of fileLis) {

            const uuid = fileLi.getAttribute('data-uuid')
            const fileName = fileLi.getAttribute('data-fileName')
            const uploadPath = fileLi.getAttribute('data-uploadPath')
            const image = fileLi.getAttribute('data-image')

            const fileObj = {uuid: uuid, fileName: fileName, uploadPath: uploadPath, image: image}

            arr.push(fileObj)

        }

        const checkShowed = document.querySelector(".checkShowed")


        const obj = {
            title: title,
            category: cate,
            writer: writer,
            content: content,
            showed: checkShowed.checked,
            list: arr
        }

        service.sendJson("/admin/notice/register", obj, csrfTokenValue).then(result => {
            console.dir(result)
            if (result[0]) {

                document.querySelector(".checkModalBody").innerHTML = "<p>" + result[0].defaultMessage + "</p>"

                $("#registerModal").modal("hide")

                $("#checkModal").modal("show")

                document.querySelector(".checkBtn").setAttribute("onclick", "modalHide()")

            } else {

                document.querySelector(".checkModalBody").innerHTML = "<p>등록완료</p>"

                document.querySelector(".checkBtn").setAttribute("onclick", 'location.href="/admin/notice/list"')

                $("#registerModal").modal("hide")

                $("#checkModal").modal("show")
            }
        })

    }, false)


    /* 	document.querySelector(".checkBtn").addEventListener("click", function(e){
            
            location.href="/admin/notice/list"
            
        },false) */


    document.querySelector("input[name='files']").addEventListener("change", function (e) {

        e.preventDefault()

        const formdata = new FormData()

        const files = document.querySelector("input[name='files']").files

        for (var i = 0; i < files.length; i++) {

            formdata.append("uploadFile", files[i])

        }

        service.upload(formdata, csrfTokenValue).then(jsonObj => {
            console.log(jsonObj)
            for (var i = 0; i < jsonObj.length; i++) {

                var file = jsonObj[i];

                if (!file.image) {

                    console.log(file.link)
                    fileUl.innerHTML += "<li id='li" + file.uuid + "' data-uuid='" + file.uuid + "' data-fileName='" + file.fileName + "' data-uploadPath='" + file.uploadPath + "' data-image='" + file.image + "'><i class='fas fa-file'></i>" + file.fileName + "<button onclick='delTempImg(event," + JSON.stringify(file) + ")'>삭제</button></li>"

                } else {
                    fileUl.innerHTML += "<li id='li" + file.uuid + "' data-uuid='" + file.uuid + "' data-fileName='" + file.fileName + "' data-uploadPath='" + file.uploadPath + "' data-image='" + file.image + "'>" + file.fileName + "<img src='/admin/common/notice/preview?link=" + file.thumbLink + "'/><button onclick='delTempImg(event," + JSON.stringify(file) + ")'>삭제</button></li>"

                }
            }
        })

    }, false)

    document.querySelector(".listBtn").addEventListener("click", function (e) {

        e.preventDefault();

        actionForm.submit();

    }, false)

    function delTempImg(event, file) {

        event.preventDefault()

        const fileLi = document.querySelector("#li" + file.uuid)

        fileLi.remove()

    }

</script>
<%@ include file="../includes/footer.jsp"%>