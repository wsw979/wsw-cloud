package io.cloud.redis.exception;

/**
 * @program: wsw-cloud-servce
 * @description:
 * @author: wsw
 * @create: 2020-06-05 15:08
 **/
public class LockException extends RuntimeException {

    private static final long serialVersionUID = 6610083281801529147L;

    public LockException(String message) {
        super(message);
    }


}
