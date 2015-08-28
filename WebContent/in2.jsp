<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:include page="/include/jsfile.jsp" />
<jsp:include page="/include/head.jsp" />
<%request.setCharacterEncoding("UTF-8"); %>  
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
                   <div  class="panel panel-default w80">
		               <div class="panel-heading">入库操作</div>
		               <div id="dateMessage" class="maxhei">   
                       <table id="tab1" class="table">   
                      <tr><td>位置号</td><td>行号</td><td>列号</td><td>型号</td><td>输入条码</td></tr>   
                       </table>   
        </div>   
		</div>
		<div class="juzhong">
		<button class="button button-royal button-raised  juzhong" id="button1">确认入库</button>
		<button class="button button-royal button-raised  juzuo" id="button2">取消操作</button>
		</div>
                    </div>
                    </div>
                    </div>>
			</section>
			<footer class="footer">
		        <h1>杭州世创电子技术股份有限公司</h1>
		   </footer>
		   <script > 
		      var a=<%=session.getAttribute("mes")%>
              var id=a.toString();
              var arrid=new Array();
              $.ajax( {    
            	    url:'${pageContext.request.contextPath}/InselServlet',
            	    data:{id:id},
            	    type:'post',    
            	    cache:false,    
            	    dataType:'json',
            	    success: function (msg) {
            	        var str = "";
            	        for (i in msg) {
            	            str += "<tr><td>" + msg[i].id + "</td><td>" + msg[i].row + "</td><td>" + msg[i].col + "</td><td>" + msg[i].model + "</td><td><input name=aaa style='width:200px'>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<button onclick='del(this)' class='button button-pill button-primary'>重新扫描</button></td></tr>";
            				arrid[i]=msg[i].id;
            	        }
            	        $("tbody").append(str);
            	        var dlall=document.getElementsByName('aaa');        
            	        for(i=0;i<dlall.length;i++){
            	        	dlall[i].id='inp'+i;
            	        }
            	        var d = dialog({
            	    		title: '消息',
            	    		content: '请打开柜门，逐个扫码放表，开始入库操作',
            	    		 okValue: '确定',
            	    		 ok: function () {},
            	    		 cancel: false,
            	    	});
            	        $.ajax({    
            	            url:'${pageContext.request.contextPath}/OpendoorServlet',
            	            data:{id:id},
            	            type:'post',    
            	            cache:false,
            	            dataType:'json',
            	        });
            	    	d.showModal();
            	    },

            	    error: function(aa) {
            	    	var d = dialog({
            	    		lock:true,
            	    		title: '警告消息',
            	    		content: '请求入库仪表数量大于可放仪表数量，请点击确定返回上一级重新选择入库数量',
            	    		 okValue: '确定',
            	    		    ok: function () {
            	    		    	location.reload(true);
            	    		    },
            	    		    cancel: false,
            	    	});
            	    	d.showModal();
            	    },
            	    complete: function(XMLHttpRequest, textStatus) {
            	        this; // 调用本次AJAX请求时传递的options参数
            	    }
            	    });
              
              
              
              
              
              
        	  $("#button1").click(function(){
        		  var arrcode= new Array();
        		  var dlall=document.getElementsByName('aaa');
                    for(i=0;i<dlall.length;i++){
                	   arrcode[i]=document.getElementById('inp'+i).value;
                    }
        		  var scode=arrcode.join(",");
        		  var sid=arrid.join(",");
            $.ajax( {    
            url:'${pageContext.request.contextPath}/InsaveServlet',
            data:{scode:scode,sid:sid,id:id},
            type:'post',    
            cache:false,    
            dataType:'json',
            success: function (a){
            	if(a=="成功"){
            		
            		var d = dialog({
                		lock:true,
                		title: '消息',
                		content: '入库成功，系统将回到首页',
                		 okValue: '确定',
                		    ok: function () {
                		    	 window.location.href="n3.htm";
                		    },
                		    cancel: false,
                	});

                	d.showModal();
                	complete();
            	}
            	else if(a=="继续"){
            		var d = dialog({
                		lock:true,
                		title: '消息',
                		content: '本周转柜入库成功，继续在下一个周转柜入库',
                		 okValue: '确定',
                		    ok: function () {
                		    	window.location.reload();
                		    },
                		    cancel: false,
                	});
                	d.showModal();
            	}
            	else if(a == "条码重复"){
            		var d = dialog({
                		lock:true,
                		title: '提示',
                		content: '扫描条码存在重复，将回到上一页面重新开始本次入库',
                		 okValue: '确定',
                		    ok: function () {
                		    	$.ajax({    
	                     	            url:'${pageContext.request.contextPath}/CancelServlet',
	                     	            data:{set:id},
	                     	            type:'post',    
	                     	            cache:false,    
	                     	            dataType:'json',
	                     	        });
	                		    	window.location.href = "${pageContext.request.contextPath}/in1.jsp";
                		    },
                		    cancel: false,
                	});
                	d.showModal();
            	}
            	else if(a=="串口通讯错误"){
	            	var d = dialog({
	                		lock:true,
	                		title: '消息',
	                		content: '与周转柜串口通信出现问题，将取消本次入库',
	                		 okValue: '确定',
	                		    ok: function () {
		                		    $.ajax({    
	                     	            url:'${pageContext.request.contextPath}/CancelServlet',
	                     	            data:{set:id},
	                     	            type:'post',    
	                     	            cache:false,    
	                     	            dataType:'json',
	                     	        });
	                		    	window.location.href="n3.htm";
	                		    },
	                		    cancel: false,
	                	});
	                d.showModal();
            	}
            	else{
            		var d = dialog({
                		lock:true,
                		title: '消息',
                		content: '失败，储位异常，存在错误，请将储位保持出库前状态点击取消入库或点击进入盘库',
                		okValue: '取消入库',
                		    ok: function () {
                		    	 $.ajax({    
                     	            url:'${pageContext.request.contextPath}/CancelServlet',
                     	            data:{set:id},
                     	            type:'post',    
                     	            cache:false,    
                     	            dataType:'json',
                     	        });
                                 window.location.href="n3.htm";
                		    },
                		cancelValue: '开启盘库',
                		cancel: function () {
                			window.location.href="panku.jsp";
                		    }

                	});
                	d.showModal();
            	}
            },

            error: function(XMLHttpRequest, textStatus, errorThrown) {
                //alert(textStatus);
            },
            complete: function(XMLHttpRequest, textStatus) {
                this; // 调用本次AJAX请求时传递的options参数
            }
            });
        });
        	  $("#button2").click(function(){
                		    	 $.ajax({    
                     	            url:'${pageContext.request.contextPath}/CancelServlet',
                     	            data:{set:id},
                     	            type:'post',    
                     	            cache:false,    
                     	            dataType:'json',
                     	        });
                                 window.location.href="n3.htm";
                		   
        });
