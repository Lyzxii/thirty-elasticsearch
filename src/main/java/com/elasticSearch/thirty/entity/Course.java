package com.elasticSearch.thirty.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @author ：yanpeidong371
 * @description：
 * @date : 2022年02月09日
 * @since: 1.0.0
 */
@Data
@TableName("course")
public class Course {
    @TableId(value = "cid", type = IdType.INPUT)
    private Long cid;

    @TableField("cname")
    private String cname;

    @TableField("user_id")
    private Long userId;

    @TableField("cstatus")
    private String cstatus;

    @TableField("create_time")
    private Date createTime;
}
