//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//
package com.lazyxu.base.utils.permission

import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

data class Permission(
    val name: String,
    val granted: Boolean,
    val shouldShowRequestPermissionRationale: Boolean
) {
    constructor(name: String, granted: Boolean) : this(name, granted, false)


    private companion object {
        fun combineName(permissions: List<Permission>): String {
            return runBlocking {
                permissions.asFlow()
                    .map { it.name }
                    .toList()
                    .joinToString(", ")
            }
        }

    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Permission) return false

        if (name != other.name) return false
        if (granted != other.granted) return false
        if (shouldShowRequestPermissionRationale != other.shouldShowRequestPermissionRationale) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + granted.hashCode()
        result = 31 * result + shouldShowRequestPermissionRationale.hashCode()
        return result
    }

    override fun toString(): String {
        return "Permission(name='$name', granted=$granted, shouldShowRequestPermissionRationale=$shouldShowRequestPermissionRationale)"
    }
}
