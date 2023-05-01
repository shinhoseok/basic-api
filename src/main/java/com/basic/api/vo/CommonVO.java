package com.basic.api.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class CommonVO implements Serializable {
    private Long regId;
    private LocalDateTime regDt;
    private Long modId;
    private LocalDateTime modDt;
    private String searchCondition;
    private String searchKeyword;
    private String sortSubject;
    private String sortDescend;
    //현재 페이지
    private int page = 1;
    //페이지 사이즈
    private int pageSize = 10;
}
