/**
 * Created by zhouli on 2015/10/26.
 */

var Global_Sever_Url="http://121.40.214.161:8080/";
var SUCCESS_CODE = "0000";
var Global_Version="1.0";

////确认提交框
//confirmToSubmit: function () {
//
//},


    function confirmToSubmit(){
        if(confirm('确定要执行此操作吗?'))
        {
            return true;
        }
        return false;
    };
	function goUrl(url){
		window.location = url;
	};

	//=====================================================
	//==功能描述：页面调用Ajax的统一方法
	//==参数说明：
	//==toUrl:  转向页面地址
	//==async:  默认异步
	//==param:  参数，格式为 {key:value,key1,value1}
	//==backMethod:  回调函数
	//=====================================================
	function commonAjax(toUrl,async,param,backMethod){
		var ret ;
		var loder;
		jQuery.ajax({
			url :toUrl,
			async:async,
			type:'post',
			dataType:'json',
            // contentType : 'application/json',
			cache: false,
			data:param,
			success:function(res){
				if(backMethod!=null && backMethod!=""){
					backMethod(res);
				} else {
					ret = res;	
				}
			},
			error:function (XMLHttpRequest, textStatus, errorThrown) {
			  document.write(XMLHttpRequest.responseText);
			},
			beforeSend:function(xhr){
				//if(!jQuery('.ajax_loader')){
					//loder=new ajaxLoader(jQuery('body'));
				//}
			},
			complete:function(xhr, ts){
				if(loder){
					loder.remove();
				}
			}
		});
		if(backMethod==null||backMethod==""){
		  return ret;	
		}
	};

	//获取 选择 的复选框的值
	function getchked(selector){
		var ct="";
		var id="#chkdyntable input[type=checkbox]:checked";
		if(selector){
			id=selector;
		}
		jQuery(id).each(function(index, element) {
			if(jQuery(this).attr('name') != 'checkall'){
				ct+=jQuery(this).val()+",";
			}
		});
		if(ct!=""){
			ct=ct.substr(0, ct.length-1);
		}
		return ct;
	};


	function getchkedByCheckBox(selector){
		var litem =$(selector);
		var ids = "";
		if(litem){
			for(var x=0;x<litem.length;x++){
				if(litem[x].checked)
					ids = ids + litem[x].value + ",";
			}
		}
		if(ids!=""){
			ids=ids.substr(0, ids.length-1);
		}
		return ids;
	};

	function resultDataEvent(data,backMethod){
		if(data){
			if(data["success"]){
				backMethod();
				alert(data["description"]); 
			}else{
				alert(data["description"]);
			}
		}
	}
	$.ajaxSetup({
	    contentType: "application/x-www-form-urlencoded; charset=utf-8"
	});
	var DataDeal = {
	//将从form中通过$('#form').serialize()获取的值转成json
	           formToJson: function (data) {
	               data=data.replace(/&/g,"\",\"");
	               data=data.replace(/=/g,"\":\"");
	               data="{\""+data+"\"}";
	               return JSON.parse(data);
	            },
	};

var setLeftMenu=function(obj){
    var option={
        data:"home"
    }
    $.extend(option,obj);
    var data=option.data;
    var liArr=data.split("_");
    $("#side-menu .active").removeClass("active");
    $("#side-menu li").each(function(){
        var liData=$(this).attr("data");
        if(liData && $.inArray(liData,liArr)>=0){
            $(this).addClass("active");
        }
    })
}

