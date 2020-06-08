package com.example.demo.proxy;

/**
 * 静态代理：实现代理类的接口
 */
public class StaticProxy implements Animal {

    private Dog dog;

    public StaticProxy(Dog dog) {
        this.dog = dog;
    }

    @Override
    public String call() {
        return dog.call();
    }
}
