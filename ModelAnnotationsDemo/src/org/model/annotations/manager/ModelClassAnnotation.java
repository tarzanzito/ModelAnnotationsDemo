package org.model.annotations.manager;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.ElementType;


@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ModelClassAnnotation {

//	public enum ModelClassAnnotationType {
//	    WALK,
//	    RUN,
//	    SWIM,
//	    FREE_FALLIN
//	}
	
    public String id();
    public int version() default 0;
    public ModelClassAnnotationType type() default ModelClassAnnotationType.WALK;
}
