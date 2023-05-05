package org.aseguradora.aseguradora.exception;

public class DaoExecption extends Exception{

    public DaoExecption() {
    }

    public DaoExecption(String message) {
        super(message);
    }

    public DaoExecption(String message, Throwable cause) {
        super(message, cause);
    }

    public DaoExecption(Throwable cause) {
        super(cause);
    }

    public DaoExecption(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
