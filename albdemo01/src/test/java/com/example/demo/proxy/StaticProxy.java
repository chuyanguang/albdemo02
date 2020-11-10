package com.example.demo.proxy;

/**
 * 静态代理：实现代理类的接口
 */
public class StaticProxy implements Animal {

    private Animal animal;

    public StaticProxy(Dog dog) {
        this.animal = dog;
    }

    @Override
    public String call() {
        return animal.call();
    }
}
