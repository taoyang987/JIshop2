Feature: 修改发货方式
  Background: 清空原有订单信息
    Given 清空原有订单信息


  Scenario: 自营自发订单修改为自营自发订单
    Given 	生成网上订单
    When 	修改设为自发订单
    Then 	检验数据提示 设置成功
    And 	校验已被设为自发
#    And    校验 网上订单列表有该订单

  Scenario: 自营自发订单修改为自营代发订单
    Given 	生成网上订单
    When 	修改设为代发订单
    Then 	检验数据提示 设置成功
    And 	校验已被设为代发
#    And     校验 跳转到代销订单列表检查存在该订单

  Scenario: 自营代发订单修改为自营代发订单
    Given 	生成网上订单
    When 	修改设为代发订单
    When 	修改设为代发订单
    Then 	检验数据提示 设置成功
    And 	校验已被设为代发


  Scenario: 代销自发订单修改为代销自发订单
    Given 	生成指定网上订单 auto010100000006,1
    And     操作订单 弃审
    When 	修改设为自发订单
    When 	修改设为自发订单
    Then 	检验数据提示 设置成功
    And 	校验已被设为自发

  Scenario: 代销自发订单修改为代销代发订单
    Given 	生成指定网上订单 auto010100000006,1
    And     操作订单 弃审
    When 	修改设为自发订单
    When 	修改设为代发订单
    Then 	检验数据提示 设置成功
    And 	校验已被设为代发

  Scenario: 代销代发订单修改为代销代发订单
    Given 	生成指定网上订单 auto010100000006,1
    And     操作订单 弃审
    When 	修改设为代发订单
    Then 	检验数据提示 设置成功
    And 	校验已被设为代发

   @UI
   Scenario: UI订单自发代发修改
     Given 	生成网上订单
     And    跳转到网上订单管理页面并查询
     When   UI修改为代发订单
     Then    UI提示 设置成功
     And 	校验已被设为代发
     When   UI修改为自发订单
     Then    UI提示 设置成功
     And 	校验已被设为自发
