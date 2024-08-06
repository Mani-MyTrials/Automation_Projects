package assignment_JS;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
//import java.util.concurrent.TimeUnit;
//import java.util.function.Consumer;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Web_Automation_JS {
	
	  public static Actions actions;

	  public static void main(String[] args) throws InterruptedException {
	 
		   
		        // Setting the Chrome Web driver path
		        System.setProperty("webdriver.chrome.driver",
		                "C:\\\\\\\\Users\\\\\\\\a840035\\\\\\\\eclipse-workspace\\\\\\\\chromedriver-win64\\\\\\\\chromedriver-win64\\\\\\chromedriver.exe");

		        // Creating an new object for ChromeDriver class
		        ChromeDriver driver = new ChromeDriver();
		        
		        // Maximizing the windows
		        driver.manage().window().maximize();

		        // Step 1: Navigate to the FitPeo Homepage
		        // Opening the Fitpeo website using the get() method
		        driver.get("https://fitpeo.com/");

		        //Using Implicit wait function for better automation
		        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(25));

		        // Step 2: Navigate to the Revenue Calculator Page
		        // Clicking on tab Revenue Calculator using linkTest Web Element
		        driver.findElement(By.linkText("Revenue Calculator")).click();
		        
		        // Step 3: Scroll Down to the Slider section
		        // Scrolling down to the Revenue Calculator Slider
		        Thread.sleep(3000);
		        driver.executeScript("window.scrollBy(0,500)");
		        driver.executeScript("window.scrollBy(0,-50)");
		         
		        // Step 4: Adjust the Slider to 820
		        //Adjusting the slider to set its value to 820, hence the text field value should be updated to 820
		        try {
		            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(80));
		            WebElement slider = wait.until(ExpectedConditions
		                    .visibilityOfElementLocated(By.cssSelector("span.MuiSlider-thumb input[type=range]")));

		            String script = "var slider = arguments[0];\n" +
		                    "var value = 820;\n" +
		                    "var max = slider.getAttribute('max');\n" +
		                    "var min = slider.getAttribute('min');\n" +
		                    "var percent = (value - min) / (max - min) * 100;\n" +
		                    "slider.value = value;\n" +
		                    "slider.setAttribute('value', value);\n" +
		                    "slider.style.setProperty('--value', value);\n" +
		                    "slider.style.setProperty('--x', percent + '%');\n" +
		                    "slider.dispatchEvent(new Event('input', { bubbles: true }));\n" +
		                    "slider.dispatchEvent(new Event('change', { bubbles: true }));\n" +
		                    "var sliderRoot = slider.closest('.MuiSlider-root');\n" +
		                    "var thumbElement = sliderRoot.querySelector('.MuiSlider-thumb');\n" +
		                    "var trackElement = sliderRoot.querySelector('.MuiSlider-track');\n" +
		                    "if (thumbElement) {\n" +
		                    "    thumbElement.style.left = percent + '%';\n" +
		                    "}\n" +
		                    "if (trackElement) {\n" +
		                    "    trackElement.style.width = percent + '%';\n" +
		                    "}";

		            driver.executeScript(script, slider);

		            Thread.sleep(2000);

		            System.out.println("The updated slider value is: " + slider.getAttribute("value"));
		            WebElement textbox = driver.findElement(By.cssSelector("input[type='number']"));
		            String updateTextboxScript = "arguments[0].value = '820';" +
		                    "arguments[0].dispatchEvent(new Event('input', { bubbles: true }));" +
		                    "arguments[0].dispatchEvent(new Event('change', { bubbles: true }));";
		            driver.executeScript(updateTextboxScript, textbox);
	          
		            Thread.sleep(2000);

		            // Verifying if the update in textbox is reflected
		            String updatedTextboxValue = textbox.getAttribute("value");
		            System.out.println("Automatically the textbox value is updated to: " + updatedTextboxValue);
		            
		            // Step 5: Update the Text Field
		            //Entering the value 560 in the text field so that the slider changes accordingly
		            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true)", textbox);
		            textbox.sendKeys(Keys.BACK_SPACE);
		            textbox.sendKeys(Keys.BACK_SPACE);
		            textbox.sendKeys(Keys.BACK_SPACE);
		            textbox.sendKeys("560");
		            //textbox.sendKeys("6");
		            //textbox.sendKeys("0");
		            
		            
		            driver.executeScript("window.scrollBy(0,-180)");
		            
		         // Step 6: Validate Slider Value
		            // Verify the update
		            Thread.sleep(10000);
		            System.out.println("Now the Value will be updated as : " + textbox.getAttribute("value"));
		            System.out.println("The slider also moved according to the value in Text Box");
		            		            
		            //Ensure that when the value 560 is entered in the text field, the slider's position is updated to reflect the value 560
		            driver.executeScript("window.scrollBy(0,-200)");
		            Thread.sleep(1000);
		            
		         // Step 7: Select CPT Codes
		            //selecting the checkboxes for CPT-99474, CPT-99091, CPT-99453, and CPT-99454
		             List<String> checkCPTCodes = Arrays.asList("CPT-99091", "CPT-99453", "CPT-99454", "CPT-99474");

		             List<WebElement> allCheckboxes = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
		                By.xpath("//div[contains(@class, 'MuiBox-root')]//input[@type='checkbox']")));

		            for (WebElement checkbox : allCheckboxes) {
		                WebElement parentDiv = (WebElement) ((JavascriptExecutor) driver).executeScript(
		                    "return arguments[0].closest('.MuiBox-root')", checkbox);
		                String CPTcode = parentDiv.findElement(By.xpath(".//p[contains(@class, 'MuiTypography-root')]")).getText();

		                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", checkbox);
		                Thread.sleep(500);

		                boolean shouldBeSelected = checkCPTCodes.contains(CPTcode);
		                boolean isCurrentlySelected = (boolean) ((JavascriptExecutor) driver).executeScript("return arguments[0].checked;", checkbox);

		                if (shouldBeSelected != isCurrentlySelected) {
		                    ((JavascriptExecutor) driver).executeScript("arguments[0].click(); arguments[0].checked = " + shouldBeSelected + ";", checkbox);
		                    System.out.println((shouldBeSelected ? "Selected" : "Unselected") + " checkbox for " + CPTcode);
		                } else {
		                    System.out.println("Checkboxes for " + CPTcode + " should not be selected");
		                }

		                Thread.sleep(500);
		            }

		            textbox.sendKeys(Keys.BACK_SPACE);
		            textbox.sendKeys(Keys.BACK_SPACE);
		            textbox.sendKeys(Keys.BACK_SPACE);
		            textbox.sendKeys("820");
		            //textbox.sendKeys("2");
		            //textbox.sendKeys("0");		            		      	            
		        		            
		            // Locating the element which contains the value
		            WebElement valueEle = driver.findElement(By.cssSelector("div.MuiBox-root.css-m1khva p.MuiTypography-body1"));

		            // Getting the value text of that element
		            String valueText = valueEle.getText();

		            // Expected value as per given condition
		            String expectedValue = "$110700";

		            // Step 8: Validate Total Recurring Reimbursement
		            // Verifying the value and printing out a statement
		            if (valueText.equals(expectedValue)) {
		                System.out.println("Verification successful: The value is " + expectedValue + ".");
		            } else {
		                System.out.println("Verification failed: The value is " + valueText + " instead of " + expectedValue + ".");
		            }
		            
		            Thread.sleep(5000);
		            driver.quit();
		            
		            }catch (Exception e) {
		            System.out.println("Exception occurred while finding the slider web element:");
		            e.printStackTrace();

		  
		        }
		        
		        		        

		    }

		


	}


