package github.malandrakisgeo.extended_annotations.testannotations;

public interface TestClass {

	@GET(fullUrl = "localhost:8080")
	public boolean getTrue();

	@POST(fullUrl = "localhost:8080")
	public boolean postTrue();
}
