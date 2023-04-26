package com.basic.api.entity;

import com.basic.api.vo.UserVO;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "BASIC_USER")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserEntity extends CommonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userId")
    private Long userId;
    @Column(name = "userPw")
    private String userPw;
    @Column(name = "userNm")
    private String userNm;
    @Column(name = "mblPno")
    private String mblPno;
    @Column(name = "addr")
    private String addr;
    @Column(name = "emailAddr")
    private String emailAddr;

    public UserEntity (UserVO userVO) {
        this.userPw = userVO.getUserPw();
        this.userNm = userVO.getUserNm();
        this.mblPno = userVO.getMblPno();
        this.addr = userVO.getAddr();
        this.emailAddr = userVO.getEmailAddr();
        super.regId = userVO.getRegId();
        super.regDt = userVO.getRegDt();
    }
}
