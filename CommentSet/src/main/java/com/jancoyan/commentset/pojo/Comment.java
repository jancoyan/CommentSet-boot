package com.jancoyan.commentset.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

/**
 * @author Jancoyan
 * @since 2021-08-22
 */
@TableName("tbl_comment")
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment extends Model<Comment> {

    @TableId(value = "comment_id", type = IdType.AUTO)
    private Integer commentId;

    private Integer commentAuthorId;

    private String commentContent;

    private Date postDate;

    private Integer commentLikeCount;

    private Integer commentCollectCount;

    @TableField(exist = false)
    private String userName;

}
