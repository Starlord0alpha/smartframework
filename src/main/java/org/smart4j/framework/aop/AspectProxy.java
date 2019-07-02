package org.smart4j.framework.aop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smart4j.framework.aop.proxy.Proxy;
import org.smart4j.framework.aop.proxy.ProxyChain;

import java.lang.reflect.Method;

public class AspectProxy implements Proxy {
    private static final Logger LOGGER = LoggerFactory.getLogger(AspectProxy.class);

    @Override
    public Object doProxy(ProxyChain proxyChain) throws Throwable {
        Object result = null;

        Class<?> cls = proxyChain.getTargetClass();
        Method method = proxyChain.getTargetMethod();
        Object[] params = proxyChain.getMethodParams();

        begin();
        try {
            if(intercept(cls, method, params)) {
                befor(cls, method, params);
                result = proxyChain.doProxyChain();
                after(cls, method, params);
            } else {
                result  = proxyChain.doProxyChain();
            }
        } catch (Exception e) {
            LOGGER.error("proxy failure", e);
            error(cls, method, params);
            throw e;
        } finally {
            end();
        }

        return result;
    }

    public void begin() {

    }

    public boolean intercept(Class<?> cls, Method method, Object[] params) throws Throwable{
        return true;
    }

    public void befor(Class<?> cls, Method method, Object[] params) throws Throwable {

    }

    public void after(Class<?> cls, Method method, Object[] params) throws Throwable {

    }

    public void error(Class<?> cls, Method method, Object[] params) throws Throwable {

    }

    public void end() {

    }
}
