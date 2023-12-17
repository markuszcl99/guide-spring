package com.markus.spring.bean.scope;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;
import org.springframework.core.NamedThreadLocal;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: markus
 * @date: 2023/12/17 7:08 PM
 * @Description:
 * @Blog: https://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public class ThreadLocalScope implements Scope {

    public static final String THREAD_LOCAL_SCOPE_NAME = "thread-local-scope";

    private NamedThreadLocal<Map<String, Object>> threadLocal = new NamedThreadLocal("thread-local") {
        @Override
        public Map<String, Object> initialValue() {
            return new HashMap<>();
        }
    };

    @Override
    public Object get(String name, ObjectFactory<?> objectFactory) {
        Map<String, Object> context = getContext();
        Object bean = context.get(name);
        if (bean == null) {
            bean = objectFactory.getObject();
            context.put(name, bean);
        }
        return bean;
    }

    @Override
    public Object remove(String name) {
        Map<String, Object> context = getContext();
        return context.remove(name);
    }

    @Override
    public void registerDestructionCallback(String name, Runnable callback) {
        // todo
    }

    @Override
    public Object resolveContextualObject(String key) {
        return getContext().get(key);
    }

    @Override
    public String getConversationId() {
        Thread thread = Thread.currentThread();
        return String.valueOf(thread.getId());
    }

    private Map<String, Object> getContext() {
        return threadLocal.get();
    }
}
