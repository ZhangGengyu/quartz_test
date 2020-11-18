package feishu.exception;

/**
 * @author zhanggengyu
 * @date 2020/11/15
 */
public class FeiShuException extends RuntimeException {

    public FeiShuException() {
        super();
    }

    public FeiShuException(String message) {
        super(message);
    }

    public FeiShuException(String message, Throwable cause) {
        super(message, cause);
    }

    public FeiShuException(Throwable cause) {
        super(cause);
    }

    protected FeiShuException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}


