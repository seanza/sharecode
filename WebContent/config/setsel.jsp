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

		

					<div class="row mar">
						<div class="col-md-12">
                        </div><!-- /col-md-12 -->
						</div>
						
					<div class="row mar">
						<div class="col-sm-6 juzhong">
						     <button class="button button-box button-giant" style="width:210px" onclick="go('${pageContext.request.contextPath}/config/setcom.jsp')">添加一个周转柜</button>
                        </div><!-- /col-xs-4 -->
                        <div class="col-sm-6 juzhong">
						    <button class="button button-box button-giant" style="width:210px" onclick="go('${pageContext.request.contextPath}/config/reset.jsp')">恢复原厂设置</button>
                        </div><!-- /col-md-4 -->
					</div>

                        
                        
                        </div>


			</section>
			<footer class="footer">
		        <h1>Footer</h1>
		   </footer>
</body>
<script > 
   function go(a){
	   window.location.href=a;
   }

</script>
</html>