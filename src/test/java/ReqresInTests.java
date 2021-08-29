import org.junit.jupiter.api.Test;


import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;

public class ReqresInTests {
	@Test
	void usersCreateNew() {
		given ()
				.contentType (JSON)
				.body ("{\"name\": \"suburban\"," +
						"\"job\": \"qa\"}")
				.when ()
				.post ("https://reqres.in/api/users")
				.then ()
				.statusCode (201)
				.body ("name", is ("suburban"), "job", is ("qa"))
				.extract().response().asString();
	}


	@Test
	void usersGetInfo() {
		given()
				.contentType(JSON)
				.when()
				.get("https://reqres.in/api/users/6")
				.then()
				.statusCode(200)
				.body("data.email", is("tracey.ramos@reqres.in"))
				.body("data.last_name", is("Ramos"));
	}

		@Test
		void usersListGetFindUrl () {
			given()
					.contentType(JSON)
					.when()
					.get("https://reqres.in/api/users?page=3")
					.then()
					.statusCode(200)
					.body("support.url", is("https://reqres.in/#support-heading"));
		}

	@Test
	void negativeCreateUser() {
		given()
				.contentType(JSON)
				.body("{\n" +
						"    \"name\": \"morpheus\",\n" +
						"}")
				.when()
				.post("https://reqres.in/api/users")
				.then()
				.assertThat().statusCode(400);
	}

	@Test
	void delayedResponse() {
		given()
				.when()
				.get("https://reqres.in/api/users/23")
				.then()
				.statusCode(404);
	}
}