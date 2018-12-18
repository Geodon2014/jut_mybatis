package com.jut.mybatis;

/***
 * 执行mapper具体方法的回调接口
 *
 * @author geodon
 * @param <T> 调用的mapper类型
 * @param <R> 调用的mapper方法的返回类型
 */
public interface CallbackInterface<T,R> {
    public R execute(T mapper);
}
