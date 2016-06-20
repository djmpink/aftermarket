/**
 * Created by zl on 3/2/2015.
 */

//启动加载 & 初始化
$(document).ready(function () {
    //复选框效果
    $('.i-checks').iCheck({
        checkboxClass: 'icheckbox_square-green',
        radioClass: 'iradio_square-green'
    });

    //日历插件
    $("#validity_datepicker").datepicker({
        format: "yyyy-mm-dd"
    });

    initMethod.getCouponsList(1);
});


$("#check_coupons_btn").click(function () {
    $("#verify-coupons-modal").find("input[type=text]").val("");
    $("#verify-coupons-modal").find("textarea").val("");
    $("#verify-coupons-modal").find("input[type=checkbox]").val("");
    $("#verify-coupons-modal").find("select").val("");
    $("#coupons_info").hide();
    $("#check_info").html("");

});


$("#send_coupons_btn").click(function () {

    $("#send-coupons-modal").find("input[type=text]").val("");
    $("#send-coupons-modal").find("textarea").val("");
    $("#send-coupons-modal").find("input[type=checkbox]").val("");
    $("#send-coupons-modal").find("select").val("");

    var url = Global_Sever_Url + "coupons/getNewActivityCode";//请求地址
    var jsonData = commonAjax(url, false, null, null);
    if (jsonData.code == SUCCESS_CODE) {
        $('#verification').val(jsonData.data);
    } else {
        alert("获取失败");
    }
});

$("#confirm_btn").click(function () {
    if ($("#verification").val() == null || $("#phone").val() == "") {
        alert("信息填写不完整！");
        return;
    }
    if (confirmToSubmit()) {
        var url = Global_Sever_Url + "coupons/addCouponsBind";//请求地址
        //获取表单值，并以json的数据形式保存到params中

        var params = {
            activityCode: $("#verification").val(),
            type: $("#type").val(),
            channel: $("#channel").val(),
            userName: $("#userName").val(),
            phone: $("#phone").val(),
            startTime: $("#startTime").val(),
            endTime: $("#endTime").val(),
            status: 1,
            purchase: $("#purchase").val()

        };
        var jsonData = commonAjax(url, false, params, null);
        if (jsonData.code == SUCCESS_CODE) {
            alert("发放成功");
            $("#send-coupons-modal").modal("hide");
            initMethod.getCouponsList(1);
        } else {
            alert("获取失败");
        }
    }

});

$("#use_btn").click(function () {

    if (confirmToSubmit()) {
        var url = Global_Sever_Url + "coupons/editCoupons";//请求地址
        //获取表单值，并以json的数据形式保存到params中
        var id = $("#check_info").attr("identify");
        var params = {
            id: id,
            status: 4
        };
        var jsonData = commonAjax(url, false, params, null);
        if (jsonData.code == SUCCESS_CODE) {
            alert("使用成功");
            $("#send-coupons-modal").modal("hide");
            initMethod.getCouponsList(1);
        } else {
            alert("使用失败");
        }
    }

});

$("#close_btn").click(function () {
    if (confirmToSubmit()) {
        var url = Global_Sever_Url + "coupons/editCoupons";//请求地址
        //获取表单值，并以json的数据形式保存到params中
        var id = $("#check_info").attr("identify");
        var params = {
            id: id,
            status: 5
        };
        var jsonData = commonAjax(url, false, params, null);
        if (jsonData.code == SUCCESS_CODE) {
            alert("关闭成功");
            $("#send-coupons-modal").modal("hide");
            initMethod.getCouponsList(1);
        } else {
            alert("关闭失败");
        }
    }

});

$("#check_btn").click(function () {

    var url = Global_Sever_Url + "coupons/checkCoupons";//请求地址
    //获取表单值，并以json的数据形式保存到params中

    var params = {
        activityCode: $("#verification_info").val(),
        phone: $("#phone_info").val()
    };
    var jsonData = commonAjax(url, false, params, null);
    if (jsonData.code == SUCCESS_CODE) {
        var data = jsonData.data;
        var statusStr = "";
        var status = data.status;
        if (status == 1) {
            statusStr = "发放";
        } else if (status == 4) {
            statusStr = "已使用";
        } else if (status == 5) {
            statusStr = "已废除";
        } else if (status == 6) {
            statusStr = "已超期";
        } else if (status == 7) {
            statusStr = "未生效";
        } else {
            statusStr = "未知";
        }

        $("#check_info").attr("identify", data.id);

        var htmlStr = "验证成功，状态：" + statusStr;
        $("#check_info").html(htmlStr);
        $("#coupons_info").show();
        $("#type_info").val(data.type);
        $("#channel_info").val(data.channel);
        $("#userName_info").val(data.userName);
        $("#phone_info").val(data.phone);
        $("#startTime_info").val(data.startTime);
        $("#endTime_info").val(data.endTime);
        $("#purchase_info").val(data.purchase);
        $("#status_info").val(data.status);
    } else {
        alert("验证失败");
        $('#check_info$').html("验证失败");
    }
});

