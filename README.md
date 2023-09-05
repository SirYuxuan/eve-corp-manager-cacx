## 项目目录介绍
有问题或定制请联系 QQ：1718018032
1. application = 军团系统主程序API
2. biz = 军团业务处理中心
3. discord = discord机器人程序
4. framework = 军团系统核心框架
5. job = 定时工作，用来执行用户数据的刷新，定时任务的处理
6. ui = 军团系统前端UI界面
## 部署操作
1. 导入sql目录下的eve_corp_manager_cacx.sql 到数据库
2. 导入sql目录下的data.sql 到数据库
3. 修改ecmc-biz/src/main/resources/config/application-db.yml 中的数据库连接信息
4. 安装redis，修改ecmc-biz/src/main/resources/config/application-redis.yml 中的redis连接信息
5. 修改数据库中sys_config表中配置
6. Jdk版本为17，maven版本为3.8.2
7. 启动idea直接运行ecmc-application中的EcmcApplication.java
8. 打包执行maven clean package
9. CentOS下后台执行 `nohup java -jar ecmcApp.jar > cataline.out 2>&1 &`
```shell
QQ讨论群：255369105
```
### 界面展示：
![WX20230901-085242@2x.png](images%2FWX20230901-085242%402x.png)
![WX20230901-085426@2x.png](images%2FWX20230901-085426%402x.png)
![WX20230901-085542@2x.png](images%2FWX20230901-085542%402x.png)
![WX20230901-085624@2x.png](images%2FWX20230901-085624%402x.png)
![WX20230901-085642@2x.png](images%2FWX20230901-085642%402x.png)
![WX20230901-085656@2x.png](images%2FWX20230901-085656%402x.png)
![WX20230901-085802@2x.png](images%2FWX20230901-085802%402x.png)
![WX20230901-085823@2x.png](images%2FWX20230901-085823%402x.png)
![WX20230901-085845@2x.png](images%2FWX20230901-085845%402x.png)
![WX20230901-085939@2x.png](images%2FWX20230901-085939%402x.png)
![WX20230901-090031@2x.png](images%2FWX20230901-090031%402x.png)
![WX20230901-090112@2x.png](images%2FWX20230901-090112%402x.png)
![WX20230901-090138@2x.png](images%2FWX20230901-090138%402x.png)
![WX20230901-090204@2x.png](images%2FWX20230901-090204%402x.png)
![WX20230901-090223@2x.png](images%2FWX20230901-090223%402x.png)
![WX20230901-090238@2x.png](images%2FWX20230901-090238%402x.png)
![WX20230901-090411@2x.png](images%2FWX20230901-090411%402x.png)
![WX20230901-090428@2x.png](images%2FWX20230901-090428%402x.png)
![WX20230901-090446@2x.png](images%2FWX20230901-090446%402x.png)
![WX20230901-090503@2x.png](images%2FWX20230901-090503%402x.png)
![WX20230901-090540@2x.png](images%2FWX20230901-090540%402x.png)
![WX20230901-091113@2x.png](images%2FWX20230901-091113%402x.png)
![WX20230901-091122@2x.png](images%2FWX20230901-091122%402x.png)
![WX20230901-091136@2x.png](images%2FWX20230901-091136%402x.png)