api—web模块中引入了Shiro权限控制 
-- 
api-biz业务逻辑代码   dao  mapper domain核心代码
-- 
api-tools 实现代码生成 业务流程阅读 generatorCode.MD 
--
1. 技术层面小工具 mysql主从读写分离，动态加入数据源，实时可切换、mongoDB工具类
2. 框架式层面 多模块项目、springboot、shiro
--


 
菜单
系统管理
    用户管理
    角色管理
    菜单管理
    系统参数 
# redis存放 系统参数，项目启动 随之加载
日志数据 一级
商品管理
    
    产品阅览 查看 (nginx 静态服务器 图片地址)
    
    产品订购 参数配置爬虫 比如 价格 位置 产权等
应用模板
    模板  一个 对应 绑定多个策略 
    template name 状态 (禁用启用) 删除状态 添加时间 修改时间 修改人id 修改人名称
    策略  策略(事件类型)   绑定多个执行模板
    temp_strategy_relation
     strategy  eventType  name 状态 (禁用启用) 删除状态 添加时间 修改时间 修改人id 修改人名称
     
