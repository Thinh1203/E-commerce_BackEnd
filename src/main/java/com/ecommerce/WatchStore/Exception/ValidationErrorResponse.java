package com.ecommerce.WatchStore.Exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ValidationErrorResponse {
    private int status;
    private String error;
    private String path;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<ErrorDetail> errors;

    public ValidationErrorResponse(int status, String error, String path, List<ErrorDetail> errors) {
        this.status = status;
        this.error = error;
        this.path = path;
        this.errors = errors;
    }
}
