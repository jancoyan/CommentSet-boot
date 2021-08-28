const install = (Vue, vm) => {
	// 此为自定义配置参数，具体参数见上方说明
	Vue.prototype.$u.http.setConfig({
		// baseUrl: 'http://101.201.64.102:9090',
		baseUrl: 'http://localhost:9090',
		loadingText: '努力加载中~',
		loadingTime: 800,
		// 设置自定义头部content-type
		header: {
			'Content-type':'application/x-www-form-urlencoded'
		}
		// ......
	});
	
	// 响应拦截，判断状态码是否通过
	Vue.prototype.$u.http.interceptor.response = (res) => {
		if(res.code == 0) {
			// 请求成功，后端返回 0
			return res.extend;
		} else {
			// 请求失败，后端返回非 0
			let msg = res.extend.msg
			vm.$u.toast(msg === null ? "请求失败" : msg);
			return false;
		}
	}
}

export default {
	install
}