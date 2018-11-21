<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/include/initTag.jsp"%>
<div class="row wrapper wrapper-content animated fadeInRight gray-bg" style="height:auto;min-height:100%;">
<div class="panel-body m-t-xs" style="padding:0px">
  <div class="col-sm-5">
    <div class="index-title">
	    <span>
	      <c:choose>
       		<c:when test="${empty companyInfoBean}">
       			<spring:message code='scinan.title'/>
       		</c:when>
       		<c:otherwise>
       		  <c:choose>
	       		<c:when test="${language == 'en'}">
	       		   ${companyInfoBean.name}
	       		</c:when>
                <c:otherwise>
                   ${companyInfoBean.name}
                </c:otherwise>
              </c:choose>
       		</c:otherwise>
       	  </c:choose>
       	</span>
	    <p><spring:message code='Official.webadd'/>：
	       <c:choose>
       		<c:when test="${empty companyInfoBean}">
       			<a href="http://www.scinan.com" target="_blank">http://www.scinan.com</a>
       		</c:when>
       		<c:otherwise>
       			<a href="${companyInfoBean.company_website}" target="_blank">${companyInfoBean.company_website}</a>
       		</c:otherwise>
       	   </c:choose>
		</p>
    </div>
    <div class="index-company-info">
	    <p>
	      <c:choose>
       		<c:when test="${empty companyInfoBean}">
       		    <spring:message code='scinan.int'/>
       		</c:when>
       		<c:otherwise>
       			${companyInfoBean.company_info}
       		</c:otherwise>
       	   </c:choose>
	    </p>
    </div>
    <div class="index-company-info">
	    <p>
	      <c:choose>
       		<c:when test="${empty companyInfoBean.link_name}">
       		    &nbsp;
       		</c:when>
       		<c:otherwise>
       			联系人:${companyInfoBean.link_name} &nbsp;&nbsp;&nbsp;&nbsp;联系电话:${companyInfoBean.phone}
       		</c:otherwise>
       	   </c:choose>
	    </p>
    </div>
  </div>
  <div class="col-sm-7">
     <div class="col-sm-6 index-device-a">
	    <span><spring:message code='device_total'/></span>
	    <p><a id="index-device-total" onclick='msg_click("#1005003")'>${deviceAllNum}</a></p>
     </div>
     <div class="col-sm-6 index-device-a">
	    <span>分红金额[元]</span>
	    <p><a id="index-device-fault" onclick='msg_click("#1006003")'>${accountInfo.ratio_amount}</a></p>
     </div>
     <div class="col-sm-12 index-device-b">
	    <span>总销售数</span>
	    <a id="index-device-online" onclick='msg_click("#1005001")'>${soldNum}</a>
     </div>
      <div class="col-sm-12 index-device-b">
	    <span>今日销售数</span>
	    <a id="index-device-online">${soldNumDay}</a>
     </div>
     <div class="col-sm-12 index-device-b">
     	<span><spring:message code='device_active'/></span>
     	<a id="index-device-activate" >${countJoin}</a>
     </div>
     <div class="col-sm-12 index-device-b">
     	<span>今日激活量</span>
     	<a id="index-device-activate" >${countJoinDay}</a>
     </div>
     <div class="col-sm-12 index-device-b">
     	<span>现有库存</span>
     	<a id="index-device-outline" >${inventoryNum}</a>
     </div>
     <div class="col-sm-12 index-device-b">
     	<span><spring:message code='device_inactive'/></span>
     	<a id="index-device-noactivate" >${countUnJoin}</a>
     </div>
     <div class="col-sm-12 index-device-b">
     	<span>补贴金额</span>
     	<a id="index-device-noactivate"  onclick='msg_click("#1006005")'>${accountInfo.subsidy_amount}</a>
     </div>
  </div>
</div>
</div>
<script>
	function turn(val){
		$("#" + val + " span",window.parent.document).parent().parent().parent().collapse("show");
		$("#" + val + " span",window.parent.document).trigger('click');
		$("#" + val + " span",window.parent.document).parent().focus();
		$("#" + val + " span",window.parent.document).parent().parent().parent().parent().siblings().children("ul").collapse("hide");
	} 
	
	
	
	function turn_common(val,status){
		$('#ifr_' + val,window.parent.document).remove();
		setCookie("dstatus",status,1);
		$("#" + val + " span",window.parent.document).parent().parent().parent().collapse("show");
		$("#" + val + " span",window.parent.document).trigger('click');
		$("#" + val + " span",window.parent.document).parent().focus();
		$("#" + val + " span",window.parent.document).parent().parent().parent().parent().siblings().children("ul").collapse("hide");
	}
	
	
	 function msg_click(id){
		   window.parent.$(id, window.parent.document).trigger('click');
		}
	
</script>