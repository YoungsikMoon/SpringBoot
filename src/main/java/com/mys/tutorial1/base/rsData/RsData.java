package com.mys.tutorial1.base.rsData;


import lombok.AllArgsConstructor;
import lombok.Data;


@AllArgsConstructor
@Data
public class RsData {
    private String resultCode;
    private String msg;
    private Object data;

    public static RsData of(String resultCode, String msg) {
        return new RsData(resultCode, msg, null);
    }

    public static RsData of(String resultCode, String msg, Object data) {
        return new RsData(resultCode, msg, data);
    }

    public boolean isSuccess() {
        return resultCode.startsWith("S-");
    }
}
