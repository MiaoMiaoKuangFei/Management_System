package com.service;
import com.pojo.Student;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("mobileCardService")
public interface studentService {
    //增加

    boolean saveMobileCard(Student student);
    //删除
    boolean deleteMobileCard(String phoneNumber);
    String deleteZombieMobileCard(Student student);
    //修改
    boolean updateMobileCard(Student student);
    //查询
    Student getMobileCard(String phoneNumber);
    boolean isExistMobileCard(String phoneNumber);
    boolean isExistMobileCard(String phoneNumber,String passWord);
    //爬取所有MobileCard
    List<Student> queryAllMobileCard();
}
