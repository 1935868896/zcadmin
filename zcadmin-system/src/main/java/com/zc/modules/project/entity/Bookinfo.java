package com.zc.modules.project.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.zc.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.Date;

/**
 * 图书表 bookinfo
 *
 * @author ruoyi
 * @date 2021-08-09
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(description="图书",parent=BaseEntity.class)
public class Bookinfo extends BaseEntity{

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value="主键",name="book_id")
    private Integer book_id;

    @ApiModelProperty(value="状态",name="status")
    private String status;

    @ApiModelProperty(value="标签",name="tags")
    private String tags;

    @ApiModelProperty(value="分数",name="score")
    private String score;

    @ApiModelProperty(value="评分人数",name="score_count")
    private String score_count;

    @ApiModelProperty(value="书名",name="title")
    private String title;

    @ApiModelProperty(value="作者",name="author")
    private String author;

    @ApiModelProperty(value="封面图",name="cover")
    private String cover;

    @ApiModelProperty(value="字数",name="count_word")
    private String count_word;

    @ApiModelProperty(value="更新时间",name="update_at")
    private Date update_at;

}
