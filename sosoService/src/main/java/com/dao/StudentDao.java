package com.dao;

import com.pojo.Student;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository("mobileCardDao")
public interface StudentDao {
    //增加

    boolean saveOnlineCourseCard(Student student);
    //删除
    boolean deleteOnlineCourseCard(String phoneNumber);
    //修改
    boolean updateOnlineCourseCard(Student student);
    boolean updateOnlineCourseCardWhenChangePac(Student student);
    boolean updateOnlineCourseCardWhenCost(Student student);
    boolean updateOnlineCourseCardWhenCharge(Student student);

    boolean deleteZombieUser(Student student);

    //查询
    Student getOnlineCourseCard(String phoneNumber);
    List<Student> getOnlineCourseCardByName(String username);

    List<Student> queryAllOnlineCourseCard();
}
