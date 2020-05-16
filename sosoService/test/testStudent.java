import com.dao.StudentDao;
import com.pojo.Student;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;


public class testStudent extends BaseTest{
    @Qualifier("mobileCardDao")
    @Autowired
    private StudentDao studentDao;

    @Test
    public void testSaveMobileCard() throws Exception {
        Student currCard=new Student("for_test","test","test",111,1);
        boolean insert = studentDao.saveOnlineCourseCard(currCard);
        System.out.println(insert);
        //经过调试发现save成功，接下来调试其他内容
    }
    @Test
    public void testDeleteMobileCard()throws Exception{
        boolean deleteCard = studentDao.deleteOnlineCourseCard("13903031905");

        System.out.println(deleteCard);

    }

    @Test
    public void testUpdateMobileCard()throws Exception{
        //这里需要构造MobileCard的对象传入
        Student updateMC = new Student("123456","qww","hello",10000,2);
        updateMC.getAccordPackage();
        boolean updateMobileCard = studentDao.updateOnlineCourseCard(updateMC);
        System.out.println(updateMobileCard);


    }

    @Test
    public void testGetMobileCard() throws Exception{
        Student testCard= studentDao.getOnlineCourseCard("13903031905");
        testCard.getAccordPackage();
        System.out.println(testCard.showMeg());

    }

    @Test
    public void testQueryAllMobileCard()throws Exception{
        List<Student> allCard= studentDao.queryAllOnlineCourseCard();
        for (Student eachCard : allCard){
            eachCard.getAccordPackage();
            System.out.println(eachCard.showMeg());
        }
        return;
    }

    @Test
    public void testUpdateOnlineCourseCardWhenChangePac()throws Exception{
        Student updateMC = studentDao.getOnlineCourseCard("123456789");
        if(updateMC==null){
            updateMC = new Student("123456789","qww","hello",10000,2);
            updateMC.getAccordPackage();
            studentDao.saveOnlineCourseCard(updateMC);
        }
        updateMC.setPackageIndex(0);
        updateMC.setRealChatFlow(0);
        updateMC.setRealHomeworkUsingTime(0);
        updateMC.setRealOnlineCourseTime(0);
        updateMC.getAccordPackage();
        boolean updateMobileCard = studentDao.updateOnlineCourseCardWhenChangePac(updateMC);
        System.out.println(updateMobileCard);
    }

    @Test
    public void testDeleteZombieCard()throws Exception{
        Student student = new Student("111111","qww","学号",10000,2);
        studentDao.deleteZombieUser(student);
    }

}
