Feature: 删除网上订单
  Background: 清空原有订单信息
    Given 清空原有订单信息

  Scenario: 单条未审核订单
    Given 	生成网上订单
    And     删除网上订单
    Then 	检验数据提示 删除成功
    And 	删除网上订单成功

  Scenario: 10条未审核订单
    Given 	生成网上订单
    Given 	生成网上订单
    Given 	生成网上订单
    Given 	生成网上订单
    Given 	生成网上订单
    Given 	生成网上订单
    Given 	生成网上订单
    Given 	生成网上订单
    Given 	生成网上订单
    Given 	生成网上订单
    And     删除网上订单
    Then 	检验数据提示 删除成功
    And 	删除网上订单成功

  Scenario: 单条已审核订单
    Given 	生成网上订单
    When 	审核订单
    And     删除网上订单
    Then 	检验数据提示 已审核订单无法删除
    And 	删除网上订单失败

  Scenario: 10条已审核订单
    Given 	生成网上订单
    When 	审核订单
    When 	审核订单
    Given 	生成网上订单
    When 	审核订单
    Given 	生成网上订单
    When 	审核订单
    Given 	生成网上订单
    When 	审核订单
    Given 	生成网上订单
    When 	审核订单
    Given 	生成网上订单
    When 	审核订单
    Given 	生成网上订单
    When 	审核订单
    Given 	生成网上订单
    When 	审核订单
    Given 	生成网上订单
    When 	审核订单
    Given 	生成网上订单
    When 	审核订单
    Given 	生成网上订单
    When 	审核订单
    Given 	生成网上订单
    When 	审核订单
    And     删除网上订单
    Then 	检验数据提示 已审核订单无法删除
    And 	删除网上订单失败

  Scenario: 单条已合并未审核订单
    Given 	生成网上订单
    Given 	生成网上订单
    When 	合并订单
    And     删除合并后的网上订单
    Then 	检验数据提示 删除成功
    And 	删除合并后的网上订单成功
    And     校验合并前的原订单未删除


  Scenario: 3条已合并未审核订单
    Given 	生成网上订单
    Given 	生成网上订单
    When 	合并订单
    Given 	生成网上订单
    Given 	生成网上订单
    When 	合并订单
    Given 	生成网上订单
    Given 	生成网上订单
    When 	合并订单
    And    删除合并后的网上订单
    Then 	检验数据提示 删除成功
    And 	删除合并后的网上订单成功
    And    校验合并前的原订单未删除

  Scenario: 单条已合并已审核订单
    Given 	生成网上订单
    Given 	生成网上订单
    When 	合并订单
    And     审核合并后的订单
    And     删除合并后的网上订单
    Then 	检验数据提示 已审核订单无法删除
    And 	删除合并后的网上订单失败

  Scenario: 单条部分已拆分未审核订单
    Given   生成指定网上订单 auto000100000001,2|auto000100000002,1
    When    拆分订单
    And     删除拆分后的网上订单
    Then 	检验数据提示 成功
    And 	删除拆分后的网上订单成功


  Scenario: 单条已打印订单
    Given 	生成网上订单
    And     操作订单 审核
    And     操作订单 绑定快递单
    And     操作订单 打印快递单
    And     操作订单 打印订单
    And    删除网上订单
    Then 	检验数据提示 已审核订单无法删除
    And 	删除网上订单失败


  Scenario: 单条已发货订单
    Given 	生成网上订单
    And     操作订单 审核
    And     操作订单 绑定快递单
    And     操作订单 打印快递单
    And     操作订单 打印订单
    And     操作订单 扫描出库
    And     删除网上订单
    Then 	检验数据提示 已审核订单无法删除
    And 	删除网上订单失败
  Scenario: 已加入采购看板并取消加入采购看板的订单
    Given   生成指定网上订单 auto004400000007,1
    And     操作订单 加入采购看板
    And     操作订单 取消采购看板
    And     删除网上订单
    Then 	检验数据提示 成功
    And 	删除网上订单成功

  Scenario: 已加入采购看板订单
    Given   生成指定网上订单 auto004400000007,1
    And     操作订单 加入采购看板
    And     删除网上订单
    Then 	检验数据提示 删除成功
    And 	删除网上订单成功



