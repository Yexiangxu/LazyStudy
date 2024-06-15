package lazyxu.design.builder


/**
 * User:Lazy_xu
 * Date:2023/12/06
 * Description:
 * FIXME
 */
class HeadToolbar private constructor(
    val layoutId: Int,
    val toolbarTitle: Int,
    val toolbarTitleColor: Int
) {
    class Builder : IHeaderBuilder {
        private var layoutId: Int = -1
        private var toolbarTitle: Int = -1
        private var toolbarTitleColor: Int = -1
        override fun mContentViewId(layoutId: Int): IHeaderBuilder {
            this.layoutId = layoutId
            return this
        }

        override fun toolbarTitle(toolbarTitle: Int): IHeaderBuilder {
            this.toolbarTitle = toolbarTitle
            return this
        }

        override fun toolbarTitleColor(toolbarTitleColor: Int): IHeaderBuilder {
            this.toolbarTitleColor = toolbarTitleColor
            return this
        }

        override fun builder(): HeadToolbar {
            return HeadToolbar(layoutId,toolbarTitle,toolbarTitleColor)
        }
    }
}