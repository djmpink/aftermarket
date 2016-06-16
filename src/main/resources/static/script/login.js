/**
 * Created by zl on 6/14/2016.
 */

/**
 * 登陆
 */

var initMethod = {
    init: function () {
        var _this = this;
        _this.setAllBtnFunc();
    },

    setAllBtnFunc: function () {
        $("#btn_login").unbind().click(function () {
            initMethod.login();
        });
    },

    login: function () {
        var params = {
                userName: $("#userName").val(),
                password: $("#password").val()
            },
            url = Global_Sever_Url + "user/login";//请求地址

        var jsonData = commonAjax(url, false, params, null);
        if (jsonData.code == SUCCESS_CODE) {
            $('#login_info').html("登陆成功...！");
            location.href = "../aftermarket.html";
            alert("登录成功");
        } else {
            alert("登录失败");
            $('#login_info').html(jsonData.msg);
            document.getElementById("login_info").style.color = "red";
        }
    }
};

