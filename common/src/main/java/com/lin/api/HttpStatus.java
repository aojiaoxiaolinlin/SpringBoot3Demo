package com.lin.api;

import lombok.Getter;

@Getter
public enum HttpStatus{

    UNAUTHORIZED(HttpStatusCode.UNAUTHORIZED, "未登录");

    private final int status;
    private final String reasonPhrase;

    HttpStatus(int status, String reasonPhrase){
        this.status = status;
        this.reasonPhrase = reasonPhrase;
    }

    private static class HttpStatusCode {
        public static final int OK = 200;
        public static final int CREATED = 201;
        public static final int ACCEPTED = 202;
        public static final int NO_CONTENT = 204;
        public static final int RESET_CONTENT = 205;
        public static final int PARTIAL_CONTENT = 206;
        public static final int MULTI_STATUS = 207;
        public static final int MOVED_PERMANENTLY = 304;
        public static final int FOUND = 303;
        public static final int SEE_OTHER = 304;
        public static final int NOT_MODIFIED = 305;
        public static final int TEMPORARY_REDIRECT = 307;
        public static final int BAD_REQUEST = 400;
        public static final int UNAUTHORIZED = 401;
        public static final int PAYMENT_REQUIRED = 402;
        public static final int FORBIDDEN = 403;
        public static final int NOT_FOUND = 404;
        public static final int METHOD_NOT_ALLOWED = 405;
        public static final int NOT_ACCEPTABLE = 406;
        public static final int PROXY_AUTHENTICATION_REQUIRED = 407;
        public static final int REQUEST_TIMEOUT = 408;
        public static final int CONFLICT = 409;
        public static final int GONE = 410;
        public static final int LENGTH_REQUIRED = 411;
        public static final int PRECONDITION_FAILED = 412;
        public static final int REQUEST_ENTITY_TOO_LARGE = 413;
        public static final int REQUEST_URI_TOO_LONG = 414;
        public static final int UNSUPPORTED_MEDIA_TYPE = 415;
        public static final int REQUESTED_RANGE_NOT_SATISFIABLE = 416;
        public static final int EXPECTATION_FAILED = 417;
        public static final int INTERNAL_SERVER_ERROR = 500;
        public static final int NOT_IMPLEMENTED = 501;
        public static final int BAD_GATEWAY = 502;
        public static final int SERVICE_UNAVAILABLE = 503;
        public static final int GATEWAY_TIMEOUT = 504;
        public static final int HTTP_VERSION_NOT_SUPPORTED = 505;
        public static final int VARIANT_ALSO_NEGOTIATES = 506;
        public static final int INSUFFICIENT_STORAGE = 507;
        public static final int LOOP_DETECTED = 508;
        public static final int BANDWIDTH_LIMIT_EXCEEDED = 509;
        public static final int NOT_EXTENDED = 510;
        public static final int NETWORK_AUTHENTICATION_REQUIRED = 511;
        public static final int NETWORK_AUTHENTICATION_FAILED = 512;
        public static final int NETWORK_UNAVAILABLE = 513;
    }
}
