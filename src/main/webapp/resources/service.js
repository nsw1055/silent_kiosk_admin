var service = (function() {
	
	function deleteNotice(nno){
		
		return fetch("/admin/notice/delete", {
				method : 'post',
				headers : {'Content-Type' : 'application/x-www-form-urlencoded'},
				body : "nno="+nno+""
		}).then(res => res.text())
	}
	
	function register(obj){
	
		return fetch("/admin/notice/register",{
				method : 'post',
				headers : {'Content-Type' : 'application/json'},
				body : JSON.stringify(obj)
		}).then(res => res.text())
	
	}
	
	function upload(formdata){
	
		return fetch("/admin/common/notice/upload",{
				method : 'post',
				body : formdata
		}).then(res => res.json())
	}
	
	
	function modify(obj){
		
		return fetch("/admin/notice/modify",{
				method : 'post',
				headers : {'Content-Type' : 'application/json'},
				body : JSON.stringify(obj)
		}).then(res => res.text())
	}
	
	function fileDelete(param){
	
		return fetch("/admin/common/notice/delete",{
			method : 'post',
			headers : {'Content-Type' : 'application/json'},
			body : JSON.stringify(param)
		})
	}
	
	function getFiles(nno){
	
		return fetch("/admin/common/notice/getFiles?nno="+nno,{
			method : 'get'
		})
	}
	
	

     function sendUpload(fd){
      return fetch("/admin/common/manager/doc/upload",{
         method : 'post',
         body : fd
      }).then(res => res.json())
   }
   
   function sendUploadThumb(fd){
   
   	sendUpload(fd).then(result => {
      for (var i = 0; i < result.length; i++) {
         let file = result[i]
         console.log(file.link)
      document.querySelector(".fileThumb").innerHTML = "<img src='/admin/common/manager/view?link="+file.thumbLink+"' style = 'width: 90px; height: 90px' />" +
                                           "<button onclick=sendRemove("+JSON.stringify(file)+")'>DEL</button>"
         
      }
   })
   }
       
    function sendRegister(obj){
		return fetch("/admin/manager/register" , {
			method : 'post',
			headers : {"Content-Type":"application/json"},
			body : JSON.stringify(obj)
		}).then(res => res.text())
	}   
       
       
        return {deleteNotice:deleteNotice, register:register, upload:upload , modify:modify, fileDelete:fileDelete, getFiles:getFiles,sendUpload:sendUpload, sendUploadThumb:sendUploadThumb , sendRegister:sendRegister}

    }())