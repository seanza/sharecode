<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:include page="/include/jsfile.jsp" />
<jsp:include page="/include/head.jsp" />
<%request.setCharacterEncoding("UTF-8");%>  
<style>
.button-circle.button-giant, .button-box.button-giant, .button-square.button-giant {
width: 140px;
}
</style>
<style type="text/css">
       .in1{
display: block;
width: 100%;
height: 70px;
padding: 12px 12px;
font-size: 14px;
margin-left:13px  ! important;
line-height: 1.42857143;
color: #555;
background-color: #fff;
background-image: none;
border: 1px solid #ccc;
border-radius: 4px;
-webkit-box-shadow: inset 0 1px 1px rgba(0,0,0,.075);
box-shadow: inset 0 1px 1px rgba(0,0,0,.075);
-webkit-transition: border-color ease-in-out .15s,-webkit-box-shadow ease-in-out .15s;
-o-transition: border-color ease-in-out .15s,box-shadow ease-in-out .15s;
transition: border-color ease-in-out .15s,box-shadow ease-in-out .15s;
	   }
	   .aaa h1{
	   font-size:45px;
	   }
	   .mar{
	   margin-top:30px;
	   }
	   .juzhong{
	  text-align:center;
	  margin:20px auto;
	   }
	   .w80{
	   margin:30px auto;
	   width:80%;
	   }
	   .table>thead>tr>th>input{
	    width:220px;
	   
	   }

	   .maxhei{
	   max-height: 280px;
	   overflow:scroll;
	   }
        </style>
