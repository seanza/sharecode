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
                        <div class="row">
			            <div class='col-sm-2 juzhong'></div>
				            <div class='col-sm-3 juzhong'><label>用户名：</label></div> 
				            <div class='col-sm-5 juzhong'><label><input type="text" id="keyboard" name="userName" class="form-control"/></label>
				            </div>
				            <div class='col-sm-2 juzhong'></div>
				        </div>    
				        <div class="row">
				            <div class='col-sm-2 juzhong'></div>
				            <div class='col-sm-3 juzhong'><label>密&nbsp;&nbsp;&nbsp;&nbsp;码</label></div> 
				            
				            <div class='col-sm-5 juzhong'><label><input type="password" name="userPwd" id="keyboard1" class="form-control"/></label>
			              

			              </div>
			              <div class='col-sm-2 juzhong'></div>
			              </div>  
			            <div class='juzhong'>
				            <label><input type="submit" id='button32' class="button-wrap" value="登录" /></label> 
	 		           </div>
	            	</form>
                   <div class="col-sm-3">

                      

                        </div>
				   <div id="softkey" class="col-sm-6"></div>

                </div><!-- /row -->
            </div>
        </section>
        <footer class="footer">
		        <h1>杭州世创电子技术股份有限公司</h1>
		   </footer>
        </body>
         <script type="text/javascript">
    var webSocket = new WebSocket("ws://"+ window.location.host+"/${pageContext.request.contextPath}/websocket");
    webSocket.onerror = function(event) {
      onError(event)
    };

    webSocket.onopen = function(event) {
      onOpen(event)
    };

    webSocket.onmessage = function(event) {
      onMessage(event)
    };
    function onMessage(event) {
    	VoiceObj.Speak('门异常', 1);
    }
    function onOpen(event) {
      webSocket.send('hello');
    }
    function onError(event) {
      alert(event.data);
    }
  </script>
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