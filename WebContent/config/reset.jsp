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
                            <h1>恢复出厂设置</h1>
                        </div><!-- /section-title -->

                        <!-- section-desc -->
                        <div class="section-desc uk-scrollspy-init-inview uk-scrollspy-inview uk-animation-fade">
                            <p>将本周转柜所有信息恢复出厂设置，如您非厂家人员看到此页面请退出</p>
                        </div><!-- /section-desc -->
                    </div><!-- /col-md-12 -->
                    </div>

						
					<div class="row mar">
                        <div class="col-sm-12 juzhong">
						    <button class="button button-box button-giant" style="width:280px" id="button">确认恢复原厂，谨慎！</button>
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
		  var a1="reset";
    $.ajax( {    
    url:'${pageContext.request.contextPath}/ResetServlet',
    data:{set:a1},
    type:'post',    
    cache:false,    
    dataType:'json',
    success: function (a) {
    	if(a=='0'){
    		 var d = dialog({
    	    		title: '消息',
    	    		content: '出现异常，请联系厂家',
    	    		 okValue: '确定',
    	    		 ok: function () {},
    	    		 cancel: false,
    	    	});
    		 d.showModal();
    	}
    	else{
    		window.location.href="${pageContext.request.contextPath}/config/setsel.jsp";
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