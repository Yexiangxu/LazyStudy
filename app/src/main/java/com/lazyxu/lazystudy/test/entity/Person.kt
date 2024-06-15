package com.lazyxu.lazystudy.test.entity

import java.util.Objects

/**
 * User:Lazy_xu
 * Date:2024/01/03
 * Description:
 * FIXME
 */

class Person(val name: String, val age: Int) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Person) return false

        return this.name == other.name && this.age == other.age
    }
    override fun hashCode(): Int {
        return Objects.hash(name, age)
    }

    override fun toString(): String {
        return "name=$name,age=$age"
    }
}
