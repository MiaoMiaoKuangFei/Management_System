package com.service.impl;

import com.dao.StudentDao;
import com.pojo.ConsumeInfo;
import com.pojo.Student;
import com.pojo.cardPackage.ChatPackage;
import com.pojo.cardPackage.SuperPackage;
import com.pojo.cardPackage.OnlineCoursePackage;
import com.pojo.exception.IllegalTypeException;
import com.pojo.exception.InsufficientMoneyException;
import com.pojo.scene.Scene;
import com.pojo.scene.SceneList;
import com.service.BasicFunctionService;
import com.service.ConsumeInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service("basicFunctionService")
public class BasicFunctionServiceImpl implements BasicFunctionService {
    //定义
    @Autowired
    private ConsumeInfoService consumeInfoService;
    @Qualifier("mobileCardDao")
    @Autowired
    private StudentDao studentDao;
//    @Autowired
//    private MobileCardService mobileCardService;
    private boolean isExistMobileCard(String cardNum){
        Student currCard= studentDao.getOnlineCourseCard(cardNum);
        if(currCard==null)
            return false;
        return true;
    }
    @Override
    public String useMainService(String cardNum) {

        Student currCard= studentDao.getOnlineCourseCard(cardNum);

        if(currCard==null)
            return "不存在为该卡号的用户";
        else{
            currCard.getAccordPackage();
        }
        Scene currScene=randomScene();
        String returnInfo = currScene.printInfo();
        try{
            this.cost(currCard,currScene);//cost更改完不再是按址操作了，要上传回去，写进cost里头了
            return returnInfo+("消费成功，当前账单为：")+ searchMonthList(cardNum);
        }catch(IllegalTypeException i){
            return returnInfo+i.showException();
        }catch(InsufficientMoneyException e){
            return returnInfo+e.showException();
        }
    }

    @Override
    public String chargeMoney(String cardNum, int money) {
        Student tmpStudent = studentDao.getOnlineCourseCard(cardNum);
        if(tmpStudent ==null)
            return "不存在为该卡号的用户";
        else{
            tmpStudent.getAccordPackage();
        }
        if(money<50)
            return ("每次充值必须大于50元!");
        tmpStudent.setMoney(tmpStudent.getMoney()+money);
        studentDao.updateOnlineCourseCardWhenCharge(tmpStudent);
        return "充值成功<br>"+ tmpStudent.showMeg();

    }

    @Override
    public String showInformation(String cardNum) {
        try{
            return
            ("****资费说明****"+"<br>")
            +("使用网课套餐，套餐内网课时间为500分钟，题库免费刷题时间30分钟。<br>")
            +("使用超级套餐，套餐内网课时间为200分钟，聊天流量为1GB，题库免费刷题时间50分钟。<br>")
            +("使用聊天套餐，套餐内聊天流量为3GB。<br>")
            +("套餐外：网课按分钟计费，每分钟为0.2元，海量刷题时间0.1元/分钟，聊天流量为0.1元/MB<br>");
        }catch(Exception e){//一开始调不通是因为有这个exception，现在catch到了就不会报错了
            e.printStackTrace();
            return "暂无信息<br>";
        }
    }

    @Override
    public Scene randomScene() {

    final int sceneSize = 6;
    //找一个随机的场景出来
    SceneList myList = new SceneList();//调出随机myList
    Random random = new Random();//指定种子数字
    int randomChoice = random.nextInt(sceneSize);

    return myList.getSceneList()[randomChoice];//pick出一个场景出来
    }
    @Override
    public String searchMonthList(String currCardNumber) {
        Student currCardObject= studentDao.getOnlineCourseCard(currCardNumber);
        if(currCardObject==null)
            return "不存在为该卡号的用户";
        else
            currCardObject.getAccordPackage();
        String monthListInformation = "";
        monthListInformation = monthListInformation+("您的卡号为："+currCardObject.getCardNumber()+"<br>")
                +("当月账单<br>")
                +("套餐资费："+currCardObject.getSerPackage().price+"<br>")
                +("本月消费:"+currCardObject.getConsumAmount()+"<br>")
                +("账户余额："+currCardObject.getMoney()+"<br>");
        return monthListInformation;
    }

    @Override
    public String searchRemaining(String cardNum) {
        Student currCardObject= studentDao.getOnlineCourseCard(cardNum);
        if(currCardObject==null)
            return "不存在为该卡号的用户";
        else
            currCardObject.getAccordPackage();
        //fixme：这个地方要把这些println全部变成string传回去！！！
        //fixme：上面的那些东西也要修修补补
//        currCardObject.getSerPackage().showInfo();
        //这个地方想必是bePositive的问题
        return ("当前聊天室余额为："+(bePositive(currCardObject.getSerPackage().getChatFlow()-currCardObject.getRealChatFlow())))
        + ("<br>当前网课时长余额为："+(bePositive(currCardObject.getSerPackage().getCourseTime()-currCardObject.getRealOnlineCourseTime())))
        +("<br>当前题库使用时间余额为："+(bePositive(currCardObject.getSerPackage().getCourseCost()-currCardObject.getRealHomeworkUsingTime())));
    }

