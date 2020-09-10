'use strict';
exports.main = async (event, context) => {
  try {
    const res = await uniCloud.sendSms({
      smsKey: 'a782ab4aacbbe6422b7bfd2344820b33',
      smsSecret: '855ffc28e6cd85b1a7ba07d2e02e36a2',
      phone: event.mobile,
      templateId: 'uniID_code',
      data: {
        name: '去哪儿网',
        code: event.randomCode,
        action: '用户注册',
        expMinute: '3',
      }
    })
    // 调用成功，请注意这时不代表发送成功
    return res
  } catch(err) {
    // 调用失败
    console.log(err.errCode);
    console.log(err.errMsg);
    return {
      code: err.errCode,
      msg: err.errMsg
    }
  }
};