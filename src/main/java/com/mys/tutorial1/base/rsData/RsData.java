package com.mys.tutorial1.base.rsData;


import lombok.AllArgsConstructor;
import lombok.Data;


@AllArgsConstructor
@Data
public class RsData {
    private String resultCode;
    private String msg;

    public static RsData of(String resultCode, String msg) {
        return new RsData(resultCode, msg);
    }

}
