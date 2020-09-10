<template>
	<view class="container">
		<button type="default" @tap="testLoginApi">测试用户登录接口</button>
		<button type="default" @tap="testRegApi">测试用户注册接口</button>
		<button type="default" @tap="testUpdateApi">测试用户更新接口</button>
		<button type="default" @tap="testDeleteApi">测试用户删除接口</button>
	</view>
</template>

<script>
	export default{
		data(){
			return{
				regUser:{
					username:'wanyueqi',
					password:'20000610wyq',
					birthday:'2000-06-10',
					gender:'女',
					regDate:'2020-09-01',
					status:1
				}
			}
		},
		methods:{
			testLoginApi:function(){
				//发送一条axios请求，调用本地用户登录接口
				//明确一下，调用localhost开头的本地接口，应该是不行的
				uni.request({
					url:'https://l7g02ivr.xiaomy.net/moon/users/auth',
					data:{
						identify:'1103140687@qq.com',
						password:'20000830lzy'
					},
					method:'GET',
					success: (res) => {
					    console.log(res.data);
					}
				})
			},
			testRegApi:function(){
				uni.request({
					url:'https://l7g02ivr.xiaomy.net/moon/users/',
					data:this.regUser,
					header: {
						'content-type':'application/json'
					},
					method:'POST',
					success: (res) => {
					    console.log(res.data);
					}
				})
			},
			testUpdateApi:function(){
				let updateUser={
					uid:10,
					username:'adminadmin',
					password:'20000830',
					birthday:'2000-10-02',
					gender:'女',
					regDate:'2020-09-03',
					status:1
				}
				uni.request({
					url:'https://l7g02ivr.xiaomy.net/moon/users/',
					data:updateUser,
					header: {
						'content-type':'application/json'
					},
					method:'PUT',
					success: (res) => {
					    console.log(res.data);
					}
				})
			},
			testDeleteApi:function(){
				uni.request({
					url:'https://l7g02ivr.xiaomy.net/moon/users/10',
					method:'DELETE',
					success: (res) => {
					    console.log(res.data);
					}
				})
			}
		}
	}
</script>

<style>
</style>
