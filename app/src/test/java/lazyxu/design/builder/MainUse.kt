package lazyxu.design.builder

import org.junit.Test

/**
 * User:Lazy_xu
 * Date:2023/12/05
 * Description:
 * FIXME
 */
class MainUse {
    @Test
    fun main() {
        val headToolbar = HeadToolbar.Builder()
            .mContentViewId(1)
            .toolbarTitle(1)
            .toolbarTitleColor(1)
            .builder()
        if (headToolbar.toolbarTitle != -1) {
        }
    }
}