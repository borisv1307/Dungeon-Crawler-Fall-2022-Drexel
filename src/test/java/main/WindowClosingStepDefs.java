package main;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.runtime.java.StepDefAnnotation;
import engine.GameEngine;
import org.junit.Assert;
import ui.GameFrame;

import java.awt.event.WindowEvent;

@StepDefAnnotation
public class WindowClosingStepDefs {

    GameFrame gameFrame;
    GameEngine gameEngine;

    @Given("^the application is running$")
    public void the_application_is_running() {
        gameEngine = ObjectFactory.getDefaultGameEngine();
        gameFrame = ObjectFactory.getDefaultGameFrame();
    }

    @When("^I close the application by using the X button$")
    public void i_close_the_application_by_using_the_X_button() {
        gameFrame.dispatchEvent(new WindowEvent(gameFrame, WindowEvent.WINDOW_CLOSING));
    }

    @Then("^the application is terminated$")
    public void the_application_is_terminated() throws Throwable {
        Assert.assertTrue(gameEngine.isExit());
    }

}
