package manager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ListenerWD extends AbstractWebDriverEventListener {

    Logger logger = LoggerFactory.getLogger(ListenerWD.class);

    public ListenerWD(Logger logger) {
        this.logger = logger;
    }

    @Override
    public void beforeFindBy(By by, WebElement element, WebDriver driver) {
        super.beforeFindBy(by, element, driver);
        logger.info("before find element: " + by);
    }

    @Override
    public void afterFindBy(By by, WebElement element, WebDriver driver) {
        super.afterFindBy(by, element, driver);
        logger.info("after find element: " + by);
    }

    @Override
    public void onException(Throwable throwable, WebDriver driver) {
        super.onException(throwable, driver);
        logger.info("Huston, we have a problem");
        logger.info(throwable.getMessage());
        logger.info(throwable.fillInStackTrace().toString());
        HelperBase helperBase = new HelperBase(driver);
        int i = (int) (System.currentTimeMillis()/10)%3600;
        String link = "src/test/screenshots/screen-" + i + ".png";
        helperBase.getScreen(link);
        logger.info("This is a link to screenshot with error --->"+ link);
    }
}
