package com.basic.api.service;

import com.basic.api.vo.UserVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserManageService {
    public Page<UserVO> selectUserList(UserVO userVO, Pageable pageable) throws Exception;
    public void insertUserProc(UserVO userVO) throws Exception;
}
