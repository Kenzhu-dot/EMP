<%--
  Created by IntelliJ IDEA.
  User: 12940
  Date: 2024/8/21
  Time: 下午4:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<link rel="stylesheet" href="/static/layui/css/layui.css">
<link rel="stylesheet" href="/static/layui/layui.js">
<script src="/static/jquery-2.1.4.js" type="text/javascript" charset="utf-8"></script>
<script src="/static/mylayer.js" type="text/javascript" charset="utf-8"></script>
<script src="/static/layui/layui.js" type="text/javascript" charset="utf-8"></script>
<%--
<div class="layui-layout layui-layout-admin">
--%>
<!-- 左侧索引栏 -->
<!-- 右侧内容区域 -->
<div class="layui-layout layui-layout-admin" style="padding: 20px">
    <div style="padding: 0px;">
        <section class="dashboard">
            <h1 class="layui-bg-gray">主页</h1>

            <!-- 快捷统计信息 -->
            <div class="layui-row layui-col-space15">
                <div class="layui-col-md3">
                    <div class="layui-card">
                        <div class="layui-card-header">所在项目组</div>
                        <div class="layui-card-body">
                            120
                        </div>
                    </div>
                </div>
                <div class="layui-col-md3">
                    <div class="layui-card">
                        <div class="layui-card-header">请假记录</div>
                        <div class="layui-card-body">
                            450
                        </div>
                    </div>
                </div>
                <div class="layui-col-md3">
                    <div class="layui-card">
                        <div class="layui-card-header">迟到记录</div>
                        <div class="layui-card-body">
                            85
                        </div>
                    </div>
                </div>
                <div class="layui-col-md3">
                    <div class="layui-card">
                        <div class="layui-card-header">旷工记录</div>
                        <div class="layui-card-body">
                            1345
                        </div>
                    </div>
                </div>
            </div>

            <!-- 图表部分 -->
            <div class="layui-row layui-col-space15">
                <div class="layui-col-md6">
                    <div class="layui-card">
                        <div class="layui-card-header">当前项目数</div>
                        <div class="layui-card-body">
                        </div>
                    </div>
                </div>
                <div class="layui-col-md6">
                    <div class="layui-card">
                        <div class="layui-card-header">已完成的项目数</div>
                        <div class="layui-card-body">
                        </div>
                    </div>
                </div>
            </div>

            <!-- 最近的操作 -->
            <div class="layui-card">
                <div class="layui-card-header">当前项目</div>
                <div class="layui-card-body">
                    <ul class="layui-timeline">
                        <li class="layui-timeline-item">
                            <i class="layui-icon layui-timeline-axis">&#xe63f;</i>
                            <div class="layui-timeline-content layui-text">
                                <h3 class="layui-timeline-title">New post created: "How to Improve Your Blog"</h3>
                            </div>
                        </li>
                        <li class="layui-timeline-item">
                            <i class="layui-icon layui-timeline-axis">&#xe63f;</i>
                            <div class="layui-timeline-content layui-text">
                                <h3 class="layui-timeline-title">User "John Doe" registered</h3>
                            </div>
                        </li>
                        <li class="layui-timeline-item">
                            <i class="layui-icon layui-timeline-axis">&#xe63f;</i>
                            <div class="layui-timeline-content layui-text">
                                <h3 class="layui-timeline-title">Comment approved: "Great article!"</h3>
                            </div>
                        </li>
                        <li class="layui-timeline-item">
                            <i class="layui-icon layui-timeline-axis">&#xe63f;</i>
                            <div class="layui-timeline-content layui-text">
                                <h3 class="layui-timeline-title">Post "The Future of Web Development" updated</h3>
                            </div>
                        </li>
                    </ul>
                </div>
            </div>

            <!-- 快捷操作 -->
            <div class="layui-row layui-col-space15">
                <div class="layui-col-md4">
                    <button  class="layui-btn layui-btn-fluid" onclick="clickMessage()">消息<span class="layui-badge-dot layui-bg-orange"></span></button>
                </div>
                <div class="layui-col-md4">
                    <button class="layui-btn layui-btn-normal layui-btn-fluid" onclick="clickContract()">合同上传</button>
                </div>
                <div class="layui-col-md4">
                    <a href="users.jsp" class="layui-btn layui-btn-warm layui-btn-fluid">项目汇报</a>
                </div>
            </div>
        </section>
    </div>
</div>
</div>
<script>
    function clickContract() {
        layer.open({
            type:2,
            area:["50%","90%"],
            content : "/page/project/add"
        })
    }
    function clickMessage() {
        layer.open({
            type:2,
            area:["50%","90%"],
            content : "/page/message"
        })
    }
</script>
</body>
</html>
