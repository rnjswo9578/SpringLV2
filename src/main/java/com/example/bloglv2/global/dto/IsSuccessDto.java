package com.example.bloglv2.global.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IsSuccessDto {
    private boolean success;

    public IsSuccessDto(boolean success) {
        this.success = success;
    }
}
