<template>
	<view class="wrap">
		<view class="content">
			<view class="title">登录</view>
			<form @submit="formSubmit">
				<input class="u-border-bottom uni-form-item" name="username" placeholder="请输入用户名" />
				<input class="u-border-bottom uni-form-item" type="password" name="password" placeholder="请输入密码" />
				<button type="primary" form-type="submit" class="submitBtn">登录</button>
				<button type="default" form-type="reset" style="margin-top: 10px;height: 50px;" @click="register">注册</button>
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
			if(username === "" || password === ""){
				uni.showModal({
					title:'提示',
					content:'请输入账号和密码'
				})
				return
			}
			// 发起注册或者登录的请求
			this.$u.post('/user/login', {username: username, password: password }).then(res => {
				let user = res.user
				if(user != null && user != undefined && user != {}){
					uni.setStorageSync("globalUser", user)
					uni.showToast({
						title:"登录成功",
						duration:1000
					})
					setTimeout(() => {
						// 跳转到个人中心
						uni.switchTab({
							url:'../home/home'
						})
					}, 1000)
				} else {
					uni.showToast({
						icon:'error',
						title:"登录失败",
						duration:1000
					})
				}
			});
		},
		register(){
			uni.navigateTo({
				url:'./register'
			})
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
			color: white;
			border: none;
			font-size: 35rpx;
			padding: 12rpx 0;
			
			&::after {
				border: none;
			}
		}
	}
		.buttom {
			.loginType {
				display: flex;
				padding: 300rpx 150rpx 200rpx 150rpx;
				justify-content:space-between;
				
				.item {
					display: flex;
					flex-direction: column;
					align-items: center;
					color: $u-content-color;
					font-size: 28rpx;
				}
			}
			.hint {
				padding: 20rpx 40rpx;
				font-size: 20rpx;
				color: $u-tips-color;
				
				.link {
					color: $u-type-warning;
				}
			}
		}
}
</style>