package com.jancoyan.commentset.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author Jancoyan
 * @since 2021-08-22
 */
@TableName("tbl_collection_comment")
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentCollection extends Model<CommentCollection> {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer commentId;

    private Integer userId;

    private Integer collectionId;

}
