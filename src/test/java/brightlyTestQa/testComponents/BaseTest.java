package brightlyTestQa.testComponents;

import brightlyTestQa.pagesObjects.LandingPage;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

public class BaseTest {
    protected WebDriver driver;
    protected LandingPage landingPage;

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
            driver = null;
        }
    }
    public WebDriver initializeDriver() throws IOException {
        Properties fileProperties= new Properties();

        try(FileInputStream fis= new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/config.properties");) {
            fileProperties.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
            throw new IOException("Failed to load config.properties file", e);
        }

        String browserName;
        if(System.getProperty("browser")==null || System.getProperty("browser").isEmpty()){
            browserName= fileProperties.getProperty("browser");
        }
        else{
            browserName= System.getProperty("browser");

        }

        boolean headless = Boolean.parseBoolean(System.getProperty("headless", "false"));

        if(browserName.equalsIgnoreCase("chrome")){
            ChromeOptions options = new ChromeOptions();
            if (headless) {
                options.addArguments("--headless=new");
                options.addArguments("--window-size=1920,1080");
                options.addArguments("--no-sandbox");
                options.addArguments("--disable-dev-shm-usage");
            }
            driver = new ChromeDriver(options);
        }
        else if (browserName.equalsIgnoreCase("firefox")) {
            FirefoxOptions options = new FirefoxOptions();
            if(headless){
                options.addArguments("--headless=new");
                options.addArguments("--window-size=1920,1080");
                options.addArguments("--no-sandbox");
                options.addArguments("--disable-dev-shm-usage");
            }
            driver = new FirefoxDriver(options);
        }
        else if (browserName.equalsIgnoreCase("edge")) {
            EdgeOptions options = new EdgeOptions();
            if(headless){
                options.addArguments("--headless=new");
                options.addArguments("--window-size=1920,1080");
                options.addArguments("--no-sandbox");
                options.addArguments("--disable-dev-shm-usage");
            }
            driver = new EdgeDriver(options);
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
        TakesScreenshot ts= ((TakesScreenshot)driver);
        File src=ts.getScreenshotAs(OutputType.FILE);
        File screenshotDir = new File("src/main/resources/screenShots");
        if (!screenshotDir.exists()) {
            screenshotDir.mkdirs();
        }
        String fileName = testCaseName + "_"  + ".png";
        FileUtils.copyFile(src, new File(screenshotDir, fileName));
        return System.getProperty("user.dir") + "\\src\\main\\resources\\screenShots\\" + fileName;
    }


}