<body>
<jsp:include page="/include/divhead.jsp" />
    <section id="contact-section">
           <div id="div2">
		   <div  class="panel panel-default w80">
		   <div class="panel-heading">盘库操作</div>
		<div id="dateMessage" class="maxhei">   
        <table id="tab1" class="table">   
            <tr><td>位置号</td><td>输入位置条码</td><td>输入电表条码</td></tr>   
        </table>   
        </div>   
            

         
		</div>
		<div class="juzhong">
		<button class="button button-royal button-raised  juzhong" id="button1">确认盘库操作</button>
		</div>
		</div>
        </section>

		<div >  
		</div>
		<footer class="footer">
		<h1>Footer</h1>
		</footer>
        <!-- =========================================
        java script
        ========================================== -->

        <script type="text/javascript" src="css/bootstrap.min.js"></script>
        <script type="text/javascript" src="css/jquery.easing.1.3.js"></script>
        <script type="text/javascript" src="css/uikit.js"></script>
        <script type="text/javascript" src="css/jquery.lwtCountdown-1.0.js"></script>
        <script type="text/javascript" src="css/jquery.scrollto.js"></script>
        <script type="text/javascript" src="css/jquery.parallax-1.1.3.js"></script>
        <script type="text/javascript" src="css/owl.carousel.min.js"></script>
        <script type="text/javascript" src="css/jquery.mousewheel-3.0.6.pack.js"></script>
        <script type="text/javascript" src="css/jquery.fancybox.js"></script>
        <script type="text/javascript" src="css/jquery.fancybox-media.js"></script>
        <script type="text/javascript" src="css/jquery.fitvids.js"></script>
        <script type="text/javascript" src="css/jquery.cbpQTRotator.min.js"></script>
		 
		<script > 
      $(document).ready(function(){
		  var arrid=new Array();
		  var a1="发";
		  var a2="送";
		  <%session.setAttribute("mes",1);%>
    $.ajax( {    
    url:'PankuchushiServlet',
    data:{name:a1,password:a2},
    type:'post',    
    cache:false,    
    dataType:'json',
    success: function (msg) {
    	var num= parseInt(msg);
    	if(num!=-9){
    		if(num==0){
    			var d = dialog({
    	    		title: '消息',
    	    		content: '库内无表',
    	    		 okValue: '确定',
    	    		 ok: function () {
    	    			 window.location.href="${pageContext.request.contextPath}/n3.htm";
    	    		 },
    	    		 cancel: false,
    	    	});
    	    	d.showModal();
    		}
        var str = "";
        for (i=0;i<num;i++) {
            str += "<tr><td>" + (i+1) + "</td><td><input name=aaa style='width:200px'></td><td><input name=aaa style='width:200px'>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</td><td><a onclick='del(this)' class='button button-pill button-primary'>重新扫描</a></td></tr>";
			arrid[i]=i;
        }
        $("tbody").append(str);
        var dlall=document.getElementsByName('aaa');        
        for(i=0;i<dlall.length;i++){
        	dlall[i].id='inp'+i;
        }
        var d = dialog({
    		title: '消息',
    		content: '请依次扫描每个位置表计和储位条码',
    		 okValue: '确定',
    		 ok: function () {},
    		 cancel: false,
    	});
    	d.showModal();
    	}
    	else {
    		 var d = dialog({
    	    		title: '消息',
    	    		content: '可能遇到通讯错误,请联系管理员',
    	    		 okValue: '确定',
    	    		 ok: function () {
    	    			 window.location.href="${pageContext.request.contextPath}/n3.htm";
    	    		 },
    	    		 cancel: false,
    	    	});
    	    	d.showModal();
    	    	}
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
		  var arrcodeid= new Array();
		  var dlall=document.getElementsByName('aaa');
		  var j=0;
            for(var i=0;i<dlall.length;i++){
        	   arrcode[j]=document.getElementById('inp'+i).value;
        	   i++;
        	   j++;
            }
            j=0;
            for(var i=1;i<dlall.length;i++){
         	   arrcodeid[j]=document.getElementById('inp'+i).value;
         	  i++;
         	  j++;
         	  
             }
            j=0;
		  var scode=arrcode.join(",");
		  var sid=arrcodeid.join(",");
    $.ajax( {    
    url:'PankuquerenServlet',
    data:{name:scode,password:sid},
    type:'post',    
    cache:false,    
    dataType:'json',
    success: function (a) {
    	if(a == "位置条码重复"){
    		var d = dialog({
        		lock:true,
        		title: '警告',
        		content: '位置条码发生重复，请检查后重新进行盘库扫描操作',
        		 okValue: '确定',
        		    ok: function () {
        		    	 self.location.reload();
        		    },
        		    cancel: false,
        	});
        	d.showModal();
    	}
    	else if(a == "电表条码重复"){
    	    var d = dialog({
        		lock:true,
        		title: '警告',
        		content: '电表条码发生重复，请检查后重新进行盘库扫描操作',
        		 okValue: '确定',
        		    ok: function () {
        		    	 self.location.reload();
        		    },
        		    cancel: false,
        	});
        	d.showModal();
    	}
    	else if(a=="成功"){
    		var d = dialog({
        		lock:true,
        		title: '消息',
        		content: '成功，系统将回到首页',
        		 okValue: '确定',
        		    ok: function () {
        		    	 window.location.href="n3.htm";
        		    },
        		    cancel: false,
        	});
        	d.showModal();
        	SpeakTextses();
    	}
    	else{
            self.location.reload();
    	}
    },

    error: function(XMLHttpRequest, textStatus, errorThrown) {
        alert(textStatus);
    },
    complete: function(XMLHttpRequest, textStatus) {
        this; // 调用本次AJAX请求时传递的options参数
    }
    });
});



});
</script>   
<script type="text/javascript">
var keystring = "";//记录按键的字符串
var linshi="";
var a=1;
//var ab = document.getElementById("tab1").rows.length;
function keypress(e)
　　{
　　var currKey=0,CapsLock=0,e=e||event;
　　currKey=e.keyCode||e.which||e.charCode;
　　CapsLock=currKey>=65&&currKey<=90;
    var table =document.getElementById("tab1");
    var rows = table.rows.length;
	
　　switch(currKey)
　　{
　　　　//屏蔽了退格、制表、回车、空格、方向键、删除键
　　　　case 8: case 9:case 32:case 37:case 38:case 39:case 40:case 46:keyName = "";break;
　　　　default:keyName = String.fromCharCode(currKey); break;
　　}
　　keystring += keyName;
   if(currKey==13)
	{
		   for ( var i = 0; i < (rows*2+1); i++) //循环到arr数组的长度减一，最后一个就不用循环了 
		{
		  if(document.getElementById('inp'+i).value==keystring){
			  $("input").val("");
		  }
		  else if(document.getElementById('inp'+i).value==""){
			  if(linshi!=keystring){
          document.getElementById('inp'+i).value=keystring;
			  }
		  linshi=keystring;
		  keystring="";
          keyName="";
          //delinpval();
		 break;
          }
		}
	}

}
function keyup(e)
{
　　//document.getElementById("content").innerHTML=keystring;
    //document.getElementById("aa1").value=keystring;
	currKey=e.keyCode||e.which||e.charCode;
	//if(currKey==13){
      //     keystring="";
        //   keyName="";
		//{
}
document.onkeypress=keypress;
document.onkeyup =keyup;

</script>
<script >   
		function del(obj)   
		  {   
		     obj.parentNode.firstChild.value="";   
		     obj.parentNode.children[1].value="";   
		  }
		</script >
		<SCRIPT LANGUAGE="JavaScript">
            var VoiceObj = new ActiveXObject("Sapi.SpVoice"); //创建一个朗读人
            VoiceObj.Rate=-4;
            VoiceObj.Speak("进入盘库功能", 1);
            function SpeakText()
            {
            	VoiceObj.Speak("请打开柜门，按顺序在亮灯位置放表扫码", 1);
            }
            function SpeakTextses()
            {
            	VoiceObj.Speak("入库成功，请关闭柜门", 1);
            }
            function SpeakTextfal()
            {
            	VoiceObj.Speak("入库失败，表位异常，请放回表计", 1);
            }
           
        </SCRIPT>
</body>
</html>