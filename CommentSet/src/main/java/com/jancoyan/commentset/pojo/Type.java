package com.jancoyan.commentset.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Jancoyan
 * @since 2021-08-24
 */
@TableName("tbl_type")
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Type extends Model<Type> {

    @TableId(value = "type_id", type = IdType.AUTO)
    private Integer typeId;

    private String typeName;

    private String typeDescription;

}
