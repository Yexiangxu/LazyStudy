package lazyxu.design.builder

/**
 * User:Lazy_xu
 * Date:2023/12/06
 * Description:
 * FIXME
 */
interface IHeaderBuilder {
    fun mContentViewId(layoutId: Int): IHeaderBuilder
    fun toolbarTitle(toolbarTitle: Int): IHeaderBuilder
    fun toolbarTitleColor(toolbarTitleColor: Int): IHeaderBuilder
    fun builder():HeadToolbar
}