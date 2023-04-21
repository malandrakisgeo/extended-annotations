package github.malandrakisgeo.extended_annotations;


import github.malandrakisgeo.extended_annotations.core.AnnotationHandler;
import github.malandrakisgeo.extended_annotations.testannotations.GET;
import github.malandrakisgeo.extended_annotations.testannotations.HttpRequest;
import github.malandrakisgeo.extended_annotations.testannotations.POST;
import github.malandrakisgeo.extended_annotations.testannotations.TestClass;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class mainTest {
	TestClass testClass;

	@BeforeEach
	public void setUp() {
		this.testClass = new TestClass() {
			@Override
			@GET(fullUrl = "local")
			public boolean getTrue() {
				return false;
			}

			@Override
			@POST(fullUrl = "localhost:8080")
			public boolean postTrue() {
				return false;
			}

			;
		};
	}

	@Test
	public void firstTest() {
		assert (AnnotationHandler.isChildAnnotation(HttpRequest.class, GET.class));
		assert (!AnnotationHandler.isChildAnnotation(HttpRequest.class, POST.class)); //Post is not a child, because it lacks the extraField()

		Arrays.stream(testClass.getClass().getMethods()).toList().forEach(method -> {
			var getInstance = method.getAnnotation(GET.class);
			if (getInstance != null) {
				HttpRequest httpRequest = (HttpRequest) AnnotationHandler.getAsParent(HttpRequest.class, getInstance);
				assert (httpRequest != null);
				assert (httpRequest.fullUrl().equals(getInstance.fullUrl()));
			}
		});
	}
}
