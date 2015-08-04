<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:include page="./include/jsfile.jsp" />
<jsp:include page="./include/head.jsp" />
<style>
.button-circle.button-giant, .button-box.button-giant, .button-square.button-giant {
width: 140px;
}
     #scrollUp {
     bottom: 122px;
background-color: #777;
color: #eee;
font-size: 30px;
line-height: 1;
text-align: center;
text-decoration: none;
right: 20px;
overflow: hidden;
width: 92px;
height: 46px;
border: none;
opacity: .8;
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
                            <h1>报警设置</h1>
                        </div>
						</div>
					</div>
					<div class="row mar15">
					<div class="col-sm-3 ">
                        </div>
						<div class="col-sm-4 ">
						    <h1>温湿度报警</h1>
						    
                        </div><!-- /col-xs-4 -->
                        <div class="col-sm-5">
                            <label><input name="group" type="radio" value="1" checked/>开 </label> 
                            <label class="juzuo"><input name="group" type="radio" value="0" />关 </label>
                        </div><!-- /col-md-4 -->
					</div>
					<div class="row mar15">
					<div class="col-sm-3 ">
                        </div>
						<div class="col-sm-4 ">
						    <h1>储位报警</h1>
						    
                        </div><!-- /col-xs-4 -->
                        <div class="col-sm-5">
                            <label><input name="group1" type="radio" value="1" checked/>开 </label> 
                            <label class="juzuo"><input name="group1" type="radio" value="0" />关 </label>
                        </div><!-- /col-md-4 -->
					</div>
					<div class="row mar15">
					<div class="col-sm-3 ">
                        </div>
						<div class="col-sm-4 ">
						     <h1>温度范围设定</h1>
                        </div>
                        <div class="col-sm-5">
						   低限 <input name="group" type="text" id="teml" style='width:80px' value="" />高限<input name="group" type="text" id="temh" style='width:80px' value="" />
                        </div><!-- /col-xs-4 -->
					</div>
					<div class="row mar15">
					<div class="col-sm-3 ">
                        </div>
						<div class="col-sm-4 ">
						     <h1>湿度范围设定</h1>
                        </div>
                        <div class="col-sm-5">
						   低限 <input name="group" type="text" id="wetl" style='width:80px' value="" />高限<input name="group" type="text" id="weth" style='width:80px' value="" />
                        </div><!-- /col-xs-4 -->
					</div>
					<div class="row mar15">
					<div class="col-sm-3 ">
                        </div>
						<div class="col-sm-4 ">
						     <h1>储位报警设定</h1>
                        </div>
                        <div class="col-sm-5">
						   小于 <input name="group" type="text" id="chul" style='width:80px' value="" />会报警
                        </div><!-- /col-xs-4 -->
					</div>
                    <div class="row mar">
						<div class="col-md-12 juzhong">
                                <button class="button button-royal button-raised  juzhong" id="button1">确认添加</button>
						</div>
					</div>
                        
                        </div>
			</section>
			         <a id="scrollUp" style="position: fixed;display: block;">后退</a>
<footer class="footer">
		        <h1>杭州世创电子技术股份有限公司</h1>
		   </footer>
</body>
<script>


$("#button1").click(function(){
	var teml=document.getElementById('teml').value;
	var temh=document.getElementById('temh').value;
	var wetl=document.getElementById('wetl').value;
	var weth=document.getElementById('weth').value;
	var chul=document.getElementById('chul').value;
	var temp = document.getElementsByName("group");
    var temp1 = document.getElementsByName("group1");
	 var che1="";
	 var che2="";
	for(var i=0;i<2;i++){
		 if(temp[i].checked){
	        che1 = temp[i].value;
	                        }
		 if(temp1[i].checked){
		        che2 = temp1[i].value;
		                    }
		 }
	if(teml!=""&&temh!=""&&wetl!=""&&weth!=""&&chul!=""){
		$.ajax({    
			url:'BaojingcanshuServlet',
            data:{teml:teml,temh:temh,wetl:wetl,weth:weth,che1:che1,che2:che2,chul:chul},
			type:'post',    
			cache:false,    
			success: function (a) {
				var aa=a.toString();
				if(aa=="成功"){
					var d = dialog({
			  		lock:true,
			  		title: '消息',
			  		content: '成功，系统将回到首页',
			  		 okValue: '确定',
			  		    ok: function () {
			  		    	 window.location.href="sconfig.jsp";
			  		    },
			  		    cancel: false,
			  	});
			  	d.showModal();
				}
				else{
			      window.location.href="sconfig.jsp";
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
$("#scrollUp").click(function(){
	   window.location.href="sconfig.jsp";
	   
});
</script>
</html>