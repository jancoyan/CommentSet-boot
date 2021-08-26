const install = (Vue, vm) => {
	// 此为自定义配置参数，具体参数见上方说明
	Vue.prototype.$u.http.setConfig({
		// baseUrl: 'http://101.201.64.102:9090',
		baseUrl: 'http://localhost:9090',
		loadingText: '努力加载中~',
		loadingTime: 800,
		// 设置自定义头部content-type
		// header: {
		// 	'content-type': 'xxx'
		// }
		// ......
	});
	
	// 响应拦截，判断状态码是否通过
	Vue.prototype.$u.http.interceptor.response = (res) => {
		if(res.code == 0) {
			// res为服务端返回值，可能有code，result等字段
			// 这里对res.result进行返回，将会在this.$u.post(url).then(res => {})的then回调中的res的到
			// 如果配置了originalData为true，请留意这里的返回值
			return res.extend;
		} else {
			// 假设201为token失效，这里跳转登录
			vm.$u.toast('网络连接失败');
			// setTimeout(() => {
				// 此为uView的方法，详见路由相关文档
				// vm.$u.route('/pages/user/login')
			// }, 1500)
			return false;
		}
	}
}

export default {
	install
}