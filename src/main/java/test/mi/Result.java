package test.mi;

import com.sun.org.apache.regexp.internal.RE;
import lombok.Getter;

/**
 * @author l
 * @create 2020-12-23-11:19
 */
@Getter
public class Result<T> {
    private int code;
    private String msg;
    private T data;

    public static <S> Result<S> fail(successCode code){
        return  new Result<S>().setCode(code.getCode()).setMsg(code.getMsg()).setData(null);
    }

    public static <S> Result<S> of(S data){
        return  new Result<S>().setCode(successCode.SUCCESS_CODE.getCode()).setMsg(successCode.SUCCESS_CODE.getMsg()).setData(data);
    }

    public Result<T> setCode(int code){
        this.code=code;
        return this;
    }

    public Result<T> setMsg(String msg){
        this.msg=msg;
        return  this;
    }

     public Result<T> setData(T data){
        this.data=data;
        return this;
     }

}
