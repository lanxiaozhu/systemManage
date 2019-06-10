
各位看官如果项目有兴趣 请 stat 奥！！！！


个人暂时没用博客，技术分享地址： https://www.yuque.com/yuque_iq （语雀）阿里出版


后端项目启动流程：
mysql导入数据库，首先你要新建数据库名称为 moyu;然后将sql文件导入  moyu.sql


后端项目有建议使用 idea ，jdk 1.8版本

后端项目中需要resources 目录下application.properties文件 

数据库地址 ：  jdbc:mysql://XXX:3306/moyu?useUnicode=true&characterEncoding=utf8


管理后台项目为： api-data-manage

简单的爬虫贝壳项目为： beike

前端项目为： my-project

项目技术： 进度.xls

----------------------------------------------------------------





前端启动流程：

0、下载node.js  node-v8.11.3-x64.msi 双击运行node-v8.11.3-x64.msi   一路next

1.安装完成后   cmd 输入 node -v 出现版本信息表示安装成功

2. 安装完nodejs之后,打开cmd终端输入一下

使用国内的淘宝镜像安装 cnpm命令

npm install -g cnpm --registry=https://registry.npm.taobao.org

3.下载vscode 

4.将前端项目拖至vscode桌面图标 打开项目

5.快捷键ctrl+j 打开终端命令行 逐行输入一下命令安装必须插件

cnpm install 命令构建项目(从git 上面获取的项目，是需要再次编译的)  

》》》》》》》》》》》》》》》》以下命令 如果 同学之前安装过 无需再次安装

cnpm install --save axios   >>安装请求http 

cnpm install --save element-ui    >>安装element ui  

cnpm install --save moment vue-axios

cnpm install --save vuex  >>安装vuex

6.构建完成后输入 cnpm run dev 运行项目  (访问http://localhost:8080)


注意事项：
1、此时需要后端项目已经启动

2、前端项目中 src 目录需要 修改后台访问地址：  axios.defaults.baseURL = "http://XXX:8088/" ;

// 关键步骤–填写后台请求统一的地址  (修改为你自己的后台地址)


3、productRead.vue 文件中有图片访问地址，是nginx的图片绝对访问地址前缀

if your vsCode throws error (

    To install them, you can run: npm install --save async-validator deepmerge normalize-wheel
    
    resize-observer-polyfill throttle-debounce/debounce throttle-debounce/throttle vuex
    
)
npm install sass-loader --save

npm install node-sass --save



