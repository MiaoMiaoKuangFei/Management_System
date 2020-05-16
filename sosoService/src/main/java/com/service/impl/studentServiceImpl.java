package com.service.impl;

import com.dao.ConsumeInfoDao;
import com.dao.StudentDao;
import com.pojo.Student;
import com.service.studentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("mobileCardService")
public class studentServiceImpl implements studentService {
    @Qualifier("mobileCardDao")
    @Autowired//完成自动装配 使得类中getter/setter能被自动填充
    private StudentDao studentDao;

    @Qualifier("consumeInfoDao")
    @Autowired
    private ConsumeInfoDao consumeInfoDao;
    @Override

    public boolean saveMobileCard(Student student) {
        return studentDao.saveOnlineCourseCard(student);
    }

    @Override
    public boolean deleteMobileCard(String phoneNumber) {
        try{
            consumeInfoDao.deleteConsumeInfo(phoneNumber);
            return studentDao.deleteOnlineCourseCard(phoneNumber);
        }catch(Exception e){
            return false;
        }

    }

    @Override
    public String deleteZombieMobileCard(Student student) {
        try{
            studentDao.deleteZombieUser(student);
            List<Student> currStudentList = studentDao.getOnlineCourseCardByName(student.getUserName());
            StringBuilder reportCard = new StringBuilder();
            reportCard.append("当前仍然保存的").append(student.getUserName()).append("的账号为：");
            for(Student eachStudent:currStudentList){
                reportCard.append(eachStudent.getCardNumber());
                reportCard.append("<br>");
            }
            return reportCard.toString();
        }catch(Exception e){
            e.printStackTrace();
            return "查询错误，请检查是否有该类卡";
        }

    }

    @Override
    public boolean isExistMobileCard(String phoneNumber) {
        return studentDao.getOnlineCourseCard(phoneNumber)!=null;
    }

    @Override
    public boolean isExistMobileCard(String phoneNumber, String passWord) {
        if(isExistMobileCard(phoneNumber))
            return getMobileCard(phoneNumber).getPassWord().equals(passWord);
        else
            return false;
    }

    @Override
    public boolean updateMobileCard(Student student) {
        return studentDao.updateOnlineCourseCard(student);
    }

    @Override
    public Student getMobileCard(String phoneNumber) {
        Student tmp = studentDao.getOnlineCourseCard(phoneNumber);
        tmp.getAccordPackage();
        return tmp;
    }

    @Override
    public List<Student> queryAllMobileCard() {
        List<Student> tmp =  studentDao.queryAllOnlineCourseCard();
        for(Student card:tmp){
            card.getAccordPackage();
        }

        return tmp;
    }

}
