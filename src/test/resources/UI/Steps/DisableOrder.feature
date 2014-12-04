Feature: 作废订单
  Background: 清空原有订单信息
    Given 清空原有订单信息

  @UI
  Scenario: UI普通B2B平台订单
    Given   跳转到B2B订单管理页面
    When    点新增按钮
    And     在基本信息中录入所有必输项数据
      | 所属店铺    | 联系人 | 联系电话 | 手机号         | 省  | 市   | 区   | 详细地址   |
      | B2B自动化北京店铺 | 陶杨  |      | 13488888888 | 北京 | 北京市 | 东城区 | 北京详细地址 |
    And     在明细信息选择默认商品
    Then    点保存并返回按钮
    And     校验新增成功
    And     跳转到平台订单列表并查询
    Then    UI作废订单
    And     UI提示 作废成功!

  Scenario: 普通平台订单
    Given 生成平台订单
    When 作废订单
    Then 检验数据提示 作废成功!
    And 订单已作废

  Scenario: 普通B2B平台订单
    Given 生成B2B平台订单
    And 操作订单 批量支付
    When 作废订单
    Then 检验数据提示 作废成功!
    And 订单已作废

  Scenario: 已锁定
    Given 生成平台订单
    When 转单
    And 操作订单 锁定
    And 作废订单
    Then 检验数据提示 作废成功!
    And 订单已作废

  Scenario: 已转单
    Given 生成平台订单
    When 转单
    And 作废订单
    Then 检验数据提示 作废成功!
    And 订单已作废

  Scenario: 已审核
    Given 生成平台订单
    When 转单
    And 操作订单 审核
    And 作废订单
    Then 检验数据提示 作废成功!
    And 订单已作废

  Scenario: 无效网上订单
    Given 生成平台订单
    When 转单
    And 操作订单 无效
    And 作废订单
    Then 检验数据提示 作废成功!
    And 订单已作废

  Scenario: 已加入采购看板订单
    Given 生成指定网上订单 auto004400000007,1
    And 操作订单 加入采购看板
    And 作废订单
    Then 检验数据提示 对应网上订单已加入采购看板，无法作废！
    And 订单未作废

  Scenario: 已加入并取消采购看板订单
    Given 生成指定网上订单 auto004400000007,1
    And 操作订单 加入采购看板
    And 操作订单 取消采购看板
    And 作废订单
    Then 检验数据提示 作废成功!
    And 订单已作废

  Scenario: 已发货订单
    Given 生成平台订单
    When 转单
    And 操作订单 审核
    And 操作订单 绑定快递单
    And 操作订单 打印快递单
    And 操作订单 打印订单
    And 操作订单 扫描出库
    And 作废订单
    Then 检验数据提示 关联网上订单已发货不允许平台订单作废!
    And 订单未作废

  Scenario: 部分已发货订单
    Given 生成平台订单
    When 转单
    And 操作订单 审核
    And 操作订单 绑定快递单
    And 操作订单 打印快递单
    And 操作订单 打印订单
    And 操作订单 部分扫描出库
    And 作废订单
    Then 检验数据提示 关联网上订单已发货不允许平台订单作废!
    And 订单未作废

  Scenario: 已合并
    Given 生成网上订单
    And 生成网上订单
    When 合并订单
    And 作废合并或拆分订单
    Then 检验数据提示 作废成功!
    And 合并订单已作废

  Scenario: 已拆分
    Given 生成指定网上订单 auto000100000001,2|auto000100000002,1
    When 拆分订单
    And 作废合并或拆分订单
    Then 检验数据提示 作废成功!
    And 订单已作废

  Scenario: 合并订单已发货订单
    Given 生成网上订单
    And 生成网上订单
    When 合并订单
    And 操作订单 审核
    And 操作订单 绑定快递单
    And 操作订单 打印快递单
    And 操作订单 打印订单
    And 操作订单 扫描出库
    And 作废合并或拆分订单
    Then 检验数据提示 关联网上订单已发货不允许平台订单作废!
    And 订单未作废

  Scenario: 拆分订单已发货订单
    Given 生成指定网上订单 auto000100000001,2|auto000100000002,1
    When 拆分订单
    When 用拆分后的一个订单做操作
    And 操作订单 审核
    And 操作订单 绑定快递单
    And 操作订单 打印快递单
    And 操作订单 打印订单
    And 操作订单 扫描出库
    And 作废合并或拆分订单
    Then 检验数据提示 关联网上订单已发货不允许平台订单作废
    And 订单未作废