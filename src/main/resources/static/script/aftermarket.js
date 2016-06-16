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

////购买
//$("#btn_confirm_purchase").click(function () {
//    var url = 'purchaseFund.action';
//    //获取表单值，并以json的数据形式保存到params中
//
//    var params = {
//
//        fundCode: $("#code").val(),
//        fundName: $("#name").val(),
//        purchaseCredit: $("#purchaseCredit").val(),
//        purchaseShare: $("#purchaseShare").val(),
//        purchaseNetWorth: $("#purchaseNetWorth").val(),
//        purchaseDate: $("#purchaseDate").val(),
//        saleRate:$("#saleRate").val()
//
//    };
//    var jsData = JSON.stringify(params);
//    //使用$.post方式
//    $.post(
//        url,        //服务器要接受的url
//        {"data":jsData},     //传递的参数
//        function (json) { //服务器返回后执行的函数 参数 data保存的就是服务器发送到客户端的数据
//            if(!(typeof json == 'object')){
//                alert(0);
//            }
//            switch (json.ACK) {
//                case 1 :
//                    alert("卖出成功");
//                    window.location.reload();
//                    break;
//                case -1:
//                    //$('#span1').html("用户" + params.nickname + "不存在！");
//                    alert("购买不成功");
//                    break;
//            }
//        },
//        'json'  //数据传递的类型  json
//    );
//});
