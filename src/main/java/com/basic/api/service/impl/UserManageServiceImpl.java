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
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserManageServiceImpl implements UserManageService {
    private final UserManageRepo userManageRepo;

    public Map<String, Object> selectUserList(Pageable pageable, UserVO userVO) throws Exception {
        Map<String, Object> rsltMap = new HashMap<>();
        Page<UserVO> userList = userManageRepo.selectUserList(userVO, pageable);
        rsltMap.put("userList", userList);
        return rsltMap;
    }

    public void insertUserProc(UserVO userVO) throws Exception {
        userVO.setRegDt(LocalDateTime.now());
        UserEntity userEntity = new UserEntity(userVO);
        userManageRepo.save(userEntity);
    }
}
