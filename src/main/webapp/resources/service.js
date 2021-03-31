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

        return {deleteNotice:deleteNotice, register:register, upload:upload}

    }())