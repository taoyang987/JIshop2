Feature: 拆分订单
  Background: 清空原有订单信息
    Given 清空原有订单信息

  Scenario: 一个商品订单拆分
    Given 生成网上订单
    When 拆分订单
    Then 检验数据提示 拆分明细数量有误

  Scenario: 二个相同商品订单拆分
    Given 生成指定网上订单 auto000100000001,2
    When 拆分订单
    Then 检验数据提示 拆分明细数量有误

  Scenario: 二种相同商品订单拆分
    Given 生成指定网上订单 auto000100000001,2|auto000100000002,1
    When 拆分订单
    Then 拆分成功

  Scenario: 二种相同商品订单拆分到第二个订单中
    Given 生成指定网上订单 auto000100000001,2|auto000100000002,1
    When 指定拆分主订单 2
    Then 拆分成功



  Scenario: 已审核订单拆分
    Given 生成指定网上订单 auto000100000001,2|auto000100000002,1
    When 操作订单 审核
    And  拆分订单
    Then 检验数据提示 已审核订单无法修改

  Scenario: 无效订单拆分
    Given 生成指定网上订单 auto000100000001,2|auto000100000002,1
    When 操作订单 无效
    And  拆分订单
    Then 拆分成功

  Scenario: 拆分过的订单拆分
    Given 生成指定网上订单 auto000100000001,2|auto000100000002,1
    When 拆分订单
    And  拆分订单
    Then 检验数据提示 该订单已经拆分过或者当前状态不可拆分

  Scenario: 合并过的订单拆分
    Given 生成网上订单
    And 生成指定网上订单 auto000100000001,2|auto000100000002,1
    When 合并订单
    And  拆分已合并订单
    Then 检验数据提示 该订单由拆分或合并而来,无法继续拆分


  Scenario: 已打印订单拆分
    Given 生成指定网上订单 auto000100000001,2|auto000100000002,1
    When 操作订单 审核
    When 操作订单 打印订单
    And  拆分订单
    Then 检验数据提示 已审核订单无法修改

  Scenario: 已发货订单拆分
    Given 生成指定网上订单 auto000100000001,2|auto000100000002,1
    And 	操作订单 审核
    And 	操作订单 绑定快递单
    And 	操作订单 打印发货单
    And 	操作订单 打印快递单
    And 	操作订单 扫描出库
    And     拆分订单
    Then 检验数据提示 已审核订单无法修改

  Scenario: 已加入采购看板订单拆分
    Given 生成指定网上订单 auto004400000007,2|auto003300000000,1
    When 操作订单 加入采购看板
    And  拆分订单
    Then 拆分成功


  Scenario: 取消采购看板订单拆分
    Given 生成指定网上订单 auto004400000007,2|auto003300000000,1
    When  操作订单 加入采购看板
    And   操作订单 取消采购看板
    And   拆分订单
    Then  拆分成功