    @Override
    public String printConsumeList(String cardNum) {
        try{
            Student currCardObject = studentDao.getOnlineCourseCard(cardNum);
            StringBuilder consumeList = new StringBuilder();

            consumeList.append("------------------------------****消费详单****------------------------------").append("<br>");
            //因为把流定向到外面了，只要在把资费输出重新输出出来就行了
            consumeList.append("对应卡号为").append(currCardObject.getCardNumber()).append("<br>");
            List<ConsumeInfo> currConsumeInfoList = consumeInfoService.getConsumeInfo(currCardObject.getCardNumber());
            for(int i=0;i<currConsumeInfoList.size();i++){
                consumeList.append(currConsumeInfoList.get(i).printInfo()).append("<br>");
            }

            return consumeList.toString();
        }catch(Exception e){//一开始调不通是因为有这个exception，现在catch到了就不会报错了
            e.printStackTrace();
            return "打印失败！";
        }
    }

    @Override
    public String changePack(String cardNum,int selectNewPac) {
        Student currCardObject= studentDao.getOnlineCourseCard(cardNum);
        if(currCardObject==null)
            return "不存在为该卡号的用户";
        else
            currCardObject.getAccordPackage();
        if(currCardObject.getSerPackage() instanceof OnlineCoursePackage &&selectNewPac==0||currCardObject.getSerPackage() instanceof ChatPackage &&selectNewPac==1||currCardObject.getSerPackage() instanceof SuperPackage &&selectNewPac==2){
            return "与原来的卡包相同，请重选！";//凡是之前错误提示的部分都改成return false
        }
        switch(selectNewPac){
            case 0:
            case 1:
            case 2:
                //重新排布这些东西的内容
                currCardObject.setPackageIndex(selectNewPac);
                currCardObject.setRealChatFlow(0);
                currCardObject.setRealHomeworkUsingTime(0);
                currCardObject.setRealOnlineCourseTime(0);

                this.studentDao.updateOnlineCourseCardWhenChangePac(currCardObject);
                break;
            default:
                return "候选卡包不存在！";

        }
        return "修改成功！新的套餐为"+selectNewPac+"号。<br>";
    }

    @Override
    public String deleteUser(String currPhoneNum) {
        //完成迁移

        try{
            studentDao.deleteOnlineCourseCard(currPhoneNum);
            consumeInfoService.deleteConsumeInfo(currPhoneNum);
            return "删除成功！";
        }catch (Exception e){
            e.printStackTrace();
            return "删除失败！";
        }
    }

    //下面的都不是Override了
    public String getCardNum(){
        //这个是内置的，并不是继承的，下同
        Random random = new Random();//指定种子数字
        String tmpNum="139";
        for(int j=0;j<8;j++){//逐个产生电话号码
            int tmp = random.nextInt(10);
            tmpNum+=tmp;

        }
        return tmpNum;
    }
    public String[] getNewNumbers(){
        /*
         * 获得一个合法定长卡号数组
         * */
        final int cardNum = 9;
        String[] altCardNumbers = new String[cardNum];
        for (int i=0;i<cardNum;i++){
            String tmpNum=getCardNum();
            while(this.isExistMobileCard(tmpNum)){
                tmpNum=getCardNum();
            }
            altCardNumbers[i]=tmpNum;//调试通过

        }
        return altCardNumbers;
    }
    int bePositive(int i){
        if(i>=0) return i;
        else return 0;
    }
    double bePositive(double i){
        if(i>=0) return i;
        else return 0;
    }
    void cost(Student currCard, Scene scene) throws InsufficientMoneyException, IllegalTypeException {
        int data = scene.getData();
        String type =scene.getType();

        if(type.equals("聊天"))
            currCard.getSerPackage().netPlay(data,currCard);
        else if(type.equals("网课"))
            currCard.getSerPackage().call(data,currCard);
        else if(type.equals("作业"))
            currCard.getSerPackage().send(data,currCard);

        else
            throw new IllegalTypeException(type);
        //更新数据库
        //之后放开
        ConsumeInfo consumeInfo = new ConsumeInfo(currCard.getCardNumber(),type,data);
        consumeInfoService.saveConsumeInfo(consumeInfo);
        studentDao.updateOnlineCourseCardWhenCost(currCard);

    }
}
