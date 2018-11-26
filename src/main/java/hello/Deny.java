// quickstart from https://spring.io/guides/gs/rest-service/

package hello;

public class Deny {

	private final String content;

	public Deny() {
		this.content = "Go Away!";

	}

	public String getContent() {
		return content;
	}

}
