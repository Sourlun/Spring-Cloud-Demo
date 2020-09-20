package com.sour.springcloud.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 *
 * @author xgl
 * @date 2020/9/13 14:59
 **/
@Data //作用于类上，是以下注解的集合：@ToString @EqualsAndHashCode @Getter
@AllArgsConstructor // 生成全参构造器
@NoArgsConstructor //生成无参构造器；
public class CommonResult<T> {

    private Integer code;

    private String message;

    private T data;

    public CommonResult(Integer code, String message) {
        this(code, message, null);
    }
}
