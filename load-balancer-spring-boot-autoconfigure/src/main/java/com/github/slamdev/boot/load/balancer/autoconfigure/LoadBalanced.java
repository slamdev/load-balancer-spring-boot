package com.github.slamdev.boot.load.balancer.autoconfigure;

import org.springframework.beans.factory.annotation.Qualifier;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({FIELD, METHOD, TYPE, PARAMETER})
@Retention(RUNTIME)
@Qualifier
public @interface LoadBalanced {
}
