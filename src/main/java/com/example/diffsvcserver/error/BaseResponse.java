package com.example.diffsvcserver.error;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class BaseResponse {
    private String result;
    private String reason;

    public BaseResponse(){
        this.result = MessageUtils.SUCCESS;
        this.reason  = "";
    }
    public BaseResponse(String reason){
        this.result = MessageUtils.FAIL;
        this.reason = reason;
    }
}
