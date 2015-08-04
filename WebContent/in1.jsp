<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:include page="/include/jsfile.jsp" />
<jsp:include page="/include/head.jsp" />
<style>
.button-circle.button-giant, .button-box.button-giant, .button-square.button-giant {
width: 140px;
}
     #scrollUp {
     bottom: 122px;
background-color: #777;
color: #eee;
font-size: 20px;
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
	<script type="text/javascript">
	function addnum(id){
        var num = document.getElementById(id).value;
        document.getElementById(id).value = parseInt(num)+1;
	}
	function minnum(id){
		
      var num = document.getElementById(id).value;
      if(num==0){
      alert("数量不能小于0");
	  }
	  else{
      document.getElementById(id).value = parseInt(num)-1;
	  }
	}
	</script>
<body>
<jsp:include page="/include/divhead.jsp" />
 <section id="contact-section">
        <div class="container" id="div1" style="display:block;">
                <!-- row -->
                <div class="row">

                    <!-- col-md-12 -->
                    <div class="col-sm-12">

                        <!-- section-title -->
                        <div class="section-title uk-scrollspy-init-inview uk-scrollspy-inview uk-animation-fade" data-uk-scrollspy="{cls:'uk-animation-fade', delay:500}">
                            <h1>选择入库表计数量</h1>
                        </div><!-- /section-title -->

                        <!-- section-desc -->
                        <div class="section-desc uk-scrollspy-init-inview uk-scrollspy-inview uk-animation-fade" data-uk-scrollspy="{cls:'uk-animation-fade', delay:700}">
                            <p>请根据表计类型选择相应的数量</p>
                        </div><!-- /section-desc -->

                    </div><!-- /col-md-12 -->


                    <!-- form -->
                    <div  id="form" class="contactForm" >

                        <div>
                        <!-- col-md-6 -->
						
                        <div class="col-sm-6 aaa">
                                <h1>选择单相表数量:</h1>
                        </div>
                       
               
                        <div class="col-sm-1">
                           
                               <button class="button button-box button-giant" onclick=minnum("text1")><i class="icon-minus"></i></button>
                             
                        </div>


                        <div class="col-sm-2">
                            
                               <input type="text" class="in1" id="text1" value="0" >
                             
                        </div>

						  <div class="col-sm-2">
                           
                               <button class="button button-box button-giant" onclick=addnum("text1") ><i class="icon-plus"></i></button>
                             
                        </div>
						
						 </div>
                    </div><!-- /form -->



                </div><!-- /row -->

				 <div class="row mar">


                    <!-- form -->
                    <div  id="form" class="contactForm" >

                        <div>
                        <!-- col-md-6 -->
		
                        <div class="col-sm-6 aaa">
                                <h1>选择三相表数量:</h1>
                        </div>
                       
               
                        <div class="col-sm-1">
                           
                               <button class="button button-box button-giant" onclick=minnum("text2")><i class="icon-minus"></i></button>
                             
                        </div>


                        <div class="col-sm-2">
                            
                               <input type="text" class="in1" id="text2" value="0" >
                             
                        </div>

						  <div class="col-sm-2">
                           
                               <button class="button button-box button-giant" onclick=addnum("text2") ><i class="icon-plus"></i></button>
                             
                        </div>
						 </div>
                    </div><!-- /form -->

                </div><!-- /row -->

                <div class="juzhong">
				<a class="btn btn-lg btn-nesto scrollto juzhong" id="button" title="View Portfolio" data-uk-scrollspy="{cls:&#39;uk-animation-slide-bottom&#39;, delay:500}">
                               确  认
                            </a><!-- /btn-nesto -->
                </div>
           </div>
        </section>
        
         <a id="scrollUp" style="position: fixed;display: block;">后退</a>
			<footer class="footer">
		        <h1>杭州世创电子技术股份有限公司</h1>
		   </footer>
</body>
		   <script> 
      $(document).ready(function(){
	  $("#button").click(function(){
		  var a1=document.getElementById("text1").value;
		  var a2=document.getElementById("text2").value;
		  if(a1!="0"||a2!="0"){
              $.ajax({    
                      url:'${pageContext.request.contextPath}/InboundServlet',
                      data:{dan:a1,san:a2},
                      type:'post',    
                      cache:false,    
                      dataType:'text',
                      success: function(aa) {
                          if(aa == "成功")
                    	      window.location.href="${pageContext.request.contextPath}/in2.jsp";
                    	  else
                    	  {
                    	      var d = dialog({
            	    		      				lock:true,
            	    		       				title: '警告消息',
            	    							content: '请求入库仪表数量大于可放仪表数量，请重新选择入库数量',
            	    		 					okValue: '确定',
            	    		    				ok: function () {
            	    		    									location.reload(true);
            	    		    			 					},
            	    		    			    cancel: false,
            	    						});
            	    		  d.showModal();
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
   					 	SpeakText();
					  }
		  else{
			  var d = dialog({
		    		lock:true,
		    		title: '警告消息',
		    		content: '请求数量需大于0',
		    		 okValue: '确定',
		    		    ok: function () {
		    		    	location.reload(true);
		    		    },
		    		    cancel: false,
		    	});
		    	d.showModal();
		  }
          });
          });
      $("#scrollUp").click(function(){
   	   window.location.href="n3.htm";
   	   
      });
</script>   
<script>
function del(obj){   
obj.parentNode.firstChild.value="";   
}
</script>
<SCRIPT LANGUAGE="JavaScript">
	var VoiceObj = new ActiveXObject("Sapi.SpVoice");
	VoiceObj.Rate=-1;
	VoiceObj.Speak("请分别选择两种表计数量",1);
</SCRIPT>
</html>