package com.basic.api.component;

import com.basic.api.vo.response.ResponseVO;
import org.springframework.stereotype.Component;

@Component
public class ResponseComponent {

    public enum CommonResponse {
        SUCCESS(0, "성공하였습니다."), FAIL(-1, "실패하였습니다.");
        private final int code;
        private final String msg;
        CommonResponse(int code, String msg) {
            this.code = code;
            this.msg = msg;
        }
        public int getCode() {
            return code;
        }
        public String getMsg() {
            return msg;
        }
    }

    public <T> ResponseVO<T> getResponseVO(T data) {
        ResponseVO<T> result = new ResponseVO<T>();
        result.setData(data);
        result.setResult(true);
        result.setCode(CommonResponse.SUCCESS.getCode());
        result.setMsg(CommonResponse.SUCCESS.getMsg());
        return result;
    }

    public <T> ResponseVO<T> getNoDataReponseVO(CommonResponse response) {
        ResponseVO<T> result = new ResponseVO<>();
        result.setResult(true);
        result.setCode(response.getCode());
        result.setMsg(response.getMsg());
        return result;
    }
}
