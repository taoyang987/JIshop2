Feature: 修改货架
  Background: 清空原有订单信息
    Given 清空原有订单信息

  Scenario: 同一商品同仓库没设置货架
    Given 	生成指定网上订单 auto000100000002,1
    When    修改货架 auto01
    Then 	检验数据提示 并不存放在指定商品

  Scenario: 同一商品同仓库同货架
    Given 	生成网上订单
    When    修改货架 auto01
    Then 	检验数据提示 修改成功
    And    检验仓库信息
      | 仓库编码 | 货架     | 供应商 | 库位     |
      | auto | auto01 |   S0001  | auto01 |

  Scenario: 同一商品同仓库不同货架
    Given 	生成网上订单
    When    修改货架 auto02
    Then 	检验数据提示 并不存放在指定商品

  Scenario: 同一商品不同仓库不同货架
    Given 	生成网上订单
    When    修改货架 HJHZ001
    Then 	检验数据提示 并不存放在指定商品


#
#  Scenario: 不同商品不同仓库不同货架
#    Given 	生成指定网上订单 auto000100000001,1|auto006600000001,1
#    When 	修改第一个商品货架 auto01
#    Then 	检验数据提示 修改成功
#
#  Scenario: 不同商品同仓库同货架
#    Given 	生成网上订单
#    When 	操作 修改仓库
#    Then 	检验数据提示 修改成功
#    And 	检验 仓库名称，仓库编码、货架、供应商、商品的默认库位
#
#  Scenario: 不同商品不同仓库不同货架
#    Given 	生成网上订单
#    When 	操作 修改仓库
#    Then 	检验数据提示 修改成功
#    And 	检验 仓库名称，仓库编码、货架、供应商、商品的默认库位



  Scenario: 未审核的网上订单
    Given 	生成网上订单
    When    修改货架 auto01
    Then 	检验数据提示 修改成功
    And    检验仓库信息
      | 仓库编码 | 货架     | 供应商 | 库位     |
      | auto | auto01 |   S0001  | auto01 |


  Scenario: 已审核的网上订单
    Given 	生成网上订单
    And     审核订单
    When    修改货架 auto01
    Then 	检验数据提示 修改成功
    And    检验仓库信息
      | 仓库编码 | 货架     | 供应商 | 库位     |
      | auto | auto01 |   S0001  | auto01 |

  Scenario: 弃审后的网上订单
    Given 	生成网上订单
    And     审核订单
    And     弃审订单
    When    修改货架 auto01
    Then 	检验数据提示 修改成功
    And    检验仓库信息
      | 仓库编码 | 货架     | 供应商 | 库位     |
      | auto | auto01 |   S0001  | auto01 |

  Scenario: 已打印的网上订单
    Given 	生成网上订单
    And     审核订单
    And     操作订单 绑定快递单
    And     操作订单 打印快递单
    And     操作订单 打印订单
    When    修改货架 auto01
    Then 	检验数据提示 修改成功
    And    检验仓库信息
      | 仓库编码 | 货架     | 供应商 | 库位     |
      | auto | auto01 |   S0001  | auto01 |

  Scenario: 已全部发货的网上订单
    Given    生成网上订单
    And     审核订单
    And     操作订单 绑定快递单
    And     操作订单 打印快递单
    And     操作订单 打印订单
    And     操作订单 扫描出库
    When    修改货架 auto01
    Then 	检验数据提示 修改成功
    And    检验仓库信息
      | 仓库编码 | 货架     | 供应商 | 库位     |
      | auto | auto01 |   S0001  | auto01 |

  Scenario: 已加入采购看板的网上订单
    Given    生成网上订单
    And     操作订单 加入采购看板
    When    修改货架 auto01
    Then 	检验数据提示 修改成功
    And    检验仓库信息
      | 仓库编码 | 货架     | 供应商 | 库位     |
      | auto | auto01 |   S0001  | auto01 |

  Scenario: 取消采购看板的网上订单
    Given 生成指定网上订单 auto004400000007,1
    And     操作订单 加入采购看板
    And     操作订单 取消采购看板
    When    修改货架 auto01
    Then 	检验数据提示 修改成功
    And    检验仓库信息
      | 仓库编码 | 货架     | 供应商 | 库位     |
      | auto | auto01 |   S0001  | auto01 |


  Scenario: 已锁定的网上订单
    Given    生成网上订单
    And     操作订单  锁定
    When    修改货架 auto01
    And    检验仓库信息
      | 仓库编码 | 货架     | 供应商 | 库位     |
      | auto | auto01 |   S0001  | auto01 |

  Scenario: 解锁后未审核的网上订单
    Given    生成网上订单
    And     操作订单  锁定
    And     操作订单  解锁
    When    修改货架 auto01
    And    检验仓库信息
      | 仓库编码 | 货架     | 供应商 | 库位     |
      | auto | auto01 |   S0001  | auto01 |


  Scenario: 解锁后已审核的网上订单
    Given    生成网上订单
    And     操作订单 锁定
    And     操作订单 解锁
    And     审核订单
    When    修改货架 auto01
    And    检验仓库信息
      | 仓库编码 | 货架     | 供应商 | 库位     |
      | auto | auto01 |   S0001  | auto01 |
