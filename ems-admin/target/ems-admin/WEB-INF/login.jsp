<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Demo</title>
    <!-- 请勿在项目正式环境中引用该 layui.css 地址 -->
    <link href="/static/layui/css/layui.css" rel="stylesheet">
</head>
<body>
<style>
    .demo-login-container {
        width: 320px;
        margin: 21px auto 0;
    }

    .demo-login-other .layui-icon {
        position: relative;
        display: inline-block;
        margin: 0 2px;
        top: 2px;
        font-size: 26px;
    }
</style>
<form class="layui-form">
    <div class="demo-login-container">
        <div class="layui-form-item">
            <div class="layui-input-wrap">
                <div class="layui-input-prefix">
                    <i class="layui-icon layui-icon-username"></i>
                </div>
                <input type="text" name="id" value="" lay-verify="required" placeholder="用户名"
                       lay-reqtext="请填写用户名" autocomplete="off" class="layui-input" lay-affix="clear" onblur="reshow(this.value)">
            </div>
            <span id="msg"></span>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-wrap">
                <div class="layui-input-prefix">
                    <i class="layui-icon layui-icon-password"></i>
                </div>
                <input type="password" name="password" value="" lay-verify="required" placeholder="密   码"
                       lay-reqtext="请填写密码" autocomplete="off" class="layui-input" lay-affix="eye" >
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-row">
                <div class="layui-col-xs7">
                    <div class="layui-input-wrap">
                        <div class="layui-input-prefix">
                            <i class="layui-icon layui-icon-vercode"></i>
                        </div>
                        <input type="text" name="code" value="" lay-verify="required" placeholder="验证码"
                               lay-reqtext="请填写验证码" autocomplete="off" class="layui-input" lay-affix="clear">
                    </div>
                </div>
                <div class="layui-col-xs5">
                    <div style="margin-left: 10px;">
                        <img src="/verifyCode"><br>
                    </div>
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <input type="checkbox" name="autoLogin" lay-skin="primary" title="记住密码">
            <a href="#forget" style="float: right; margin-top: 7px;">忘记密码？</a>
        </div>
        <div class="layui-form-item">
            <button class="layui-btn layui-btn-fluid" lay-submit lay-filter="demo-login">登录</button>
        </div>
        <div class="layui-form-item demo-login-other">
            <label>社交账号登录</label>
            <span style="padding: 0 21px 0 6px;">
        <a href="javascript:;"><i class="layui-icon layui-icon-login-qq" style="color: #3492ed;"></i></a>
        <a href="javascript:;"><i class="layui-icon layui-icon-login-wechat" style="color: #4daf29;"></i></a>
        <a href="javascript:;"><i class="layui-icon layui-icon-login-weibo" style="color: #cf1900;"></i></a>
      </span>
            或 <a href="#reg">注册帐号</a>
        </div>
    </div>
</form>

<!-- 请勿在项目正式环境中引用该 layui.js 地址 -->
<script src="/static/jquery-2.1.4.js" type="text/javascript" charset="utf-8"></script>
<script src="/static/layui/layui.js" type="text/javascript" charset="utf-8"></script>
<script src="/static/mylayer.js" type="text/javascript" charset="utf-8"></script>
<script>
    function reshow(value){
        var reg =/[^0-9]/;
        var result = value.match(reg);
        console.log(result);
        var msg =null;
        if (result!=null){
            msg = '用户名格式有误';
        }
        $('#msg').html(msg);
    }
    if (top != window)
        top.location.href = window.location.href;
    layui.use(function () {
        var form = layui.form;
        var layer = layui.layer;
        // 提交事件
        form.on('submit(demo-login)', function (data) {
            var field = data.field; // 获取表单字段值
            $.post(
                '/users?method=login',
                field,
                function (result) {
                    console.log(result);
                    if (result.code == 0) {
                        mylayer.okUrl(result.msg, '/');
                    } else {
                        mylayer.errorMsg(result.msg);
                    }
                },
                'json'
            );
            return false; // 阻止默认 form 跳转
        });
    });
</script>

</body>
</html>