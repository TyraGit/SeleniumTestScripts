package athirahrahmat.TestCases;

import org.testng.annotations.Test;

public class GetPageTitle extends BaseTest {

    @Test
    public void getWebPageTitle() {
    	
        System.out.println(driver.getTitle());
    }
}
