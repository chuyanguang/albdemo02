package com.example.demo.proxy;

import lombok.val;
import org.junit.jupiter.api.Test;

public class ProxyTest {

    @Test
    public void staticProxyTest(){
        StaticProxy proxy = new StaticProxy(new Dog());
        System.out.println(proxy.call());
    }

    @Test
    public void DynamicProxyTest(){
        ProxyInvocationHandler proxy = new ProxyInvocationHandler();
        Animal animal = (Animal) proxy.getProxy(new Dog());
        System.out.println("animal.call() = " + animal.call());
    }

    @Test
    void cglibTest(){
       CglibProxy proxy = new CglibProxy();
       Animal animal = (Animal) proxy.getProxy(new Dog());
       System.out.println(animal.call());
    }

}
