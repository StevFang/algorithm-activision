package org.education.ade.resp;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 基础响应
 *
 * @author fbin
 * @since 2023-05-11
 */
@Setter
@Getter
@Accessors(chain = true)
public class BaseResp<T> {
    @Builder.Default
    private String code = "0";
    @Builder.Default
    private String message = "success";
    private T data;

    public static BaseResp<Object> ok() {
        return new BaseResp<>();
    }

    public static <T> BaseResp<T> ok(T data) {
        return new BaseResp<T>().setData(data);
    }

    public static BaseResp<Object> fail() {
        return new BaseResp<>().setCode("-1").setMessage("Error");
    }

    public static BaseResp<Object> fail(String code, String message) {
        return new BaseResp<>().setCode(code).setMessage(message);
    }

    public static <T> BaseResp<T> fail(String code, String message, T data) {
        return new BaseResp<T>().setCode(code).setMessage(message).setData(data);
    }
}
