import com.pojo.Student;
import com.service.studentService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class testStudentService extends BaseTest {
    @Autowired
    private studentService studentService;

    @Test
//    @Bean
    public void testSaveMobileCard() throws Exception {
        Student currCard=new Student("testService","test","test",111,1);
        boolean insert = studentService.saveMobileCard(currCard);
        System.out.println(insert);
    }
    @Test
    public void testDeleteMobileCard()throws Exception{
        boolean deleteCard = studentService.deleteMobileCard("123456789");

        System.out.println(deleteCard);

    }

    @Test
    public void testUpdateMobileCard()throws Exception{
        //这里需要构造MobileCard的对象传入
        Student updateMC = new Student("testService","111","test",1222,1);
        updateMC.getAccordPackage();
        boolean updateMobileCard = studentService.updateMobileCard(updateMC);
        System.out.println(updateMobileCard);

    }

    @Test
    public void testGetMobileCard() throws Exception{
        Student testCard= studentService.getMobileCard("13903031905");
        testCard.getAccordPackage();
        testCard.showMeg();

    }

    @Test
    public void testQueryAllMobileCard()throws Exception{
        List<Student> allCard= studentService.queryAllMobileCard();
        return;
    }
    @Test
    public void testIsExistMobileCard()throws Exception{
        System.out.println(studentService.isExistMobileCard("testService","1111"));
    }
}
