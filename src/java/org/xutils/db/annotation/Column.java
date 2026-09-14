package org.xutils.db.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Retention;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD })
public @interface Column {
    boolean autoGen() default true;
    
    boolean isId() default false;
    
    String name();
    
    String property() default "";
}
