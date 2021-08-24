package com.jancoyan.commentset.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author Jancoyan
 * @since 2021-08-22
 */
@TableName("tbl_collection")
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Collection extends Model<Collection> {

    private Integer collectionId;

    private Integer userId;

    private String collectionName;

}
