package cn.mrcode.study.rpc.s05;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * JDK 代理类生成
 *
 * @author zhuqiang
 * @date 2021/5/11 16:10
 */
public class JDKProxy implements InvocationHandler {
    private Object target;

    JDKProxy(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return ((RealHello) target).invoke();
    }
}
