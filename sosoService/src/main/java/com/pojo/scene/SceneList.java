package com.pojo.scene;

public  class SceneList {
    final Scene sceneList[]=new Scene[10];

    public Scene[] getSceneList() {
        return sceneList;
    }

    public SceneList(){
        sceneList[0]=new Scene("聊天",100,"网课聊天室聊天，花掉100MB");
        sceneList[1]=new Scene("网课",45,"花费45分钟听了一次网课");
        sceneList[2]=new Scene("作业",50,"磨洋工，花费50分钟写作业");
        sceneList[3]=new Scene("聊天",1000,"聊天聊疯了，花费100MB");
        sceneList[4]=new Scene("网课",10,"看青年大学习，花费10分钟");
        sceneList[5]=new Scene("作业",20,"花费20分钟完成作业");
    }
}
