package cayxanh.GreencareTest.exception;

public enum ErrorCode {
    USER_EXISTED(1002, "User already existed"),
    USER_NONEXISTED(1003, "User not existed"),
    AUTHENTICATION(1004, "Authentication failed"),
    ;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    private int code;
    private String message;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
