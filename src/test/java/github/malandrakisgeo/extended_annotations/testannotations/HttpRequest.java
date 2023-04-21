package github.malandrakisgeo.extended_annotations.testannotations;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Target({METHOD, ANNOTATION_TYPE})
@Retention(RUNTIME)
public @interface HttpRequest {

	String url() default ""; //path for a base url

	String fullUrl() default "";

	String extraField() default ""; //Will throw en error if not present in child annotations

}
