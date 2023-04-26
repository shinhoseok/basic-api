package com.basic.api.vo.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseVO<T> {
    @Schema(description = "응답 성공여부 : true/false", accessMode = Schema.AccessMode.READ_ONLY)
    private boolean result;

    @Schema(description = "응답 코드 번호 : >= 0 정상, < 0 비정상", accessMode = Schema.AccessMode.READ_ONLY)
    private int code;

    @Schema(description = "응답 메시지", accessMode = Schema.AccessMode.READ_ONLY)
    private String msg;

    private T data;
}
