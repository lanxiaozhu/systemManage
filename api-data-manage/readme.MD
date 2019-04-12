业务方向阅读(businessProcess.MD)
--
api—web模块中引入了Shiro权限控制 阅读（shrio.MD）
-- 
api-biz业务逻辑代码   dao  mapper domain核心代码
-- 
api-tools 实现代码生成 业务流程阅读 generatorCode.MD 
-- 
 
菜单
系统管理
    用户管理
    角色管理
    菜单管理
    系统参数  redis存放！
日志数据 一级
商品管理
    产品添加 带有图片 和 产品详情
    产品阅览 查看 添加订购
    产品订购 订单取消 订单查看
应用模板
    模板  一个 对应 绑定多个策略 
    template name 状态 (禁用启用) 删除状态 添加时间 修改时间 修改人id 修改人名称
    策略  策略(事件类型)   绑定多个执行模板
    temp_strategy_relation
     strategy  eventType  name 状态 (禁用启用) 删除状态 添加时间 修改时间 修改人id 修改人名称
     
    绑定执行
    notice—temp （通知） 接收者名称 联系类型  钉钉 电话 短信  联系方式    开关（关闭通知)   删除状态 添加时间 修改时间 修改人id 修改人名称
    某个模板 绑定了 一定数量的 策略，根据事件类型 分发到不同的联系人 告警通知
    
    主要是什么场景 触发告警？首先 登陆吧！然后某用户 删除
    
    登陆时 绑定告警
     
     
     消息中间件 通知！ 

    
    
    
    

