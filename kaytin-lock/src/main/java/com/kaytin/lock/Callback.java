package com.kaytin.lock;

/**
 * <p>
 * description
 * </p>
 *
 * @author KayTin 2018/06/07 13:48
 */
public interface Callback {
    Object onGetLock() throws InterruptedException;

    Object onTimeout() throws InterruptedException;
}
