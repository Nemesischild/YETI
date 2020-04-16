package COM.ExampleProject.Web.Responsiveness.Pages;

import com.testautomationguru.ocular.snapshot.Snap;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.WebElement;


import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;


@Snap("HomePage.png")
public class MubalooHomePage extends BasePage{

    @FindBy(css = "a.et_pb_button.et_pb_button_0.et_hover_enabled.et_pb_bg_layout_light")
    @CacheLookup
    private WebElement seeOurApproach;

    public MubalooHomePage(RemoteWebDriver driver){
        super(driver);
    }


}
