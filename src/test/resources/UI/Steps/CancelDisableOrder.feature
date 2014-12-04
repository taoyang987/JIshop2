Feature: 撤销作废平台订单
  Background: 清空原有订单信息
    Given 清空原有订单信息

  @UI
  Scenario: UI普通B2B平台订单
    Given   生成B2B平台订单
    And     作废订单
    Given   跳转到B2B订单管理页面
    And     跳转到平台订单列表并查询
    When    UI撤销作废订单
    And     UI提示 撤销成功

  Scenario: 普通B2B-选择已作废订单操作撤销作废功能测试（单条）
    Given 	生成B2B平台订单
    And     作废订单
    When 	撤销作废订单
    Then 	检验数据提示 撤销成功
    And 	订单未作废
  
  Scenario: 普通淘宝平台订单-选择已作废订单操作撤销作废功能测试（单条）
    Given 	生成平台订单
    And     作废订单
    And 	撤销作废订单
    Then 	检验数据提示 撤销成功
    And 	订单未作废

  Scenario: 普通平台订单-选择已作废订单操作撤销作废功能测试（批量）
    Given 	生成平台订单
    And     作废订单
    Given 	生成平台订单
    And     作废订单
    Given 	生成平台订单
    And     作废订单
    When 	批量撤销作废订单
    Then 	检验数据提示 撤销成功
    And 	订单未作废


  Scenario: 普通平台订单-选择未作废订单操作撤销作废功能测试（单条）
    Given 	生成平台订单
    When 	撤销作废订单
    Then 	检验数据提示 订单撤销作废出错,状态为非作废,不能撤销作废
    And 	订单未作废


  Scenario: 普通平台订单-选择未作废订单操作撤销作废功能测试（批量）
    Given 	生成平台订单
    Given 	生成平台订单
    Given 	生成平台订单
    When 	批量撤销作废订单
    Then 	检验数据提示 订单撤销作废出错,状态为非作废,不能撤销作废
    And 	订单未作废