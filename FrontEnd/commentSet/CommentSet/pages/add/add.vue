<template>
	<view>
		<view class="form">
			<form @submit="formSubmit">
				<view class="uni-form-item uni-common-mt commentarea">
					<textarea 
						name="content" 
						placeholder="请输入" 
						maxlength="500"
						auto-height
						v-model="commentText"
						/>
				</view>
				<!-- <view class="uni-form-item uni-common-mt">
					<view class="uni-list">
						<view class="uni-list-cell">
							<view class="uni-list-cell-left">
								类别
							</view>
							<view class="uni-list-cell-db">
								<picker @change="bindPickerChange" :value="typeIndex" :range="typeArray" range-key="name">
									<view class="uni-input">{{typeArray[typeIndex].name}}</view>
								</picker>
							</view>
						</view>
					</view>
				</view> -->
				<view class="uni-btn-v submit-comment">
					<button type="primary" form-type="submit">提交</button>
					<button @click="resetComment">重置</button>
				</view>
			</form>
		</view>

		<u-tabbar :list="tabbar" :mid-button="true" active-color=#5098FF></u-tabbar>	
	</view>
</template>

<script>
	export default {
		data() {
			return {
				commentText: "",
				typeArray: [{name:'无'},{name:'网抑云热评'},{name: 'b站热评'}, {name:'某音热评'}],
				typeIndex: 0
			}
		},
		onLoad() {
			this.tabbar = [{
					iconPath: "/static/icon/home.png",
					selectedIconPath: "/static/icon/home-active.png",
					text: '逛逛',
					pagePath: "/pages/index/index"
				},
				{
					iconPath: "/static/icon/add.png",
					selectedIconPath: "/static/icon/add-active.png",
					text: '发布',
					midButton: true,
					pagePath: "/pages/add/add"
				},
				{
					iconPath: "/static/icon/member.png",
					selectedIconPath: "/static/icon/member-active.png",
					text: '我的',
					pagePath: "/pages/home/home"
				}
			]
		},
		methods: {
			bindPickerChange: function(e) {
				console.log('picker发送选择改变，携带值为：' + e.detail.value)
				this.typeIndex = e.detail.value
			},
			formSubmit: function(e) {
				var formdata = e.detail.value
				if(formdata.content === ""){
					uni.showModal({
						content:"内容不能为空！"
					})
					return
				}
				var _this = this
				this.$u.get('/comment/post', {content: formdata.content}).then(res => {
					uni.showToast({
						title:"成功"
					})
					_this.commentText = ""
				});
			},
			resetComment: function(){
				var _this = this
				uni.showModal({
					content: '确认重置？',
					success: function (res) {
						if (res.confirm) {
							_this.commentText = ""
						}
					}
				})
			}
		}
	}
</script>

<style lang="scss">
	.submit-comment{
		margin-top: 20rpx;
	}
	
	.submit-comment>button{
		margin-top: 10px;
		width: 90%;
	}
	
	textarea{
		width: 700rpx;
		min-height: 300rpx;
		border-radius: 10px;
		border: 1px solid #cccccc;
		padding: 10px;
	}

</style>
