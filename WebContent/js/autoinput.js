function addnum(id){
        var num = document.getElementById(id).value;
        document.getElementById(id).value = parseInt(num)+1;
	}
	function minnum(id){
		
      var num = document.getElementById(id).value;
      if(num==0){
      alert("数量不能小于0");
	  }
	  else{
      document.getElementById(id).value = parseInt(num)-1;
	  }
	}