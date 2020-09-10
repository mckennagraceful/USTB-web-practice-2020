'use strict';
exports.main = async (event, context) => {
	//event为客户端上传的参数
	let result = event.num1 + event.num2;
	event.result=result;
	console.log('event : ', event);
	//返回数据给客户端
	return event
};
