Feature: 审核订单
  Background: 清空原有订单信息
    Given 清空原有订单信息

  Scenario: 普通网上订单
    Given 生成网上订单
    When 审核订单
    Then 检验数据提示 审核成功
    And 订单已审核

  Scenario: 无效网上订单
    Given 生成网上订单
    When 操作订单 无效
    And  审核订单
    Then 检验数据提示 订单无法操作
    And 订单未审核

  Scenario: 已审核订单
    Given 生成网上订单
    When 操作订单 审核
    And 审核订单
    Then 检验数据提示 订单已经审核过
    And 订单已审核

  Scenario: 已弃审订单
    Given 生成网上订单
    When 操作订单 审核
    And 操作订单 弃审
    And 审核订单
    Then 检验数据提示 审核成功
    And 订单已审核

  Scenario: 已审核锁定订单
    Given 生成网上订单
    When 操作订单 审核
    And 操作订单 锁定
    And 审核订单
    Then 检验数据提示 订单已经审核过
    And 订单已审核

  Scenario: 已审核弃审锁定订单
    Given 生成网上订单
    When 操作订单 审核
    And 操作订单 弃审
    And 操作订单 锁定
    And 审核订单
    Then 检验数据提示 锁定订单无法审核
    And 订单未审核

  Scenario: 已审核弃审审核订单
    Given 生成网上订单
    When 操作订单 审核
    And 操作订单 弃审
    And 操作订单 审核
    And 审核订单
    Then 检验数据提示 订单已经审核过
    And 订单已审核

  Scenario: 已锁定订单
    Given 生成网上订单
    When 操作订单 锁定
    And 审核订单
    Then 检验数据提示 锁定订单无法审核
    And 订单未审核

  Scenario: 已锁定解锁订单
    Given 生成网上订单
    When 操作订单 锁定
    And 操作订单 解锁
    And 审核订单
    Then 检验数据提示 审核成功
    And 订单已审核

  Scenario: 已锁定解锁锁定订单
    Given 生成网上订单
    When 操作订单 锁定
    And 操作订单 解锁
    And 操作订单 锁定
    And 审核订单
    Then 检验数据提示 锁定订单无法审核
    And 订单未审核

  Scenario: 仓库不足订单
    Given 生成指定网上订单 auto004400000007,1
    When 审核订单
    Then 检验数据提示 锁定订单无法审核
    And 订单未审核

  Scenario: 部分仓库不足订单
    Given 生成指定网上订单 auto004400000007,1|auto000100000001,1
    When 审核订单
    Then 检验数据提示 锁定订单无法审核
    And 订单未审核

  Scenario: 仓库未设置订单
    Given 生成指定网上订单 auto005500000007,1
    When 审核订单
    Then 检验数据提示 锁定订单无法审核
    And 订单未审核

  Scenario: 部分仓库未设置订单
    Given 生成指定网上订单 auto005500000007,1|auto000100000001,1
    When 审核订单
    Then 检验数据提示 锁定订单无法审核
    And 订单未审核

  Scenario: 已发货订单
    Given 生成网上订单
    When 操作订单 审核
    And 操作订单 绑定快递单
    And 操作订单 打印快递单
    And 操作订单 打印订单
    And 操作订单 扫描出库
    And 审核订单
    Then 检验数据提示 订单已经审核过
    And 订单已审核

  Scenario: 批量审核三个订单
    Given 生成网上订单
    And 生成网上订单
    And 生成网上订单
    When 批量审核订单
    Then 批量提示
      | Tips			|
      | 审核成功		|
      | 审核成功		|
      | 审核成功		|
    And 批量订单审核结果
      | Results |
      | True    |
      | True	  |
      | True    |

  Scenario: 批量审核订单中包含已审核订单
    Given 生成网上订单
    And 生成网上订单
    When 操作订单 审核
    Given 生成网上订单
    When 批量审核订单
    Then 批量提示
      | Tips			|
      | 审核成功		|
      | 订单已经审核过|
      | 审核成功		|
    And 批量订单审核结果
      | Results |
      | True    |
      | True	  |
      | True    |

  Scenario: 批量审核订单中包含已锁定订单
    Given 生成网上订单
    And 生成网上订单
    When 操作订单 锁定
    Given 生成网上订单
    When 批量审核订单
    Then 批量提示
      | Tips			|
      | 审核成功		|
      | 锁定订单无法审核		|
      | 审核成功		|
    And 批量订单审核结果
      | Results |
      | True    |
      | False   |
      | True    |



  Scenario: 已加入采购看板订单
    Given 生成网上订单
    When  操作订单 加入采购看板
    And   审核订单
    Then  检验数据提示 审核成功
    And   订单已审核


  Scenario: 取消采购看板的订单
    Given 生成指定网上订单 auto004400000007,1
    When  操作订单 加入采购看板
    And   操作订单 取消采购看板
    And   审核订单
    Then  检验数据提示 锁定订单无法审核
    And   订单未审核
