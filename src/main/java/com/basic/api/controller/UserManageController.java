package com.basic.api.controller;

import com.basic.api.component.ResponseComponent;
import com.basic.api.service.UserManageService;
import com.basic.api.vo.UserVO;
import com.basic.api.vo.response.ResponseVO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
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
    public ResponseVO<Map> selectUserList(@RequestBody UserVO userVO) throws Exception {
        PageRequest pageRequest = PageRequest.of(userVO.getPage()-1, userVO.getPageSize());
        Map<String, Object> rsltMap = userManageService.selectUserList(pageRequest, userVO);
        return responseComponent.getResponseVO(rsltMap, ResponseComponent.CommonResponse.OK);
    }

    @PostMapping("/admin/user/insertUserProc")
    public ResponseVO insertUserProc(@RequestBody UserVO userVO) throws Exception {
        userVO.setRegId(1L);
        userManageService.insertUserProc(userVO);
        return responseComponent.getNoDataReponseVO(ResponseComponent.CommonResponse.OK);
    }
}
