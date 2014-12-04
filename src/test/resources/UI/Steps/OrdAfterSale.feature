Feature: 生成售后申请单
  Background: 清空原有订单信息
    Given 清空原有订单信息

    @UI
  Scenario: UI未审核网上订单
    Given 	生成网上订单
    And     跳转到网上订单管理页面并查询
    When 	生成售后申请单
    When 	填写售后申请单
    And     UI提示 添加成功
    Then 	售后申请单处理
    And 	UI提示 处理成功
    Then    退款确认
    And 	UI提示 退款确认成功

  @UI
  Scenario: UI已审核网上订单
    Given 	生成网上订单
    And     审核订单
    And     跳转到网上订单管理页面并查询
    When 	生成售后申请单
    When 	填写售后申请单
    And     UI提示 添加成功
    Then 	售后申请单处理
    And 	UI提示 处理成功
    Then    退款确认
    And 	UI提示 退款确认成功

  @UI
  Scenario: UI已打印网上订单
    Given 	生成网上订单
    When    操作订单 审核
    And     操作订单 绑定快递单
    And     操作订单 打印快递单
    And     操作订单 打印订单
    And     跳转到网上订单管理页面并查询
    When 	生成售后申请单
    When 	填写售后申请单
    And     UI提示 添加成功
    Then 	售后申请单处理
    And 	UI提示 处理成功
    Then    退款确认
    And 	UI提示 退款确认成功

  @UI
  Scenario: UI已发货网上订单
    Given 	生成网上订单
    When    操作订单 审核
    And     操作订单 绑定快递单
    And     操作订单 打印快递单
    And     操作订单 打印订单
    And     操作订单 扫描出库
    And     跳转到网上订单管理页面并查询
    When 	生成售后申请单
    When 	填写售后申请单
    And     UI提示 添加成功
    Then 	售后申请单处理
    And 	UI提示 处理成功
    Then    退款确认
    And 	UI提示 退款确认成功