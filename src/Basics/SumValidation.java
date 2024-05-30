package Basics;


import org.testng.Assert;

import Body_Files.ComplexJsonParse;
import io.restassured.path.json.JsonPath;

public class SumValidation {
	public static void main(String[] args) {

		JsonPath js = new JsonPath(ComplexJsonParse.CoursePrice());
		int count = js.getInt("courses.size()");
		System.out.println(count);
		int sum =0;
		// 6. Verify if Sum of all Course prices matches with Purchase Amount
		for (int i = 0; i < count; i++) {
			int price = js.getInt("courses[" + i + "].price");
			int copies = js.getInt("courses[" + i + "].copies");
			int amount = price * copies;
			System.out.println(amount);
			sum=sum+amount;
		}
		System.out.println(sum);
		int purchaseAmount = js.getInt("dashboard.purchaseAmount");
		Assert.assertEquals(sum, purchaseAmount);
	}
}
