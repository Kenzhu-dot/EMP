<%--
  Created by IntelliJ IDEA.
  User: 12940
  Date: 2024/8/23
  Time: 下午5:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="/static/echarts.min.js"></script>
    <script src="/static/jquery-2.1.4.js"></script>
    <script src="/static/mylayer.js"></script>
    <link rel="stylesheet" href="/static/layui/css/layui.css">
</head>
<body>
    <div id="number" style="width: 600px;height:400px;"></div>
    <div id="clicknumber" style="width: 600px;height:400px;"></div>
        <script>
            $.post(
                "/employee?method=selectNumber",
                function(result){
                    var name =new Array();
                    var value = new Array();
                    $(result).each(function () {
                        name.push(this.name);
                        value.push(this.value);
                    })
                    console.log(name);
                    console.log(value);
                    var myChart = echarts.init(document.getElementById('number'));
                    var option = {
                        title: {
                            text: "各个职位的人数"
                        },
                        tooltip: {},
                        legend: {
                            data: ['人数']
                        },
                        xAxis: {
                            data: name
                        },
                        yAxis: {},
                        series: [
                            {
                                name: '人数',
                                type: 'bar',
                                data: value
                            }
                        ]
                    };

                    // 使用刚指定的配置项和数据显示图表。
                    myChart.setOption(option);
                },
                "json"
            )
        </script>

</body>
</html>
