import app from "../../App.vue"

// 定义全局参数,控制数据加载
var _self, page = 1,
	timer = null;

export default {
	data() {
		return {
			total:0,
			loadingText: '',
			list_orders: [],
			page: 0, //当前分页页码
			apiUrl: '', //后端接口地址
			id: '', //传值使用,方便存在本地的locakStorage  
			del_id: '' //方便存在本地的locakStorage  
		}
	},
	components: {

	},
	onLoad(options) {
		_self = this;

		//检查是否登录参考代码,需要用的时候，可以把注释取掉
		//if(this.checkLogin()==false){
		//	return;
		//}

		//this.getLaction();//得到gps

		this.page = 0;

		//检测有没有传入id参数
		if (options.id != null && options.id != "") {
			this.id = options.id;
		}
		//执行初始化,需要用的时候，可以把注释取掉
		//this.Refresh("init");

	},
	onShow() {
		console.log("on show");
		console.log('开始刷新订单...');
		uni.request({
			url: 'http://l7g02ivr.xiaomy.net:48361/moon/orders/data',
			data: {
				
			},
			method: 'GET',
			success: (res) => {
				console.log(res);
				this.list_orders = res.data.data;
				console.log(this.list_orders);
			}
		})
		console.log("开始核算总金额...");
		uni.request({
			url: 'http://l7g02ivr.xiaomy.net:48361/moon/orders/total',
			data: {
				
			},
			method: 'GET',
			success: (res) => {
				console.log(res);
				this.total=res.data.data;
			}
		})
	},
	onPullDownRefresh: function() {
		//下拉刷新的时候请求一次数据
		this.Refresh();
	},
	methods: {
		allSettle:function(){
			uni.request({
				url: 'http://l7g02ivr.xiaomy.net:48361/moon/orders/allSettle',
				data: {
			
				},
				method: 'PUT',
				success: (res) => {
					console.log(res);
					uni.showToast({
						icon: 'none',
						position: 'bottom',
						title: '您已成功支付¥'+this.total+'!'
					});
					setTimeout(function() {
						location.reload();
					}, 1000)
				}
			})
		},
		pay: function(oid,price) {
			console.log(oid);
			uni.request({
				url: 'http://l7g02ivr.xiaomy.net:48361/moon/orders/pay/'+oid,
				data: {

				},
				method: 'PUT',
				success: (res) => {
					console.log(res);
					uni.showToast({
						icon: 'none',
						position: 'bottom',
						title: '您已成功支付¥'+price+'!'
					});
					setTimeout(function() {
						location.reload();
					}, 1000)
				}
			})
		},
		deleteOrder: function(oid) {
			console.log(oid);
			console.log('开始删除订单...');
			uni.request({
				url: 'http://l7g02ivr.xiaomy.net:48361/moon/orders/' + oid,
				data: {

				},
				method: 'DELETE',
				success: (res) => {
					console.log(res);
					uni.showToast({
						icon: 'none',
						position: 'bottom',
						title: '您已成功删除该订单'
					});
					setTimeout(function() {
						location.reload();
					}, 1000)
				}
			})
		},
		/**
		 * all_orders_6_6处理函数
		 * 数据绑定  data-index="{{index}}" 
		 * 数据取值  var index = e.currentTarget.dataset.index; var cata = this.list_cata_list[index];
		 */
		all_orders_6_6_click: function(event) {

		},

		/**
		 * all_orders_8_8处理函数
		 * 数据绑定  data-index="{{index}}" 
		 * 数据取值  var index = e.currentTarget.dataset.index; var cata = this.list_cata_list[index];
		 */
		all_orders_8_8_click: function(event) {

		},

		/**
		 * all_orders_10_10处理函数
		 * 数据绑定  data-index="{{index}}" 
		 * 数据取值  var index = e.currentTarget.dataset.index; var cata = this.list_cata_list[index];
		 */
		all_orders_10_10_click: function(event) {

		},

		/**
		 * all_orders_12_12处理函数
		 * 数据绑定  data-index="{{index}}" 
		 * 数据取值  var index = e.currentTarget.dataset.index; var cata = this.list_cata_list[index];
		 */
		all_orders_12_12_click: function(event) {

		},

		//刷新数据
		Refresh: function(_action) {

			uni.showLoading();
			console.log('开始刷新数据...');
			//提交到服务器
			var that = this
			var url = that.apiUrl + '{server_code_file_path}';
			console.log(url);
			uni.request({
				url: url, //后端接口地址，需要改成自己的接口地址
				data: {
					action: _action, //上传动作，如按钮点击会产生click动作，分页也会产生，在后端根据具体的动作来判断事件
					uid: that.getUid(), //上传用户id,在登录中获得
					//上传页面中的变量
					//定义变量---start

					//定义变量---end
					id: that.id, //上传id值，后端可以根据此值来操作当前id
					del_id: that.del_id, //在列表中删除按钮选中时，上传到有后端，进行删除操作
					page: that.page
				},
				method: 'GET',
				success: function(res) { //后端返回数据

					// 隐藏导航栏加载框  
					uni.hideNavigationBarLoading();
					// 停止下拉动作  
					uni.stopPullDownRefresh();

					// 隐藏加载框  
					uni.hideLoading();

					var tmp = res.data;

					//初始化，对页面上的控件进行赋值操作
					if (_action == "init") {

					}

					{
						deal_listpages
					}

					that.address_from = tmp.address_from;
					that.address_to = tmp.address_to;
					that.orderNo = tmp.orderNo;



					//如果后端有返回消息，则弹出消息提示
					if (tmp.message != null && tmp.message != "") {
						uni.showToast({
							title: tmp.message,
							icon: 'none',
							duration: 2000
						})
					}



					//如果后端有返回页码，则更改当前页码
					if (tmp.page != null && tmp.page != "") {
						page = tmp.page;
					}

				},
				fail: function(res) {
					uni.showToast({
						title: "服务器访问失败",
						icon: 'none',
						duration: 2000
					})
					console.log(res.data);
					console.log('is failed')
				}
			})
		},
	}
}
