# 							博客初始化步骤

> 注意：**需要有Java环境(1.8版本)**

1. 安装gradle(3.5)项目管理工具
2. 安装Mysql5.7，并创建数据库名为:**blog**
3. 安装Elasticsearch(2.4.2)并启动
4. 将项目下载解压导入IDEA启动

### 预览图：

![](http://ww1.sinaimg.cn/large/006lAwRRly1g3clo8lptwj32801amthb.jpg)

用户登录页面：

![](http://ww1.sinaimg.cn/large/006lAwRRly1g3clqbunk6j328014wq71.jpg)

![](http://ww1.sinaimg.cn/large/006lAwRRly1g3clr4ihigj32801ecn21.jpg)

![](https://ws1.sinaimg.cn/large/006lAwRRly1g3clskvtbnj326g1080wm)





### 博客待完成事项：

- [x] 增加邮箱认证（2019-6-12）
- [x] 增加Redis缓存(2019-6-20)
- [ ] 增加hexo博客的小宠物看管家
- [ ] 增加RSS订阅按钮
- [x] 改进手机端客户的体验（2019-6-12）

### 博客示例：

网址:[http://22k-me.cn](http://22k-me.cn)

### 网站的注意事项：

1. ​	如果在本地调试的时候,发现之前的博客文章有但是访问不了的，可以下载下Elasticsearch-head插件，然后把数据清除就可以了，关于Elasticsearch-head插件的安装。可以自行百度下

2. ​	遇到在本地调试项目可以运行，但是打成war包之后却不能运行的。可以在我项目的主程序中查看下Application.java中的代码。(折腾一下午才知道这是SpringBoot的问题…….)

   参考文章:[https://blog.csdn.net/qq_26684469/article/details/81475432](https://blog.csdn.net/qq_26684469/article/details/81475432)