//翻页
function PagerFunc(){
    this.totalPage=0//总页数
        ,this.totalNum=0//总条数
        ,this.curno=1//当前第几页
        ,this.pageNum=10//每一页多少条
        ,this.fn=function(curno){} //翻页事件
        ,this.fnTips=function(msg){alert(msg)} //输入错误页码提示
        ,this.pagingSize=6;
    this.create=function(curno,pageNum,totalNum,element){
        var _this=this;
        var tmp = '';
        element.hide();
        curno = parseInt(curno);
        pageNum = parseInt(pageNum);
        totalNum = parseInt(totalNum);
        //判断总条数是否大于一页的条数
        if(totalNum>pageNum){
            _this.totalPage=Math.ceil(totalNum/pageNum);
        }else{
            _this.totalPage=1;
        }
        _this.totalNum=totalNum;
        _this.curno=curno;
        _this.pageNum=pageNum;
        var maxPage=_this.pagingSize; //最多有几个分页按钮至少大于5
        if(_this.totalPage <= maxPage && _this.totalPage > 0){
            for(var i = 1 ; i <= _this.totalPage ; i++){
                if(i == curno){
                    tmp+='<button class="btn btn-white active">'+i+'</button>';
                }
                else{
                    tmp+='<button class="btn btn-white">'+i+'</button>';
                }
            }
        }
        else if(_this.totalPage > maxPage){
            if(curno <= 3){
                for(i = 1 ; i <= 5 ; i++){
                    if(i == curno){
                        tmp+='<button class="btn btn-white active">'+i+'</button>';
                    }
                    else{
                        tmp+='<button class="btn btn-white">'+i+'</button>';
                    }
                }
            }
            else if(curno < (_this.totalPage - 3) && curno > 3){
                tmp += '<button class="btn btn-white">'+(curno - 2)+'</button>';
                tmp += '<button class="btn btn-white">'+(curno - 1)+'</button>';
                tmp += '<button class="btn btn-white active">'+(curno)+'</button>';
                tmp += '<button class="btn btn-white">'+(curno + 1)+'</button>';
                tmp += '<button class="btn btn-white">'+(curno + 2)+'</button>';
            }
            else if(curno >= (_this.totalPage - 3) && curno <= _this.totalPage){
                for(i = 4 ; i >= 0 ; i--){
                    if((_this.totalPage - i) == curno){
                        tmp += '<button class="btn btn-white">'+(_this.totalPage - i)+'</button>';
                    }
                    else{
                        tmp += '<button class="btn btn-white">'+(_this.totalPage - i)+'</button>';
                    }
                }
            }
        }
        pagerStr='<button class="btn btn-white" type="button"><i class="fa fa-step-backward"></i></button>'+
        '<button class="btn btn-white" type="button"><i class="fa fa-chevron-left"></i></button>'+tmp+
        '<button class="btn btn-white" type="button"><i class="fa fa-chevron-right"></i> </button>'+
        '<button class="btn btn-white" type="button"><i class="fa fa-step-forward"></i> </button>';
        element.html(pagerStr).show();

        $(".btn.btn-white",element).unbind().click(function(){
            $(this).addClass("active");
            var curnum=$(this).text();
            _this.fn(curnum-0);
        });
        //上一页，下一页
        $(".fa-chevron-left",element).parent().unbind().click(function(){
            var curnum=$(".active",element).text();//获取当前的页码
            if(!curnum){
                return;
            }
            else{
                curnum = parseInt(curnum);
            }
            if(curnum > 1){
                _this.fn(curnum-1);
            }
        });
        $(".fa-chevron-right",element).parent().unbind().click(function(){
            var curnum=$(".active",element).text();//获取当前的页码
            if(!curnum){
                return;
            }
            else{
                curnum = parseInt(curnum);
            }
            if(curnum < _this.totalPage){
                _this.fn(parseInt(curnum)+1);
            }
        });
        //第一页
        $(".fa-step-backward",element).parent().unbind().click(function(){
            _this.fn(1);
        })
        //最后一页
        $(".fa-step-forward",element).parent().unbind().click(function(){
            if(_this.totalPage<=0){
                _this.totalPage=1;
            }
            _this.fn(_this.totalPage);
        })
    };
}


//获取浏览器传值
function getUrlPara(key) {
    var uri = window.location.search;
    var re = new RegExp("" +key+ "=([^&?]*)", "ig");
    return ((uri.match(re))?(uri.match(re)[0].substr(key.length+1)):null);
}

