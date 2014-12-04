Feature: 锁定订单
  Background: 清空原有订单信息
    Given 清空原有订单信息

  Scenario: 普通网上订单
    Given 生成网上订单
    When 锁定订单
    Then 检验数据提示 锁定成功
    And 订单已锁定

  Scenario: 无效网上订单
    Given 生成网上订单
    When 操作订单 无效
    And 锁定订单
    Then 检验数据提示 订单无法操作
    And 订单未锁定

  Scenario: 已锁定
    Given 生成网上订单
    When 操作订单 锁定
    And 锁定订单
    Then 检验数据提示 不能重复挂起
    And 订单已锁定

  Scenario: 已锁定解锁
    Given 生成网上订单
    When 操作订单 锁定
    And 操作订单 解锁
    And 锁定订单
    Then 检验数据提示 锁定成功
    And 订单已锁定

  Scenario: 已审核
    Given 生成网上订单
    When 操作订单 审核
    And 锁定订单
    Then 检验数据提示 已审核订单无法锁定
    And 订单未锁定

  Scenario: 已审核弃审
    Given 生成网上订单
    When 操作订单 审核
    And 操作订单 弃审
    And 锁定订单
    Then 检验数据提示 锁定成功
    And 订单已锁定

  Scenario: 已发货订单
    Given 生成网上订单
    When 操作订单 审核
    And 操作订单 绑定快递单
    And 操作订单 打印快递单
    And 操作订单 打印订单
    And 操作订单 扫描出库
    And 锁定订单
    Then 检验数据提示 已审核订单无法锁定
    And 订单未锁定

  Scenario: 批量锁定三个订单
    Given 生成网上订单
    And 生成网上订单
    And 生成网上订单
    When 批量锁定订单
    Then 批量提示
      | Tips			|
      | 锁定成功		|
      | 锁定成功		|
      | 锁定成功		|
    And 批量订单锁定结果
      | Results |
      | True    |
      | True	  |
      | True    |

  Scenario: 批量锁定订单中包含已锁定订单
    Given 生成网上订单
    And 生成网上订单
    When 操作订单 锁定
    Given 生成网上订单
    When 批量锁定订单
    Then 批量提示
      | Tips			|
      | 锁定成功		|
      | 不能重复挂起	|
      | 锁定成功		|
    And 批量订单锁定结果
      | Results |
      | True    |
      | True	  |
      | True    |

  Scenario: 批量锁定订单中包含已发货订单
    Given 生成网上订单
    And 生成网上订单
    When 操作订单 审核
    And 操作订单 绑定快递单
    And 操作订单 打印快递单
    And 操作订单 打印订单
    And 操作订单 扫描出库
    Given 生成网上订单
    When 批量锁定订单
    Then 批量提示
      | Tips			|
      | 锁定成功		|
      | 已审核订单无法锁定	|
      | 锁定成功		|
    And 批量订单锁定结果
      | Results |
      | True    |
      | False	  |
      | True    |


  Scenario: 已加入采购看板订单
    Given 生成网上订单
    When  操作订单 加入采购看板
    And   锁定订单
    Then  检验数据提示 锁定成功
    And   订单已锁定

  Scenario: 取消采购看板订单
    Given 生成指定网上订单 auto004400000007,1
    When  操作订单 加入采购看板
    And   操作订单 取消采购看板
    And   锁定订单
    Then  检验数据提示 不能重复挂起
    And   订单已锁定