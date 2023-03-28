# yizhi-medical

## 介绍

> 易知医疗挂号平台——尚医通项目按部就班复现，由于在学习原项目时容易搞混数据库之间的关系以及各类命名，所以复现时对各方面进行了优化，如有不足欢迎指出。

## 创新点

- 对复杂项目解耦，注重模块化设计
- 将Swagger与Gateway进一步结合，同时引入Apifox文档

- 丰富注释

- URL接口地址优化
- 模块，包名优化
- 依赖优化
- 数据库命名、字段优化

## 目录说明（你的Star是我持续跟进更新的动力）

> 列个目录，数据库分开放便于理解

- yizhi-medical-server 医疗挂号服务端
  - yizhi-service
    - [x] dictionary-service（Done✓）
    - [x] medical-service（后台管理微服务）（Done✓）
    - [ ] order-service（Not Yet）
    - [ ] user-service（Not Yet）
    - [ ] msm（Not Yet）
    - [ ] oss（Not Yet）
    - [ ] task（Not Yet）
    - [ ] statistics（Not Yet）

  - doc
    - mysql
      - medical_common.sql（字典）
      - medical_admin.sql
        - hospital_setting（多个医院设置管理，下发给医院认证key）

      - medical_order.sql（Web订单）
      - medical_user.sql（Web用户）

- yizhi-medical-admin 医疗挂号后台管理（基于Vue的版本非最新Typescript版本[xpromise/syt-admin (gitee.com)](https://gitee.com/xxpromise/syt-admin)）
  - [x] 数据管理（Done✓）
  - [x] 医院管理（Done✓）
  - [ ] 会员管理（Not Yet）
  - [ ] 订单管理（Not Yet）
  - [ ] 统计管理（Not Yet）

- yizhi-medical-web 医疗挂号用户端（Not Yet）
- hospital-manage-mock 模拟某个医院的后台管理（Done✓）
  - doc
    - mongodb（海量数据存储模拟上传）
      - Department.json
      - Hospital.json
      - Schedule.json

    - mysql
      - medical_hospital_manage.sql（医院模拟数据）
        - hospital_setting（仅当前医院设置一条数据）
        - order_info（当前医院订单）
        - schedule（当前医院排班）


## Api文档

- [易知医疗 (apifox.com)](https://apifox.com/apidoc/shared-be05ce7c-c0b1-4e26-a272-a8ffaae41a13)（）

## UML建模

- // TODO

## 软件架构
- // TODO


## 安装教程

- // TODO

## 使用说明

- // TODO

## 参与贡献，鸣谢

- 尚医通项目
