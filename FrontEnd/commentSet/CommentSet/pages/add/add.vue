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
				<view class="uni-list">
					<view class="uni-list-cell">
						<view class="uni-list-cell-left">
							类型
						</view>
						<view class="uni-list-cell-db">
							<picker @change="bindPickerChange" :value="index" :range="array" range-key="name">
								<label class="uni-input">{{array[index].name}}</label>
							</picker>
						</view>
					</view>
				</view>
				
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
				array: [{name:'中国'},{name: '美国'}, {name:'巴西'}, {name:'日本'}],
				index: 0,
				userInfo: {}
			}
		},
		onShow() {
			var _this = this
			var userInfo = uni.getStorageSync("globalUser")
			if(userInfo != null && userInfo != "" && userInfo != undefined){
				this.userInfo = userInfo
			} else {
				uni.showModal({
					content: '请先登录',
					success: function (res) {
						uni.navigateTo({
							url:'../login/login'
						})
					},
					fail: function(res) {
						uni.navigateTo({
							url:'../index/index'
						})
					}
				})
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
				this.index = e.detail.value
			},
			formSubmit: function(e) {
				var formdata = e.detail.value
				if(formdata.content === ""){
					uni.showModal({
						content:"内容不能为空！"
					})
					return
				}
				this.debug(JSON.stringify(formdata))
				var _this = this
				/**
				 * userId
				 * content
				 * typeId
				 */
				
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
			},
			debug(msg){
				console.log(msg)
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
	
	.uni-list-cell{
		margin: 20px;
		display: flex;
		font-size: 17px;
		border-top: 1px solid #bdbdbd;
		border-bottom: 1px solid #bdbdbd;
		view{
			padding: 5px;
			
		}
		
		.uni-list-cell-left{
			color: #8a8a8a;
		}
		.uni-list-cell-db{
			width: 550rpx;
			picker{
				display: block;
			}
		}
		
	}

</style>
