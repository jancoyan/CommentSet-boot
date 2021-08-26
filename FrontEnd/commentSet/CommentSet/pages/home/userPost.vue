<template>
	<view class="u-demo">
		<u-cell-item v-for="(item,index) in commentList" :key="item.commentId" 
			:border-bottom="true" :title="item.commentContent" 
			:arrow="false">
			<u-icon @click="deleteComment(item.commentId)" slot="right-icon" size="34" name="close"></u-icon>
		</u-cell-item>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				commentList: [],
				currentPage: 1,
				maxPage: 1,
				userInfo: {}
			}
		},
		computed: {
		},
		onShow() {
			var _this = this
			var userInfo = uni.getStorageSync("globalUser")
			if(userInfo != null && userInfo != "" && userInfo != undefined){
				this.userInfo = userInfo
			}
			this.getCommentList(0)
		},
		methods: {
			deleteComment(commentId){
				uni.showModal({
					title:'确定删除？',
					content:'删除之后无法复原',
					confirmText:'删除',
					confirmColor:'#f00',
					success:function(){
						// 删除逻辑
					}
				})
			},
			getCommentList(page){
				this.$u.get('/comment/user', {userId:this.userInfo.userId, page:page, limit:10}).then(res => {
					let pageInfo = res.pageInfo
					let records = pageInfo.records
					for (let i = 0; i < records.length; i++) {
						records[i].postDate = records[i].postDate.substr(0, 10)
						this.commentList.push(JSON.parse(JSON.stringify(records[i])))
					}
					this.maxPage = pageInfo.pages
					this.currentPage = page === 0 ? 1 : page
				});
			}
			
		}
	};
</script>

<style lang="scss" scoped>
</style>
