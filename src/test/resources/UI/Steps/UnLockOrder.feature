Feature: 解锁订单
  Background: 清空原有订单信息
    Given 清空原有订单信息

  Scenario: 普通网上订单
    Given 生成网上订单
    When 解锁订单
    Then 检验数据提示 该订单尚未挂起
    And 订单未锁定

  Scenario: 无效网上订单
    Given 生成网上订单
    When 操作订单 无效
    And 解锁订单
    Then 检验数据提示 订单无法操作
    And 订单未锁定

  Scenario: 已审核订单
    Given 生成网上订单
    When 操作订单 审核
    And 解锁订单
    Then 检验数据提示 该订单尚未挂起
    And 订单未锁定

  Scenario: 已审核弃审订单
    Given 生成网上订单
    When 操作订单 审核
    When 操作订单 弃审
    And 解锁订单
    Then 检验数据提示 该订单尚未挂起
    And 订单未锁定

  Scenario: 已锁定网上订单
    Given 生成网上订单
    When 操作订单 锁定
    And 解锁订单
    Then 检验数据提示 解锁成功
    And 订单未锁定

  Scenario: 锁定解锁网上订单
    Given 生成网上订单
    When 操作订单 锁定
    And 操作订单 解锁
    And 解锁订单
    Then 检验数据提示 该订单尚未挂起
    And 订单未锁定

  Scenario: 锁定解锁锁定网上订单
    Given 生成网上订单
    When 操作订单 锁定
    And 操作订单 解锁
    And 操作订单 锁定
    And 解锁订单
    Then 检验数据提示 解锁成功
    And 订单未锁定

  Scenario: 已发货网上订单
    Given 生成网上订单
    When 操作订单 审核
    And 操作订单 绑定快递单
    And 操作订单 打印快递单
    And 操作订单 打印订单
    And 操作订单 扫描出库
    And 解锁订单
    Then 检验数据提示 订单已发货
    And 订单未锁定

  Scenario: 批量解锁三个网上订单
    Given 生成网上订单
    When 操作订单 锁定
    Given 生成网上订单
    When 操作订单 锁定
    Given 生成网上订单
    When 操作订单 锁定
    And 批量解锁订单
    Then 批量提示
      | Tips			|
      | 解锁成功		|
      | 解锁成功		|
      | 解锁成功		|
    And 批量订单锁定结果
      | Results |
      | False    |
      | False	   |
      | False    |

  Scenario: 批量解锁订单中包含未锁定订单
    Given 生成网上订单
    When 操作订单 锁定
    Given 生成网上订单
    Given 生成网上订单
    When 操作订单 锁定
    And 批量解锁订单
    Then 批量提示
      | Tips			|
      | 解锁成功		|
      | 该订单尚未挂起|
      | 解锁成功		|
    And 批量订单锁定结果
      | Results  |
      | False    |
      | False	   |
      | False    |

  Scenario: 批量解锁订单中包含已发货订单
    Given 生成网上订单
    When 操作订单 解锁
    Given 生成网上订单
    When 操作订单 审核
    And 操作订单 绑定快递单
    And 操作订单 打印快递单
    And 操作订单 打印订单
    And 操作订单 扫描出库
    Given 生成网上订单
    When 操作订单 解锁
    And 批量解锁订单
    Then 批量提示
      | Tips			|
      | 该订单尚未挂起		|
      | 订单已发货	|
      | 该订单尚未挂起		|
    And 批量订单锁定结果
      | Results |
      | False    |
      | False	   |
      | False    |

  Scenario: 仓库未设置
    Given 生成指定网上订单 auto009900000009,1
    When 解锁订单
    Then 检验数据提示 仓库未设置无法解锁
    And 订单已锁定

  Scenario: 部分仓库未设置
    Given 生成指定网上订单 auto009900000009,1|auto000100000001,1
    When 解锁订单
    Then 检验数据提示 仓库未设置无法解锁
    And 订单已锁定

  Scenario: 库存不足
    Given 生成指定网上订单 auto004400000007,1
    When 解锁订单
    Then 检验数据提示 第1条物料[auto0044|00000007]需要数量1,可用库存[0],不足无法解锁
    And 订单已锁定

  Scenario: 部分库存不足
    Given 生成指定网上订单 auto000100000001,1|auto004400000007,1
    When 解锁订单
    Then 检验数据提示 第2条物料[auto0044|00000007]需要数量1,可用库存[0],不足无法解锁
    And 订单已锁定

  Scenario: 批量解锁订单中包含库存不足
    Given 生成网上订单
    When 操作订单 锁定
    Given 生成指定网上订单 auto004400000007,1
    Given 生成网上订单
    When 操作订单 锁定
    And 批量解锁订单
    Then 批量提示
      | Tips			|
      | 解锁成功		|
      | 第1条物料[auto0044\|00000007]需要数量1,可用库存[0],不足无法解锁|
      | 解锁成功		|
    And 批量订单锁定结果
      | Results |
      | False    |
      | True	   |
      | False    |

  Scenario: 批量解锁订单中包含仓库未设置
    Given 生成网上订单
    When 操作订单 锁定
    Given 生成指定网上订单 auto009900000009,1
    Given 生成网上订单
    When 操作订单 锁定
    And 批量解锁订单
    Then 批量提示
      | Tips			|
      | 解锁成功		|
      | 仓库未设置无法解锁|
      | 解锁成功		|
    And 批量订单锁定结果
      | Results |
      | False    |
      | True	   |
      | False    |

  Scenario: 已加入采购看板订单
    Given 生成网上订单
    When  操作订单 加入采购看板
    And   解锁订单
    Then  检验数据提示 该订单尚未挂起
    And   订单未锁定

  Scenario: 取消采购看板订单
    Given 生成指定网上订单 auto004400000007,1
    When  操作订单 加入采购看板
    And   操作订单 取消采购看板
    And   解锁订单
    Then  检验数据提示 第1条物料[auto0044|00000007]需要数量1,可用库存[0],不足无法解锁
    And   订单已锁定