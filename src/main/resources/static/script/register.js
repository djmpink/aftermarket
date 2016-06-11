
$("#btn_register").click(function () {
	var url = 'register.action';
	//获取表单值，并以json的数据形式保存到params中
	var params = {
		email: $("#email").val(),
		nickname: $("#nickname").val(),
		password: $("#password").val(),
		mobile: $("#mobile").val()
	};
	var jsData = JSON.stringify(params);
	//使用$.post方式
	$.post(
		url,        //服务器要接受的url
		{"data":jsData},     //传递的参数
		function (json) { //服务器返回后执行的函数 参数 data保存的就是服务器发送到客户端的数据
			//var member = eval("(" + json + ")");    //包数据解析为json 格式
			switch (json.ACK) {
				case 1 :
					location.href = "login.html";
					break;
				case -1 :
					$('#register_info').html(json.INFO);
					//$('#register_info').style.color="red"
					document.getElementById("register_info").style.color="red";
					break;
			}
		},
		'json'  //数据传递的类型  json
	);
})