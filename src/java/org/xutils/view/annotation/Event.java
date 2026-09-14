package org.xutils.view.annotation;

import android.view.View$OnClickListener;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Retention;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD })
public @interface Event {
    String method() default "";
    
    int[] parentId() default { 0 };
    
    String setter() default "";
    
    Class<?> type() default View$OnClickListener.class;
    
    int[] value();
}
