package com.example.demo.proxy;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * cglib代理
 */
public class CglibProxy implements MethodInterceptor {

    private Object target;

    public Object getProxy(Object obj){
        this.target = obj;
        // 创建Enhancer对象
        Enhancer enhancer = new Enhancer();
        // 使用Enhancer对象中的方法设置父类（将目标类设置为代理类的父类）
        enhancer.setSuperclass(target.getClass());
        // 这里需要传入一个CallBack对象，因为MethodInterceptor接口继承了CallBack
        // 而我们的Friend又实现了CallBack所以我们直接传入 this。这行代码的意思是：
        // 设置拦截器，回调对象为本身对象。
        enhancer.setCallback(this);
        // 返回Enhancer中的create()方法拿到代理对象实例给调用者
        return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        // 假装有增强行为 ˋ(°▽°)`
        System.out.println("增强行为");
        // 使用代理类对方法的代理引用,来调用invoke方法
        Object object = methodProxy.invoke(target,objects);
        return object;
    }

}
