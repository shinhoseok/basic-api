package com.basic.api.service;

import com.basic.api.vo.UserVO;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface UserManageService {
    public Map<String, Object> selectUserList(UserVO userVO, Pageable pageable) throws Exception;
    public void insertUserProc(UserVO userVO) throws Exception;
}
