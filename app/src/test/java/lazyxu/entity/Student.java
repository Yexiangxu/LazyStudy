package lazyxu.entity;

import androidx.annotation.NonNull;

import java.io.Serializable;

/**
 * User:Lazy_xu
 * Date:2023/10/27
 * Description:
 * FIXME
 */
public class Student implements Cloneable {
    public String name;
    public int age;

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

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    private Teacher teacher;

    @NonNull
    @Override
    public Object clone() throws CloneNotSupportedException {
        Object object = super.clone();
        return object;
    }
}
