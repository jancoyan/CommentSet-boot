<template>
	<view class="post">
		<view class="main">
			<form @submit="formSubmit">
				<view class="uni-form-item  uni-common-mt">
					<textarea 
						name="content" 
						placeholder="请输入" 
						maxlength="500"
						auto-height
						:value="commentText"
						/>
				</view>
				<view class="uni-btn-v submit-comment">
					<button type="primary" form-type="submit">提交</button>
					<button form-type="reset">重置</button>
				</view>
			</form>
		</view>
		
	</view>
</template>

<script>
	export default {
		data() {
			return {
				commentText: ""
			}
		},
		methods: {
			formSubmit: function(e) {
				var formdata = e.detail.value
				if(formdata.content === ""){
					uni.showModal({
						content:"内容不能为空！"
					})
					return
				}
				this.$myRequest({
					url:'/comment/post',
					data: {
						"content": formdata.content
					},
					success: function(res){
						console.log("chenggong")
						this.value = ""
					},
					fail: function(){
						uni.showToast({
							title:"失败"
						})
					}
				})
				uni.showToast({
					title:"成功"
				})
				this.commentText = ""
			}
			
		}
	}
</script>

<style lang="scss">
	.main{
		margin: 10px 20px;
	}
	
	.submit-comment{
		margin-top: 20rpx;
	}
	
	.submit-comment>button{
		margin-top: 10px;
	}

	textarea{
		min-height: 300rpx;
		border-radius: 10px;
		border: 1px solid #cccccc;
		padding: 10px;
	}
</style>