$("#search_btn").click(
    function () {
        initMethod.getCouponsList(1);
    }
);

var initMethod = {
    /*数据与变量*/
    option: {
        currentPage: 1,
        pageSize: 20,
        startTime: null,
        endTime: null
    },

    getCouponsList: function (curPage) {
        var _this = this;

        //请求参数
        var currentPage = curPage,
            pageSize = _this.option.pageSize;
        _this.option.currentPage = curPage;

        var params = {
            keywords: $("#keywords").val(),
            currentPage: _this.option.currentPage,
            pageSize: _this.option.pageSize
        };
        var url = Global_Sever_Url + "coupons/getCouponsList";//请求地址
        var jsonData = commonAjax(url, false, params, null);
        if (jsonData.code == SUCCESS_CODE) {
            var str = "";
            var data = jsonData.data;
            var list = data.datas;
            var totalNum = data.totalRows;
            if (list) {
                for (var i = 0; i < list.length; i++) {
                    var id = list[i].id;
                    var userName = list[i].userName;
                    var activityCode = list[i].activityCode;
                    var phone = list[i].phone;
                    var type = list[i].type;
                    var channel = list[i].channel;
                    var purchase = list[i].purchase;
                    var createTime = list[i].createTime;
                    var startTime = list[i].startTime;
                    var endTime = list[i].endTime;
                    var status = list[i].status;
                    var optBtn = "";
                    var indexNum = pageSize * (currentPage - 1) + i + 1;

                    var statusStr = "";
                    if (status == 1) {
                        statusStr = "发放";
                    }
                    if (status == 4) {
                        statusStr = "已使用";
                    }
                    if (status == 5) {
                        statusStr = "已废除";
                    }
                    if (status == 6) {
                        statusStr = "已超期";
                    }
                    if (status == 7) {
                        statusStr = "未生效";
                    }


                    var typeStr = "";
                    if (type == 0) {
                        typeStr = "通用券";
                    }
                    if (type == 1) {
                        typeStr = "现金券";
                    }
                    if (type == 2) {
                        typeStr = "体验券";
                    }
                    if (type == 3) {
                        typeStr = "礼品券";
                    }
                    if (type == 4) {
                        typeStr = "折扣券";
                    }
                    if (type == 5) {
                        typeStr = "特价券";
                    }
                    if (type == 6) {
                        typeStr = "换购券";
                    }
                    var channelStr = "";
                    if (channel == 0) {
                        channelStr = "通用";
                    }
                    if (channel == 1) {
                        channelStr = "饿了么";
                    }
                    if (channel == 2) {
                        channelStr = "美团";
                    }
                    if (channel == 3) {
                        channelStr = "大众";
                    }
                    if (channel == 4) {
                        channelStr = "百度糯米";
                    }
                    if (channel == 5) {
                        channelStr = "淘点点";
                    }
                    if (channel == 6) {
                        channelStr = "其他";
                    }

                    str +=
                        '<tr identify="' + id + '">' +
                        '<td><input type="checkbox" value="' + id + '"/></td>' +
                        '<td>' + indexNum + '</td>' +
                        '<td>' + id + '</td>' +
                        '<td>' + userName + '</td>' +
                        '<td style="color: #ce8735">' + activityCode + '</td>' +
                        '<td style="color: #ce8735">' + phone + '</td>' +
                        '<td>' + typeStr + '</td>' +
                        '<td>' + channelStr + '</td>' +
                        '<td>' + purchase + '</td>' +
                        '<td>' + createTime + '</td>' +
                        '<td>' + startTime + '</td>' +
                        '<td>' + endTime + '</td>' +
                        '<td style="color: #ce8735">' + statusStr + '</td>' +
                        '<td style=" text-align:right" > ' +
                        optBtn +
                            //'<a name="rootEditBtn" class="btn btn-danger btn-rounded" href="#edit-modal-form" data-toggle="modal">修改</a>'+
                        '</td>' +
                        '</tr>';
                }
            }

            $("#couponsList").html(str);

            var totalPage = Math.ceil(totalNum / pageSize);
            totalPage = totalPage == 0 ? 1 : totalPage;
            var pageStr = '当前第 ' + currentPage + '/' + totalPage + ' 页 , 共 ' + totalNum + ' 条记录';
            $("#pageStr").html(pageStr);

            _this.setListFunc();
            var newPagerFunc = new PagerFunc();
            newPagerFunc.fn = function (currentPage) {
                initMethod.getCouponsList(currentPage);
            };
            newPagerFunc.create(currentPage, pageSize, totalNum, $("#selectPage"));
        } else {
            alert("获取失败");
        }
    },

    //绑定表格中功能按钮
    setListFunc: function () {
        //
        //$("button[name='delBtn']").unbind().click(function(){
        //    var _this = $(this);
        //    if(confirmToSubmit()){
        //        investManageMethod.deleteOneInvest(_this);
        //    }else{
        //        return;
        //    }
        //});


    }
};