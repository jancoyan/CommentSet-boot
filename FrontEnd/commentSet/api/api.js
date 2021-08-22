const BASE_URL = 'http://localhost:8888'

export const myRequest = (options)=>{
	return new Promise((resolve,reject)=>{
		uni.request({
			url: BASE_URL + options.url,
			method: options.method || 'GET',
			data: options.data || {},
			success: (res) => {
				// if(res.code !== 0){
				// 	return uni.showToast({
				// 		title:'获取数据失败'
				// 	})
				// }
				resolve(res)
			},
			fail: (err) => {
				// return uni.showToast({
				// 	title:'获取数据失败'
				// })
				reject(err)
			}
		})
	})
}
