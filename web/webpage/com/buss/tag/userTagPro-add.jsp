<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="listBoxGen">
    <div class="listBoxTitleGen">
        <div class="showTitle">提交留言</div>
    </div>
    <div class="listBoxContentGen">
        <form id="form">
            <table width="100%" border="0" align="center" cellpadding="3" cellspacing="0" class="CommonTb">
                <tbody>

                <tr>
					<td align="right">
						<label class="Validform_label">
							标签类型:
						</label>
					</td>
					<td class="value">
							<select id="tagId" name="tagId" datatype="*" onchange="show()">
								<c:forEach items="${tagList}" var="tag">
									<option value="${tag.id }">${tag.name}</option>
								</c:forEach>
								<option value="0">新增标签</option>
							</select>
						<span class="Validform_checktip">请选择标签类型</span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							产品ID:
						</label>
					</td>
					<td class="value">
					     	 <input id="productId" name="productId" value="${userTagProPage.productId }" type="text" style="width: 150px" readonly="readonly" class="inputxt"
								               datatype="*"
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">productId</label>
						</td>
				</tr>
                
				<tr id="tagN" style="display: none">
					<td align="right">
						<label class="Validform_label">
							标签名称:
						</label>
					</td>
					<td class="value">
					     	 <input id="tagName" name="tagName" type="text" style="width: 150px" class="inputxt"
								               datatype="*"
								               >
							<span class="Validform_checktip"></span>
						</td>
				</tr>
				
                <tr>
                    <td align="center" colspan="2"><input type="button" name="Button1" value="提 交" id="Button2"
                                                          class="btn btn-primary" onclick="dosubmit()"><span
                            id="showText"></span></td>
                </tr>
                </tbody>
            </table>
        </form>

    </div>
    <br><br>
</div>
<script>
    function dosubmit() {
        $.ajax({
            cache: true,
            type: "POST",
            url: "userTagProController.do?doAdd",
            data: $('#form').serialize(),// 你的formid
            async: false,
            dataType: 'json',
            error: function (request) {
                showContent('', 'frontController.do?list');
            },
            success: function (tt) {
                var msg = tt.msg;
                var success = tt.success;
                if (success == true) {
					alert(msg);
                    window.close(); 
                } else {
                    alert(msg);
                }

            }
        });
    }
	
	function show(){
		var tag = document.getElementById("tagId").value;
		if(tag == "0"){
			$("#tagN").css('display' ,'');
		}else{
			$("#tagN").css('display' ,'none');
		}
	}

	$.ajax({
		url: "frontController.do?tag_list",
		success: function (data) {
			data = eval(data);
			$('#tagId').html("");
			var str = "<option value=\"\">---请选择---</option>";
			for (var i = data.length-1, o; i >= 0; i--) {
				o = data[i];
				str += "<option value=\""+o.id+"\">"+o.name+"</option>";
			}
			str += "<option value=\"0\">新增标签</option>";
			$('#tagId').append(str);
		},
		error: function (e) {
		}
	});
</script>