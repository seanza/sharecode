<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
        <jsp:include page="./include/jsfile.jsp" />
        <jsp:include page="./include/head.jsp" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap-datetimepicker.min.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/jqueryui/css/smoothness/jquery-ui-1.10.4.custom.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/jqgrid/css/ui.jqgrid.css">
		
				
		<script type="text/javascript"src="${pageContext.request.contextPath}/css/bootstrap-datetimepicker.min.js"></script>
		<script type="text/javascript"src="${pageContext.request.contextPath}/jqueryui/js/jquery-ui-1.10.4.custom.js"></script>
		<script type="text/javascript"src="${pageContext.request.contextPath}/jqgrid/js/i18n/grid.locale-en.js"></script>
		<script type="text/javascript"src="${pageContext.request.contextPath}/jqgrid/js/jquery.jqGrid.src.js"></script>
		<script type="text/javascript"src="${pageContext.request.contextPath}/jqgrid/src/jquery.jqGrid.js"></script>
		<script type="text/javascript"src="${pageContext.request.contextPath}/jqgrid/js/jquery.jqGrid.min.js"></script>
		<script type="text/javascript"src="${pageContext.request.contextPath}/css/bootstrap-datetimepicker.zh-CN.js"></script>
        <style>
                 .button-circle.button-giant, .button-box.button-giant, .button-square.button-giant {
                         width: 140px;
                 }
                      #scrollUp {
     bottom: 122px;
background-color: #777;
color: #eee;
font-size: 30px;
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
<body>
<jsp:include page="./include/divhead.jsp" />
 <section id="contact-section">
        <div class="container">
                <!-- row -->
                <div class="row">
               <div class="col-sm-12">
                        <div class="section-title uk-scrollspy-init-inview uk-scrollspy-inview uk-animation-fade">
                            <h2>按日期查询报警记录</h2>
                        </div>

               </div><!-- /col-md-12 -->
               <div class="mar">
               <div id="divri" class="col-sm-4">
                <div class="input-group date form_date" data-date="" data-date-format="yyyy-mm-dd" data-link-field="dtp_input2" data-link-format="yyyy-mm-dd">
                    <input id="dtpicday" class="form-control" size="16"  type="text"  readonly />
                    <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                    <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                    <input type="hidden" id="Hidden4" value="" />
                </div> 
                <div id="btchaxun" class="col-sm-2">
                      <input id="qsrichaxun" type="button" value="查询" />   
                </div>
                </div>
                </div>
                <div class="col-sm-12 juzhong" id="home"  style="height:420px">
                <table id="list1"></table>
                </div>
       </div>
           
</div>
</section>
         <a id="scrollUp" style="position: fixed;display: block;">后退</a>
<footer class="footer">
		        <h1>杭州世创电子技术股份有限公司</h1>
		   </footer>

</body>
<script type="text/javascript">
    $('.form_date').datetimepicker({
        language: 'zh-CN',
        weekStart: 1,
        todayBtn: 1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 2,
        minView: 2,
        forceParse: 0,
        pickerPosition: "bottom-left"
    });
    var date="2015-01-01";
    var PostData = { date:date };
    $("#list1").jqGrid({
    	url:'${pageContext.request.contextPath}/GetalertServlet',
        datatype: "json",
        height: 400,
        cache:false,
        rowNum:1000,
        colModel: [
            { name: '日期', index: 'date', width: 100, align: "left" },
            { name: '时间', index: 'time', width: 100, align: "left" },
            { name: '事件', index: 'log', width: 400, align: "left" },
        ],
        postData: PostData,
      });
    $('#dtpicday').change(function () {//获取输入的年月日
        date= $("#dtpicday").val();
    });
    $('#qsrichaxun').click(function () {
      PostData = { date:date };
      $("#list1").jqGrid('setGridParam',{ 
    	url:'${pageContext.request.contextPath}/GetalertServlet',
        datatype: "json",
        colModel: [
   	            { name: 'date', index: 'date', width: 100, align: "left" },
   	            { name: 'time', index: 'time', width: 100, align: "left" },
   	            { name: 'log', index: 'log', width: 400, align: "left" },
   	        ],
   	     rowNum:1000,
        postData: PostData,
      }).trigger("reloadGrid");
    });
    $("#scrollUp").click(function(){
 	   window.location.href="find.jsp";
 	   
    });
</script>
</html>