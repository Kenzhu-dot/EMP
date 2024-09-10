<%--
  Created by IntelliJ IDEA.
  User: 12940
  Date: 2024/9/4
  Time: 下午7:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/static/layui/css/layui.css">
    <link rel="stylesheet" href="/static/layui/layui.js">
    <script src="/static/jquery-2.1.4.js" type="text/javascript" charset="utf-8"></script>
    <script src="/static/mylayer.js" type="text/javascript" charset="utf-8"></script>
    <script src="/static/layui/layui.js"></script>
</head>
<body>
<form class="layui-form layui-row layui-col-space16">
    <table class="layui-hide" id="tableId"></table>
</form>
</div>
<script type="text/html" id="stausSwitchId">
    <!-- 这里的 checked 的状态值判断仅作为演示 -->
    <input type="checkbox" id="state" name="state" value="{{= d.auth.id }}" title="启用|禁用" lay-skin="switch" lay-filter="statusSwitch" {{= d.state == 1? "checked" : ""}}>
</script>
<script>
    layui.use(['form'], function() {
        var form = layui.form;
        // 提交事件
        var table=layui.table;
        var queryString = window.location.search;
        var urlParams = new URLSearchParams(queryString);
        var id = urlParams.get("id");
        table.render({
            elem: '#tableId',
            url: '/auth?method=selectAll&id='+id,
            toolbar: '#toolbarDemo',
            page: true,
            height: '315px',
            cols: [[
                // 模板 - 选择器写法
                {field: 'auth' , title: 'name'},
                // 模板 - 函数写法
                {field:'state',title: '状态', width:85,templet: '#stausSwitchId'},]]
        });
        form.on('switch(statusSwitch)', function(obj){
            var authid = this.value;
            var checked =obj.elem.checked;
            var state = checked? 1 :0;
            $.post(
                "/role?method=updateMidTable",
                {"authId":authid,"roleId": id,"state":state},
                function (result) {
                    console.log(result);
                    mylayer.okMsg(result.msg);
                },
                "json"
            )
        });

    })
</script>
</body>
</html>
