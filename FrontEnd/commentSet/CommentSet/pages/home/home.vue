<template>
	<view>
		<view class="u-flex user-box u-p-l-30 u-p-r-20 u-p-b-30">
			<view class="u-m-r-10">
				<u-avatar :src="pic" size="140"></u-avatar>
			</view>
			<view class="u-flex-1">
				<view class="u-font-18 u-p-b-20">Jancoyan</view>
				<view class="u-font-14 u-tips-color">ID:10000</view>
			</view>
			<view class="u-m-l-10 u-p-10">
				<u-icon name="arrow-right" color="#969799" size="28"></u-icon>
			</view>
		</view>
		
		<view class="u-m-t-20">
			<u-cell-group>
				<u-cell-item icon="edit-pen" title="发布句子"></u-cell-item>
			</u-cell-group>
		</view>
		
		<view class="u-m-t-20">
			<u-cell-group>
				<u-cell-item icon="list-dot" title="我发布的"></u-cell-item>
				<u-cell-item icon="star" title="收藏"></u-cell-item>
				<u-cell-item icon="heart" title="喜欢"></u-cell-item>
			</u-cell-group>
		</view>
		
		<view class="u-m-t-20">
			<u-cell-group>
				<u-cell-item icon="setting" title="设置"></u-cell-item>
			</u-cell-group>
		</view>
		<u-tabbar :list="tabbar" :mid-button="true" active-color=#5098FF></u-tabbar>	
	</view>
</template>

<script>
	export default {
		data() {
			return {
				pic:'../../static/logo.png',
				show:true
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
			
		}
	}
</script>

<style lang="scss">
page{
	background-color: #ededed;
}

.camera{
	width: 54px;
	height: 44px;
	
	&:active{
		background-color: #ededed;
	}
}
.user-box{
	background-color: #fff;
}
</style>
