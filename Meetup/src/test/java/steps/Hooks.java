package steps;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import utilities.Driver;

public class Hooks {

    @Before
    public void setUp(){
        //what can we put here?
        //you DO NOT NEED to set up your driver here
        //Environment set up here
    }

    @After
    public void tearDown(Scenario scenario){
        //we can check if scenario has failed and take a screenshot and attach it to our report
        try{
            if(scenario.isFailed()){
                //take a screenshot
                final byte[] screenshot = ((TakesScreenshot)Driver.getDriver()).getScreenshotAs(OutputType.BYTES);
                //we can add the screenshot top html report
                scenario.embed(screenshot, "image/png");
            }
        }catch(Exception e){
            System.out.println("The error happened while cleaning up after the test: "+
                    e.getMessage());
        }

        //we perform a clean up after each test
        Driver.closeDriver();
    }
}
