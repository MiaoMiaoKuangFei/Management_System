package com.controller;

import com.pojo.Student;
import com.pojo.tool.MD5;
import com.service.BasicFunctionService;
import com.service.studentService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@SessionAttributes({"user","userInfo","title","changePackInfo","chargeInfo","useSosoInfo","currCardByName"})
@RequestMapping("/soso")
public class OnlineCourseSystemController {
    @Autowired
    public studentService studentService;
    @Autowired
    public BasicFunctionService basicFunctionService;

    @RequestMapping(value = "/Register")
    public String Register(@RequestParam("cardNum")String cardNum, @RequestParam("choosePackageIndex")String choosePackageIndex, @RequestParam("userName")String userName, @RequestParam("passWord")String passWord,@RequestParam("money")String money, Model model,HttpServletRequest request, HttpServletResponse response)throws IOException {
//        HttpServletRequest request, HttpServletResponse response
        {
            try{
                int index = Integer.parseInt(choosePackageIndex);
                int currMoney =Integer.parseInt(money);
                Student student = new Student(cardNum, MD5.convertToMD5(passWord),userName,currMoney,index);
                //若是存在了这个卡
                if(studentService.isExistMobileCard(cardNum))
                    return "redirect:/FailToOperate.jsp";
                studentService.saveMobileCard(student);

                return "redirect:/SuccessToOperate.jsp";

//                response.sendRedirect("index.jsp");
            }catch(Exception e){
                e.printStackTrace();
//                session.setAttribute("msg","保存失败！");
                return "redirect:/FailToOperate.jsp";
            }

        }
    }
    @RequestMapping("/login")
    public String logIn(@RequestParam("cardNum")String cardNum,@RequestParam("passWord")String passWord,Model model){
        try{
            if(!studentService.isExistMobileCard(cardNum,MD5.convertToMD5(passWord)))
                return "redirect:/FailToOperate.jsp";
            Student student = studentService.getMobileCard(cardNum);
            model.addAttribute("user", student);
            return "redirect:/user.jsp";
        }
        catch(Exception e){
            e.printStackTrace();
            return "redirect:/FailToOperate.jsp";
        }
    }
    @RequestMapping("/deleteUser")
    public String deleteUser(String cardNum,Model model){
            try{
                studentService.deleteMobileCard(cardNum);

                return "redirect:/index.jsp";
            }catch (Exception e){
                e.printStackTrace();
                return "redirect:/FailToOperate.jsp";
            }

    }
    @RequestMapping("/getInfo")
    public String getInfo(@SessionAttribute("user") Student student, Model model) {
       model.addAttribute("userInfo",basicFunctionService.showInformation(student.getCardNumber()));
       model.addAttribute("title","用户资费说明");
       return "redirect:/userInfoPage.jsp";
    }
    @RequestMapping("/getMonthList")
    public String getMonthList(@SessionAttribute("user") Student student, Model model) {
        model.addAttribute("userInfo",basicFunctionService.searchMonthList(student.getCardNumber()));
        model.addAttribute("title","用户本月账单");
        return "redirect:/userInfoPage.jsp";
    }
    @RequestMapping("/changePack")
    public String changePack(@SessionAttribute("user") Student student, @RequestParam("packageIndex")String newPackIndex, Model model) {
        try{
            model.addAttribute("changePackInfo",basicFunctionService.changePack(student.getCardNumber(),Integer.parseInt(newPackIndex)));

        }catch (Exception e){
            model.addAttribute("changePackInfo","输入非法，请重新输入！");
        }

        return "redirect:/changePackPage.jsp";
    }
    @RequestMapping("/chargeMoney")
    public String chargeMoney(@SessionAttribute("user") Student student, @RequestParam("money")String money, Model model) {

        try{
            int chargeMoney = Integer.parseInt(money);
            model.addAttribute("chargeInfo",basicFunctionService.chargeMoney(student.getCardNumber(),chargeMoney));
        }catch (Exception e){
            model.addAttribute("chargeInfo","输入非法，请重新输入！");
        }

        return "redirect:/chargePage.jsp";
    }
    @RequestMapping("/useSoso")
    public String useSoso(@SessionAttribute("user") Student student, Model model) {

        try{
            model.addAttribute("useSosoInfo",basicFunctionService.useMainService(student.getCardNumber()));
            return "redirect:/useMainService.jsp";
        }catch (Exception e){
            model.addAttribute("useSosoInfo","模拟使用网课失败");
            return "redirect:/useMainService.jsp";
        }


    }
    @RequestMapping("/getRemainInfo")
    public String getRemainInfo(@SessionAttribute("user") Student student, Model model) {
        model.addAttribute("userInfo",basicFunctionService.searchRemaining(student.getCardNumber()));
        model.addAttribute("title","查询余量");
        return "redirect:/userInfoPage.jsp";
    }
    @RequestMapping("/printDetailedList")
    public String printDetailedList(@SessionAttribute("user") Student student, Model model) {
        model.addAttribute("userInfo",basicFunctionService.printConsumeList(student.getCardNumber()));
        model.addAttribute("title","打印消费详单");
        return "redirect:/userInfoPage.jsp";
    }

    @RequestMapping("/deleteZombieCard")
    public String deleteZombieCard(@SessionAttribute("user")Student student,Model model){
        model.addAttribute("currCardByName",studentService.deleteZombieMobileCard(student));
        return "redirect:/deleteZombieCard.jsp";
    }
}

