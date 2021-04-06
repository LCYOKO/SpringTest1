package test.pojo;

import lombok.Data;

/**
 * @author l
 * @create 2020-05-23-14:55
 */
@Data
public class Teacher implements Cloneable {
    private String name;
    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public Teacher clone() throws CloneNotSupportedException {
        return (Teacher) super.clone();
    }
}
