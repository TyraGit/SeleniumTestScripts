package athirahrahmat.TestCases;

import org.testng.annotations.Test;

import athirahrahmat.Utils.BaseTest;

public class GetPageTitle extends BaseTest {

    @Test
    public void getWebPageTitle() {
    	
        System.out.println(driver.getTitle());
    }
}
