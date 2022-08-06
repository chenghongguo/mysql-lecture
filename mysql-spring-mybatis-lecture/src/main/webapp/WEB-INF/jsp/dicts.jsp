<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <c:set var="path" value="${pageContext.request.contextPath}"/>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>字典信息</title>
</head>
<body>
<table>
    <tr>
        <th colspan="4">字典管理</th>
    </tr>
    <tr>
        <th>类别名</th>
        <th>字典名</th>
        <th>字典值</th>
        <th>操作 [<a href="${path}/dict/add">新增</a> ]</th>
    </tr>
    <c:forEach items="${dicts}" var="dict">
        <tr id="dict-${dict.id}">
            <td>${dict.code}</td>
            <td>${dict.name}</td>
            <td>${dict.value}</td>
            <td>
                [<a href="${path}/dict/add?id=${dict.id}">编辑</a> ]
                [<a href="javascript:void(0);" onclick="deleteById(${dict.id}, '${dict.name}')">删除</a> ]
            </td>
        </tr>
    </c:forEach>
</table>

<script>
    function deleteById(id, label) {
        var r = confirm('确定要删除"' + label + '"?');
        if (r) {
            $.ajax({
                url: '${path}/dict/delete',
                data: {
                    id: id
                },
                dataType: 'json',
                type: 'POST',
                success: function (data) {
                    if (data.success) {
                        $('#dict-' + id).remove();
                    } else {
                        alert(data.msg);
                    }
                }
            })
        }
    }
</script>
<script src="${path}/static/jquery.min.js"></script>
</body>
</html>
