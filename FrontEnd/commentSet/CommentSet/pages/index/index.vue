<template>
	<view>
		<view class="wrap">
			<view class="u-tabs-box">
				<u-tabs-swiper 
					activeColor="#5098ff" 
					ref="tabs" 
					:list="list" 
					:current="current" 
					@change="change" 
					:is-scroll="false" 
					swiperWidth="750"></u-tabs-swiper>
			</view>
			<swiper class="swiper-box" 
				:current="swiperCurrent" 
				@transition="transition" 
				@animationfinish="animationfinish">
				<swiper-item class="swiper-item">
					<view>
					<scroll-view scroll-y @scrolltolower="reachBottom">
					<!-- <scroll-view scroll-y> -->
						<u-card v-for="(item,index) in commentList[0]" :key="item.commentId" :show-head="false">
							<view class="" slot="body">
								{{item.commentContent}}
							</view>
							<view class="info" slot="foot">
								<view class="author"> {{item.userName}} </view>
								<view class="date"> {{item.postDate}} </view>
								<!-- <view class="like"> 点赞 </view> -->
								<!-- <view class="collect"> 收藏 </view> -->
							</view>
						</u-card>
						<u-loadmore :status="loadStatus[0]"  bgColor="#f2f2f2" :load-text="loadText"></u-loadmore>
					</scroll-view>
					</view>
				</swiper-item>
				<swiper-item class="swiper-item">
					b站热评，待开发
				</swiper-item>
				<swiper-item class="swiper-item">
					网抑云，待开发
				</swiper-item>
				<swiper-item class="swiper-item">
					某音热评，待开发
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
			// 一共四个tab，每一个在一个orderlist数组中
			commentList: [[], [], [], []],
			list: [
				{
					name: '推荐'
				},
				{
					name: 'b站热评'
				},
				{
					name: '网抑云'
				},
				{
					name: '某音热评'
				}
			],
			// 每一个swiper当前的页码
			currentPage: [0, 0, 0, 0],
			// 最大页码
			maxPage: [1, 1, 1, 1],
			// 滑动用的, swipercurrent 和 current 都是当前所在swiper
			current: 0,
			swiperCurrent: 0,
			dx: 0,
			// tabsHeight: 0,
			loadStatus: ['loadmore','loadmore','loadmore','loadmore'],
			loadText:{
				loadmore: '轻轻上拉加载更多',
				loading: '努力加载中',
				nomore: '实在没有了'
			}
		};
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
		// 获取每一个页面的数据
		this.getOrderList(this.currentPage[0] + 1);
		// this.getOrderList(this.currentPage[1]);
		// this.getOrderList(this.currentPage[2]);
		// this.getOrderList(this.currentPage[3]);
	},
	computed: {
	},
	onPullDownRefresh() {
		this.commentList[this.current] = []
		this.currentPage[this.current] = 1
		this.maxPage[this.current] = 1
		this.getOrderList(0)
		setTimeout(()=>{
			uni.stopPullDownRefresh()
		}, 1000)
	},
	onReachBottom() {
		this.reachBottom();
	},
	methods: {
		// 页面数据
		async getOrderList(page) {
			if(page <= this.currentPage[this.current]){
				return
			}
			// 请求数据并赋值
			await this.$u.get('/comment/all', {page: page,limit:7}).then(res => {
				let pageInfo = res.pageInfo
				let records = pageInfo.records
				for (let i = 0; i < records.length; i++) {
					records[i].postDate = records[i].postDate.substr(0, 10)
					this.commentList[this.current].push(JSON.parse(JSON.stringify(records[i])))
				}
				this.maxPage[this.current] = pageInfo.pages
				this.currentPage[this.current] += 1
			});
			this.loadStatus.splice(this.current,1,"loadmore")
		},
		// tab栏切换
		change(index) {
			this.swiperCurrent = index;
			this.getOrderList(index);
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
			this.getOrderList(this.currentPage[this.current] + 1)
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
	height: calc(100vh - var(--window-top));
	// height: 750rpx;
	width: 100%;
}
.swiper-box {
	flex: 1;
}
.swiper-item>view {
	height: 100%;
	display: flex;
}
</style>
