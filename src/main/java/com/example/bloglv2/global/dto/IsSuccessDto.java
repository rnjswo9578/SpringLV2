package com.example.bloglv2.global.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IsSuccessDto {
    private String msg;
    private int statusCode;

    public IsSuccessDto(String msg, int statusCode) {
        this.msg = msg;
        this.statusCode = statusCode;
    }
}
