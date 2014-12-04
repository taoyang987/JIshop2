Feature: 合并订单
  Background: 清空原有订单信息
    Given 清空原有订单信息

  Scenario: 普通网上订单
    Given 生成网上订单
    When 合并订单
    Then 检验数据提示 合并订单至少2张订单以上

  Scenario: 合并两个订单-相同商品
    Given 生成网上订单
    And 生成网上订单
    When 合并订单
    Then 提示订单号
    And 订单已合并

  Scenario: 合并两个订单-不同商品
    Given 生成网上订单
    And 生成网上订单
    When 合并订单
    Then 提示订单号
    And 订单已合并


  Scenario: 合并10个订单
    Given 生成网上订单
    And 生成网上订单
    And 生成网上订单
    And 生成网上订单
    And 生成网上订单
    And 生成网上订单
    And 生成网上订单
    And 生成网上订单
    And 生成网上订单
    And 生成网上订单
    When 合并订单
    Then 提示订单号
    And 订单已合并

  Scenario: 合并订单中包含已审核
    Given 生成网上订单
    And 生成网上订单
    When 操作订单 审核
    Given 生成网上订单
    When 合并订单
    Then 订单合并结果
      | Results | Tip       |
      |         |           |
      | False   | 已审核,无法合并 |
      |         |           |

  Scenario: 合并订单中包含无效订单
    Given 生成网上订单
    And 生成网上订单
    When 操作订单 无效
    Given 生成网上订单
    When 合并订单
    Then 订单合并结果
      | Results | Tip       |
      |         |           |
      | False   | 无法操作,无法合并 |
      |         |           |

  Scenario: 合并订单中包含已发货订单
    Given 生成网上订单
    And 生成网上订单
    When 操作订单 审核
    And 操作订单 绑定快递单
    And 操作订单 打印快递单
    And 操作订单 打印订单
    And 操作订单 扫描出库
    Given 生成网上订单
    When 合并订单
    Then 订单合并结果
      | Results | Tip       |
      |         |           |
      | False   | 已发货,无法合并 |
      |         |           |

  Scenario: 不同联系人订单合并
    Given 生成网上订单
    And 生成不同信息订单 不同联系人
    And 生成网上订单
    When 合并订单
    Then 订单合并结果
      | Results | Tip       |
      |         |           |
      | False   | 联系人不同无法合并 |
      |         |           |

  Scenario: 不同联系电话订单合并
    Given 生成网上订单
    And 生成不同信息订单 不同联系电话
    And 生成网上订单
    When 合并订单
    Then 订单合并结果
      | Results | Tip       |
      |         |           |
      | False   | 联系电话不同无法合并 |
      |         |           |

  Scenario: 不同手机订单合并
    Given 生成网上订单
    And 生成不同信息订单 不同手机
    And 生成网上订单
    When 合并订单
    Then 订单合并结果
      | Results | Tip       |
      |         |           |
      | False   | 手机不同无法合并 |
      |         |           |

  Scenario: 不同发货地址订单合并
    Given 生成网上订单
    And 生成不同信息订单 不同发货地址
    And 生成网上订单
    When 合并订单
    Then 订单合并结果
      | Results | Tip       |
      |         |           |
      | False   | 发货地址不同无法合并 |
      |         |           |

  Scenario: 不同付款方式订单合并
    Given 生成网上订单
    And 生成不同信息订单 不同付款方式
    And 生成网上订单
    When 合并订单
    Then 订单合并结果
      | Results | Tip       |
      |         |           |
      | False   | 付款方式不同无法合并 |
      |         |           |




  Scenario: 同一仓库不同货架订单合并
    Given 生成网上订单
    And 生成不同信息订单 不同货架
    And 生成网上订单
    When 合并订单
    Then 订单已合并
