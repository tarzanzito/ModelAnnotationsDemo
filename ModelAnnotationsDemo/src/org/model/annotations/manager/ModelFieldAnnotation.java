package org.model.annotations.manager;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.ElementType;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ModelFieldAnnotation {
    public String id();
    public int index();
    public int size() default 0;
    //public MemberInfo MemberInfo { get; private set; }
}
