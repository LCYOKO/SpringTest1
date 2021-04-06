package test.copy;

import lombok.Data;

import java.util.HashSet;

/**
 * @author l
 * @create 2020-12-28-10:47
 */
@Data
public class User {
    private Integer id;
    private String name;
    private String password;
    private HashSet<String> set;
    private void readObject(){

    }
}
