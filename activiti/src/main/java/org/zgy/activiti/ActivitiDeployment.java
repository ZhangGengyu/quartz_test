package org.zgy.activiti;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;

public class ActivitiDeployment {

    // 流程定义部署
    public static void main(String[] args) {
        // 1、创建ProcessEngine对象
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        // 2、得到RespositoryService实例
        RepositoryService repositoryService = processEngine.getRepositoryService();
        // 3、进行部署
        repositoryService.createDeployment()
                .addClasspathResource("")
                .deploy();
    }
}
