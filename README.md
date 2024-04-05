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
  - `openldap`：ldap服务挂载目录
    - `initdb`：
      - `init.ldif`：初始化数据
  - `product`：微服务部署资源
  - `redis`：redis部署资源
  - `uaa`：微服务部署资源
- `doc-spring-boot`：自定义Spring Boot Starter，做些web相关swagger配置。
- `gateway`：微服务 api 网关模块，Sping Cloud Gateway，请求拦截、token校验、令牌桶限流。
- `product`：微服务 产品模块。
- `uaa`：微服务 用户鉴权模块（鉴权可以放到网关）。

## 功能介绍
前端是简单的html+js实现的，后端是jwt会话token实现的登陆认证，自定义注解@PermissionRequired+aop实现接口层面权限控制。
### 功能
1. 系统账号的登陆、注册
2. ldap账号的登陆
3. github授权的登陆
4. 不同角色的的权限控制，产品的CRUD

 | 服务名称           | 账号密码                               | 说明                     | 端口        |
  |----------------|------------------------------------|------------------------|-----------|
  | MySQL          | root:jedeiah 、nacos:nacosjedeiah   | 业务服务连接账号、nacos服务连接账号   | 3306      |
  | Redis          | jedeiah（密码）                        | redis登陆                | 6379      |
  | Nacos          | nacos:nacosjedeiah                 | nacos-web管理登陆          | 8848、9848 |
  | Gateway        | -                                  | -                      | 8890      |
 | uaa            | -                                  | -                      | 8889      |
 | product        | -                                  | -                      | 8888      |
  | Nginx          | -                                  | -                      | 8080      |
  | ldap-server    | cn=admin,dc=jedeiah,dc=com:jedeiah | 管理员账号                  | 389、636   |
  | ldap-admin-web | -                                  | 可访问web端 http://ip:port | 8091      |
  


## 安装、配置、运行\部署
1. 自行安装docker、docker-compose
2. 安装maven
3. 克隆项目仓库：`git clone https://github.com/Jedeiah/luban-cloud.git`
4. 进入项目目录：`cd luban-cloud`
5. **修改项目相关ip**：项目全局替换 192.168.0.151 为 your_ip ！！！
6. install项目：`mvn clean install -f ./pom.xml`
7. 进入项目部署目录：`cd ./doc`
8. 若要远程部署，则将当前目录下所有文件cp至服务器
9. 一键部署启动：`docker-compose -f ./docker-compose.yml up -d --build`
10. **修改github授权部分**：登陆个人github，`https://github.com/settings/developers` 新建\修改 Oauth Apps 的对应配置（Client ID、Client secrets、Homepage URL（http://your_ip:8090）、Authorization callback URL（http://your_ip:8090/githubloding.html））
11.  **登陆Nacos Web**，`http://your_ip:8848/nacos/index.html` 账号密码：nacos:nacosjedeiah, 配置管理->配置列表->dev（命名空间）->修改uaa-dev.yml服务的配置文件中github oauth相关信息（github.clientId、github.clientSecret、github.redirectUrl(只修改ip部分)）

## 效果
http://your_ip:8090/login.html

## curl简单部分api测试

1. 获取会话token（登陆）“**响应体 data 参数**”，需要传请求参数 **username：注意权限**、**password：注意权限**：
    ```bash
     本系统账号密码登陆  curl -X GET 'http://your_ip:8890/api/uaa/users/login/jwt?username=user_1&password=user_1' -H 'Accept: */*'
                      {"success":true,"errCode":null,"errMsg":null,"data":"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOiJiZjA0M2VkZC1mZWMyLTQwYWYtOWQ0MS1iMzQ4NjNiYTZiNTIiLCJsb2dpblR5cGUiOiJVU0VSIiwianRpIjoiZTBiN2I2YmMtNzExNy00MmU5LWFkZTctMzIxYmU3NzY4N2NlIiwiZXhwIjoxNzEyMjIyMjk0LCJpYXQiOjE3MTIyMjA0OTQsInN1YiI6ImxvZ2luQXV0aGVudGljYXRpb24iLCJpc3MiOiJjaGoifQ.cC8MJbN_1qG5fx-ht6ELyR6FeMsr3wAe0dPCmQVR_F4"}  
     ldap系统账号密码登陆  curl -X GET 'http://your_ip:8890/api/uaa/ldap/login?username=ldap_user_1&password=ldap_user_1' -H 'Accept: */*'
                      {"success":true,"errCode":null,"errMsg":null,"data":"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOiJsZGFwX3VzZXJfMSIsImxvZ2luVHlwZSI6IkxEQVAiLCJqdGkiOiJlZTgxN2IwNi1iMWMyLTQ5N2YtYjQwYy1hMDAyMWZlYTVjNGMiLCJleHAiOjE3MTIyMjI0MzAsImlhdCI6MTcxMjIyMDYzMCwic3ViIjoibG9naW5BdXRoZW50aWNhdGlvbiIsImlzcyI6ImNoaiJ9.HdQ75_OXO6niYferZi_KLUMGJFec_8gtuBaRj3XA8Uw"}
    ```
2. 根据会话token，对产品CRUD
   - 新增（需要传参数：**请求头"：AuthorizationJwt：登陆接口返回的data**、**请求体：{name}：产品名称**）： 
    ```bash
        curl -X POST 'http://your_ip:8890/api/product/products/add' -H 'AuthorizationJwt: ' -H 'Content-Type: application/json' -H 'Accept: */*' -d '{"name": ""}'
        {"success":true,"errCode":null,"errMsg":null,"data":"新增成功"} 
    ```
   - 修改（需要传参数：**请求头：AuthorizationJwt**、**请求体：{id、name}**）：
    ```bash
        curl -X PUT 'http://your_ip:8890/api/product/products/update' -H 'AuthorizationJwt: ' -H 'Content-Type: application/json' -H 'Accept: */*' -d '{"id": 0,"name": ""}'
        {"success":true,"errCode":null,"errMsg":null,"data":"修改成功"}
    ```
   - 删除（需要传参数：**请求头：AuthorizationJwt**、**请求行拼接productId：**）：
    ```bash
        curl -X DELETE 'http://your_ip:8890/api/product/products/delete/11' -H 'AuthorizationJwt: ' -H 'Accept: */*'
        {"success":true,"errCode":null,"errMsg":null,"data":"删除成功"}%
    ```
   - 查询（需要传参数：**请求头：AuthorizationJwt**）：
    ```bash
        curl -X GET 'http://your_ip:8890/api/product/products/listAll' -H 'AuthorizationJwt: ' -H 'Accept: */*'
        {"success":true,"errCode":null,"errMsg":null,"data":[{"id":1,"name":"Product1-moke"},{"id":2,"name":"product-curl-add-update"},{"id":3,"name":"Product3-moke"},{"id":4,"name":"Product4-moke"},{"id":5,"name":"Product5-moke"},{"id":6,"name":"Product6-moke"},{"id":7,"name":"Product7-moke"},{"id":8,"name":"Product8-moke"},{"id":9,"name":"Product9-moke"},{"id":10,"name":"Product10-moke1"},{"id":12,"name":"嗷嗷"},{"id":14,"name":"product-curl-add"}]}
    ```