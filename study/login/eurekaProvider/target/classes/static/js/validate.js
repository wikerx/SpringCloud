// JavaScript Document
//回车事件
document.onkeydown = function (event) {
	var e = event || window.event;
	if (e && e.keyCode == 13) { //回车键的键值为13
		checkForm(); //调用登录按钮的登录事件
	}
};
//登录验证
function checkForm(){
	if($('#account').val()==""){
		layer.msg('账户不能为空！');
		$('#account').focus();
	}else if($('#password').val()==""){
		layer.msg("密码不能为空！");
		$('#password').focus();
	}else if($('#vcode').val()==""){
		layer.msg("验证码不能为空！");
		$('#vcode').focus();
	}else if($('#vcode').val().toLowerCase()!="tlj3".toLowerCase()){
		layer.msg("验证码错误！");
		$('#vcode').focus();
	}else{
		param = {
			userName:$('#account').val(),
			userPwd:$('#password').val()
		}
		//ajax 请求
		$.ajax({
			type: "POST",
			url: "loginForm",
			data: param,
			async:false,
			dataType: "text",
			success: function (data,status) {
				if(data=="OK"){
					location.href = "main";
				}else{
					layer.msg(data);
				}
			},
			error: function (err,err1,err2) {
				layer.msg("系统异常，请稍后再试...");
				console.log("调用方法发生异常:"+JSON.stringify(err)+"err1"+ JSON.stringify(err1)+"err2:"+JSON.stringify(err2));
			}
		});

	}
	
}

//注册验证
function checkFormInsert(){
	if($('#account').val()==""){
		layer.msg('账户不能为空！');
		$('#account').focus();
	}else if($('#password').val()==""){
		layer.msg("密码不能为空！");
		$('#password').focus();
	}else if($('#password').val().length < 6){
		layer.msg("密码安全系数太低，请至少输入6位！");
		$('#password').val("");
		$('#password').focus();
	}else if($('#tel').val()==""){
		layer.msg("电话号码不能为空！");
		$('#tel').focus();
	}else if($('#tel').val().length>11){
		layer.msg("电话号码格式错误，请重新填写！");
		$('#tel').focus();
	}else if($('#email').val()==""){
		layer.msg("邮箱不能为空！");
		$('#email').focus();
	}else if($('#email').val().indexOf("@")==-1 || $('#email').val().indexOf(".com")==-1){
		layer.msg("邮箱格式错误，请重新填写！");
		$('#email').focus();
	}else{
		//ajax 请求
		param = {
			userName:$('#account').val(),
			userPwd:$('#password').val(),
			userTel:$('#tel').val(),
			userEmail:$('#email').val()
		}
		console.log(param);
		//ajax 请求
		$.ajax({
			type: "POST",
			url: "insertForm",
			data: param,
			async:false,
			dataType: "text",
			success: function (data,status) {
				if(data=="OK"){
					location.href = "main";
				}else{
					layer.msg(data);
				}
			},
			error: function (err,err1,err2) {
				layer.msg("系统异常，请稍后再试...");
				console.log("调用方法发生异常:"+JSON.stringify(err)+"err1"+ JSON.stringify(err1)+"err2:"+JSON.stringify(err2));
			}
		});
	}
	
	}