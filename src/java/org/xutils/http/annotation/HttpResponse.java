package org.xutils.http.annotation;

import org.xutils.http.app.ResponseParser;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Retention;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE })
public @interface HttpResponse {
    Class<? extends ResponseParser> parser();
}
