package assignment_Java;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


public class Java_Automation {

    public static void main(String[] args) {
    	
        // Set the path to the ChromeDriver executable
        System.setProperty("webdriver.chrome.driver", "C:\\\\Users\\\\a840035\\\\eclipse-workspace\\\\chromedriver-win64\\\\chromedriver-win64\\\\chromedriver.exe");

        // Initialize the WebDriver
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        try {
            // Create a WebDriverWait instance
        	// WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            // Step 1: Navigate to the FitPeo Homepage
            driver.get("https://www.fitpeo.com");
            
            //Using Implicit wait function for better automation
	        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

            // Step 2: Navigate to the Revenue Calculator Page
	        // Clicking on tab Revenue Calculator using linkTest Web Element
	        driver.findElement(By.linkText("Revenue Calculator")).click();
	        
	        Thread.sleep(3000);
	        JavascriptExecutor js = (JavascriptExecutor) driver;
	        
	        js.executeScript("window.scrollBy(0,500)");
	        js.executeScript("window.scrollBy(0,-50)");
	        

            // Step 3: Scroll Down to the Slider section
            //WebElement sliderSection = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span.MuiSlider-thumb input[type=range]"))); 
           // ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", sliderSection);

	        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	        
            // Step 4: Adjust the Slider to 820
            WebElement slider = driver.findElement(By.cssSelector("span.MuiSlider-thumb input[type=range]")); 
            ((JavascriptExecutor) driver).executeScript("arguments[0].value=820;", slider);
            
            // Alternatively, use actions to drag slider (if JavaScript approach fails)
            // Actions actions = new Actions(driver);
            // actions.dragAndDropBy(slider, xOffset, yOffset).perform();

            // Verify slider value
            WebElement sliderValue = driver.findElement(By.cssSelector("span.MuiSlider-thumb input[type=range]")); 
            String sliderValueText = sliderValue.getText();
            assert sliderValueText.equals("820") : "Slider value is not 820";

            // Step 5: Update the Text Field
            WebElement textField = driver.findElement(By.cssSelector("span.MuiSlider-thumb input[type=range]")); 
            textField.click();
            textField.clear();
            textField.sendKeys("560");

            // Step 6: Validate Slider Value
            WebElement updatedSlider = driver.findElement(By.cssSelector("span.MuiSlider-thumb input[type=range]"));
            String updatedSliderValueText = updatedSlider.getAttribute("value");
            assert updatedSliderValueText.equals("560") : "Slider value is not 560";

            // Step 7: Select CPT Codes
            WebElement cpt99091Checkbox = driver.findElement(By.xpath("//div[contains(@class, 'MuiBox-root')]//input[@type='checkbox']")); // 
            WebElement cpt99453Checkbox = driver.findElement(By.xpath("//div[contains(@class, 'MuiBox-root')]//input[@type='checkbox']")); // 
            WebElement cpt99454Checkbox = driver.findElement(By.xpath("//div[contains(@class, 'MuiBox-root')]//input[@type='checkbox']")); // 
            WebElement cpt99474Checkbox = driver.findElement(By.xpath("//div[contains(@class, 'MuiBox-root')]//input[@type='checkbox']")); // 
            
            if (!cpt99091Checkbox.isSelected()) cpt99091Checkbox.click();
            if (!cpt99453Checkbox.isSelected()) cpt99453Checkbox.click();
            if (!cpt99454Checkbox.isSelected()) cpt99454Checkbox.click();
            if (!cpt99474Checkbox.isSelected()) cpt99474Checkbox.click();

            // Step 8: Validate Total Recurring Reimbursement
            WebElement totalReimbursementHeader = driver.findElement(By.cssSelector("div.MuiBox-root.css-m1khva p.MuiTypography-body1")); 
            String totalReimbursementText = totalReimbursementHeader.getText();
            assert totalReimbursementText.contains("$110700") : "Total recurring reimbursement is not $110700";

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close the browser
            driver.quit();
        }
    }
}
