package com.example.diffsvcserver.error;

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
