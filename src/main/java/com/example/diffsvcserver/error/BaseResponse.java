package com.example.diffsvcserver.error;

import lombok.Getter;

@Getter
public class BaseResponse {
    private String result;
    private String message;

    public BaseResponse(){
        this.result = MessageUtils.SUCCESS;
        this.message  = "";
    }
    public BaseResponse(String reason){
        this.result = MessageUtils.FAIL;
        this.message = reason;
    }
}
