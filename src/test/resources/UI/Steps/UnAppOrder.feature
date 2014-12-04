Feature: 弃审订单
  Background: 清空原有订单信息
    Given 清空原有订单信息

  Scenario: 无效网上订单
    Given 生成网上订单
    When 操作订单 无效
    And 弃审订单
    Then 检验数据提示 订单无法操作
    And 订单未审核

  Scenario: 普通网上订单
    Given 生成网上订单
    When 弃审订单
    Then 检验数据提示 订单尚未审核
    And 订单未审核

  Scenario: 已审核订单
    Given 生成网上订单
    When 操作订单 审核
    And 弃审订单
    Then 检验数据提示 弃审成功
    And 订单未审核

  Scenario: 已审核弃审订单
    Given 生成网上订单
    When 操作订单 审核
    When 操作订单 弃审
    And 弃审订单
    Then 检验数据提示 订单尚未审核
    And 订单未审核

  Scenario: 锁定网上订单
    Given 生成网上订单
    When 操作订单 锁定
    And 弃审订单
    Then 检验数据提示 订单尚未审核
    And 订单未审核

  Scenario: 锁定解锁网上订单
    Given 生成网上订单
    When 操作订单 锁定
    And 操作订单 解锁
    And 弃审订单
    Then 检验数据提示 订单尚未审核
    And 订单未审核

  Scenario: 已发货网上订单
    Given 生成网上订单
    When 操作订单 审核
    And 操作订单 绑定快递单
    And 操作订单 打印快递单
    And 操作订单 打印订单
    And 操作订单 扫描出库
    And 弃审订单
    Then 检验数据提示 已经发货无法弃审
    And 订单已审核

  Scenario: 批量弃审三个网上订单
    Given 生成网上订单
    When 操作订单 审核
    Given 生成网上订单
    When 操作订单 审核
    Given 生成网上订单
    When 操作订单 审核
    And 批量弃审订单
    Then 批量提示
      | Tips			|
      | 弃审成功		|
      | 弃审成功		|
      | 弃审成功		|
    And 批量订单审核结果
      | Results |
      | False    |
      | False	   |
      | False    |

  Scenario: 批量弃审订单中包含未审核订单
    Given 生成网上订单
    When 操作订单 审核
    Given 生成网上订单
    Given 生成网上订单
    When 操作订单 审核
    And 批量弃审订单
    Then 批量提示
      | Tips			|
      | 弃审成功		|
      | 订单尚未审核	|
      | 弃审成功		|
    And 批量订单审核结果
      | Results |
      | False    |
      | False	   |
      | False    |

  Scenario: 批量弃审订单中包含已发货订单
    Given 生成网上订单
    When 操作订单 审核
    Given 生成网上订单
    When 操作订单 审核
    And 操作订单 绑定快递单
    And 操作订单 打印快递单
    And 操作订单 打印订单
    And 操作订单 扫描出库
    Given 生成网上订单
    When 操作订单 审核
    And 批量弃审订单
    Then 批量提示
      | Tips			|
      | 弃审成功		|
      | 已经发货无法弃审|
      | 弃审成功		|
    And 批量订单审核结果
      | Results |
      | False    |
      | TRUE	   |
      | False    |


  Scenario: 已加入采购看板订单
    Given 生成网上订单
    When  操作订单 加入采购看板
    And   弃审订单
    Then  检验数据提示 订单尚未审核
    And   订单未审核


  Scenario: 取消采购看板订单
    Given 生成指定网上订单 auto004400000007,1
    When  操作订单 加入采购看板
    And   操作订单 取消采购看板
    And   弃审订单
    Then  检验数据提示 订单尚未审核
    And   订单未审核
