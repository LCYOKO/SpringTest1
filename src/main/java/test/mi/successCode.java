package test.mi;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author l
 * @create 2020-12-23-11:16
 */
@AllArgsConstructor
@Getter
public enum successCode implements Describable {

    SUCCESS_CODE(200,"登录成功");

    private int code;
    private String msg;
}
