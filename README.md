# Mobile Testing Framework

An Appium-based mobile testing framework for Android applications with comprehensive gesture handling, device interaction capabilities, and hybrid application support.

## Overview

This framework provides a foundation for automating mobile testing on Android devices and emulators. It implements various mobile-specific interactions including:

- Gesture support (tapping, swiping, scrolling, long press)
- Device orientation control
- Clipboard manipulation
- Context switching for hybrid apps
- Direct activity navigation
- Keyboard interactions

## Project Structure

```
Mobile_Testing/
├── src/
│   ├── main/
│   │   └── java/
│   │       ├── AppiumBasics.java
│   │       ├── BaseTest.java
│   │       └── Hexaware.java
│   └── test/
│       └── java/
│           └── org/
│               └── example/
│                   └── AppTest.java
├── .gitignore
├── pom.xml
└── README.md
```

## Prerequisites

- Java JDK 11
- Maven
- Appium Server 2.0+
- Android SDK
- Node.js and npm
- A connected Android device or emulator

## Dependencies

- Appium Java Client 8.5.1
- TestNG 7.8.0

## Setup and Configuration

1. Clone this repository:
   ```
   git clone https://github.com/yourusername/Mobile_Testing.git
   cd Mobile_Testing
   ```

2. Install dependencies:
   ```
   mvn clean install
   ```

3. Configure the device settings in `BaseTest.java`:
   - Update the Appium server path
   - Set your device name
   - Specify the application path
   - Configure ChromeDriver path for hybrid app testing

## Key Features

### Gesture Support

The framework provides methods for various touch gestures:

```java
// Long press on an element
public void longPress() {
    WebElement ele = driver.findElement(By.xpath("//a[@id='userName']"));
    ((JavascriptExecutor)driver).executeScript("mobile: longClickGesture", 
        ImmutableMap.of("elementId", ((RemoteWebElement)ele).getId(),"duration",2000));
}

// Swipe in a direction
public void swipeAction(WebElement ele, String direction) {
    ((JavascriptExecutor) driver).executeScript("mobile: swipeGesture", 
        ImmutableMap.of("elementId", ((RemoteWebElement)ele).getId(), 
                       "direction", direction, "percent", 0.75));
}

// Drag and drop
public void dragAndDrop() {
    WebElement element = driver.findElement(By.xpath("//a[@id='userName']"));
    ((JavascriptExecutor) driver).executeScript("mobile: dragGesture", 
        ImmutableMap.of("elementId", ((RemoteWebElement) element).getId(),
                       "endX", 100, "endY", 100));
}
```

### Device Interaction

Methods for interacting with device features:

```java
// Change screen orientation
public void landScapeMode() {
    DeviceRotation landscape = new DeviceRotation(0, 0, 90);
    driver.rotate(landscape);
}

// Use clipboard
public void setClipBoard(String text) {
    driver.setClipboardText(text);
    driver.findElement(By.xpath("//a[@id='userName']"))
        .sendKeys(driver.getClipboardText());
}

// Press hardware keys
public void pressHome() {
    driver.pressKey(new KeyEvent(AndroidKey.HOME));
}
```

### Hybrid App Support

The framework includes functionality for handling hybrid applications (apps with web content):

```java
// Switch between app context and web context
public void switchToBrowser() {
    Set<String> context = driver.getContextHandles();
    for (String contextName : context) {
        System.out.println(contextName);
    }
    driver.context("Webview");   // Switch to web content
    driver.context("NativeApp"); // Switch back to native app
}
```

### Direct Activity Launch

Launch specific activities directly:

```java
public void toNavigate2Page() {
    Activity activity = new Activity("package", "activity");
    driver.startActivity(activity);
}
```

## Usage Examples

### Basic Test Setup

```java
public class SampleTest extends BaseTest {
    
    @Test
    public void loginTest() {
        // Find and interact with elements
        driver.findElement(By.id("username")).sendKeys("testuser");
        driver.findElement(By.id("password")).sendKeys("password");
        driver.findElement(By.id("loginButton")).click();
        
        // Verify login success
        Assert.assertTrue(driver.findElement(By.id("welcomeMessage")).isDisplayed());
    }
}
```

### Using Gesture Actions

```java
@Test
public void menuNavigation() {
    // Scroll to find an element
    driver.findElement(AppiumBy.androidUIAutomator(
        "new UiScrollable(new UiSelector()).scrollIntoView(text(\"Settings\"));"));
    
    // Tap on the element
    driver.findElement(By.xpath("//android.widget.TextView[@text='Settings']")).click();
}
```

## Running Tests

Execute tests using Maven:

```
mvn test
```

Or run specific test classes:

```
mvn test -Dtest=AppiumBasics
```

## Tips for Finding Elements

1. Use Appium Inspector to locate elements
2. For Android-specific locators, use UiAutomator:
   ```java
   driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"element text\")"));
   ```
3. Use accessibility IDs when available:
   ```java
   driver.findElement(AppiumBy.accessibilityId("elementId"));
   ```

## Troubleshooting

- **Appium server connection issues**: Ensure server is running and the URL is correct
- **Element not found**: Try different locator strategies or add waits
- **Device not detected**: Check adb connection with `adb devices`
- **For hybrid app issues**: Verify the correct ChromeDriver version is being used

## Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## Author
Mohammed Lukmanudin M - [GitHub Profile](https://github.com/redJavaMan)
