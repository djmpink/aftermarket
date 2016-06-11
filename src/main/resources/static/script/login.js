/**
 * Created by zl on 2/10/2015.
 */

/**
 * 登陆
 */
$(document).ready(function () {
    //使用 Ajax 的方式 判断登录
    $("#btn_login").click(function () {
        var url = 'user/login';
        //获取表单值，并以json的数据形式保存到params中
        var params = {
            userName: $("#nickname").val(),
            password: $("#password").val()
        };
        var jsData = JSON.stringify(params);
        //使用$.post方式
        $.post(
            url,        //服务器要接受的url
            params,     //传递的参数
            function (json) { //服务器返回后执行的函数 参数 data保存的就是服务器发送到客户端的数据
                switch (json.ACK) {
                    case 1:
                        $('#login_info').html("登陆成功...！");
                        location.href = "../fund_trade.html";
                        break;
                    case -1:
                        $('#login_info').html(json.INFO);
                        document.getElementById("login_info").style.color="red";

                        break;
                }
            },
            'json'  //数据传递的类型  json
         );
    });
});