</script>
<script>
var keystring = "";//记录按键的字符串
var linshi="";
var a=1;
function keypress(e){
	   var currKey=0,CapsLock=0,e=e||event;
	   currKey=e.keyCode||e.which||e.charCode;
	   CapsLock=currKey>=65&&currKey<=90;
	   var table =document.getElementById("tab1");
	   var rows = table.rows.length;
	   switch(currKey)
	   {
	        //屏蔽了退格、制表、回车、空格、方向键、删除键
	      case 8: case 9:case 32:case 37:case 38:case 39:case 40:case 46:keyName = "";break;
	      default:keyName = String.fromCharCode(currKey); break;}
	   keystring += keyName;

	   if(currKey==13)
		{
			   for ( var i = 0; i < rows+2; i++) //循环到arr数组的长度减一，最后一个就不用循环了 
			{
			  if(document.getElementById('inp'+i).value==""){
				  if(linshi!=keystring){
	          document.getElementById('inp'+i).value=keystring;
	          
				  }
			  linshi=keystring;
			  keystring="";
	          keyName="";
			  break;
	          }
			 }
		}
	}
	function keyup(e)
	{
		currKey=e.keyCode||e.which||e.charCode;
	}
	document.onkeypress=keypress;
	document.onkeyup =keyup;
	function del(obj){   
		obj.parentNode.firstChild.value="";   
		}
	var VoiceObj = new ActiveXObject("Sapi.SpVoice");
	VoiceObj.Rate=-1;
	VoiceObj.Speak("请打开柜门，按顺序放表，扫码，完成后点击确认按钮",1);
	function complete()
    {
    	VoiceObj.Speak("入库成功，请关闭柜门", 1);
    }
</script>
</body>
</html>