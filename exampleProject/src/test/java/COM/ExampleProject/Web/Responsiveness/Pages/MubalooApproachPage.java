package COM.ExampleProject.Web.Responsiveness.Pages;

import com.testautomationguru.ocular.snapshot.Snap;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

@Snap("ApproachPage.png")
public class MubalooApproachPage extends BasePage {

    @FindBy(css = "a[href='https://mubaloo.com/']")
    @CacheLookup
    private  WebElement goHome;

    public MubalooApproachPage(RemoteWebDriver driver){
        super(driver);
    }

}
