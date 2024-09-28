package org.mtcoding.poly.core.util;

import lombok.AllArgsConstructor;
import lombok.Data;


@AllArgsConstructor
@Data
public class Resp<T> {
    private Integer status;
    private String msg;
    private T body;

    public static <B> Resp<?>ok(B body) {
        return new Resp<>(200,"ok", body);
    }

    public static Resp<?> fail(Integer status, String msg)  {
        return new Resp<>(status, msg, null);
    }

}