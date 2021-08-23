<template>
	<view>
		<view class="wrap">
			<view class="u-tabs-box">
				<u-tabs-swiper activeColor="#0aff78" ref="tabs" :list="list" :current="current" @change="change" :is-scroll="false" swiperWidth="750"></u-tabs-swiper>
			</view>
			<swiper class="swiper-box" :current="swiperCurrent" @transition="transition" @animationfinish="animationfinish">
				<swiper-item class="swiper-item">
					<scroll-view scroll-y @scrolltolower="reachBottom">
						<u-card v-for="(item,index) in commentList[0]" :key="item.commentId" :show-head="false">
							<view class="" slot="body">
								{{item.commentContent}}
							</view>
							<view class="info" slot="foot">
								<view class="author"> {{item.userName}} </view>
								<view class="date"> {{dateFormat(item.postDate)}} </view>
								<!-- <view class="like"> 点赞 </view> -->
								<!-- <view class="collect"> 收藏 </view> -->
							</view>
						</u-card>
						<u-loadmore :status="loadStatus[0]" bgColor="#f2f2f2"></u-loadmore>
					</scroll-view>
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
			current: 0,
			swiperCurrent: 0,
			tabsHeight: 0,
			dx: 0,
			loadStatus: ['loadmore','loadmore','loadmore','loadmore'],
		};
	},
	onLoad() {
		// 获取第一个页面的数据
		this.getOrderList(0);
	},
	computed: {
		// 价格小数
		dateFormat() {
			return val =>{
				return val.substr(0,10) 
			}
		},
		// 价格整数
		priceInt() {
			return val => {
				if (val !== parseInt(val)) return val.split('.')[0];
				else return val;
			};
		}
	},
	methods: {
		reachBottom() {
			// 此tab为空数据
			if(this.current != 2) {
				this.loadStatus.splice(this.current,1,"loading")
				setTimeout(() => {
					this.getOrderList(this.current);
				}, 1200);
			}
		},
		// 页面数据
		async getOrderList(idx) {
			// 请求数据并赋值
			await this.$u.get('/comment/all', {}).then(res => {
				this.commentList[0] = res.pageInfo.records
				// console.log(this.commentList[0])
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
	height: calc(100vh + var(--window-top));
	width: 100%;
}
.swiper-box {
	flex: 1;
}
.swiper-item {
	height: 100%;
}
</style>
