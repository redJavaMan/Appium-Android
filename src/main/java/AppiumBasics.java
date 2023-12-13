import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.By;
import org.openqa.selenium.DeviceRotation;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Set;


public class AppiumBasics extends BaseTest {
    @Test
    //LongPress on a Element
    public void longPress() {
        WebElement ele =driver.findElement(By.xpath("//a[@id='userName']"));
        ((JavascriptExecutor)driver).executeScript("mobile: longClickGesture", ImmutableMap
                .of("elementId", ((RemoteWebElement)ele).getId(),"duration",2000));
    }


    //ScrollDown
    public void scrollDown() {
        driver.findElement(AppiumBy
                .androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"WebView\"));"));
    }
    //This will scroll till WebView is visible


    //Swipe Assert: Swipe By checking focusable is true or false
    public void swipeAssert(){
        Assert.assertEquals(driver.findElement(By.xpath("//a[@id='userName']"))
                .getAttribute("focusable"),"true");
    }
    //Swipe left or right
    public void swipeAction(WebElement ele,String direction){
        ((JavascriptExecutor) driver).executeScript("mobile: swipeGesture", ImmutableMap
                .of("elementId", ((RemoteWebElement)ele).getId(), "direction", direction, "percent", 0.75));
    }

    //Drag and Drop
    public void dragAndDrop(){
        WebElement element=driver.findElement(By.xpath("//a[@id='userName']"));
        ((JavascriptExecutor) driver).executeScript("mobile: dragGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement) element).getId(),
                "endX", 100,      //x and y are co-ordinates to drop the element
                    "endY", 100));    // we can get co-ordinates from emulator
    }

    //Rotate the Screen to Landscape
    public void landScapeMode(){
        DeviceRotation landscape=new DeviceRotation(0,0,90);
        driver.rotate(landscape);
    }

    //Copy to clipboard and Paste to clipboard
    public void setClipBoard(String text){
        driver.setClipboardText(text);
        driver.findElement(By.xpath("//a[@id='userName']")).sendKeys(driver.getClipboardText());
    }

    //To press Keys
    public void pressHome(){

        driver.pressKey(new KeyEvent(AndroidKey.HOME));
    }


    //To open a page directly
    //abd shell dumpsys window | grep -E 'mCurrentFocus'  -- For Mac & Linux
    //abd shell dumpsys window | find "mCurrentFocus"  -- For Windows
    //The above command can be used in command prompt.
    //Make sure that the emulator is open with the page that is required to get the
    // address of navigation Page
    public void toNavigate2Page(){
        Activity activity =new Activity("package","activity");
        driver.startActivity(activity);
    }

    //To Hide Keyboard in Appium
    public void hideKeyBoard(){
        driver.hideKeyboard();
    }

    //To Get Toast Message
    public String getAllert(){
        return driver.findElement(By.xpath("")).getAttribute("name");
    }

    //To Handle Hybrid Apps OR Handle Apps and Browser
    public void switchToBrowser(){
        Set<String> context = driver.getContextHandles(); //gets the window handles of Browser and App
        for (String contextName:context){
            System.out.println(contextName); //prints Window Handles
        }
        driver.context("Webview"); //switch to Browser by parsing window Handle
        driver.context("NativeApp"); //switch to App by parsing window Handle
    }
}
