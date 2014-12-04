Feature: 修改仓库

  Background: 清空原有订单信息
    Given 清空原有订单信息

  Scenario: 没设置默认库位
    Given    生成网上订单
    When    修改仓库 house04
    Then    检验数据提示 成功
    And    检验仓库信息
      | 仓库编码    | 货架 | 供应商 | 库位 |
      | house04 |    |     |    |

  Scenario: 没设置默认货架
    Given    生成指定网上订单 auto000100000002,1
    When    修改仓库 auto
    Then    检验数据提示 成功
    And    检验仓库信息
      | 仓库编码 | 货架 | 供应商 | 库位     |
      | auto |    |     | auto01 |

  Scenario: 没设置货架对应的供应商
    Given    生成指定网上订单 auto000100000003,1
    When    修改仓库 auto
    Then    检验数据提示 成功
    And    检验仓库信息
      | 仓库编码 | 货架     | 供应商 | 库位     |
      | auto | auto02 |     | auto01 |


  Scenario: 同一商品一个仓库-正常设置了仓库、库位、货架、供应商
    Given    生成指定网上订单 auto000100000001,1
    When    修改仓库 auto
    Then    检验数据提示 成功
    And    检验仓库信息
      | 仓库编码 | 货架     | 供应商 | 库位     |
      | auto | auto01 |   S0001  | auto01 |

  Scenario:同一商品两个仓库-正常设置了仓库、库位、货架、供应商
    Given    生成指定网上订单 auto000100000004,1
    When    修改仓库 auto01
    Then    检验数据提示 成功
    And    检验仓库信息
      | 仓库编码 | 货架     | 供应商 | 库位     |
      | auto01 | HJHZ001 |   S0001  | auto0101 |

#多个商品需要设置特殊货架
  Scenario:多个商品同一仓库同一库位-正常设置了仓库、库位、货架、供应商
    Given    生成指定网上订单 auto000100000001,1|auto000100000004,1
    When    修改仓库 auto
    Then    检验数据提示 成功
    And    检验仓库信息
      | 仓库编码 | 货架     | 供应商 | 库位     |
      | auto | auto01 |   S0001  | auto01 |


  Scenario:多个商品同一仓库不同库位-正常设置了仓库、库位、货架、供应商
    Given    生成指定网上订单 auto000100000001,1|auto005500000007,1
    When    修改仓库 auto
    Then    检验数据提示 成功
    And    检验仓库信息
      | 仓库编码 | 货架     | 供应商 | 库位     |
      | auto | auto01 |   S0001  | auto01 |


  Scenario: 未审核的网上订单
    Given    生成指定网上订单 auto000100000004,1
    When    修改仓库 auto01
    Then    检验数据提示 成功
    And    检验仓库信息
      | 仓库编码 | 货架     | 供应商 | 库位     |
      | auto01 | HJHZ001 |   S0001  | auto0101 |


  Scenario: 已审核的网上订单
    Given    生成指定网上订单 auto000100000001,1
    And     审核订单
    When    修改仓库 auto
    Then    检验数据提示 已审核订单无法修改


  Scenario: 弃审后的网上订单
    Given    生成指定网上订单 auto000100000004,1
    And     审核订单
    And     弃审订单
    When    修改仓库 auto01
    Then    检验数据提示 成功
    And    检验仓库信息
      | 仓库编码 | 货架     | 供应商 | 库位     |
      | auto01 | HJHZ001 |   S0001  | auto0101 |

  Scenario: 已打印的网上订单
    Given    生成网上订单
    And     审核订单
    And     操作订单 绑定快递单
    And     操作订单 打印快递单
    And     操作订单 打印订单
    When    修改仓库 auto01
    Then    检验数据提示 已审核订单无法修改

  Scenario: 已全部发货的网上订单
    Given    生成网上订单
    And     审核订单
    And     操作订单 绑定快递单
    And     操作订单 打印快递单
    And     操作订单 打印订单
    And     操作订单 扫描出库
    When    修改仓库 auto01
    Then    检验数据提示 已审核订单无法修改


  Scenario: 已加入采购看板的网上订单
    Given    生成网上订单
    And     操作订单 加入采购看板
    When    修改仓库 auto01
    Then    检验数据提示 成功

  Scenario: 取消采购看板的网上订单
    Given 生成指定网上订单 auto004400000007,1
    And     操作订单 加入采购看板
    And     操作订单 取消采购看板
    When    修改仓库 auto01


  Scenario: 已锁定的网上订单
    Given    生成网上订单
    When    操作订单 锁定
    When    修改仓库 auto01
    Then    检验数据提示 成功

  Scenario: 解锁后未审核的网上订单
    Given    生成网上订单
    When    操作订单 锁定
    When    操作订单 解锁
    When    修改仓库 auto01
    Then    检验数据提示 成功


  Scenario: 解锁后已审核的网上订单
    Given    生成网上订单
    When    操作订单 锁定
    When    操作订单 解锁
    When    操作订单 审核
    When    修改仓库 auto01
    Then    检验数据提示 已审核订单无法修改

    @UI
  Scenario: UI修改仓库
    Given    生成网上订单
    And    跳转到网上订单管理页面并查询
    When    UI修改仓库 自动化仓库杭州
    When    UI提示 成功
