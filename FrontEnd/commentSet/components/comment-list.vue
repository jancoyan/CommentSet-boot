<template>
	<view class="comment-list">
		<view class="comment-item" v-for="comment in comments" :key="comment.commentId">
			<uni-card
				:is-shadow="true" 
				extra="技术没有上限" 
				note="true"
			>
			<template v-slot:footer>
				<view class="info">
					<text>Jancoyan</text>
					<text style="margin: 0 5px;">2021-08-23</text>
					<uni-fav  style="margin: 0 5px;" 
								class="favBtn" 
								circle="true" 
								bgColor="#f7ff55" 
								bgColorChecked="#f7ff55"/>
					<uni-fav class="favBtn" 
								circle="true" 
								star="false"
								bgColor="#f0f0f0" 
								bgColorChecked="#ff0000"
								:contentText="{contentDefault: '点赞',contentFav: '已赞'}"/>
				</view>
			</template>
			{{comment.commentContent}}
			</uni-card>
		</view>
	</view>
</template>

<script>
	export default {
		props: ['comments'],
		name:"comment-list",
		data() {
			return {
				
			};
		}
	}
</script>

<style lang="scss">
	.info{
		display: flex;
		margin: 0 10px;
	}
</style>
