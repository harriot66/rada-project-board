package com.rada.projectboard.domain;

import java.time.LocalDateTime;

public class ArticleCommnet {
    private Long id;
    private Article article; //게시글(ID)
    private String content; // 내용

    private LocalDateTime createdAt; //생성일시
    private String createBy; //생성자
    private LocalDateTime modifiedAt; //수정일시
    private String modifiedBy; //수정자
}
