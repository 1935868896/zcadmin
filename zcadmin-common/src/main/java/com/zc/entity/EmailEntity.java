package com.zc.entity;

import lombok.Data;

/**
 * @author ZhangC
 * @create 2021-08-27-11:43
 */
@Data
public class EmailEntity {
    String to;
    String title;
    String content;
    String filePath;
    String fileName;
}
