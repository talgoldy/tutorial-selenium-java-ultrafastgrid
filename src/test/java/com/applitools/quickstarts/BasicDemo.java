package com.applitools.quickstarts;

import com.applitools.eyes.ProxySettings;
import com.applitools.eyes.StdoutLogHandler;
import com.applitools.eyes.appium.Eyes;
import com.applitools.eyes.config.Configuration;
import com.applitools.eyes.visualgrid.model.IosDeviceInfo;
import com.applitools.eyes.visualgrid.model.IosDeviceName;
import com.applitools.eyes.visualgrid.services.VisualGridRunner;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

import java.net.URL;

public class BasicDemo {

	public final static String SAUCE_USERNAME = System.getenv("SAUCE_USERNAME");
	public final static String SAUCE_ACCESS_KEY = System.getenv("SAUCE_ACCESS_KEY");
	public final static String SAUCE_SELENIUM_URL = String.format("https://%s:%s@ondemand.us-west-1.saucelabs.com:443/wd/hub", SAUCE_USERNAME, SAUCE_ACCESS_KEY);

	@Test
	public void test() throws Exception {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("platformName", "iOS");
		capabilities.setCapability("deviceName", "iPhone XS Simulator");
		capabilities.setCapability("app", "storage:ad215039-24a9-4a1a-a8e5-1ea622196789");
		capabilities.setCapability("platformVersion", "14");
		capabilities.setCapability("automationName", "XCUITest");
		capabilities.setCapability("name", "UFG Native");
		capabilities.setCapability("newCommandTimeout", 300);
		capabilities.setCapability("fullReset", false);
		IOSDriver driver = new IOSDriver<>(new URL(SAUCE_SELENIUM_URL), capabilities);
		driver.findElementByName("Controls").click();
		driver.findElementByName("Buttons").click();
		VisualGridRunner visualGridRunner = new VisualGridRunner(10);
		Eyes eyes = new Eyes(visualGridRunner);
		Configuration configuration = eyes.getConfiguration();
		configuration.addBrowser(new IosDeviceInfo(IosDeviceName.iPhone_8));
		configuration.addBrowser(new IosDeviceInfo(IosDeviceName.iPhone_11_Pro));
		configuration.addBrowser(new IosDeviceInfo(IosDeviceName.iPhone_12_Pro_Max));
		eyes.setConfiguration(configuration);
		eyes.setLogHandler(new StdoutLogHandler(true));
		eyes.setProxy(new ProxySettings("http://localhost:8888"));
		eyes.setApiKey("TKYcQIFGLNd0KxaoV72Ruw5CDPRUGEWmy1qqF9mTtqw110");
		eyes.setBranchName("master");
		eyes.setSaveNewTests(false);
		try {
			eyes.open(driver, "UFG Native", "Demo");
			eyes.checkWindow();
			eyes.closeAsync();
		} finally {
			driver.quit();
			eyes.abortAsync();
			visualGridRunner.getAllTestResults();
		}
	}
}
