Feature: 删除平台订单
  Background: 清空原有订单信息
    Given 清空原有订单信息

  @UI
  Scenario: UI删除B2B一条未支付平台订单
    Given   生成B2B平台订单
    Given   跳转到B2B订单管理页面
    And     跳转到平台订单列表并查询
    When    UI删除订单
    And     UI提示 删除成功

  Scenario: 删除B2B一条未支付平台订单
    Given 	生成B2B平台订单
    When 	删除订单
    And 	平台订单已删除

  Scenario: 删除B2C一条未支付平台订单
    Given 	生成B2C平台订单
    When 	删除订单
    And 	平台订单已删除


  Scenario: 删除一条B2B已支付平台订单
    Given 	生成B2B平台订单
    When 	操作订单 批量支付订单
    And 	删除订单
    Then    检验数据提示 删除发生错误:订单已转单成功,不允许删除!
    And 	平台订单未删除

  Scenario: 删除一条已转单锁定的平台订单-库存不足的订单
    Given 	生成指定网上订单 auto004400000007,1
    And 	删除订单
    And 	平台订单未删除

  Scenario: 删除B2B一条已转单平台订单-库存不足的订单
    Given 	生成指定B2B平台订单 auto004400000007,1
    When 	操作订单 批量支付
    And 	删除订单
    And 	平台订单未删除

  Scenario: 删除一条已转单已审核平台订单
    Given 	生成网上订单
    And     操作订单 审核
    And 	删除订单
    And 	平台订单未删除


  Scenario: 删除一条已发货的平台订单
    Given 	生成平台订单
    When 	操作订单 转单
    And 	操作订单 审核
    And 	操作订单 绑定快递单
    And 	操作订单 打印发货单
    And 	操作订单 打印快递单
    And 	操作订单 扫描出库
    And 	删除订单
    And 	平台订单未删除



  Scenario: 删除一条未支付的作废订单
    Given 	生成B2B平台订单
    And     操作订单 作废
    When 	删除订单
    And 	平台订单已删除


  Scenario: 删除一条已支付的作废订单
    Given 	生成B2B平台订单
    And     操作订单 支付
    And     操作订单 作废
    When 	删除订单
    And 	平台订单已删除



  Scenario: 删除一条已转单锁定的作废订单-库存不足
    Given 	生成指定网上订单 auto004400000007,1
    And     操作订单 作废
    And 	删除订单
    And 	平台订单已删除



  Scenario: 删除一条已转单已审核的作废订单-库存足
    Given 	生成网上订单
    And     操作订单 审核
    And     操作订单 作废
    And 	删除订单
    And 	平台订单已删除




  Scenario: 删除多条未支付平台订单
    Given 	生成B2B平台订单
    Given 	生成B2B平台订单
    Given 	生成B2B平台订单
    Given 	生成B2B平台订单
    Given 	生成B2B平台订单
    When 	删除订单
    And 	平台订单已删除



  Scenario: 删除多条已支付平台订单
    Given 	生成B2B平台订单
    And     操作订单 支付
    Given 	生成B2B平台订单
    And     操作订单 支付
    Given 	生成B2B平台订单
    And     操作订单 支付
    Given 	生成B2B平台订单
    And     操作订单 支付
    When 	删除订单
    And 	平台订单未删除


  Scenario: 删除多条B2C已支付平台订单
    Given 	生成B2C平台订单
    And     操作订单 支付
    Given 	生成B2C平台订单
    And     操作订单 支付
    Given 	生成B2C平台订单
    And     操作订单 支付
    And 	删除订单
    And 	平台订单未删除

