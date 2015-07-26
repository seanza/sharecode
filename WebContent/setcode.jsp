<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html  class=" js flexbox flexboxlegacy canvas canvastext webgl no-touch geolocation postmessage websqldatabase indexeddb hashchange history draganddrop websockets rgba hsla multiplebgs backgroundsize borderimage borderradius boxshadow textshadow opacity cssanimations csscolumns cssgradients cssreflections csstransforms no-csstransforms3d csstransitions fontface generatedcontent video audio localstorage sessionstorage webworkers applicationcache svg inlinesvg smil svgclippaths" style=""><!--<![endif]--><!-- =========================================
    head
    ========================================== --><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <!-- =========================================
        Basic
        ========================================== -->
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <title>智能周转柜</title>
        <!-- =========================================
        Mobile Configurations
        ========================================== -->
        <meta name="viewport" content="width=device-width, initial-scale=1.0 user-scalable=no">
        <meta name="apple-mobile-web-app-status-bar-style" content="black">
        <meta name="GOOGLEBOT" content="index follow">
        <meta name="apple-mobile-web-app-capable" content="yes">

        <!-- =========================================
        CSS
        ========================================== -->
        
        <link rel="stylesheet" media="screen" href="css/bootstrap.min.css">
		<link rel="stylesheet" media="screen" href="css/font-awesome.min.css">
        <link rel="stylesheet" media="screen" href="css/style.css">
		<link rel="stylesheet" type="text/css" href="css/demo.css" />
        <link rel="stylesheet" type="text/css" href="css/style3.css" />
		<link rel="stylesheet" href="css/buttons.css">
		<link rel="stylesheet" href="css/ui-dialog.css">

        <!-- =========================================
        Head Libs
        ========================================== -->
                <script type="text/javascript" src="css/jquery-1.11.0.min.js"></script>
        <script type="text/javascript" src="css/modernizr.custom.js"></script>
        <script src="dist/dialog-min.js"></script>
        <jsp:include page="/include/jsfile.jsp" />
        <jsp:include page="/include/head.jsp" />
        <%request.setCharacterEncoding("UTF-8"); %>
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
	   .juzuo{
	  
	  margin-left:30px;
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

    </head><!-- /head -->




    <!-- =========================================
    body
    ========================================== -->
    <body>



        <!--[if lt IE 7]>
            <p class="browsehappy">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade your browser</a> to improve your experience.</p>
        <![endif]-->





        <!-- =========================================
        Menu
        ========================================== -->
        <!-- nav-wrapper -->
        <div id="nav-wrapper">
            <!-- navbar -->
            <div class="navbar">
                <!-- container -->
                <div class="container">
                    <!-- row -->
                    <div class="row">

                        <!-- col-md-12 -->
                        <div class="col-md-12">

                            <!-- navbar-header -->
                            <div class="navbar-header">

                                <!-- Mobile Menu -->
                                <button data-target=".navbar-collapse" data-toggle="collapse" class="navbar-toggle" type="button">
                                    <i class="fa fa-bars"></i>
                                </button><!-- / mobile Menu -->

                                <!-- logo -->
                                <a href="#" class="navbar-brand scrollto" title="Horrus">
                                    <img src="./css/logo2.png" alt="logo">
                                </a><!-- /logo -->

                            </div><!-- /navbar-header -->

                            <!-- navbar-collapse -->
                            <div class="navbar-collapse collapse">

                                <!-- nav -->
                                <ul class="nav navbar-nav navbar-right">

                                    <!-- Home -->
                                    <li>
                                        <a href="#" title="Home" class="scrollto">
                                           首页
                                        </a>
                                    </li><!-- /Home -->

                                    <!-- Services -->
                                    <li>
                                        <a href="#" title="Our Services" class="scrollto">
                                           帮助                                       </a>
                                    </li><!-- /Services -->

                                    <!-- Portfolio -->
                                    <li>
                                        <a href="#" title="Portfolio" class="scrollto">
                                           关于
                                        </a>
                                    </li><!-- /Portfolio -->

                                </ul><!-- /nav -->

                            </div><!-- /navbar-collapse -->

                        </div><!-- /col-md-12 -->

                    </div><!-- /row -->
                </div><!-- /container -->
            </div><!-- /navbar -->
        </div><!-- /nav-wrapper -->

    <section id="contact-section">
        <div class="container" id="div1" style="display:none;">
                <!-- row -->
                <div class="row">

                    <!-- col-md-12 -->
                    <div class="col-md-12">

                        <!-- section-title -->
                        <div class="section-title uk-scrollspy-init-inview uk-scrollspy-inview uk-animation-fade" data-uk-scrollspy="{cls:'uk-animation-fade', delay:500}">
                            <h1>请根据周转柜结构进行选择：</h1>
                        </div><!-- /section-title -->

                        <!-- section-desc -->
                        <div class="section-desc uk-scrollspy-init-inview uk-scrollspy-inview uk-animation-fade" data-uk-scrollspy="{cls:'uk-animation-fade', delay:700}">
                            <p>请选择单相表与三相表的个数</p>
                        </div><!-- /section-desc -->

                    </div><!-- /col-md-12 -->


                    <!-- form -->
                    <div  id="form" class="contactForm" >

                        <div>
                        <!-- col-md-6 -->
						
						 <div class="col-md-1">
                           
                             
                             
                        </div>
                        <div class="col-md-5 aaa">
                                <h1>单相表的个数为:</h1>
                        </div>
                       
               
                        <div class="col-md-1">
                           
                               <button class="button button-box button-giant" onclick=minnum("text1")><i class="fa fa-minus"></i></button>
                             
                        </div>


                        <div class="col-md-2">
                            
                               <input type="text" class="in1" id="text1" value="0" >
                             
                        </div>

						  <div class="col-md-2">
                           
                               <button class="button button-box button-giant" onclick=addnum("text1") ><i class="fa fa-plus"></i></button>
                             
                        </div>
						 <div class="col-md-1">
                           
                             
                             
                        </div>
						 </div>
                    </div><!-- /form -->



                </div><!-- /row -->

				 <div class="row mar">


                    <!-- form -->
                    <div  id="form" class="contactForm" >

                        <div>
                        <!-- col-md-6 -->
						
						 <div class="col-md-1">
                           
                             
                             
                        </div>
                        <div class="col-md-5 aaa">
                                <h1>三相表的个数为:</h1>
                        </div>
                       
               
                        <div class="col-md-1">
                           
                               <button class="button button-box button-giant" onclick=minnum("text2")><i class="fa fa-minus"></i></button>
                             
                        </div>


                        <div class="col-md-2">
                            
                               <input type="text" class="in1" id="text2" value="0" >
                             
                        </div>

						  <div class="col-md-2">
                           
                               <button class="button button-box button-giant" onclick=addnum("text2") ><i class="fa fa-plus"></i></button>
                             
                        </div>
						 <div class="col-md-1">
                           
                             
                             
                        </div>
						 </div>
                    </div><!-- /form -->

                </div><!-- /row -->

                <div class="juzhong">
				<a class="btn btn-lg btn-nesto scrollto juzhong" id="button" title="View Portfolio" data-uk-scrollspy="{cls:&#39;uk-animation-slide-bottom&#39;, delay:500}">
                               确定
                            </a><!-- /btn-nesto -->
                </div>


           </div>
           
           <div id="div2" style="display:block;">
		   <div  class="panel panel-default w80">
		   <div class="panel-heading">智能周转柜</div>
		<div id="dateMessage" class="maxhei">   
        <table id="tab1" class="table">   
            <tr><td>位置号</td><td>行号</td><td>列号</td><td>型号</td><td>位置条码</td></tr>   
        </table>   
        </div>   
		</div>
		
		<div class="juzhong">
		<a class="button button-royal button-raised  juzhong" id="button1">确定么？</a>
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
    	  var dr=<%=session.getAttribute("drow")%>
    	  var sr=<%=session.getAttribute("srow")%>
    	  var ccc=<%=session.getAttribute("comn")%>
    	  var arrid=new Array();
          var str = "";
          var model = "";
          var row;
          var col;
          for (i=1;i<=(dr*8+sr*5);i++) {
              if(i <= dr*8)
              {
                  model = "s";
                  row = (i-i%8)/8+1;
                  col = i%8;
                  if(i%8==0)
              	  {
              	      row--;
              	      col=8;
                  }
              }
              else
              {
                  model = "b";
                  row = ((i-dr*8)-(i-dr*8)%5)/5+dr+1;
                  col = (i-dr*8)%5;
                  if((i-dr*8)%5 == 0)
                  {
                      row --;
                      col = 5;
                  }
              }
              
              str += "<tr><td>" + i + "</td><td>" + row + "</td><td>" + col + "</td><td>" + model + "</td><td><input name=aaa style='width:200px' />&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<a onclick='del(this)' class='button button-pill button-primary'>重新扫描</a></td></tr>";
  			  arrid[i-1]=i;
          }
          $("tbody").append(str);
          var dlall=document.getElementsByName('aaa');        
          for(i=0;i<dlall.length;i++){
          	dlall[i].id='inp'+i;
          }
          var cccc = ccc.toString();
          var d = dialog({
            	   title: '消息',
            	   content: '请打开柜门，并根据周转柜的结构情况一次扫描位置条码',
            	   okValue: '确定',
            	   ok: function () {},
            	   cancel: false,
            	 });
          $.ajax({
	          url:'${pageContext.request.contextPath}/OpendoorServlet',
	          data:{id:cccc},
	          type:'post',
	          cache:false,
	          dataType:'json'
          });
      	  d.showModal();

      	 $("#button1").click(function(){
   		  var arrcode= new Array();
   		  var dlall=document.getElementsByName('aaa');
               for(i=0;i<dlall.length;i++){
           	   arrcode[i]=document.getElementById('inp'+i).value;
               }
   		  var scode=arrcode.join(",");
   		  var sid=arrid.join(",");
       $.ajax({    
       url:'${pageContext.request.contextPath}/SetchuweicodeServlet',
       data:{name:scode,password:sid},
       type:'post',    
       cache:false,    
       dataType:'text',
       success: function (a) {
        if(a=="条码重复"){
	        var d = dialog({
	           		lock:true,
	           		title: '警告',
	           		content: '周转柜的位置条码出现重复，请检查后重新进行条码的扫描工作',
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
           		title: '提示',
           		content: '成功设置本周转柜的位置条码，将跳转至DI/DO设置页面',
           		 okValue: '确定',
           		    ok: function () {
           		    	 window.location.href="./config/setlock.jsp";
           		    },
           		    cancel: false,
           	});
           	d.showModal();
       	}
       	else{
               alert(a);
               self.location.reload();
       	}
       },

       error: function(XMLHttpRequest, textStatus, errorThrown) {
    	   alert(XMLHttpRequest);
           alert(textStatus);
           alert(errorThrown);
       },
       complete: function(XMLHttpRequest, textStatus) {
           this; // è°ç¨æ¬æ¬¡AJAXè¯·æ±æ¶ä¼ éçoptionsåæ°
       }
       });
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
</script>

</body>
</html>