# 							博客初始化步骤

> 注意：**需要有Java环境(1.8版本)、Gradle(3.5）**

1. 安装Mysql5.7，并创建数据库名为:**blog**
2. 安装Elasticsearch(2.4.2)并启动
3. 将项目下载解压导入eclipse启动

启动后，打开[http://localhost:8080](http://localhost:8080)(首次运行项目，没有数据)

![](http://ww1.sinaimg.cn/large/006lAwRRly1g3clo8lptwj32801amthb.jpg)

（图为已经初始化了的网页）

用户登录页面：

![](http://ww1.sinaimg.cn/large/006lAwRRly1g3clqbunk6j328014wq71.jpg)

用户注册页面：

![](http://ww1.sinaimg.cn/large/006lAwRRly1g3clr4ihigj32801ecn21.jpg)

还有略带特效的网站LOGO页面

![](https://ws1.sinaimg.cn/large/006lAwRRly1g3clskvtbnj326g1080wm)





### 博客待完成事项：

- [ ] 增加邮箱认证
- [ ] 增加hexo博客的小宠物看管家（少女心）
- [ ] 增加RSS订阅按钮
- [ ] 改进手机端客户的体验
- [ ] 暂时想到这里hah



网站的效果:[http://22k-me.cn](http://22k-me.cn)





网站的注意事项：

1. ​	如果在本地调试的时候,发现之前的博客文章有但是访问不了的，可以下载下Elasticsearch-head插件，然后把数据清除就可以了，关于Elasticsearch-head插件的安装。可以自行百度下

2. ​	遇到在本地调试项目可以运行，但是打成war包之后却不能运行的。可以在我项目的主程序中查看下Application.java中的代码。(折腾一下午才知道这是SpringBoot的问题…….)

   参考文章:[https://blog.csdn.net/qq_26684469/article/details/81475432

   ](https://blog.csdn.net/qq_26684469/article/details/81475432)



