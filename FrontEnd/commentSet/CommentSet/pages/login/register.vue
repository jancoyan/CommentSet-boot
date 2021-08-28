<template>
	<view class="wrap">
		<view class="content">
			<view class="title">注册</view>
			<form>
				<u-input :height="inputHeight" v-model="username" class="u-border-bottom uni-form-item" placeholder="请输入用户名" />
				<u-input :height="inputHeight" v-model="password" class="u-border-bottom uni-form-item" type="password" placeholder="请输入密码" />
				<u-input :height="inputHeight" v-model="repeatPassword" class="u-border-bottom uni-form-item" type="password" placeholder="请再次输入密码" />
				<button type="primary" @click="formSubmit">注册</button>
			</form>
		</view>
	</view>
</template>

<script>
export default {
	data() {
		return {
			inputHeight: 100,
			username:"",
			password:"",
			repeatPassword:""
		}
	},
	methods: {
		formSubmit() {
			let username = this.username
			let password = this.password
			let repeatpassword = this.repeatPassword
			if(username === "" || password === "" || repeatpassword === ""){
				uni.showModal({
					title:'提示',
					content:'请输入完整'
				})
				return
			}
			if(password != repeatpassword){
				uni.showModal({
					title:'提示',
					content:'新旧密码不一致'
				})
				return
			}
			// 发起注册请求
			this.$u.post('/user/register', {username: username, password: password }).then(res => {
				let user = res.user
				if(user != null && user != undefined && user != {}){
					uni.setStorageSync("globalUser", user)
					uni.showToast({
						title:"注册成功",
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
						title:"注册失败",
						duration:1000
					})
				}
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
		button{
			margin-top: 60rpx;
		}
	}
}
</style>