var fancy={};
fancy.namespace=function(ns){
    if(!ns||!ns.length){
        return null;
    }
    var _pr=ns.split('.');
    var _nx=fancy;
    for(var i=0;i!=_pr.length;i++){
        _nx[_pr[i]]=_nx[_pr[i]]||{};
        _nx=_nx[_pr[i]];
    }
};
/*常用验证*/
fancy.namespace('valid');
fancy.valid={
    commonReg : {
        email : /^([a-zA-Z0-9]+[_|\_|\.|-]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/,
        number : /^\d+$/,
        phone : /^0{0,1}(13[^4]|15[^4]|14[57]|17[0-9]|18[0-9])[0-9]{8}$|134[0-8][0-9]{7}$/,
        code :  /^[0-9]{4}$/,
        pass :/^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,20}$/,
        verify : /^[0-9]{6}$/,
        name : /^[\u4E00-\u9FA5]{2,5}$/,
        identify : /^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}([0-9]|X)$/,
        bankCard : /^[0-9]{16,21}$/,
        question : /^[A-Za-z0-9\u4e00-\u9fa5]{1,16}$/,
        cvvCode : /^[0-9]{3,4}$/,
        validTime : /^[1-9]\d{3}((0\d)|(1[0-2]))$/,
        safe : /[~#$￥ˇ<>（）\s\?\*\&\\\|\/\[\]\{\}\\^%]/,
        payAmount : /^(0(?:[.](?:[1-9]\d?|0[1-9]))|[1-9]\d{0,8}(?:[.]\d{1,2}|$))$/
    },
    check : function(val, reg) {
        return this.commonReg[reg].test(val);
    }
};
$(function(){
    var nav=window.location.pathname;
    if(nav.indexOf("projectManage")>0){
        $(".title2 a[name='projectManage']").parent().parent().parent().addClass("active");
        $(".title2 a[name='projectManage']").parent().addClass("active");
    }
    if(nav.indexOf("repaymentManage")>0){
        $(".title2 a[name='repaymentManage']").parent().parent().parent().addClass("active");
        $(".title2 a[name='repaymentManage']").parent().addClass("active");
    }
    if(nav.indexOf("article")>0){
        $(".title2 a[name='article']").parent().parent().parent().addClass("active");
        $(".title2 a[name='article']").parent().addClass("active");
    }
    //if(nav.indexOf("commentList")>0){
    //    $(".title2 a[name='commentList']").parent().parent().parent().addClass("active");
    //    $(".title2 a[name='commentList']").parent().addClass("active");
    //}
    if(nav.indexOf("transactionFlowList")>0){
        $(".title2 a[name='transactionFlowList']").parent().parent().parent().addClass("active");
        $(".title2 a[name='transactionFlowList']").parent().addClass("active");
    }
    if(nav.indexOf("userList")>0){
        $(".title2 a[name='userList']").parent().parent().parent().addClass("active");
        $(".title2 a[name='userList']").parent().addClass("active");
    }
    if(nav.indexOf("transfer")>0){
        $(".title2 a[name='transfer']").parent().parent().parent().addClass("active");
        $(".title2 a[name='transfer']").parent().addClass("active");
    }
    if(nav.indexOf("transactionFlowList")>0){
        $(".title2 a[name='transactionFlowList']").parent().parent().parent().addClass("active");
        $(".title2 a[name='transactionFlowList']").parent().addClass("active");
    }
    if(nav.indexOf("financeAmountList")>0){
        $(".title2 a[name='financeAmountList']").parent().parent().parent().addClass("active");
        $(".title2 a[name='financeAmountList']").parent().addClass("active");
    }
    if(nav.indexOf("platformAccountList")>0){
        $(".title2 a[name='platformAccountList']").parent().parent().parent().addClass("active");
        $(".title2 a[name='platformAccountList']").parent().addClass("active");
    }
    if(nav.indexOf("rechargeOrderList")>0){
        $(".title2 a[name='rechargeOrderList']").parent().parent().parent().addClass("active");
        $(".title2 a[name='rechargeOrderList']").parent().addClass("active");
    }
    if(nav.indexOf("orderList")>0){
        $(".title2 a[name='orderList']").parent().parent().parent().addClass("active");
        $(".title2 a[name='orderList']").parent().addClass("active");
    }
})