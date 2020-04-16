package COM.ExampleProject.Web.Responsiveness.Pages;

import com.testautomationguru.ocular.Ocular;
import com.testautomationguru.ocular.comparator.OcularResult;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class BasePage {

    protected final RemoteWebDriver driver;

    public BasePage(RemoteWebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, driver);
    }


    public OcularResult compare(WebElement element) {
        return Ocular.snapshot()
                .from(this)
                .sample()
                .using(driver)
                .similarity(97)
                .excluding(element)
                .compare();
    }

    public OcularResult compare(List<WebElement> elements) {
        return Ocular.snapshot()
                .from(this)
                .sample()
                .using(driver)
                .similarity(97)
                .excluding(elements)
                .compare();
    }

    public OcularResult compare() {
        return Ocular.snapshot()
                .from(this)
                .sample()
                .similarity(97)
                .using(driver)
                .compare();
    }
}
