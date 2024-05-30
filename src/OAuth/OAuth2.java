package OAuth;

import static io.restassured.RestAssured.*;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.restassured.path.json.JsonPath;

public class OAuth2 {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\ACCOUNT\\eclipse-workspace\\Driver_2024\\chromedriver-win64\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		driver.manage().window().maximize();
		driver.get("https://accounts.google.com/v3/signin/identifier?opparams=%253Fauth_url%253Dhttps%25253A%25252F%25252Faccounts.google.com%25252Fo%25252Fo%252Bauth2%25252Fv2%25252Fauth&dsh=S1895942332%3A1716991020885771&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&ddm=0&o2v=2&redirect_uri=https%3A%2F%2Frahulshettyacademy.com%2FgetCourse.php&response_type=code&scope=https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email&service=lso&state=verifyfjdss&flowName=GeneralOAuthFlow&continue=https%3A%2F%2Faccounts.google.com%2Fsignin%2Foauth%2Fconsent%3Fauthuser%3Dunknown%26part%3DAJi8hAOIY7NcHxsJOZeqZ-RzyqfBfdpzDJ-C9vE4rzyRHg47Mzk4FqfWnYG0PEWjNKLG1EwkipwQxZiI3M8lbxDAqEgZZfTHnsIswxZdhlZJARCOFdsckJyyWvkGJi8K0avoeIW5tc64mI2mHGBGpXJOlZS0xJYa-sItfik0unVv1t110-7Nly7iHsRO_qYQsEdvUvAr6YyEnuxpEb3vMxNgdvS3V-qA6sXBjwI-Yz5NYIo8cnKOel7txgkZL6kfQaRVT3IS2TVpQHcyys5DJ8DLUPwkTaeYk9v55AS4K2sFsZBRkAzxoLZGTvPH8_9SV6BCQ-0ZvPd8FJUv-BMn1LrHLLqwrdSS9HhnCv9dEV8_K110MI4IK4neY4FkfcW-iqJFxK_GGqriIyBhDMk9ajydXnbKbCEnZT6675FmpCKfSkoLK3CJc3MBrRcL4Gbyz9RTI3JJeTIe-Uv8-4grxGAlDVBgXQfIEw%26flowName%3DGeneralOAuthFlow%26as%3DS1895942332%253A1716991020885771%26client_id%3D692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com%23&app_domain=https%3A%2F%2Frahulshettyacademy.com&rart=ANgoxcdLdqxlRhHW4O3QxQhv3Sc5tNyn3eZo7uW9Tik21_8I_HhcHZ8HMDoyjCogch4AW_BtV8EsurdgwsbZ_01l6aXxHoxS63yR9NyNqHXYwwDzRq1DPKQ");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.findElement(By.cssSelector("input[type='email']")).sendKeys("srinath19830");
		driver.findElement(By.cssSelector("input[type='email']")).sendKeys(Keys.ENTER);
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("input[type='password']")).sendKeys("password");
		driver.findElement(By.cssSelector("input[type='password']")).sendKeys(Keys.ENTER);
		Thread.sleep(4000);
		//if google is not allowing automation then we can do it manually upto password and then store url in one veriable
		
		String url =driver.getCurrentUrl();
		String partialCode= url.split("code=")[1];
		String code = partialCode.split("&scope")[0];
		System.out.println(code);
		
		
		String accessTokenResponse = given().urlEncodingEnabled(false)
				.queryParams("code",code)
				.queryParams("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
				.queryParams("client_scecret", "erZOWM9g3Utw NRJ340YYaK_W")
				.queryParams("redirect_uri", "https://rahulshettyacademy.com/getCourse.php")
				.queryParams("grant_type", "").when().post("https://www.googleapis.com/oauth2/v4/token").asString();
		JsonPath js = new JsonPath(accessTokenResponse);
		String accessToken = js.getString("access_token");

		String response = given().queryParam("access_token", accessToken).when()
				.get("https://rahulshettyacademy.com/getCourse.php").asString();
		System.out.println(response);
	}

}
