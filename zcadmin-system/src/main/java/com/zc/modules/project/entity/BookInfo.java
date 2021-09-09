package com.zc.modules.project.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.zc.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;
import lombok.*;
import com.baomidou.mybatisplus.annotation.TableLogic;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
/**
 * 图书表 book_info
 *
 * @author zhangc
 * @date 2021-09-09
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(description="图书",parent=BaseEntity.class)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookInfo extends BaseEntity{

    private static final long serialVersionUID=1L;

    @TableId(value = "book_id",type = IdType.AUTO)
    @ApiModelProperty(value="主键",name="bookId")
    private Integer bookId;

    @ApiModelProperty(value="状态",name="status")
    private String status;

    @ApiModelProperty(value="标签",name="tags")
    private String tags;

    @ApiModelProperty(value="分数",name="score")
    private String score;

    @ApiModelProperty(value="评分人数",name="scoreCount")
    private String scoreCount;

    @ApiModelProperty(value="书名",name="title")
    private String title;

    @ApiModelProperty(value="作者",name="author")
    private String author;

    @ApiModelProperty(value="封面图",name="cover")
    private String cover;

    @ApiModelProperty(value="字数",name="countWord")
    private String countWord;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value="更新时间",name="updateAt")
    private Date updateAt;
    @TableLogic
    @ApiModelProperty(value="逻辑删除",name="isDelete")
    private Boolean isDelete;

}
