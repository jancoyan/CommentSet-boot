<template>
	<view>
		<view class="u-flex user-box u-p-l-30 u-p-r-20 u-p-b-30" v-if="userIsLogin" @click="userDetails">
			<view class="u-m-r-30">
				<u-avatar src="../../static/logo.png" level-bg-color="#ebca0d" :show-level="true" size="140"></u-avatar>
			</view>
			<view class="u-flex-1">
				<view class="u-font-18 u-p-b-25">{{userInfo.userName}}</view>
				<view class="u-font-14 u-tips-color">ID: {{userInfo.userId}}</view>
			</view>
		</view>
		<view class="u-flex user-box u-p-l-30 u-p-r-20 u-p-b-30" v-else>
				<view class="u-m-r-30">
					<u-avatar src="../../static/logo.png" size="140"></u-avatar>
				</view>
				<view class="u-flex-1">
					<navigator url="../login/login">
						<view class="u-font-18 u-p-b-20">登录/注册</view>
					</navigator>
				</view>
				<view class="u-m-l-10 u-p-10">
					<u-icon name="arrow-right" color="#969799" size="28"></u-icon>
				</view>
		</view>
		
		<view class="u-m-t-20" v-if="userIsLogin">
			<u-cell-group>
				<u-cell-item icon="edit-pen" @click="postComment" title="发布句子"></u-cell-item>
			</u-cell-group>
		</view>
		
		<view class="u-m-t-20" v-if="userIsLogin">
			<u-cell-group>
				<u-cell-item icon="list-dot" title="我发布的" @click="gotoPersonalPost"></u-cell-item>
				<u-cell-item icon="star" title="收藏夹" @click="gotoFavList"></u-cell-item>
			</u-cell-group>
		</view>
		
		<view class="u-m-t-20" v-if="userIsLogin">
			<u-cell-group>
				<u-cell-item icon="setting" title="设置" @click='gotoSettings'></u-cell-item>
			</u-cell-group>
		</view>
		<u-tabbar :list="tabbar" :mid-button="true" active-color=#5098FF></u-tabbar>	
	</view>
</template>

<script>
	export default {
		data() {
			return {
				userIsLogin: false,
				userInfo: {}
			}
		},
		onShow() {
			var userInfo = this.getGlobalUser("globalUser")
			if(userInfo != null){
				this.userIsLogin = true
				this.userInfo = userInfo
			} else {
				this.userIsLogin = false
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
			userDetails(){
				uni.navigateTo({
					url:'./user-info'
				})
			},
			postComment(){
				uni.switchTab({
					url:'../add/add'
				})
			},
			gotoSettings(){
				uni.navigateTo({
					url:'./settings'
				})
			},
			gotoPersonalPost(){
				uni.navigateTo({
					url:'./userPost'
				})
			},
			gotoFavList(){
				uni.navigateTo({
					url:'./favlist'
				})
			}
		}
	}
</script>

<style lang="scss">
page{
	background-color: #f3f3f3;
}

.camera{
	width: 54px;
	height: 44px;
	
	&:active{
		background-color: #f3f3f3;
	}
}

.user-box{
	background-color: #fff;
}
</style>
