package COM.ExampleProject.Web.Responsiveness.Pages;

import com.testautomationguru.ocular.snapshot.Snap;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;


@Snap("Page.png")
public class MubalooGenericPage extends BasePage{

    @FindBy(css = ".et-pb-icon")
    @CacheLookup
    private WebElement seeOurApproach;

    public MubalooGenericPage(RemoteWebDriver driver){
        super(driver);
    }


}
