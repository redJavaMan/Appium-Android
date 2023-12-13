import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

public class Hexaware {
    WebDriver driver;
    @BeforeTest
    public void setup(){
        driver = new ChromeDriver();
    }

    public Object[][] loginTestData(){
        return new Object["User1"]["Password123"];
    }
    @Test
    @DataProvider(data = loginTestData)
    public void validateDropdownDuplicate(username, password){
        driver.get("https://appurl-demo-prod.com");
        List<WebElement> countries = driver.findElements(By.xpath("//div[@test-data-id='countries']"));
        countries.stream().map(WebElement::getText());
        String input = "Test123";

    }
}
