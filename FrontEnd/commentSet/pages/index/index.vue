<template>
	<view class="index">
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
昨天考试，我把冰冰的名字写满了试卷。没想到今天卷子发下来才发现没有批改。我很是疑惑，老师却说爱一个人没有答案，也不分对错。[脱单doge]		</uni-card>
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
							checked
							bgColor="#f7ff55" 
							bgColorChecked="#d4d848"/>
				<uni-fav class="favBtn" 
							circle="true" 
							star="false"
							bgColor="#f0f0f0" 
							bgColorChecked="#ff0000"
							:contentText="{contentDefault: '点赞',contentFav: '已赞'}"/>
		    </view>
		</template>
		    这是内容这是内容这是内容这是内容这是内容这是内容这是内容这是内容这是内容这是内容这是内容这是内容这是内容这是内容这是内容这是内容这是内容
		</uni-card>
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
							checked
							:contentText="{contentDefault: '点赞',contentFav: '已赞'}"/>
		    </view>
		</template>
		    这是内容这是内容这是内容这是内容这是内容这是内容这是内容这是内容这是内容这是内容这是内容这是内容这是内容这是内容这是内容这是内容这是内容
		</uni-card>
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
		    这是内容这是内容这是内容这是内容这是内容这是内容这是内容这是内容这是内容这是内容这是内容这是内容这是内容这是内容这是内容这是内容这是内容
		</uni-card>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				title: 'Hello'
			}
		},
		onLoad() {

		},
		methods: {
			
		}
	}
</script>

<style lang="scss">
	.index{
		background-color: #f8f8f8;
		width: 100%;
		padding-top: 10px;
		.info{
			display: flex;
			margin: 0 10px;
		}
	}
</style>
