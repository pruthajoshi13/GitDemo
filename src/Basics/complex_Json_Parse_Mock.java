/*
 * 1. Print No of courses returned by API

2.Print Purchase Amount

3. Print Title of the first course

4. Print All course titles and their respective Prices

5. Print no of copies sold by RPA Course

6. Verify if Sum of all Course prices matches with Purchase Amount
 */
package Basics;

import Body_Files.ComplexJsonParse;
import io.restassured.path.json.JsonPath;

public class complex_Json_Parse_Mock {
	public static void main(String[] args) {

		JsonPath js = new JsonPath(ComplexJsonParse.CoursePrice());
		// 1. Print No of courses returned by API
		int count = js.getInt("courses.size()");
		System.out.println(count);

		// 2.Print Purchase Amount
		int purchaseAmount = js.getInt("dashboard.purchaseAmount");
		System.out.println(purchaseAmount);
		// 3. Print Title of the first course
		String first_title_course = js.getString("courses[0].title");
		System.out.println(first_title_course);
		// 4. Print All course titles and their respective Prices
		for (int i = 0; i < count; i++) {
			String titles = js.get("courses[" + i + "].title");
			int price = js.getInt("courses[" + i + "].price");
			System.out.println(titles + " :" + price);

		}
		// 5. Print no of copies sold by RPA Course
		for (int i = 0; i < count; i++) {
			String titles = js.get("courses[" + i + "].title");
			if (titles.equalsIgnoreCase("rpA")) {
				int copies = js.getInt("courses[" + i + "].copies");
				System.out.println("Copies count :" + copies);
				break;
			}

		}

	}

}
