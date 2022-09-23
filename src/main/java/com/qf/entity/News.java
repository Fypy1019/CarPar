package com.qf.entity;

import lombok.*;

/**
 * Created by 15483 on 2021/4/18.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class News {
    private Integer newsId;
    private String newsTitle;
    private String newsContent;
}
