Feature: 查询平台订单


  Scenario: 查询平台订单-默认查询测试
    Given 	生成平台订单
    When 	查询平台订单
    Then 	校验查询结果中包含新建的订单

  Scenario: 查询平台订单-清空查询
    Given 	生成平台订单
    When 	清空查询平台订单
    Then 	校验查询结果中包含新建的订单


  Scenario: 查询平台订单-录入所有模糊数据查询测试
    Given 	生成平台订单
    When 	录入模糊查询数据
    Then 	校验查询结果中包含新建的订单

  Scenario: 查询平台订单-录入错误数据查询测试
    Given 	生成平台订单
    When 	录入错误查询数据
    Then 	校验查询结果中不包含新建的订单

  Scenario: 查询平台订单-翻页测试
    Given 	生成平台订单
    When 	查询平台订单第二页
    Then 	校验查询结果中不包含新建的订单

    @UI
  Scenario: UI查询平台订单-清空所选按钮测试-不同一页面
    Given 	生成平台订单
    And     转单
    When 	跳转到淘宝订单管理页面
    And     UI录入精确查询数据
    Then    列表中存在该订单
    And     点击清空按钮查询
    Then    列表中存在该订单
    And     点击跳转到下一页
    Then    列表中不存在该订单
