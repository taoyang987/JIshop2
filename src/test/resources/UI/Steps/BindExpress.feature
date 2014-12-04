Feature: 绑定快递单
  Background: 清空原有订单信息
    Given 清空原有订单信息
#  批量绑定时，一个未绑定成功，其他全部未成功。

  Scenario: 普通网上订单
    Given 生成网上订单
    When 绑定快递单
    Then 快递单绑定结果
      | Results | Tip       |
      | True   | 绑定成功!  |

  Scenario: 5个订单绑定
    Given 生成网上订单
    And  生成网上订单
    And  生成网上订单
    And  生成网上订单
    And  生成网上订单
    When 绑定快递单
    Then 快递单绑定结果
      | Results | Tip       |
      | True   | 绑定成功!  |


  Scenario: 无快递店铺
    Given 生成指定店铺网上订单 无快递店铺
    When 绑定快递单
    Then 快递单绑定结果
      | Results | Tip       |
      | False   | 的订单还未设置快递公司!<br/>  |

  Scenario: 快递单号错误
    Given 生成网上订单
    When 绑定指定快递单 123
    Then 快递单绑定结果
      | Results | Tip       |
      | False   | 快递单号123不符合自动化北京单号规范!<br/>  |

  Scenario: 批量绑定中包含无快递店铺订单
    Given 生成网上订单
    And  生成指定店铺网上订单 无快递店铺
    And  生成网上订单
    When 绑定快递单
    Then 快递单绑定结果
      | Results | Tip       |
      | 		|				|
      | False | 的订单还未设置快递公司!<br/>  |
      | 		|				|

  Scenario: 批量绑定中包含非法快递单号
    Given 生成网上订单
    And  生成网上订单
    When 绑定指定快递单 123
    Then 快递单绑定结果
      | Results | Tip       |
      | 		|				|
      | False | 快递单号123不符合自动化北京单号规范!<br/>  |
