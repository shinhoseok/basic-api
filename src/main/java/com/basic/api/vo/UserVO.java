package com.basic.api.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class UserVO extends CommonVO implements Serializable {
    private Long userId;
    private String userPw;
    private String userNm;
    private String mblPno;
    private String addr;
    private String emailAddr;
}
