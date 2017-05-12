package com.wch.guice.test.field;

import com.google.inject.Binder;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Module;

public class StaticFieldInjectDemo {
 
        @Inject
        private static Service service;
 
        public static void main(String[] args) {
            Guice.createInjector(new Module() {
                @Override
                public void configure(Binder binder) {
                    binder.requestStaticInjection(StaticFieldInjectDemo.class);
                }
            });
            StaticFieldInjectDemo.service.execute();
        }
    }