package github.malandrakisgeo.extended_annotations.testannotations;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Target({METHOD})
@Retention(RUNTIME)
@HttpRequest()
public @interface GET {

	String url() default ""; //if using a baseurl

	String fullUrl() default "";

	String extraField() default "";
}
