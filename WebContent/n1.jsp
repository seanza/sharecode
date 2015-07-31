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
<section id="home-section-wrapper">
            <!-- home-section-container -->
            <div id="home-section-container">
                <!-- container -->
                <div class="container">
                    <!-- row -->
                    <div class="row">
                        <!-- home-wrapper -->
                        <div id="home-wrapper">
                            <!-- welcome-msg -->
                            <div id="welcome-msg">
                            </div><!-- /welcome-msg -->
                            <!-- countdown_dashboard -->
                            <div id="countdown_dashboard">

                                <!-- Days -->
                                <div class="col-sm-3 col-xs-6">
                                    <div class="days_dash">
                                        <div class="digit" id="tem">26</div>
                                   
                                        <span class="dash_title">温度</span>
                                    </div>
                                </div><!-- /Days -->
                                <!-- Hours -->
                                <div class="col-sm-3 col-xs-6">
                                    <div class="hours_dash">
                                        <div class="digit" id="wet">59</div>
                                        <span class="dash_title">湿度</span>
                                    </div>
                                </div><!-- /Hours -->
                                <!-- Minutes -->
                                <div class="col-sm-3 col-xs-6">
                                    <div class="minutes_dash">
                                        <div class="digit" id="dan">0</div>
                                        <span class="dash_title">单相表</span>
                                    </div>
                                </div><!-- /Minutes -->
                                <!-- Seconds -->
                                <div class="col-sm-3 col-xs-6">
                                    <div class="seconds_dash">
                                        <div class="digit" id="san">0</div>
                                        <span class="dash_title">三相表</span>
                                    </div>
                                </div><!-- /Seconds -->
                            </div><!-- /countdown_dashboard -->
                            <!-- btn-nesto -->
                            <a class="btn btn-lg btn-nesto scrollto" title="View Portfolio" href="n2.jsp" data-uk-scrollspy="{cls:&#39;uk-animation-slide-bottom&#39;, delay:500}">
                               登  录
                            </a><!-- /btn-nesto -->
                        </div><!-- /home-wrapper -->
                    </div><!-- /row -->
                </div><!-- /container -->
            </div><!-- /home-section-container -->
        </section><!-- /home-section-wrapper -->
        <footer class="footer">
		        <h1>杭州世创电子技术股份有限公司</h1>
		   </footer>
</body>
 <SCRIPT LANGUAGE="JavaScript">
            var VoiceObj = new ActiveXObject("Sapi.SpVoice"); //创建一个朗读人
            VoiceObj.Rate=-1;
            VoiceObj.Speak("欢迎使用智能周转柜系统", 1);
        </SCRIPT>
        <script type="text/javascript">
            var a1="aaa";
            window.setInterval("time()",60000);
            function time(){
            $.ajax( {    
                url:'SummetServlet',
                data:{name:a1},
                type:'post',    
                cache:false,    
                dataType:'json',
                success: function (msg) {
    	                     document.getElementById("tem").innerHTML=msg[2];
    	                     document.getElementById("wet").innerHTML=msg[3];
    	                     document.getElementById("dan").innerHTML=msg[0];
    	                     document.getElementById("san").innerHTML=msg[1];
                                        },
                error: function(XMLHttpRequest, textStatus, errorThrown) {
                       },
                complete: function(XMLHttpRequest, textStatus) {
                      this; // 调用本次AJAX请求时传递的options参数
                      }
                      });
                      }
            window.onload = time;
         </script>
          <script type="text/javascript">
    var webSocket = 
      new WebSocket("ws://"+ window.location.host+"/${pageContext.request.contextPath}/websocket");
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
</html>