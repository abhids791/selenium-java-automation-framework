package aimsGreen.qa.test;

import AimsGreen.QA.pages.LandingPage;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

public class BaseTest {
    WebDriver driver;
    LandingPage landingPage;

    @BeforeMethod(alwaysRun = true)
    public LandingPage launchApplication() throws IOException {
        driver=initializeDriver();
        landingPage= new LandingPage(driver);
        landingPage.goToLandingPage();
        return landingPage;
    }
    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
    public WebDriver initializeDriver() throws IOException {
        Properties prop= new Properties();
        FileInputStream fis = new FileInputStream("src/main/resources/config.properties");
        prop.load(fis);
        String browserName= prop.getProperty("browser");

        if(browserName.equalsIgnoreCase("chrome")){
            driver = new ChromeDriver();
        } else if (browserName.equalsIgnoreCase("FireFox")) {
            driver = new FirefoxDriver();
        }else if (browserName.equalsIgnoreCase("Edge")) {
            driver = new EdgeDriver();
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        return driver;
    }

    public List<HashMap<String, String>> getJsonDatgaToMap(String path) throws IOException {
        String jsonContent = FileUtils.readFileToString(new File(path), "UTF-8");
        ObjectMapper objectMapper = new ObjectMapper();
        List<HashMap<String, String>> dataMap = objectMapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {

        });

        return dataMap;
    }

    public String takeScreenShot(String testCaseName, WebDriver driver) throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        TakesScreenshot ts= ((TakesScreenshot)driver);
        File src=ts.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(src,new File("src/main/resources/screenShots/"+testCaseName+timeStamp+"screenshot.png"));
        return System.getProperty("user.dir")+"\\src\\main\\resources\\screenShots\\"+testCaseName+"screenshot.png";
    }


}
