package com.basic.api.repository;

import com.basic.api.vo.UserVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserManageRepoCustom {
    Page<UserVO> selectUserList(UserVO userVO, Pageable pageable);
}
