<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:include page="/include/jsfile.jsp" />
<jsp:include page="/include/head.jsp" />
<style>
.button-circle.button-giant, .button-box.button-giant, .button-square.button-giant {
width: 140px;
}
</style>
<body>
<jsp:include page="/include/divhead.jsp" />
<section id="contact-section" >

				<div class="container">
				 <div class="row">
                   <div class="col-sm-12">

                        <!-- section-title -->
                        <div class="section-title uk-scrollspy-init-inview uk-scrollspy-inview uk-animation-fade">
                            <h1>设置门锁信号位置</h1>
                        </div><!-- /section-title -->

                    </div>
                    </div>
					<div class="row">
						<div class="col-sm-12 juzhong">
                           <table id="tab1" class="table">   
                             <tr><td>名称</td><td>所用IO板序号</td><td>IO序号</td></tr>
                             <tr><td>门锁开关DO</td><td><input name=aaa id="lo1" style='width:80px'></td><td><input name=aaa id="lo2" style='width:80px'></td></tr> 
                             <tr><td>接近开关</td><td><input name=aaa id="li1" style='width:80px'></td><td><input name=aaa id="li2" style='width:80px'></td></tr> 
                             <tr><td>门锁检测DI</td><td><input name=aaa id="li3" style='width:80px'></td><td><input name=aaa id="li4" style='width:80px'></td></tr> 
                           </table>  
                        </div><!-- /col-md-12 -->
						</div>
						
					<div class="row mar">
                        <div class="col-sm-12 juzhong">
						    <button class="button button-box button-giant" id="button">确认</button>
                        </div><!-- /col-md-12 -->
					</div>
                    </div>
			</section>
			<footer class="footer">
		        <h1>Footer</h1>
		   </footer>
		   <script > 
      $(document).ready(function(){
	  $("#button").click(function(){
		  
		  var a1=parseInt(document.getElementById("lo1").value);
		  var a2=parseInt(document.getElementById("lo2").value);
		  var b1=parseInt(document.getElementById("li1").value);
		  var b2=parseInt(document.getElementById("li2").value);
		  var b3=parseInt(document.getElementById("li3").value);
		  var b4=parseInt(document.getElementById("li4").value);
            $.ajax( {    
               url:'${pageContext.request.contextPath}/SetlockServlet',
               data:{a1:a1,a2:a2,b1:b1,b2:b2,b3:b3,b4:b4},
               type:'post',    
               cache:false,    
               dataType:'json',
               success: function (a) {
    	       if(a=='0'){
    		   var d = dialog({
    	    		title: '消息',
    	    		content: '该串口已经使用，请选择其他串口',
    	    		 okValue: '确定',
    	    		 ok: function () {},
    	    		 cancel: false,
    	    	});
    		   d.showModal();
    	       }
    	      else{
    		    window.location.href="${pageContext.request.contextPath}/setcode.jsp";
    	}
    },
    error: function(XMLHttpRequest, textStatus, errorThrown) {
        alert(XMLHttpRequest.status);
        alert(XMLHttpRequest.readyState);
        alert(textStatus);
    },
    complete: function(XMLHttpRequest, textStatus) {
        this; // 调用本次AJAX请求时传递的options参数
    }
    });
		  

});
	  
      });
      
</script>
</body>
</html>