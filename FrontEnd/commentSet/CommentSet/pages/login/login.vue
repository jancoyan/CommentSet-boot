<template>
	<view class="wrap">
		<view class="content">
			<view class="title">登录</view>
			<form @submit="formSubmit">
				<input class="u-border-bottom uni-form-item" name="username" placeholder="请输入用户名" />
				<input class="u-border-bottom uni-form-item" type="password" name="password" placeholder="请输入密码" />
				<button type="primary" form-type="submit" class="submitBtn">注册/登录</button>
			</form>
		</view>
	</view>
</template>

<script>
export default {
	data() {
		return {
			
		}
	},
	methods: {
		formSubmit(e) {
			let data = e.detail.value
			let username = data.username
			let password = data.password
			// 发起注册或者登录的请求
			this.$u.post('/user/login', {username: username, password: password }, {'Content-type':'application/x-www-form-urlencoded'}).then(res => {
				let user = res.user
				uni.setStorageSync("globalUser", user)
				setTimeout(() => {
					uni.showToast({
						title:"登录成功",
						duration:1000
					})
					// 跳转到个人中心
					uni.switchTab({
						url:'../home/home'
					})
				}, 1000)
			});
		}
	}
};
</script>

<style lang="scss" scoped>
.wrap {
	font-size: 28rpx;
	.content {
		width: 600rpx;
		margin: 80rpx auto 0;

		.title {
			text-align: left;
			font-size: 60rpx;
			font-weight: 500;
			margin-bottom: 100rpx;
		}
		input {
			text-align: left;
			margin-bottom: 20rpx;
			padding-bottom: 6rpx;
			height: 50px;
			line-height: 50px;
			border-bottom: 1px solid #999999;
		}
		.submitBtn {
			margin-top: 60rpx;
			background-color: #007AFF;
			color: white;
			border: none;
			font-size: 35rpx;
			padding: 12rpx 0;
			
			&::after {
				border: none;
			}
		}
	}
}
</style>
