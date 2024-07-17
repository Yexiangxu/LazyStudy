package com.lazyxu.network.error

enum class StatusCode(val code: Int, val errorMsg: String) {
    Unknown(0, "未知错误"),

    //
//    Continue(100),
//    SwitchingProtocols(101),
//    Processing(102),
//    EarlyHints(103),
//
//    OK(200),
//    Created(201),
//    Accepted(202),
//    NonAuthoritative(203),
//    NoContent(204),
//    ResetContent(205),
//    PartialContent(206),
//    MultiStatus(207),
//    AlreadyReported(208),
//    IMUsed(209),
//
//    MultipleChoices(300),
//    MovePermanently(301),
//    Found(302),
//    SeeOther(303),
//    NotModified(304),
//    UseProxy(305),
//    SwitchProxy(306),
//    TemporaryRedirect(307),
//    PermanentRedirect(308),
//
//    BadRequest(400),
    Unauthorized(401, "当前请求需要用户验证"),

    //    PaymentRequired(402),
    Forbidden(403, "资源不可用"),
    NotFound(404, "无法找到指定位置的资源"),

    //    MethodNotAllowed(405),
//    NotAcceptable(406),
//    ProxyAuthenticationRequired(407),
    RequestTimeout(408, "请求超时"),

    //    Conflict(409),
//    Gone(410),
//    LengthRequired(411),
//    PreconditionFailed(412),
//    PayloadTooLarge(413),
//    URITooLong(414),
//    UnsupportedMediaType(415),
//    RangeNotSatisfiable(416),
//    ExpectationFailed(417),
//    IMATeapot(418),
//    MisdirectedRequest(421),
//    UnProcessableEntity(422),
//    Locked(423),
//    FailedDependency(424),
//    TooEarly(425),
//    UpgradeRequired(426),
//    PreconditionRequired(428),
//    TooManyRequests(429),
//    RequestHeaderFieldsTooLarge(431),
//    UnavailableForLegalReasons(451),
//
    InternalServerError(500, "服务器错误"),
//    NotImplemented(501),
//
    /**
     * 服务器作为网关或者代理时，为了完成请求访问下一个服务器，但该服务器返回了非法的应答
     */
    BadGateway(502, "非法应答"),

    /**
     * 服务器由于维护或者负载过重未能应答
     */
    ServiceUnavailable(503, "服务器未能应答"),
    GatewayTimeout(504,"服务器未能应答"),
//    HTTPVersionNotSupported(505),
//    NotExtended(510),
//    NetworkAuthenticationRequired(511);
    /**
     * 未知错误
     */
    UNKNOWN(1000, "未知错误"),

    /**
     * 解析错误
     */
    PARSE_ERROR(1001, "解析错误"),

    /**
     * 网络错误
     */
    NETWORD_ERROR(1002, "网络已断开，请检查网络"),

    /**
     * 协议出错
     */
    HTTP_ERROR(1003, "404 Not Found"),

    /**
     * 证书出错
     */
    SSL_ERROR(1004, "证书出错"),

    /**
     * 连接超时
     */
    TIMEOUT_ERROR(1006, "连接超时"),

    /**
     * 未登录
     */
    UNLOGIN(-1001, "未登录"),

    /**
     * 未知Host
     */
    UNKNOW_HOST(1007, "未知Host");
}