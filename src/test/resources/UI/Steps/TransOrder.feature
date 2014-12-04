Feature: 转单功能测试
  Background: 清空原有订单信息
    Given 清空原有订单信息

  @UI
    Scenario: UI转单
    Given   生成平台订单
    Given   跳转到淘宝订单管理页面
    And     跳转到平台订单列表并查询
    When    UI批量转单
    And     UI提示 转单成功

  Scenario: 转单-单条单商品未支付平台订单转单
    Given 	生成B2B平台订单
    When 	转单
    Then 	检验数据提示 商城订单未支付无法转单
    And 	转单失败

  Scenario: 转单-商品未建立映射关系转单
    Given 	生成指定平台订单 auto007700000006,1
    And     转单
    Then 	检验数据提示 找不到相应商品和事物特性,也找不到对应的套装来解析!
    And 	转单失败


  Scenario: 转单-单条单商品已支付平台订单转单-库存不足
    Given 	生成指定平台订单 auto004400000007,1
    Then 	转单
    And 	转单成功


  Scenario: 转单-单条多商品已支付平台订单转单-某个商品库存不足
    Given   生成指定平台订单 auto000100000001,2|auto004400000007,1
    Then 	转单
    And 	转单成功


  Scenario: 转单-商品没设置默认仓库转单
    Given 	生成指定平台订单 auto009900000009,1
    When 	转单
    And 	转单成功
    And     检验仓储信息
   |仓库名称|仓库编码|货架|供应商|
   |       |       |   |     |




  Scenario: 转单-同一仓库不同库位的两商品（商品设置默认仓库）
    Given 	生成指定平台订单 auto000100000001,2|auto005500000007,1
    When 	转单
    And 	转单成功
    And     检验仓储信息
      |仓库名称|仓库编码|货架|供应商|
      |自动化仓库北京|auto| auto01  |  S0001   |



  Scenario: 转单-店铺管理中没设置快递公司
    Given 	生成未设置默认快递平台订单
    When 	转单
    Then    转单成功
    And     检验收货信息中显示的快递公司 null


  Scenario: 转单-店铺管理中已设置默认快递公司
    Given 	生成平台订单
    When    转单
    And     转单成功
    And     检验收货信息中显示的快递公司 自动化北京


  Scenario: 转单-店铺管理中已设置货到付款订单的默认快递公司
    Given 	生成货到付款订单
    When 	转单
    And     转单成功
    And     检验收货信息中显示的快递公司 自动化货到付款


  Scenario: 一键转单-正常
    Given 	生成一键转单店铺平台订单 auto000100000001,1
    Given 	生成一键转单店铺平台订单 auto000100000001,1
    When 	一键转单
    And     一键转单成功


  Scenario: 一键转单-异常（未建立商品映射关系和正常）
    Given 	生成一键转单店铺平台订单 auto000100000001,1
    Given 	生成一键转单店铺平台订单 auto007700000006,1
    And     一键转单
    And 	一键转单结果
    |Results|
    | False|
    | False|
