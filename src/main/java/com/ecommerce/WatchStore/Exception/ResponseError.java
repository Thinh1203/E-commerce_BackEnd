package com.ecommerce.WatchStore.Exception;

public class ResponseError extends ResponseData{

    public ResponseError(int status, String message) {
        super(status, message);
    }

}
