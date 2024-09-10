<%--
  Created by IntelliJ IDEA.
  User: 12940
  Date: 2024/8/31
  Time: 上午10:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<link rel="stylesheet" href="/static/layui/css/layui.css">
<link rel="stylesheet" href="/static/layui/layui.js">
<script src="/static/jquery-2.1.4.js" type="text/javascript" charset="utf-8"></script>
<script src="/static/mylayer.js" type="text/javascript" charset="utf-8"></script>
<script src="/static/layui/layui.js"></script>
<body>
<script id="imageTemplet">
    <img src="/pic/{{d.image}}"></img>
</script>

<form class="layui-form layui-row layui-col-space16">
    <div class="layui-inline">
        <div class="layui-input-wrap" class="layui-inline">
            <div class="layui-input-prefix" class="layui-inline">
                <i class="layui-icon layui-icon-username"></i>
            </div>
            <input type="text" name="id" value="" placeholder="ID" class="layui-input" lay-affix="clear">
        </div>
    </div>
    <div class="layui-inline">
        <div class="layui-input-wrap"  class="layui-inline">
            <input type="text" name="name" placeholder="name" lay-affix="clear" class="layui-input" >
        </div>
    </div>
    <div class="layui-inline">
        <div class="layui-input-inline">
            <input type="text" name="createTime" class="layui-input" id="createDateId" placeholder="yyyy-MM-dd HH:mm:ss">
        </div>
    </div>
    <div class="layui-inline">
        <div class="layui-input-inline">
            <input type="text" name="endTime" class="layui-input" id="updateDateId" placeholder="yyyy-MM-dd HH:mm:ss">
        </div>
    </div>

    <button class="layui-btn" lay-submit lay-filter="submitSearch">Search</button>
    <button type="reset" class="layui-btn layui-btn-primary">Clear</button>
</form>
<table class="layui-hide" id="tableId"></table>
<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>
<button lay-options="{accept: 'file'}" id="importExcel"></button>
<script type="text/html" id="toolbarDemo">
    <div class="layui-btn-container">
        <button class="layui-btn layui-btn-sm" lay-event="add">添加</button>
        <button class="layui-btn layui-btn-sm" lay-event="deleteAll" >删除选中</button>
        <button class="layui-btn layui-btn-sm" lay-event="exportExcel" >导出</button>
        <button class="layui-btn layui-btn-sm" lay-event="importExcel" >导入</button>
    </div>
</script>
<script type="text/html" id="stausSwitchId">
    <!-- 这里的 checked 的状态值判断仅作为演示 -->
    <input type="checkbox" id="state" name="state" value="{{= d.id }}" title="启用|禁用" lay-skin="switch" lay-filter="statusSwitch" {{= d.state == 1? "checked" : ""}}>
</script>
<script>
    layui.use(['table','form','upload'], function() {
        var table = layui.table;
        var laydate = layui.laydate;
        var upload = layui.upload;
        // 创建渲染实例
        laydate.render({
            elem: '#createDateId',
            type: 'datetime'
        });
        laydate.render({
            elem: '#updateDateId',
            type: 'datetime'
        });
        table.render({
            elem: '#tableId',
            url: '/team?method=selectByPage',
            toolbar: '#toolbarDemo',
            page: true,
            height: '315px',
            cols: [[
                {type: 'checkbox', fixed: 'left'},
                // 未自定义模板的普通列
                {field: 'id', fixed: 'left', width: 80, title: 'ID', sort: true},
                // 模板 - 选择器写法
                {field: 'name' , title: '姓名'},
                // 模板 - 函数写法
                {field: 'number' , title: '小组人数'},
                {fixed: 'right', title:'操作', toolbar: '#barDemo'}]]
        });
        laydate.render({
            elem: '#ID-laydate-range-datetime',
            type: 'datetime',
            range: true
        });

        table.on('toolbar(tableId)',function (obj) {
            var data = obj.data;
            console.log(obj);
            var id = obj.config.id;
            var checkStatus = table.checkStatus(id);
            switch (obj.event) {
                case 'add':
                    layer.open({
                        type:2,
                        area:["50%","90%"],
                        content : "/page/project/add"
                    })
                    break;
                case 'deleteAll':
                    var data = checkStatus.data;
                    console.log(data);
                    var ids = new Array();
                    $(data).each(function () {
                        ids.push(this.id);
                        console.log(this.id);
                    })
                    console.log(ids);
                    $.post(
                        "/project?method=deleteAll",
                        {"ids":ids},
                        function (jsonObj) {
                            console.log(jsonObj);
                            if (jsonObj.code == 0){
                                mylayer.okMsg(jsonObj.msg);
                                table.reload('tableId');
                            }else {
                                mylayer.errorMsg(jsonObj.msg);
                            }
                        },
                        "json"
                    )
                    break;
                case "exportExcel":
                    location.href='/project?method=exportExcel'
                    break;
                case "importExcel":
                    $('#importExcel').click();
                    break;
            }
        });
        upload.render({
            elem: '#importExcel', // 绑定多个元素
            url: '/project?method=importExcel', // 此处配置你自己的上传接口即可
            accept: 'file', // 普通文件
            done: function(res){
                layer.msg('上传成功');
                console.log(res);
            }
        });
        table.on('tool(tableId)', function (obj) { // 双击 toolDouble
            var data = obj.data;
            console.log(obj);
            var id = obj.config.id;
            var checkStatus = table.checkStatus(id);
            var othis = lay(this);
            switch(obj.event){
                case 'del':
                    layer.confirm('确认删除?', function (index) {
                        obj.del();
                        $.post(
                            "/employee?method=deleteById",
                            {"id":obj.data.id},
                            function (jsonObj) {
                                console.log(jsonObj);
                                if (jsonObj.code == 0){
                                    mylayer.okMsg(jsonObj.msg);
                                    table.reload('tableId');
                                }else {
                                    mylayer.errorMsg(jsonObj.msg);
                                }
                            },
                            "json"
                        )
                        layer.close(index);
                    });
                    break;
                case 'edit':
                    layer.open({
                        title: '编辑',
                        type: 2,
                        area: ['80%', '80%'],
                        content: '/page/employee/edit?id=' + data.id
                    });
                    break;
                case 'searchById':
                    var data = checkStatus.data;
                    layer.alert(layui.util.escape(JSON.stringify(data)));
                    break;
            };
        });

        var form = layui.form;
        form.on('switch(statusSwitch)', function(obj){
            var id = this.value;
            var checked =obj.elem,checked;
            var status = checked? 1 :0;
            console.log(id);
            console.log(status);
            $.post(
                "/employee?method=updateStatus",
                {"id":id,"status":status},
                function (result) {
                    console.log(result);
                    mylayer.okMsg(result);
                },
                "json"
            )
        });
        form.on('submit(submitSearch)', function(data){
            var field = data.field;
            // 获得表单字段
            // 执行搜索重载
            table.reload('tableId', {
                page: {
                    curr: 1 // 重新从第 1 页开始
                },
                where: field // 搜索的字段
            });
            layer.msg;
            return false; // 阻止默认 form 跳转
        });
    });
</script>
</body>

</html>
