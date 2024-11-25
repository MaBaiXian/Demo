use demo;

create table user
(
    userId   int primary key auto_increment comment '用户ID,主键自增加',
    username varchar(100) not null comment '用户名',
    password varchar(255) not null comment '密码',
    token    varchar(255) unique comment '令牌',
    avatar   varchar(255)          default 'https://www.emojiall.com/images/120/microsoft/windows-11-november-2021-update/1f975.png' comment '头像',
    roles    varchar(100) not null comment '权限角色',
    gender   varchar(32)  not null default '男' comment '性别',
    dorm     varchar(255) not null comment '宿舍号',
    major    varchar(255) not null comment '年级专业'
) default charset = 'UTF8' comment ='用户表';

create table announcement
(
    anncId    int primary key auto_increment comment '公告ID,主键自增加',
    publisher varchar(100) not null comment '发布者',
    avatar    varchar(255) not null comment '发布者头像',
    message   varchar(255) not null comment '公告内容',
    time      datetime     not null default current_timestamp comment '发布时间'
) default charset = 'UTF8' comment ='公告表';