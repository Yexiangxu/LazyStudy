package com.lazyxu.base.arouter

/**
 * User: xuyexiang
 * Date: 2019/06/11
 * Description:
 *    不同的module需要用不同的group（eg：不同的module用相同的group跳转失败）
 *    必须以 / 开头
 *
 * FIXME
 */
object ARouterPath {
    const val MAIN = "/main/main"

    /**
     * 功能demo
     */
    class Search {
        companion object {
            private const val searchGroup = "/search"
            const val SEARCH = "$searchGroup/main"
        }
    }

    /**
     * 功能demo
     */
    class Function {
        companion object {
            private const val functionGroup = "/function"
            const val MAIN = "$functionGroup/home"
            const val DRAGRECYCLERVIEW = "$functionGroup/function_dragrecyclerview"
            const val DELETEECYCLERVIEW = "$functionGroup/function_deleteecyclerview"
            const val MEDIAPLAYER = "$functionGroup/mediaplayer"//使用系统MediaPlayer播放

            const val DISPATCH = "$functionGroup/dispatch"
            const val CACHE = "$functionGroup/cache"
        }
    }

    class User {
        companion object {
            private const val userGroup = "/user"
            const val LOGIN = "$userGroup/login"
            const val REGISTER = "$userGroup/register"
            const val FORGETPWD = "$userGroup/register"
        }
    }

    /**
     * 个人中心
     */
    class Mine {
        companion object {
            private const val mineGroup = "/mine"
            const val SETTING = "$mineGroup/setting"
        }
    }
}
