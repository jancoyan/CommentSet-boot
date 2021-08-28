<template>
	<view>
		<view class="wrap">
			<view class="u-tabs-box">
				<u-tabs-swiper 
					activeColor="#5098ff" 
					ref="tabs"
					:list="typeList" 
					:current="current"
					 name="typeName"
					@change="change" 
					:is-scroll="false" 
					swiperWidth="750"></u-tabs-swiper>
			</view>
			<u-select v-model="showfavlist" :list="favlist"></u-select>
			<u-top-tips ref="uTips"></u-top-tips>
			<swiper class="swiper-box" 
				:current="swiperCurrent" 
				@transition="transition" 
				@animationfinish="animationfinish">
				<swiper-item class="swiper-item" v-for="(comments,index) in commentList">
					<scroll-view 
					scroll-y
					refresher-enabled="true"
					refresher-background="#5098ff"
					:refresher-triggered="triggered"
					@refresherrefresh="onRefresh" 
					@refresherrestore="onRestore" 
					@scrolltolower="reachBottom">
						<u-card v-for="item in comments" :show-head="false">
							<view class="" slot="body">
								{{item.commentContent}}
							</view>
							<view class="info" slot="foot">
								<view class="author"> {{item.userName}} </view>
								<view class="date"> {{item.postDate}} </view>
								<view style="margin-right: 10px;" @click="addToFavList(item.commentId)">
									<u-icon name="star" size="40"></u-icon>
								</view>
								<view>
									<u-icon name="heart-fill" size="40" color="#f00" @click="likeComment(item.commentId)"></u-icon>
									<text style="color:#f00;margin-left: 5px;">{{item.commentLikeCount}}</text>
								</view>
							</view>
						</u-card>
						<u-loadmore :status="loadStatus[index]"  bgColor="#f2f2f2" :load-text="loadText"></u-loadmore>
					</scroll-view>
				</swiper-item>
			</swiper>
		</view>
		
		<u-tabbar :list="tabbar" :mid-button="true" active-color=#5098FF></u-tabbar>
	</view>
</template>

<script>
export default {
	data() {
		return {
			typeList: [],
			// 一共四个tab，每一个tab在一个orderlist数组中
			commentList: [],
			// 每一个swiper当前的页码
			currentPage: [],
			// 最大页码
			maxPage: [],
			// 每一个页面的加载状况
			loadStatus: [],
			// 键值对，存储每一个tab页面对应的typeId
			tabAndType: [],
			// 下拉刷新状况
			// 默认是true
			triggered: true,
			// 默认是 false
			_freshing: false,
			// 滑动用的, swipercurrent 和 current 都是当前所在swiper
			current: 0,
			// 显示收藏夹的选择器
			favlist: [],
			showfavlist: true,
			swiperCurrent: 0,
			dx: 0,
			nowTab: 0,
			loadText:{
				loadmore: '轻轻上拉加载更多 ( ╯▽╰)',
				loading: '努力加载中 (○´･д･)ﾉ',
				nomore: '我是有底线的! （＞人＜；）'
			}
		};
	},
	onLoad() {
		// 底部导航栏
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
		// 获取所有分类和每一个页面的数据
		this.getTypeList();
		// 必须在这初始化
		this._freshing = false
		this.triggered = true
	},
	onReachBottom() {
		this.reachBottom();
	},
	methods: {
		async getTypeList(){
			var that = this
			await this.$u.get('/type/all',{}).then(res => {
				this.typeList = res.pageInfo.records
			})
			// 获取每一个分类的list
			for(let i = 0; i < this.typeList.length; i++){
				// 初始化一些参数
				this.commentList.push([])
				this.currentPage.push(1)
				this.maxPage.push(1)
				this.loadStatus.push('loadmore')
				this.tabAndType.push(this.typeList[i].typeId)
				// 获取标签对应的数据，第1页，类型为i
				this.getCommentList(0, this.typeList[i].typeId, i)
			}
			
			// 把type存到同步缓存中
			uni.setStorageSync("typeList", this.typeList)
			
		},
		/**
		 * @param {Object} page 要拿到第几页的数据
		 * @param {Object} typeId 数据类型
		 * @param {Number} page 为第几个tab刷新列表
		 */
		async getCommentList(page, typeId, tab){
			if(page > this.maxPage[tab]) return
			let url = '/comment/all'
			let data = {
				page: page,
				limit: 7
			}
			
			if(typeId !== 0){
				url = '/comment/type'
				data["typeId"] = typeId
			}
			// 请求数据并赋值
			await this.$u.get(url, data).then(res => {
				let pageInfo = res.pageInfo
				let records = pageInfo.records
				if(this._freshing === true){
					// 正在刷新的时候为了保持刷新流畅，先置空
					this.commentList[tab] = []
				}
				for (let i = 0; i < records.length; i++) {
					records[i].postDate = records[i].postDate.substr(0, 10)
					this.commentList[tab].push(JSON.parse(JSON.stringify(records[i])))
				}
				this.maxPage[tab] = pageInfo.pages
				this.currentPage[tab] = page === 0 ? 1 : page
			});
			this.loadStatus.splice(tab,1,"loadmore")
		},
		onRefresh() {
			if (this._freshing) return
			if(!this.triggered) this.triggered = true
			this._freshing = true
			this.currentPage[this.current] = 1
			this.maxPage[this.current] = 1
			this.getCommentList(0, this.tabAndType[this.current], this.current)
			setTimeout(() => {
				this.triggered = false;
				this._freshing = false;
				this.$refs.uTips.show({
					title: '已刷新 o(〃\'▽\'〃)o',
					type: 'success',
					duration: '1000'
				})
			}, 1000)
		},
		onRestore() {
			this.triggered = false // 需要重置
			this._freshing = false // 需要重置
		},
		addToFavList(commentId){
			console.log(commentId)
		},
		likeComment(commentId){
			console.log(commentId)
		},
		// tab栏切换
		change(index) {
			this.swiperCurrent = index;
		},
		transition({ detail: { dx } }) {
			this.$refs.tabs.setDx(dx);
		},
		animationfinish({ detail: { current } }) {
			this.$refs.tabs.setFinishCurrent(current);
			this.swiperCurrent = current;
			this.current = current;
		},
		reachBottom(){
			if(this.currentPage[this.current] >= this.maxPage[this.current]){
				this.loadStatus.splice(this.current,1,"nomore")
				return
			}
			this.loadStatus.splice(this.current,1,"loading")
			this.getCommentList(this.currentPage[this.current] + 1, this.tabAndType[this.current], this.current)
		},
		debug(msg){
		}
	}
};
</script>

<style>
/* #ifndef H5 */
page {
	height: 100%;
	background-color: #f2f2f2;
}
/* #endif */
</style>

<style lang="scss" scoped>
		
	.info{
		display: flex;
	}

	.author,.date,.like{
		margin-right: 20px;
	}

	.wrap {
		display: flex;
		flex-direction: column;
		height: calc(100vh - var(--window-top) - var(--window-bottom));
		// height: 750rpx;
		width: 100%;
	}
	.swiper-box {
		flex: 1;
	}
	.swiper-item {
		height: 100%;
		display: flex;
	}
	
</style>
