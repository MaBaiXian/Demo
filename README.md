# 学生宿舍管理系统 🏠💻

## 项目简介

这是一个专门用于存储 GDPU（广东药科大学）软件工程和 JavaWeb 大作业的仓库。项目的主要目标是开发并实现一个功能全面的学生宿舍管理系统。

## 主要功能

- **权限控制**：系统支持三种用户角色（学生、宿舍管理员、系统管理员），并根据用户角色动态生成路由，实现不同角色访问不同页面的功能。
- **报修管理**：学生可以在线提交宿舍维修申请，管理员可以实时查看并处理这些申请。
- **通知公告**：发布宿舍相关通知和公告，确保信息及时传达。
- **信息查看**：用户可以在主页查看所有个人信息。

## 技术选型

- **前端**：基于 [vue-element-admin](https://panjiachen.github.io/vue-element-admin-site/zh/) 模板进行二次开发。
- **后端**：使用 Spring Boot 3 框架。
- **数据库**：采用 MySQL 数据库。
- **开发工具**： 前端使用 VSCode，后端使用 IntelliJ IDEA。
- **版本控制**：使用 Git 和 GitHub。

## 目录结构

```shell
Demo
├── backend/                # 后端项目目录
│   └── src/                # 源代码目录
│       ├── controller/     # 控制器模块，处理HTTP请求
│       ├── dto/            # 数据传输对象（DTO），用于封装处理结果
│       ├── entity/         # 实体类模块，映射数据库表结构
│       ├── mapper/         # MyBatis Mapper接口，用于数据库操作
│       └── service/        # 业务逻辑模块
│           └── serviceimpl/ # 业务逻辑实现类
│
├── frontend/               # 前端项目目录，具体开发参考vue-element-admin项目文档
├── README.md               # 项目说明文档
└── demo.sql                # SQL脚本文件，用于数据库初始化
```

## 构建指南

1. **克隆项目**

```shell
git clone git@github.com:MaBaiXian/Demo.git
```

2. **前端开发**

```shell
# 进入前端项目目录
cd vue-element-admin

# 安装项目依赖
npm install

# 启动前端项目进行本地开发
npm run dev
```

3. **后端开发**

- 在 IntelliJ IDEA 中连接本地的 MySQL 数据库，并运行 demo.sql 脚本来还原数据库。
- 使用 Maven 安装后端项目所需的依赖。
- 修改本地数据库连接配置，更新数据库密码。

```Java
# backend/src/main/resources/application.properties
spring.application.name=backend
spring.datasource.url=jdbc:mysql://localhost:3306/demo
# 请将用户名和密码替换为本地数据库的实际用户名和密码
spring.datasource.username=root
spring.datasource.password=Ma020218
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
```

- 运行 com.gdpu.backend.BackendApplication 主类启动后端服务。

## Demo
这里放演示的gif图
