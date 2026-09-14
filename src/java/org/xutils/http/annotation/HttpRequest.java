package org.xutils.http.annotation;

import org.xutils.http.app.DefaultParamsBuilder;
import org.xutils.http.app.ParamsBuilder;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Retention;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE })
public @interface HttpRequest {
    Class<? extends ParamsBuilder> builder() default DefaultParamsBuilder.class;
    
    String[] cacheKeys() default { "" };
    
    String host() default "";
    
    String path();
    
    String[] signs() default { "" };
}
