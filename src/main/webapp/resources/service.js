var service = (function() {
	

	
	function sendJson(path, obj, csrfTokenValue){
	
		return fetch(path,{
				method : 'post',
				headers : {'Content-Type' : 'application/json',
							'X-CSRF-TOKEN': csrfTokenValue},
				body : JSON.stringify(obj)
		}).then(res => res.json())
	
	}
	
	
	function deleteNotice(nno, csrfTokenValue, writer){
		
		return fetch("/admin/notice/delete", {
				method : 'post',
				headers : {'Content-Type' : 'application/x-www-form-urlencoded',
							'X-CSRF-TOKEN': csrfTokenValue},
				body : "nno="+nno+"&writer="+writer+""
		}).then(res => res.text())
	}
			
	
	
	function upload(formdata,csrfTokenValue){
	
		return fetch("/admin/common/notice/upload",{
				headers : {'X-CSRF-TOKEN': csrfTokenValue},
				method : 'post',
				body : formdata
		}).then(res => res.json())
	}
	
	

	function fileDelete(param,csrfTokenValue){
	
		return fetch("/admin/common/notice/delete",{
			method : 'post',
			headers : {'Content-Type' : 'application/json',
						'X-CSRF-TOKEN': csrfTokenValue},
			body : JSON.stringify(param)
		})
	}
	
    function sendUpload(fd,csrfTokenValue){
      return fetch("/admin/common/manager/doc/upload",{
         method : 'post',
         headers : {'X-CSRF-TOKEN': csrfTokenValue},
         body : fd
      }).then(res => res.json())
   }
   
   function sendUploadThumb(fd, csrfTokenValue){
   
   	sendUpload(fd, csrfTokenValue).then(result => {
      for (var i = 0; i < result.length; i++) {
         let file = result[i]
         console.log(file.link)
         document.querySelector(".modal-img").setAttribute("src" , "/admin/common/manager/view?link="+file.link+"")         
      }
   })
   }
       
    function sendRegister(obj, path, csrfTokenValue){
		return fetch(path , {
			method : 'post',
			headers : {"Content-Type":"application/json",
			'X-CSRF-TOKEN': csrfTokenValue},
			body : JSON.stringify(obj)
		}).then(res => res.text())
	}   
	
	function storeUpload(formdata, csrfTokenValue){
	
		return fetch("/admin/common/store/upload",{
				method : 'post',
				headers : {'X-CSRF-TOKEN': csrfTokenValue},
				body : formdata
		}).then(res => res.json())
	}
	
	function getAjax(path){
		return fetch(path, {
					method : 'get'
				})
				
		}
	
       
        return {sendJson:sendJson, getAjax:getAjax, storeUpload:storeUpload, deleteNotice:deleteNotice, upload:upload, fileDelete:fileDelete,sendUpload:sendUpload, sendUploadThumb:sendUploadThumb , sendRegister:sendRegister}

    }())