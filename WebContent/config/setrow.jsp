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
<section id="contact-section" >

				<div class="container">
				 <div class="row">
                   <div class="col-sm-12">

                        <!-- section-title -->
                        <div class="section-title uk-scrollspy-init-inview uk-scrollspy-inview uk-animation-fade">
                            <h1>设置储位行数</h1>
                        </div><!-- /section-title -->

                        <!-- section-desc -->
                        <div class="section-desc uk-scrollspy-init-inview uk-scrollspy-inview uk-animation-fade">
                            <p>请分别选择单相表行数和三相表行数</p>
                        </div><!-- /section-desc -->
                    </div><!-- /col-md-12 -->
                    </div>
					 <div  id="form" class="contactForm" >

                        <div>
                        <!-- col-md-6 -->
						
                        <div class="col-sm-6 aaa">
                                <h1>选择单相表行数:</h1>
                        </div>
                       
               
                        <div class="col-sm-1">
                           
                               <button class="button button-box button-giant" onclick=minnum("text1")><i class="fa fa-minus"></i></button>
                             
                        </div>


                        <div class="col-sm-2">
                            
                               <input type="text" class="in1" id="text1" value="0" >
                             
                        </div>

						  <div class="col-sm-2">
                           
                               <button class="button button-box button-giant" onclick=addnum("text1") ><i class="fa fa-plus"></i></button>
                             
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
                                <h1>选择三相表行数:</h1>
                        </div>
                       
               
                        <div class="col-sm-1">
                           
                               <button class="button button-box button-giant" onclick=minnum("text2")><i class="fa fa-minus"></i></button>
                             
                        </div>


                        <div class="col-sm-2">
                            
                               <input type="text" class="in1" id="text2" value="0" >
                             
                        </div>

						  <div class="col-sm-2">
                           
                               <button class="button button-box button-giant" onclick=addnum("text2") ><i class="fa fa-plus"></i></button>
                             
                        </div>
						 </div>
                    </div><!-- /form -->

                </div><!-- /row -->
						
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
			  var arrid=new Array();
			  $("#button").click(function(){
				  var a1=document.getElementById("text1").value;
				  var a2=document.getElementById("text2").value;
				  var a3=parseInt(a1)+parseInt(a2);
				  if((a1!="0"||a2!="0")&&(a3<10)){
		                $.ajax({    
		                   url:'${pageContext.request.contextPath}/SetrowServlet',
		                   data:{dan:a1,san:a2},
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
		                   		window.location.href="${pageContext.request.contextPath}/config/setlock.jsp";
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
		           }
		           else{
					  alert("请求数量必须大于0或总共储位行数不超过9个");
			       }
	     	});
</script>
</body>
</html>