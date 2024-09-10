<%--
  Created by IntelliJ IDEA.
  User: 12940
  Date: 2024/8/20
  Time: 上午9:59
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
    <script src="/static/kindeditor/kindeditor.js"></script>
</head>
<body>
<form class="layui-form layui-form-pane" lay-filter="formFilter" action="">
    <div class="layui-form-item">
        <label class="layui-form-label">姓名</label>
        <div class="layui-input-block">
            <input type="hidden" name="id">
            <input type="text" name="name" autocomplete="off" placeholder="请输入" lay-verify="required" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">性别</label>
        <div class="layui-input-inline">
            <select name="gender">
                <option>男</option>
                <option>女</option>
            </select>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">职位</label>
        <div class="layui-input-inline">
            <select name="roleId" id="roleName">
            </select>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">薪资</label>
        <div class="layui-input-block">
            <input type="text" name="salary" autocomplete="off" placeholder="请输入" lay-verify="required" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">领导工号</label>
        <div class="layui-input-block">
            <input type="text" name="leaderId" autocomplete="off" placeholder="请输入" lay-verify="required" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <button class="layui-btn" lay-submit lay-filter="isSubmit">确认</button>
        <button type="reset" class="layui-btn layui-btn-primary">重置</button>
    </div>
</form>
<script>
    var kindEditorParams = {
        afterBlur: function() {
            this.sync();
        }
    };
    var editor = KindEditor.create('#contentId', kindEditorParams);
    layui.use(['form'], function(){
        var form = layui.form;
        // 提交事件
        var queryString = window.location.search;
        var urlParams = new URLSearchParams(queryString);
        var id = urlParams.get("id");
        $.post(
            " /employee?method=selectById",
            {"id":id},
            function (map) {
                result = map.data
                var list=result.roles;
                $(list).each(function (){
                    console.log(this);
                    $("#roleName").append("<option value="+this.id+">"+this.name+"</option>")
                })
                form.render("select");
                console.log(result.employee);
                if (map.code == 0){
                    form.val("formFilter",result.employee);
                }
            },
            "json"
        )
        // 提交事件
        form.on('submit(isSubmit)', function(data){
            var field = data.field; // 获取表单字段值
            // 显示填写结果，仅作演示用
            var layer = layui.layer;
            /*layer.alert(JSON.stringify(field), {
                title: '当前填写的字段值'
            });*/
            console.log(data.field);
            $.post(
                "/employee?method=edit",
                data.field,
                function (jsonObj) {
                    console.log(jsonObj);
                    if (jsonObj.code == 0){
                        mylayer.okMsg("编辑成功");
                        setInterval(function (){
                            var index = parent.layer.getFrameIndex(window.name);
                            parent.layer.close(index);
                            window.parent.location.reload();
                        },2000)
                    }
                },
                "json"
            )
            // 此处可执行 Ajax 等操作
            return false;
            // …
        });
    });
</script>
</body>
</html>
