import com.service.BasicFunctionService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class testBaseFunctionService extends BaseTest {
    @Autowired
    private BasicFunctionService basicFunctionService;

    @Test
    public void testUseSoso() throws Exception{
        System.out.println(basicFunctionService.useMainService("123456"));
    }

    @Test
    public void testChargeMoney()throws Exception{

        System.out.println(basicFunctionService.chargeMoney("123456",1));//返回错误信息
        System.out.println(basicFunctionService.chargeMoney("123456",100));
    }
    @Test
    public void testShowInformation(){
        System.out.println(basicFunctionService.showInformation("123456"));
    }

    @Test
    public void testRandomScene(){
        System.out.println(basicFunctionService.randomScene().printInfo());
    }
    @Test
    public void testSearchMonthList(){
        System.out.println(basicFunctionService.searchMonthList("test"));
    }
    @Test
    public void testSearchRemaining(){
        System.out.println(basicFunctionService.searchRemaining("test"));

    }

    @Test
    public void testPrintConsumeList(){
        System.out.println(basicFunctionService.printConsumeList("test"));


    }
    @Test
    public void testChangePack(){
        System.out.println(basicFunctionService.changePack("test",1));
        System.out.println(basicFunctionService.changePack("test",1));
        System.out.println(basicFunctionService.changePack("test",2));



    }
    @Test
    public void testDeleteUser(){
        System.out.println(basicFunctionService.deleteUser("test"));



    }
}
