package com.pojo.cardPackage;

import com.pojo.exception.InsufficientMoneyException;
import com.pojo.Student;

public class ServicePackage {
    /*文件名:Service.ServicePackage.java
     * 功能：作为各类服务包的父类
     * @作者：计科林铸天
     * @时间：2019.9.22
     *
     * */
    public double price=0;
    protected int courseTime =0;
    protected int courseCost =0;
    protected int chatFlow =0;
    public String showInfo(){
        return ("套餐外：网课按分钟计费，每分钟为0.2元，海量刷题时间0.1元/分钟，聊天流量为0.1元/MB\n");
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCourseTime() {
        return courseTime;
    }

    public void setCourseTime(int courseTime) {
        this.courseTime = courseTime;
    }

    public int getCourseCost() {
        return courseCost;
    }

    public void setCourseCost(int courseCost) {
        this.courseCost = courseCost;
    }

    public int getChatFlow() {
        return chatFlow;
    }

    public void setChatFlow(int chatFlow) {
        this.chatFlow = chatFlow;
    }

    /*这些功能改在servicepackage里面更好，这样更方面从上到下调用*/
    public void call(int minCount, Student card)throws InsufficientMoneyException {//这里默认可以扣掉，如果钱超出去了，那就在场景里实现
        double cost=0;
        if(minCount+card.getRealOnlineCourseTime()>card.getSerPackage().courseTime){
            //cost=0.2*(minCount+card.realTalkTime-card.serPackage.talkTime);//这里写的不对，相当于每次都得算一遍上一次的通话时间了
            if(card.getRealOnlineCourseTime()<card.getSerPackage().courseTime){
                cost=0.2*(minCount+card.getRealOnlineCourseTime()-card.getSerPackage().courseTime);
            }
            else{
                cost=0.2*minCount;
            }
        }
        if(card.getMoney()<cost){
            throw new InsufficientMoneyException("通话",minCount,cost,card.getMoney());
        }
        else{
            card.setMoney(card.getMoney()-cost);
            card.setConsumAmount(card.getConsumAmount()+cost);
            card.setRealOnlineCourseTime(card.getRealOnlineCourseTime()+minCount);
        }

    }
    public void send(int count, Student card)throws InsufficientMoneyException{
        double cost=0;
        if(count+card.getRealHomeworkUsingTime()>card.getSerPackage().courseCost){
            if(card.getRealHomeworkUsingTime()<card.getSerPackage().courseCost){
                cost=0.1*(count+card.getRealHomeworkUsingTime()-card.getSerPackage().courseCost);
            }
            else{
                cost=0.1*count;
            }
        }
        if(card.getMoney()<cost){
            throw new InsufficientMoneyException("短信",count,cost,card.getMoney());
        }
        else{
            card.setMoney(card.getMoney()-cost);
            card.setRealHomeworkUsingTime(card.getRealHomeworkUsingTime()+count);
            card.setConsumAmount(card.getConsumAmount()+cost);
        }

    }
    public void netPlay(int flow, Student card)throws InsufficientMoneyException{
        double cost=0;
        if(flow+card.getRealChatFlow()>card.getSerPackage().chatFlow){
            if(card.getRealChatFlow()<card.getSerPackage().chatFlow){
                cost=0.1*(flow+card.getRealChatFlow()-card.getSerPackage().chatFlow);
            }
            else{
                cost=0.1*flow;
            }
        }
        if(card.getMoney()<cost){
            throw new InsufficientMoneyException("数据",flow,cost,card.getMoney());
        }
        else{
            card.setMoney(card.getMoney()-cost);
            card.setRealChatFlow(card.getRealChatFlow()+flow);
            card.setConsumAmount(card.getConsumAmount()+cost);
        }
    }
}
