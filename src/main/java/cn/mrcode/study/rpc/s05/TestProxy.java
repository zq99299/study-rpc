package cn.mrcode.study.rpc.s05;

import java.lang.reflect.Proxy;

/**
 * 测试例子
 */
public class TestProxy {
    public static void main(String[] args) {
        // 构建代理器
        JDKProxy proxy = new JDKProxy(new RealHello());

        final ClassLoader classLoader = ClassLoader.getSystemClassLoader();
//        ClassLoader classLoader = ClassLoaderUtils.getCurrentClassLoader();
        // 该 ClassLoaderUtils 类没有找到，不知道是否是其他的工具类

        // 把生成的代理类保存到文件
        System.setProperty("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");

        // 生成代理类
        Hello test = (Hello) Proxy.newProxyInstance(classLoader, new Class[]{Hello.class}, proxy);
        // 方法调用
        System.out.println(test.say());
    }
}
