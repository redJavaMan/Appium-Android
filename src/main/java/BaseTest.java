import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class BaseTest {
    public AppiumDriverLocalService service;
    public AndroidDriver driver;
    @BeforeSuite
    public void setUp() throws MalformedURLException {
        service =new AppiumServiceBuilder()
                .withAppiumJS(new File("C:\\Users\\Dell\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
                .withIPAddress("http://127.0.0.1").usingPort(4723).build();
        service.start();
        UiAutomator2Options options=new UiAutomator2Options();
        options.setDeviceName("LukmanPhone");
        options.setApp("");
        //To set the path of ChromeDriver , can be used only for Hybrid Apps and Browser
        options.setChromedriverExecutable("C:\\ChromeDriver\\ChromeDriver.exe");
        //To Automate Browser Only
        options.setCapability("browserName","Chrome");
        driver=new AndroidDriver(new URL("127.0.0.1:4723"),options);
    }
    @AfterSuite
    public void tearDown(){
        driver.quit();
        service.stop();
    }
}
