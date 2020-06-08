package com.example.demo.proxy;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 动态代理：实现代理类
 */
public class ProxyInvocationHandler implements InvocationHandler {

    //被代理的接口
    private Object target;

    //生成代理类
    public Object getProxy(Object obj){
        this.target = obj;
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable   	{
        //动态代理的本质：使用反射机制实现！
        return method.invoke(target, args);
    }

}
