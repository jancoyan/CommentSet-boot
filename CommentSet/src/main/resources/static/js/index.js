layui.use(['table', 'form','laytpl', 'layer'], function(){
    var table = layui.table;
    var form = layui.form;
    var layer = layui.layer;
    var $ = layui.$

    // 伙伴
    table.render({
        elem: '#comment'
        ,url:'comment/all'
        ,parseData: function(res){ //res 即为原始返回的数据
            var data = res.extend.pageInfo;
            $.each(data.records, function (index, item) {
                item.postDate = dateFormat(item.postDate);
            })
            return {
                "code": 0, //解析接口状态
                "msg": "", //解析提示文本
                "count": data.total, //解析数据长度
                "data": data.records //解析数据列表
            };
        }
        ,toolbar: "#commentToolbar"
        ,cols: [[
            {type:'checkbox'}
            ,{field:'commentId', width:100, title: 'id', sort: true}
            ,{field:'userName', width:120, title: '用户名'}
            ,{field:'commentContent', width:560, title: '内容'}
            ,{field:'postDate', width:160, title: '发表时间', sort: true}
            ,{field:'commentCollectCount', title: '收藏', width: 80, sort: true}
            ,{field:'commentLikeCount', title: '点赞', width: 80, sort: true}
            ,{title:'操作', width:230, toolbar: '#commentBar'}
        ]]
        ,skin: 'line' //表格风格
        ,even: true
        ,page: true
    });

    //监听工具条
    table.on('tool(comment)', function(obj){
        var data = obj.data;
        if(obj.event === 'comment-info'){
            layer.open({
                title: '详细信息'
                ,content: data.commentContent
            });
        } else if (obj.event === 'delete-comment'){
            // 删除评论
            layer.confirm("确认删除?", function () {
                $.ajax({
                    url: "/comment/delete",
                    type: "POST",
                    data: {
                        "ids": data.commentId
                    },
                    success: function (result) {
                        table.reload('comment', {
                            url: '/comment/all'
                        });
                        layer.msg("删除成功");
                    }
                })
            })
        }
    });

    //头工具栏事件
    table.on('toolbar(comment)', function(obj){
        var checkStatus = table.checkStatus(obj.config.id);
        switch(obj.event){
            case 'batchDelete':
                // 未选判断
                if(checkStatus.data.length < 1){
                    layer.msg("请先选择评论")
                    return
                }
                layer.confirm("确认删除?", function () {
                    // 批量删除
                    var data = checkStatus.data;
                    console.log(data)
                    var ids = ""
                    $.each(data, function(index, item){
                        ids = ids + item.commentId + '&'
                    })
                    $.ajax({
                        url: "/comment/delete",
                        type: "POST",
                        data: {
                            "ids": ids
                        },
                        success: function (result) {
                            table.reload('comment', {
                                url: '/comment/all'
                            });
                            layer.msg("删除成功");
                        }
                    })
                })
                break;
            //自定义头工具栏右侧图标 - 提示
            case 'LAYTABLE_TIPS':
                layer.alert('这是工具栏右侧自定义的一个图标按钮');
                break;
        }
    });
});

/**
 * 将时间戳转化为标准时间
 * @param date 时间戳
 * @returns {string} 标准时间字符串
 */
function dateFormat(date) {
    var s = new Date(date)
    var y = s.getFullYear()
    var m = (s.getMonth() + 1) < 10 ? '0' + (s.getMonth() + 1) : (s.getMonth() + 1)
    var dd = s.getDate() < 10 ? '0' + s.getDate() : s.getDate()
    var enddate = y + '-' + m + '-' + dd
    return enddate
}