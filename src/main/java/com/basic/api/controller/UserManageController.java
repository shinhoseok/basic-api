package com.basic.api.controller;

import com.basic.api.component.ResponseComponent;
import com.basic.api.service.UserManageService;
import com.basic.api.vo.UserVO;
import com.basic.api.vo.response.ResponseVO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class UserManageController {
    private final UserManageService userManageService;
    private final ResponseComponent responseComponent;

    @PostMapping("/admin/user/selectUserList")
    public ResponseVO<Map> selectUserList(Pageable pageable, @RequestBody UserVO userVO) throws Exception {
        Map<String, Object> rsltMap = userManageService.selectUserList(userVO, pageable);
        return responseComponent.getResponseVO(rsltMap);
    }
}
