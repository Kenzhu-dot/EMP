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
<form class="layui-form layui-form-pane" action="">
    <div class="layui-form-item">
        <div class="layui-inline">
            <button type="button" class="layui-btn" id="uploadId">
                <i class="layui-icon layui-icon-upload"></i> 单图片上传
            </button>
            <div style="width: 132px;">
                <div class="layui-upload-list">
                    <input name="image" type="hidden" id="imagename">
                    <img class="layui-upload-img" id="imgId" style="width: 100%; height: 92px;">
                    <div id="uploadTextId"></div>
                </div>
                <div class="layui-progress layui-progress-big" lay-showPercent="yes" lay-filter="filter-demo">
                    <div class="layui-progress-bar" lay-percent=""></div>
                </div>
            </div>
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
        var upload = layui.upload;
        var layer = layui.layer;
        var element = layui.element;
        var $ = layui.$;
        var form = layui.form;
        var uploadInst = upload.render({
            elem: '#uploadId',
            url: '/upload', // 实际使用时改成您自己的上传接口即可。
            before: function(obj){
                // 预读本地文件示例，不支持ie8
                obj.preview(function(index, file, result){
                    $('#imgId').attr('src', result); // 图片链接（base64）
                });

                element.progress('filter-demo', '0%'); // 进度条复位
                layer.msg('上传中', {icon: 16, time: 0});
            },
            done: function(res){
                // 若上传失败
                if(res.code > 0){
                    return layer.msg('上传失败');
                }
                else {
                    console.log(res)
                    $("#imagename").val(res.data);
                }
                // 上传成功的一些操作
                // …
                $('#uploadTextId').html(''); // 置空上传失败的状态
            },
            error: function(){
                // 演示失败状态，并实现重传
                var demoText = $('#uploadTextId');
                demoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-xs demo-reload">重试</a>');
                demoText.find('.demo-reload').on('click', function(){
                    uploadInst.upload();
                });
            },
            // 进度条
            progress: function(n, elem, e){
                element.progress('filter-demo', n + '%'); // 可配合 layui 进度条元素使用
                if(n == 100){
                    layer.msg('上传完毕', {icon: 1});
                }
            }
        });
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
                "/project?method=addContract",
                data.field,
                function (jsonObj) {
                    console.log(jsonObj);
                    if (jsonObj.code == 0){
                        mylayer.okMsg("添加成功");
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
