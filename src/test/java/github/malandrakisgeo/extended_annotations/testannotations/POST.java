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
public @interface POST {
	String url() default ""; //path for a base url

	String fullUrl() default "";

	//String extraField() default ""; //Cannot be regarded as a child of HttpRequest, since it lacks the extraField

}
