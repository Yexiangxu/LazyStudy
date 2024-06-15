package lazyxu.entity;

import java.util.Objects;

/**
 * User:Lazy_xu
 * Date:2023/10/27
 * Description:
 * FIXME
 */
public class Teacher {
    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    @Override
    public int hashCode() {
        // 对比 name 和 age 是否相等
        return Objects.hash(name, age);
    }
}
