Feature: 修改采购缺货
  Background: 清空原有订单信息
    Given 清空原有订单信息

  Scenario: 非采购缺货修改为非采购缺货
    Given 	生成网上订单
    When 	修改为非采购缺货
    And 	校验为非采购缺货


  Scenario: 非采购缺货修改为采购缺货
    Given 	生成网上订单
    When 	修改为采购缺货
    And 	校验为采购缺货

  Scenario: 采购缺货修改为非采购缺货
    Given 	生成网上订单
    When 	修改为采购缺货
    When 	修改为非采购缺货
    And 	校验为非采购缺货


  Scenario: 采购缺货修改为采购缺货
    Given 	生成网上订单
    When 	修改为采购缺货
    When 	修改为采购缺货
    And 	校验为采购缺货

  @UI
  Scenario: UI采购缺货修改
    Given 	生成网上订单
    And     跳转到网上订单管理页面并查询
    When 	UI修改为采购缺货
    Then    UI提示 修改成功
    And 	校验为采购缺货
    When 	UI修改为非采购缺货
    Then    UI提示 修改成功
    And 	校验为非采购缺货

