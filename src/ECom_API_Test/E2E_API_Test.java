package ECom_API_Test;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;

public class E2E_API_Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//SSL
		RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				.setContentType(ContentType.JSON).build();
		LoginRequest lrp = new LoginRequest();
		lrp.setUserEmail("pruthajoshi13@yahoo.com");
		lrp.setUserPassword("Shravya@1234");

		RequestSpecification reqLogin = given().relaxedHTTPSValidation().log().all().spec(req).body(lrp);
		LoginResponse loginResponse = reqLogin.when().post("/api/ecom/auth/login").then().log().all().extract()
				.response().as(LoginResponse.class);
		String token = loginResponse.getToken();
		System.out.println("TOKEN :" + token);
		String userID = loginResponse.getUserId();
		System.out.println("USERID :" + userID);

		System.out.println("LOGIN SUCCSESSFULL");

		// Add Product
		RequestSpecification addProductBaseReq = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				.addHeader("Authorization", token).build();

		RequestSpecification reqAddProduct = given().log().all().spec(addProductBaseReq)
				.param("productName", "Laptop")
				.param("productAddedBy", userID)
				.param("productCategory", "fashion")
				.param("productSubCategory", "shirts")
				.param("productPrice", "2580")
				.param("productDescription", "Addias")
				.param("productFor", "women")
				.multiPart("productImage", new File("E:\\Selenium_ScreenShots\\Google.png"));

		String addProductResponse = reqAddProduct.when().post("/api/ecom/product/add-product")
				.then().extract().response().asString();
		JsonPath js = new JsonPath(addProductResponse);
		String productId = js.getString("productId");

		Assert.assertNotNull(productId, "Product ID should not be null");
		
		System.out.println("PRODUCT ADDED SUCCSESSFULLY");

		// Create Order
		RequestSpecification createOrderBaseReq = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				.addHeader("Authorization", token).setContentType(ContentType.JSON).build();
		OrderDetails orderDetails = new OrderDetails();
		orderDetails.setCountry("India");
		orderDetails.setProductOrderId(productId);

		List<OrderDetails> orderDetailList = new ArrayList<OrderDetails>();
		orderDetailList.add(orderDetails);

		Orders orders = new Orders();
		orders.setOrders(orderDetailList);
		RequestSpecification createOrderReq = given().spec(createOrderBaseReq).body(orders);
		String responseAddOrder = createOrderReq.when().post("/api/ecom/order/create-order").then().log().all()
				.extract().response().asString();

		System.out.println(responseAddOrder);

		System.out.println("ORDER CREATED SUCCSESSFULLY");

		// Delete Product
		RequestSpecification deleteProductBaseReq = new RequestSpecBuilder()
				.setBaseUri("https://rahulshettyacademy.com").addHeader("Authorization", token)
				.setContentType(ContentType.JSON).build();

		RequestSpecification deleteProdReq = given().log().all().spec(deleteProductBaseReq).pathParam("productId",
				productId);

		String deleteProductResponse = deleteProdReq.when().delete("/api/ecom/product/delete-product/{productId}")
				.then().log().all().extract().response().asString();
		JsonPath js1 = new JsonPath(deleteProductResponse);
		Assert.assertEquals(js1.get("message"), "Product Deleted Successfully");

	}

}
