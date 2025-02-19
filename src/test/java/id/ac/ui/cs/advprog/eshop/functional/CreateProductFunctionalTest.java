package id.ac.ui.cs.advprog.eshop.functional;

import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ExtendWith(SeleniumJupiter.class)
class CreateProductFunctionalTest {

    @LocalServerPort
    private int serverPort;

    @Value("${app.baseUrl:http://localhost}")
    private String testBaseUrl;

    private String baseUrl;

    @BeforeEach
    void setupTest() {
        baseUrl = String.format("%s:%d/product/create", testBaseUrl, serverPort);
    }

    @Test
    void testCreateProduct(ChromeDriver driver) {
        driver.get(baseUrl);

        WebElement nameInput = driver.findElement(By.id("nameInput"));
        WebElement quantityInput = driver.findElement(By.id("quantityInput"));
        WebElement submitButton = driver.findElement(By.cssSelector("button.btn-primary"));

        String productName = "Test Product";
        int productQuantity = 10;
        nameInput.sendKeys(productName);
        quantityInput.sendKeys(String.valueOf(productQuantity));

        submitButton.click();
        try {
            Thread.sleep(2000); // Adjust the sleep time as needed
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Navigate to the product list page
        String productListUrl = String.format("%s:%d/product/list", testBaseUrl, serverPort);
        driver.get(productListUrl);

        // Verify the created product is in the list
        WebElement productCard = driver.findElement(By.xpath("//div[@class='product-card']//h5[text()='" + productName + "']"));
        WebElement productQuantityElement = driver.findElement(By.xpath("//div[@class='product-card']//h5[text()='" + productName + "']/following-sibling::p/span"));

        // Assertions
        assertTrue(productCard.isDisplayed(), "The created product should be displayed in the product list");
    }
}
