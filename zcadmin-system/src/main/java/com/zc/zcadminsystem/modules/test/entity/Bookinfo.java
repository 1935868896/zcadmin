package com.zc.zcadminsystem.modules.test.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author ZhangC
 * @since 2021-08-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Bookinfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "bookId", type = IdType.AUTO)
    private Integer bookId;

    private String status;

    private String tags;

    private String score;

    @TableField("scoreCount")
    private String scoreCount;

    private String title;

    private String author;

    private String cover;

    @TableField("countWord")
    private String countWord;

    @TableField("updateAt")
    private LocalDateTime updateAt;


}
