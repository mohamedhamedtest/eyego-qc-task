package test;

import Listeners.iinvokedmethod;
import Listeners.itest;
import com.github.javafaker.Faker;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import page.pag1_login;
import utilities.json;
import utilities.log;
import utilities.utility;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;

import static DriverFactory.DriverFactory.*;

@Listeners({iinvokedmethod.class, itest.class})

public class tc4_login {
    //private static final Logger log = LogManager.getLogger(tc4_login.class);
    private final String firstname = json.GetJsonData("name", "fname") + "-" + utility.getTimeStamp();
    private final String lirstname = json.GetJsonData("name", "lname") + "-" + utility.getTimeStamp();
    private final String ZIP = new Faker().number().digits(5);
    private WebDriver Driver;

    public tc4_login() throws FileNotFoundException {
    }


    @BeforeMethod
    public void setUp() throws Exception {

        setDriver(json.GETPropertriesData("pro", "browser"));
        log.info("open pag");
        getDriver().get(json.GETPropertriesData("pro", "url"));
        log.info("page is open ");
        getDriver().manage().window().maximize();
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @Test
    public void login() throws IOException {
        System.out.println("Working Directory = " + System.getProperty("user.dir"));
        new pag1_login(getDriver())

                .username(json.GetJsonData("login", "username"))
                .password(json.GetJsonData("login", "password"))
                .botton()
                .addRandomProducte(2, 6)
                .clickButton()
                .presSheckout()
                .totalname(firstname, lirstname, ZIP)
                .CONTINUE();
        //log.info(firstname + lirstname + ZIP);

        Assert.assertTrue(utility.pageUrl(getDriver(), json.GETPropertriesData("pro", "url4")));
    }

    @AfterMethod
    public void quit() throws Exception {
        quite();
    }
}



