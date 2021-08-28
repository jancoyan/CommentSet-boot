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
						v-model="commentText"/>
				</view>
				<view class="uni-list">
					<view class="uni-list-cell">
						<view class="uni-list-cell-left">
							类型
						</view>
						<view class="uni-list-cell-db">
							<picker @change="bindPickerChange" :value="index" :range="typeList" range-key="typeName">
								<label class="uni-input">{{typeList[index].typeName}}</label>
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
				index: 0,
				userInfo: {},
				typeList: []
			}
		},
		onShow() {
			// 加载类型 picker
			let typeList = uni.getStorageSync("typeList")
			if(typeList != null && typeList != "" && typeList != undefined){
				this.typeList = typeList
				this.typeList[0].typeName = "默认"
			} else {
				this.getTypeList()
			}
			// console.log(this.typeList)
			var _this = this
			var userInfo = this.getGlobalUser("globalUser")
			if(userInfo != null){
				this.userInfo = userInfo
			} else {
				uni.showModal({
					title:'提示',
					content: '请先登录',
					success: function (res) {
						if(res.confirm){
							uni.navigateTo({
								url:'../login/login'
							})
						} else if (res.cancel){
							uni.switchTab({
								url:'../index/index'
							})
						}
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
				this.index = e.detail.value
			},
			formSubmit: function(e) {
				// 在没有登录的情况下进来
				if(this.getGlobalUser() === null){
					uni.showModal({
						title:'提示',
						content: '请先登录',
						success: function (res) {
							if(res.confirm){
								uni.navigateTo({
									url:'../login/login'
								})
							} else if (res.cancel){
								uni.switchTab({
									url:'../index/index'
								})
							}
						}
					})
					return
				}
				
				var formdata = e.detail.value
				if(formdata.content === ""){
					uni.showModal({
						content:"内容不能为空！"
					})
					return
				}
				this.debug(JSON.stringify(formdata))
				var _this = this
				this.$u.post('/comment/post', {
					userId: this.userInfo.userId,content: formdata.content,typeId: this.typeList[this.index].typeId}).then(res => {
					uni.showToast({
						title:"成功"
					})
					_this.commentText = ""
					_this.index = 0
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
			async getTypeList(){
				await this.$u.get('/type/all',{}).then(res => {
					this.typeList = res.pageInfo.records
				})
				this.typeList[0].typeName = "默认"
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
			padding: 10px;
			
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
