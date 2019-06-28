<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="cache-control" content="no-cache" />
		<meta http-equiv="expires" content="0" />
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
		<meta http-equiv="description" content="This is my page" />

		<link rel="stylesheet" type="text/css" href="<%=path %>/css/base.css" />
		
        <script language="javascript">
           function checiDel(checiId)
           {
               if(confirm('您确定删除吗？'))
               {
                   window.location.href="<%=path %>/checiDel.action?checiId="+checiId;
               }
           }
           
           function checiAdd()
           {
                 var url="<%=path %>/admin/checi/checiAdd.jsp";
				 window.location.href=url;
           }
       </script>
	</head>

	<body leftmargin="2" topmargin="2" background='<%=path %>/img/allbg.gif'>
			<table width="98%" border="0" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">
				<tr bgcolor="#E7E7E7">
					<td height="14" colspan=8" background="<%=path %>/img/tbg.gif">&nbsp;车次维护&nbsp;</td>
				</tr>
				<tr align="center" bgcolor="#FAFAF1" height="22">
					<td width="10%">始发站</td>
					<td width="10%">终点站</td>
					<td width="15%">车票日期</td>
					<td width="15%">发车时间</td>
					<td width="15%">到达时间</td>
					<td width="12%">票价</td>
					<td width="13%">票数</td>
					<td width="15%">操作</td>
		        </tr>	
				<s:iterator value="#request.checiList" id="checi">
				<tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='red';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
					<td bgcolor="#FFFFFF" align="center">
						<s:property value="#checi.shifazhan"/>
					</td>
					<td bgcolor="#FFFFFF" align="center">
						<s:property value="#checi.daodazhan"/>
					</td>
					<td bgcolor="#FFFFFF" align="center">
						<s:property value="#checi.piaoshijian"/>
					</td>
					<td bgcolor="#FFFFFF" align="center">
						<s:property value="#checi.startshijian"/>
					</td>
					<td bgcolor="#FFFFFF" align="center">
					    <s:property value="#checi.endshijian"/>
					</td>
					<td bgcolor="#FFFFFF" align="center">
						<s:property value="#checi.piaojia"/>
					</td>
					<td  bgcolor="#FFFFFF" align="center">
					    <s:property value="#checi.piaoshu"/>
					</td>
					<td bgcolor="#FFFFFF" align="center">
					    <a href="<%=path %>/checiEditPre.action?checiId=<s:property value="#checi.id"/>">编辑</a>
					    &nbsp;&nbsp;&nbsp;&nbsp;
						<a href="#" onclick="checiDel(<s:property value="#checi.id"/>)" class="pn-loperator">删除</a>
					</td>
				</tr>
				</s:iterator>
			</table>
			
			<table width='98%'  border='0'style="margin-top:8px;margin-left: 5px;">
			  <tr>
			    <td>
			      <input type="button" value="添加" style="width: 80px;" onclick="checiAdd()" />
			    </td>
			  </tr>
		    </table>
	</body>
</html>
