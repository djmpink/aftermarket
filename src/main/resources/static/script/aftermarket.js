/**
 * Created by zl on 3/2/2015.
 */
//TODO
var check_login=0;
window.onload = checkLogin();
function checkLogin() {

    var url = 'checkLogin.action';
    //使用$.post方式
    $.post(
        url,        //服务器要接受的url
        function (json) { //服务器返回后执行的函数 参数 data保存的就是服务器发送到客户端的数据
            if(!(typeof json == 'object')){
                alert(0);
            }
            switch (json.ACK) {
                case 1 :
                    check_login=1;
                    break;
                case -1 :
                    alert("尚未登录，去登录");
                    location.href = "../login.html";
                    //$('#span1').html("用户" + params.nickname + "已注销！");
                    break;
            }


        },
        'json'  //数据传递的类型  json
    );

}


$(document).ready(function() {
    //if(check_login==0){
    //    return
    //}
    var t = $('#table_fund_trade').DataTable( {
        "ajax": {
            "url": "getFundTradeData.action",
            "type": "POST",
            "data":{
                "title":$("title").eq(0).text()
            }
        },

        "columns": [
            { "data": "code" },
            { "data": "name" },
            { "data": "purchase" },
            { "data": "share" },
            { "data": "purchaseNetWorth" },
            { "data": "purchaseFee" },
            { "data": "purchaseDate" },
            { "data": "saleRate" },
            { "data": "avgPurchaseNetWorth" },
            { "data": "minNetWorth" },
            {
                "targets": -1,
                "data": null,
                "defaultContent": "<button id='btn-sale' data-toggle='modal' class='btn btn-primary' href='#modal-form-sale'>卖出</button>"
    //
            }
        ]
    } );
    $('#table_fund_trade tbody').on( 'click', 'button', function (){
        var d = t.row( $(this).parents('tr') ).data();
        document.getElementById("saleCode").value=d.code;
        document.getElementById("saleName").value=d.name;
    } );
} );

//卖出
$("#btn_confirm_sale").click(function () {
    var url = 'saleFund.action';
    //获取表单值，并以json的数据形式保存到params中

    var params = {

        saleCode: $("#saleCode").val(),
        saleName: $("#saleName").val(),
        saleShare: $("#saleShare").val(),
        saleNetWorth: $("#saleNetWorth").val(),
        saleDate: $("#saleDate").val(),
        realSaleRate: $("#realSaleRate").val()

        //saleRate:$("#saleRate").val()

    };
    var jsData = JSON.stringify(params);
    //使用$.post方式
    $.post(
        url,        //服务器要接受的url
        {"data":jsData},     //传递的参数
        function (json) { //服务器返回后执行的函数 参数 data保存的就是服务器发送到客户端的数据
            if(!(typeof json == 'object')){
                alert(0);
            }
            switch (json.ACK) {
                case 1 :
                    alert("卖出成功");
                    window.location.reload();
                    break;
                case -1:
                    //$('#span1').html("用户" + params.nickname + "不存在！");
                    alert("卖出不成功");
                    break;
            }
        },
        'json'  //数据传递的类型  json
    );
});

//购买
$("#btn_confirm_purchase").click(function () {
    var url = 'purchaseFund.action';
    //获取表单值，并以json的数据形式保存到params中

    var params = {

        fundCode: $("#code").val(),
        fundName: $("#name").val(),
        purchaseCredit: $("#purchaseCredit").val(),
        purchaseShare: $("#purchaseShare").val(),
        purchaseNetWorth: $("#purchaseNetWorth").val(),
        purchaseDate: $("#purchaseDate").val(),
        saleRate:$("#saleRate").val()

    };
    var jsData = JSON.stringify(params);
    //使用$.post方式
    $.post(
        url,        //服务器要接受的url
        {"data":jsData},     //传递的参数
        function (json) { //服务器返回后执行的函数 参数 data保存的就是服务器发送到客户端的数据
            if(!(typeof json == 'object')){
                alert(0);
            }
            switch (json.ACK) {
                case 1 :
                    alert("卖出成功");
                    window.location.reload();
                    break;
                case -1:
                    //$('#span1').html("用户" + params.nickname + "不存在！");
                    alert("购买不成功");
                    break;
            }
        },
        'json'  //数据传递的类型  json
    );
});
