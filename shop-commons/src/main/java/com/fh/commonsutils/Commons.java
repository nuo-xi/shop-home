package com.fh.commonsutils;

/**
 * 流程审批盐
 */
public interface Commons {

    /*流程审批使用*/
    String BOOS = "boos";//老板
    String MANAGER_ALL = "allManager";//总经理
    String MANAGER_HEAD = "managerHead";//部门经理
    String EMPLOYEE = "employee";//员工

    Integer APPROVE_SUCCESS=600;//请假成功
    Integer APPROVE_FAILURE=700;//请假失败
    Integer APPROVE_INFOR=500;//请假待定


    Integer SATRT_CODE=100;//初始提交状态
    Integer FIRST_CODE=200;//一级成功状态
    Integer SECOND_CODE=300;//二成功状态
    Integer THIRD_CODE=400;//三级成功

    String SESSION_TIME_OUT="800";//ajax,session失效

}
