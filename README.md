# DDDDmeo

## 概述
这是一个基于Spring Boot3.0.2、Sping Cloud 2022.0.0.0的一个集账号密码、第三方Github、LDAP登陆认证和注解加自定义AOP实现接口鉴权的简单Demo。 docker一键部署

## 目录介绍
- `api`：项目中所有接口声明。
  - `uaa-api`：uaa模块的接口声明。 
- `commons`：项目中公共部分（枚举、统一异常、工具类等）。
- `doc`：docker部署相关文件、部署文档。
  - `gateway`：网关部署资源，包含Dockerfile、可执行jar
  - `mysql8`：mysql8版本部署相关
    - `conf.d`：容器映射配置文件目录
    - `data`：数据库数据映射目录
    - `initdb`：nacos初始化脚本、系统初始化脚本
  - `nacos`：nacos日志挂载目录
  - `nginx`：
    - `html`：静态文件
    - `nginx.cnf`：配置文件
  - `product`：微服务部署资源
  - `redis`：redis部署资源
  - `uaa`：微服务部署资源
- `doc-spring-boot`：自定义Spring Boot Starter，做些web相关swagger配置。
- `gateway`：微服务 api 网关模块，Sping Cloud Gateway，请求拦截、token校验、令牌桶限流。
- `product`：微服务 产品模块。
- `uaa`：微服务 用户鉴权模块（鉴权可以放到网关）。

## 功能介绍
前端是简单的html+js实现的，后端是jwt会话token实现的登陆认证，自定义注解@PermissionRequired+aop实现接口层面权限控制。
功能有登陆、注册，产品的CRUD

 | 服务名称    | 账号密码               | 端口        |
  |---------|--------------------|-----------|
  | MySQL   | root:jedeiah       | 3306      |
  | Redis   | jedeiah（密码）        | 6379      |
  | Nacos   | nacos:nacosjedeiah | 8848、9848 |
  | Gateway | -                  | 8890      |
 | product | -                  | 8889      |
 | uaa     | -                  | 8888      |
  | Nginx   | -                  | 8080      |
  


## 安装、配置、运行\部署
1. 自行安装docker、docker-compose
2. 安装maven
3. 克隆项目仓库：`git clone https://github.com/Jedeiah/luban-cloud.git`
4. 进入项目目录：`cd spring-boot-project`
5. 修改项目相关ip：项目全局替换 localhost 为 你的机器ip （除.md、java文件。。。）
6. 修改mysql初始化sql文件中nacos数据库连接ip，`./doc/mysql8/nacos.sql`，可同上5，项目全局替换 192.168.0.151 为 你的机器ip
7. install项目：`mvn clean install -f ./pom.xml`
8. 进入项目部署目录：`cd ./doc`
9. 若要远程部署，则将当前目录下所有文件cp至服务器
10. 一键部署启动：`docer-compose -f ./docker-compose.yml -d --build`

## 效果
http://your_ip:8090/login.html

## curl简单部分api测试

1. 获取会话token（登陆）“**响应体 data 参数**”，需要传请求参数 **username**、**password**：
    ```bash
       curl -X GET 'http://localhost:8890/api/uaa/users/login/jwt?username=user_1&password=user_1' -H 'Accept: */*'
    ```
2. 根据会话token，对产品CRUD
   1. 新增（需要传参数：**请求头"：AuthorizationJwt**、**请求体：{name}**）： 
    ```bash
        curl -X POST 'http://localhost:8890/api/product/products/add' -H 'AuthorizationJwt: eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOiI1MWVmNWIzOS02Njk5LTQyNDMtYjA5Yi01YmZhMjUwMTM2NzYiLCJqdGkiOiI3OGQ3ODM2YS03MGY1LTQ2ZjItYmFmYy00MmNmM2Q5ZDVjOTgiLCJleHAiOjE3MTE4OTMwMjQsImlhdCI6MTcxMTg5MjcyNCwic3ViIjoibG9naW5BdXRoZW50aWNhdGlvbiIsImlzcyI6ImNoaiJ9.RxDCyjkcDhXO7FL3dXYb_8PkXQGJWGp15nKN-5ooS8o' -H'Content-Type: application/json' -H 'Accept: */*' -d '{"name": "product-11"}'
    ```
   2. 修改（需要传参数：**请求头：AuthorizationJwt**、**请求体：{id、name}**）：
    ```bash
        curl -X PUT 'http://localhost:8890/api/product/products/update' -H 'AuthorizationJwt: eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOiI1MWVmNWIzOS02Njk5LTQyNDMtYjA5Yi01YmZhMjUwMTM2NzYiLCJqdGkiOiI3OGQ3ODM2YS03MGY1LTQ2ZjItYmFmYy00MmNmM2Q5ZDVjOTgiLCJleHAiOjE3MTE4OTMwMjQsImlhdCI6MTcxMTg5MjcyNCwic3ViIjoibG9naW5BdXRoZW50aWNhdGlvbiIsImlzcyI6ImNoaiJ9.RxDCyjkcDhXO7FL3dXYb_8PkXQGJWGp15nKN-5ooS8o' -H'Content-Type: application/json' -H 'Accept: */*' -d '{"id": 0,"name": ""}'
    ```
   3. 删除（需要传参数：**请求头：AuthorizationJwt**、**请求行拼接productId：**）：
    ```bash
        curl -X DELETE 'http://localhost:8890/api/product/products/delete/11' -H 'AuthorizationJwt: eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOiJkYmMwMGVkNC01M2Q3LTQ1NTAtODBhMi0zYTQ4OWQ2NmFlNTMiLCJqdGkiOiJmYWY2YzdhMS1lNWI5LTQ5NDAtYmVhOS1jYTkwYzdlN2FiYzUiLCJleHAiOjE3MTE4OTQ4NDYsImlhdCI6MTcxMTg5NDU0Niwic3ViIjoibG9naW5BdXRoZW50aWNhdGlvbiIsImlzcyI6ImNoaiJ9.oOtaSMTIHdmS_NWog5wuvxmA_cJeCEI8NbloEHvXtUs' -H 'Accept: */*'
    ```
   4. 查询（需要传参数：**请求头：AuthorizationJwt**）：
    ```bash
        curl -X GET 'http://localhost:8890/api/product/products/listAll' -H 'AuthorizationJwt: eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOiI1MWVmNWIzOS02Njk5LTQyNDMtYjA5Yi01YmZhMjUwMTM2NzYiLCJqdGkiOiI3OGQ3ODM2YS03MGY1LTQ2ZjItYmFmYy00MmNmM2Q5ZDVjOTgiLCJleHAiOjE3MTE4OTMwMjQsImlhdCI6MTcxMTg5MjcyNCwic3ViIjoibG9naW5BdXRoZW50aWNhdGlvbiIsImlzcyI6ImNoaiJ9.RxDCyjkcDhXO7FL3dXYb_8PkXQGJWGp15nKN-5ooS8o' -H 'Accept: */*'
    ```