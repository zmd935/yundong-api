package com.clkj.modules.sports.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("sports_home")
public class SportsHomeEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId
    private Long id;
    /**
     * 课程或运动
     */
    private String name;
    /**
     * 图像
     */
    private String coverImage;
    /**
     * 简介
     */
    private String detail;
    /**
     * 运动时长
     */
    private String sportsTime;
    /**
     * 运动类型
     */
    private String courseType;
    /**
     * 课程种类id
     */
    private Integer courseId;
    /**
     * 信息在主页面位置
     */
    private Integer status;
    /**
     * 视频
     */
    private String video;

}
