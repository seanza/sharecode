<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:include page="./include/jsfile.jsp" />
<jsp:include page="./include/head.jsp" />
<style>
.button-circle.button-giant, .button-box.button-giant, .button-square.button-giant {
width: 140px;
}
</style>
<body>
<jsp:include page="./include/divhead.jsp" />

<section id="contact-section" >

				<div class="container">
					<div class="row">
						<div class="col-md-12">
                        <!-- section-title -->
                        <div class="section-title uk-scrollspy-init-inview uk-scrollspy-inview uk-animation-fade" data-uk-scrollspy="{cls:'uk-animation-fade', delay:500}">
                            <h1>添加用户</h1>
                        </div>
						</div>
					</div>
					<div class="row mar15">
					<div class="col-sm-3 ">
                        </div>
						<div class="col-sm-4 ">
						    <h1>用户组</h1>
						    
                        </div><!-- /col-xs-4 -->
                        <div class="col-sm-5">
                            <label><input name="group" type="radio" value="admin" checked/>管理员 </label> 
                            <label class="juzuo"><input name="group" type="radio" value="user" />操作工 </label>
                        </div><!-- /col-md-4 -->
					</div>
					<div class="row mar15">
					<div class="col-sm-3 ">
                        </div>
						<div class="col-sm-4 ">
						     <h1>用户名</h1>
                        </div>
                        <div class="col-sm-5">
						    <input name="group" type="text" id="name" class="form-control" style='width:200px' value="" />
                        </div><!-- /col-xs-4 -->
					</div>
					<div class="row mar15">
					<div class="col-sm-3 ">
                        </div>
						<div class="col-sm-4 ">
						     <h1>密码</h1>
                        </div><!-- /col-xs-4 -->
                        <div class="col-sm-5">
						    <input name="group" type="text" id="psd" class="form-control" style='width:200px' value="" />
                        </div><!-- /col-xs-4 -->
					</div>
					<div class="row mar15">
					<div class="col-sm-3 ">
                        </div>
						<div class="col-sm-4 ">
						     <h1>确认密码</h1>
                        </div><!-- /col-xs-4 -->
                        <div class="col-sm-5">
						    <input name="group" type="text" id="psd2" class="form-control" onkeyup='conpsd()' style='width:200px' value="" />
                        </div><!-- /col-xs-4 -->
					</div>
                        
                    
                    <div class="row mar">
						<div class="col-sm-12 juzhong">
                                <button class="button button-royal button-raised  juzhong" id="button1">确认添加</button>
		                        <button class="button button-royal button-raised  juzuo" id="button2">取消操作</button>
						</div>
					</div>
                        
                        </div>
			</section>
<footer class="footer">
		        <h1>杭州世创电子技术股份有限公司</h1>
		   </footer>
</body>
<script>


$("#button1").click(function(){
	var name=document.getElementById('name').value;
	var psd=document.getElementById('psd').value;
	var psd2=document.getElementById('psd2').value;
	 var temp = document.getElementsByName("group");
	 var intHot="";
	for(var i=0;i<2;i++){
		 if(temp[i].checked){
	        intHot = temp[i].value;
	                        }
		 }
	 alert(intHot);
	if(name!=""&&psd!=""){
		$.ajax({    
			url:'RigesterServlet',
			data:{name:name,psd:psd,group:intHot},
			type:'post',    
			cache:false,    
			dataType:'json',
			success: function (a) {
				if(a=="成功"){
					var d = dialog({
			  		lock:true,
			  		title: '消息',
			  		content: '成功，系统将回到首页',
			  		 okValue: '确定',
			  		    ok: function () {
			  		    	 window.location.href="n3.htm";
			  		    },
			  		    cancel: false,
			  	});
			  	d.showModal();
				}
				else{
			      alert("错误");
			      self.location.reload();
				}
			},

			error: function(XMLHttpRequest, textStatus, errorThrown) {
			  alert(textStatus);
			},
			complete: function(XMLHttpRequest, textStatus) {
			  this; // 调用本次AJAX请求时传递的options参数
			}
			});
		
	}



});

function conpsd(){
	var psd1=document.getElementById('psd').value;
	var psd2=document.getElementById('psd2').value;
	if(psd1==psd2){
		document.getElementById('psd2').style.backgroundColor = 'transparent';
	}
	else{
		document.getElementById('psd2').style.backgroundColor = 'red';
	}
}
</script>

</html>