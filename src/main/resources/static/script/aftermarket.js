/**
 * Created by zl on 3/2/2015.
 */

//卖出
$("#send_coupons_btn").click(function () {

    var url = Global_Sever_Url + "coupons/getNewActivityCode";//请求地址
    var jsonData = commonAjax(url, false, null, null);
    if (jsonData.code == SUCCESS_CODE) {
        $('#verification').val(jsonData.data);
    } else {
        alert("获取失败");
    }
});

//购买
$("#confirm_btn").click(function () {
    var url = Global_Sever_Url + "coupons/addCouponsBind";//请求地址
    //获取表单值，并以json的数据形式保存到params中

    var params = {
        activityCode: $("#verification").val(),
        type: $("#type").val(),
        userName: $("#userName").val(),
        phone: $("#phone").val(),
        startTime: $("#startTime").val(),
        endTime: $("#endTime").val(),
        purchase:$("#purchase").val()

    };
    //var jsData = JSON.stringify(params);
    //使用$.post方式
    var jsonData = commonAjax(url, false, params, null);
    if (jsonData.code == SUCCESS_CODE) {
        alert("SUCCESS_CODE");
    } else {
        alert("获取失败");
    }
});
