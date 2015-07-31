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

		

					<div class="row mar">
						<div class="col-md-12">
                        </div><!-- /col-md-12 -->
						</div>
						
					<div class="row mar">
						<div class="col-sm-4 juzhong">
						     <button class="button button-box button-giant" onclick="window.location.href('getlog.jsp')">日志查询</button>
                        </div><!-- /col-xs-4 -->
                        <div class="col-sm-4 juzhong">
						    <button class="button button-box button-giant" onclick="window.location.href('adduser.jsp')">添加用户</button>
                        </div><!-- /col-md-4 -->
                        <div class="col-sm-4 juzhong">
						    <button class="button button-box button-giant" onclick="window.location.href('setku.htm')">储位管理</button>
                        </div><!-- /col-xs-4 -->
					</div>
					<div class="row mar">
						<div class="col-sm-4 juzhong">
						    <button class="button button-box button-giant" onclick="window.location.href('baojing.jsp')" >报警管理</button>
                        </div><!-- /col-xs-4 -->
                        <div class="col-sm-4 juzhong">
						   
                        </div><!-- /col-xs-4 -->
                        <div class="col-sm-4 juzhong">
						    <button class="button button-box button-giant" onclick="window.location.href('setcode.htm')">储位条码</button>
                        </div><!-- /col-xs-4 -->
					</div>
                        
                        
                        </div>


			</section>
			<footer class="footer">
		        <h1>杭州世创电子技术股份有限公司</h1>
		   </footer>
</body>
</html>