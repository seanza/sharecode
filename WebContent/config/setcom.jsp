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
                            <h1>设置串口</h1>
                        </div><!-- /section-title -->

                        <!-- section-desc -->
                        <div class="section-desc uk-scrollspy-init-inview uk-scrollspy-inview uk-animation-fade">
                            <p>请选择本周转柜串口</p>
                        </div><!-- /section-desc -->
                    </div><!-- /col-md-12 -->
                    </div>
					<div class="row">
						<div class="col-sm-12 juzhong">
						 <select style="height:40px;width:200px" id="select">
                             <option style="width:200px">com1</option>
                             <option style="width:200px">com2</option>
                             <option style="width:200px">com3</option>
                             <option style="width:200px">com4</option>
                             <option style="width:200px">com5</option>
                             <option style="width:200px">com6</option>
                             <option style="width:200px">com7</option>
                             <option style="width:200px">com8</option>
                             <option style="width:200px">com9</option>
                        </select>
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
		  var arrid=new Array();
	  $("#button").click(function(){
		  var a1=document.getElementById("select").value;
    $.ajax( {    
    url:'${pageContext.request.contextPath}/SetcomServlet',
    data:{sel:a1},
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
    		window.location.href="${pageContext.request.contextPath}/config/setrow.jsp";
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