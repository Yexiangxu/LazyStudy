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

    class Video {
        companion object {
            private const val videoGroup = "/video"
            const val PLAY = "$videoGroup/play"
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

            const val CUSTOMVIEW1 = "$functionGroup/customview1"



            const val ANIMATION_MAIN = "$functionGroup/animation_main"
            const val ANIMATION_FRAME = "$functionGroup/animation_frame"
            const val ANIMATION_TWEEN = "$functionGroup/animation_tween"
            const val ANIMATION_PROPERTY = "$functionGroup/animation_property"
            const val ANIMATION_CUSTOM = "$functionGroup/animation_custom"
            const val ANIMATION_LAYOUT = "$functionGroup/animation_layout"
            const val ANIMATION_TRANSITION = "$functionGroup/animation_transition"
            const val ANIMATION_TRANSITION_DETAIL = "$functionGroup/animation_transition_detail"
            const val ANIMATION_CONSTRAINTLAYOUT_MAIN = "$functionGroup/animation_constraintlayout_main"
            const val ANIMATION_CONSTRAINTLAYOUT_DETAIL = "$functionGroup/animation_constraintlayout_detail"
            const val ANIMATION_TOUCHFEEDBACK = "$functionGroup/animation_touchfeedback"
            const val ANIMATION_SCENES = "$functionGroup/animation_scenes"
            const val ANIMATION_SHAREELEMENTS = "$functionGroup/animation_shareelements"
            const val ANIMATION_REVEAL = "$functionGroup/animation_reveal"
            const val ANIMATION_BEHAVIOR = "$functionGroup/animation_behavior"
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
            const val MAIN = "$mineGroup/main"
            const val SETTING = "$mineGroup/setting"
        }
    }
}
