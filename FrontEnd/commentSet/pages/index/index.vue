<template>
	<view class="index">
		<comment-list :comments="comments"></comment-list>
	</view>
</template>

<script>
	import commentList from '../../components/comment-list.vue'
	export default {
		data() {
			return {
				comments: []
			}
		},
		onLoad() {
			this.getComments()
		},
		components:{"comment-list": commentList},
		methods: {
			async getComments(){
				const res = await this.$myRequest({
					url:'/comment/all'
				})
				this.comments = res.data.extend.pageInfo.records
			}
		}
	}
</script>

<style lang="scss">
	.index{
		background-color: #f8f8f8;
		width: 100%;
		padding-top: 10px;
	}
</style>
