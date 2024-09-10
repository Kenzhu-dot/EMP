<%--
  Created by IntelliJ IDEA.
  User: 12940
  Date: 2024/9/4
  Time: 上午8:36
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
<script id="imageTemplet">
    <img src="/pic/{{d.image}}"></img>
</script>
<div class="layui-tab">
    <ul class="layui-tab-title">
        <li class="layui-this">我发出的</li>
        <li>我收到的</li>
    </ul>
    <div class="layui-tab-content">
        <div class="layui-tab-item layui-show">
            <form class="layui-form layui-form-pane"  lay-filter="formFilter" action="">
                <div class="layui-form-item">
                    <label class="layui-form-label">接收人</label>
                    <div class="layui-input-block">
                        <input type="hidden" name="id">
                        <span  name="leaderName" class="layui-input"></span>
                    </div>
                </div>
                <div style="width: 132px;">
                    <div class="layui-upload-list">
                        <input name="image" type="hidden" id="imagename">
                        <img class="layui-upload-img" id="imgId" style="width: 100%; height: 92px;">
                        <div id="uploadTextId"></div>
                    </div>
                </div>
                <div class="layui-form-item">
                    <button class="layui-btn" lay-submit lay-filter="isSubmit">确认</button>
                    <button type="reset" class="layui-btn layui-btn-primary">驳回</button>
                </div>
                <div id="demo-laypage-normal-1"></div>
            </form>
        </div>
        <div class="layui-tab-item">
            <form class="layui-form layui-form-pane"  lay-filter="formFilter2" action="">
                <div class="layui-form-item">
                    <label class="layui-form-label">发起人</label>
                    <div class="layui-input-block">
                        <input type="hidden" name="id">
                        <span  name="leaderName" class="layui-input"></span>
                    </div>
                </div>
                <div style="width: 132px;">
                    <div class="layui-upload-list">
                        <input name="image" type="hidden" id="imagename2">
                        <img class="layui-upload-img" id="imgId2" style="width: 100%; height: 92px;">
                        <div id="uploadTextId2"></div>
                    </div>
                </div>
                <div class="layui-form-item">
                    <button class="layui-btn" lay-submit lay-filter="isSubmit">确认</button>
                    <button type="reset" class="layui-btn layui-btn-primary">驳回</button>
                </div>
                <div id="demo-laypage-normal-2"></div>
            </form>
        </div>
    </div>
</div>
<script>
    layui.use(function() {
        var laypage = layui.laypage;
        // 普通用法
        $.post(
            "/message?method=selectById",

        )
        laypage.render({
            elem: 'demo-laypage-normal-1',
            limit : 1,
            count: 6 // 数据总数
        });
        layui.use(function () {
            var laypage = layui.laypage;
            // 普通用法
            laypage.render({
                elem: 'demo-laypage-normal-2',
                limit:1,
                count: 5 // 数据总数
            });
        })
    })
</script>
</body>
</html>
