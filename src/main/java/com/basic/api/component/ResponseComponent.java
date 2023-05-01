package com.basic.api.component;

import com.basic.api.vo.response.ResponseVO;
import org.springframework.stereotype.Component;

@Component
public class ResponseComponent {

    public enum CommonResponse {
        OK(200, "OK"),
        BAD_REQUEST(400, "BAD_REQUEST"),
        NOT_FOUND(404, "NOT_FOUND"),
        INTERNAL_SERER_ERROR(500, "INTERNAL_SERVER_ERROR");
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

    public <T> ResponseVO<T> getResponseVO(T data, CommonResponse commonResponse) {
        ResponseVO<T> result = new ResponseVO<T>();
        result.setData(data);
        result.setResult(true);
        result.setCode(commonResponse.getCode());
        result.setMsg(commonResponse.getMsg());
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
