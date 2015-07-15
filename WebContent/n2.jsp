<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
        <jsp:include page="./include/jsfile.jsp" />
        <jsp:include page="./include/head.jsp" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/vk/jquery-ui.min.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/vk/keyboard.css">
		<script type="text/javascript"src="${pageContext.request.contextPath}/vk/jquery-ui.min.js"></script>
		<script type="text/javascript"src="${pageContext.request.contextPath}/vk/jquery.keyboard.js"></script>
		<script type="text/javascript"src="${pageContext.request.contextPath}/vk/jquery.mousewheel.js"></script>
        <style>
                 .button-circle.button-giant, .button-box.button-giant, .button-square.button-giant {
                         width: 140px;
                 }
        </style>
        <script>
		$(function(){
			$('#keyboard').keyboard();
		});
		$(function(){
			$('#keyboard1').keyboard();
		});
	</script>
<body>
<jsp:include page="./include/divhead.jsp" />
 <section id="contact-section">
        <div class="container">
                <!-- row -->
                <div class="row">

                    <!-- col-md-12 -->
                    <div class="col-sm-12">

                        <!-- section-title -->
                        <div class="section-title uk-scrollspy-init-inview uk-scrollspy-inview uk-animation-fade">
                            <h1>用户登录</h1>
                        </div><!-- /section-title -->

                        <!-- section-desc -->
                        <div class="section-desc uk-scrollspy-init-inview uk-scrollspy-inview uk-animation-fade">
                            <p>请扫描条码登录，如无条码，可以手工输入</p>
                        </div><!-- /section-desc -->

                    </div><!-- /col-md-12 -->

                   <form action="StudentServlet" method="post">
			            <div class='juzhong'>
				            <label>用户名：</label> <label><input type="text" id="keyboard"
					          name="userName" class="form-control"/></label><br /> <label>密&nbsp;&nbsp;碼：</label> <label><input
					          type="password" name="userPwd" id="keyboard1" class="form-control"/></label>
			           </div>
			            <div class='juzhong'>
				            <label><input type="submit" id='button32' class="btn btn-default" value="登錄" /></label> 
	 		           </div>
	            	</form>
                   <div class="col-sm-3">

                      

                        </div>
				   <div id="softkey" class="col-sm-6"></div>

                </div><!-- /row -->
            </div>
        </section>
<footer class="footer">
		        <h1>Footer</h1>
		   </footer>
</body>
<script type="text/javascript">
		 $("#button32").click(function(){
			 $.ajax({
            	    url:'TakepicServlet',
            	    type:'post',    
            	    cache:false,    
            	    dataType:'json',
            	    });
		 });
		 </script>
		 <SCRIPT LANGUAGE="JavaScript">
            var VoiceObj = new ActiveXObject("Sapi.SpVoice"); //创建一个朗读人
            VoiceObj.Rate=-1;
            VoiceObj.Speak("请输入用户名密码登陆", 1);
        </SCRIPT>
</html>