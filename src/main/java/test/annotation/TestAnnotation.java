package test.annotation;

import java.lang.annotation.*;

/**
 * @author l
 * @create 2020-05-23-18:31
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface TestAnnotation {

}
