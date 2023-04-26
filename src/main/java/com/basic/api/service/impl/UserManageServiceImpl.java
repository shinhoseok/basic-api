package com.basic.api.service.impl;

import com.basic.api.entity.UserEntity;
import com.basic.api.repository.UserManageRepo;
import com.basic.api.service.UserManageService;
import com.basic.api.vo.UserVO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserManageServiceImpl implements UserManageService {
    private final UserManageRepo userManageRepo;

    public Page<UserVO> selectUserList(UserVO userVO, Pageable pageable) throws Exception {
        Page<UserVO> userList = userManageRepo.selectUserList(userVO, pageable);
        return userList;
    }

    public void insertUserProc(UserVO userVO) throws Exception {
        userVO.setRegDt(LocalDateTime.now());
        UserEntity userEntity = new UserEntity(userVO);
        userManageRepo.save(userEntity);
    }
}
