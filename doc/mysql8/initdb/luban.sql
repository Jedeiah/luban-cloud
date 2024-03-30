CREATE
DATABASE IF NOT EXISTS `luban` CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE
`luban`;

-- 产品
CREATE TABLE products
(
    id   INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);
INSERT INTO products (name)
VALUES ('Product1-moke');
INSERT INTO products (name)
VALUES ('Product2-moke');
INSERT INTO products (name)
VALUES ('Product3-moke');
INSERT INTO products (name)
VALUES ('Product4-moke');
INSERT INTO products (name)
VALUES ('Product5-moke');
INSERT INTO products (name)
VALUES ('Product6-moke');
INSERT INTO products (name)
VALUES ('Product7-moke');
INSERT INTO products (name)
VALUES ('Product8-moke');
INSERT INTO products (name)
VALUES ('Product9-moke');
INSERT INTO products (name)
VALUES ('Product10-moke');

--   rbac相关表
-- 用户表
CREATE TABLE users
(
    id          INT AUTO_INCREMENT PRIMARY KEY COMMENT '自增id',
    user_id     VARCHAR(64) UNIQUE NOT NULL COMMENT '用户唯一标识uuid',
    username    VARCHAR(50) UNIQUE NOT NULL COMMENT '用户名',
    password    VARCHAR(255)       NOT NULL COMMENT '加密后的密码',
    create_time DATETIME                    DEFAULT CURRENT_TIMESTAMP COMMENT '账户创建时间',
    update_time DATETIME                    DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '信息更新时间',
    deleted     BOOLEAN            NOT NULL DEFAULT FALSE COMMENT '逻辑删除标记'
) COMMENT='用户信息表';

-- 角色表
CREATE TABLE roles
(
    id          INT AUTO_INCREMENT PRIMARY KEY COMMENT '自增id',
    role_name   VARCHAR(50) UNIQUE NOT NULL COMMENT '角色名称',
    description TEXT COMMENT '角色描述',
    create_time DATETIME                    DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME                    DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '信息更新时间',
    deleted     BOOLEAN            NOT NULL DEFAULT FALSE COMMENT '逻辑删除标记'
) COMMENT='角色信息表';
-- 插入 USER 角色
INSERT INTO roles (role_name, description, create_time, update_time, deleted)
VALUES ('USER', '普通用户角色', NOW(), NOW(), FALSE);
-- 插入 EDITOR 角色
INSERT INTO roles (role_name, description, create_time, update_time, deleted)
VALUES ('EDITOR', '编辑用户角色', NOW(), NOW(), FALSE);
-- 插入 PRODUCT_ADMIN 角色
INSERT INTO roles (role_name, description, create_time, update_time, deleted)
VALUES ('PRODUCT_ADMIN', '产品管理员角色', NOW(), NOW(), FALSE);

-- 用户角色关联表
CREATE TABLE user_roles
(
    user_id     VARCHAR(64) NOT NULL COMMENT '用户唯一标识uuid',
    role_name   VARCHAR(50) NOT NULL COMMENT '角色名称',
    create_time DATETIME             DEFAULT CURRENT_TIMESTAMP COMMENT '关联创建时间',
    deleted     BOOLEAN     NOT NULL DEFAULT FALSE COMMENT '逻辑删除标记'
) COMMENT='用户角色关联表';

-- 权限表
CREATE TABLE permissions
(
    id              INT AUTO_INCREMENT PRIMARY KEY COMMENT '自增id',
    permission_name VARCHAR(50) UNIQUE NOT NULL COMMENT '权限名称',
    description     TEXT COMMENT '权限描述',
    deleted         BOOLEAN            NOT NULL DEFAULT FALSE COMMENT '逻辑删除标记'
) COMMENT='权限信息表';
-- 插入 "增" 权限数据
INSERT INTO permissions (permission_name, description, deleted)
VALUES ('CREATE', '允许创建新资源', FALSE);
-- 插入 "删" 权限数据
INSERT INTO permissions (permission_name, description, deleted)
VALUES ('DELETE', '允许删除资源', FALSE);
-- 插入 "改" 权限数据
INSERT INTO permissions (permission_name, description, deleted)
VALUES ('UPDATE', '允许更新资源', FALSE);
-- 插入 "查" 权限数据
INSERT INTO permissions (permission_name, description, deleted)
VALUES ('READ', '允许查看资源', FALSE);

-- 角色权限关联表
CREATE TABLE role_permissions
(
    role_name       VARCHAR(50) NOT NULL COMMENT '角色名称',
    permission_name VARCHAR(50) NOT NULL COMMENT '权限名称',
    create_time     DATETIME             DEFAULT CURRENT_TIMESTAMP COMMENT '关联创建时间',
    deleted         BOOLEAN     NOT NULL DEFAULT FALSE COMMENT '逻辑删除标记'
) COMMENT='角色权限关联表';
-- 为 USER 角色关联 READ 权限
INSERT INTO role_permissions (role_name, permission_name, create_time, deleted)
VALUES ('USER', 'READ', NOW(), FALSE);
-- 为 EDITOR 角色关联 CREATE, UPDATE, DELETE 权限
INSERT INTO role_permissions (role_name, permission_name, create_time, deleted)
VALUES ('EDITOR', 'CREATE', NOW(), FALSE);
INSERT INTO role_permissions (role_name, permission_name, create_time, deleted)
VALUES ('EDITOR', 'UPDATE', NOW(), FALSE);
INSERT INTO role_permissions (role_name, permission_name, create_time, deleted)
VALUES ('EDITOR', 'DELETE', NOW(), FALSE);
-- 为 PRODUCT_ADMIN 角色关联所有权限
INSERT INTO role_permissions (role_name, permission_name, create_time, deleted)
VALUES ('PRODUCT_ADMIN', 'CREATE', NOW(), FALSE);
INSERT INTO role_permissions (role_name, permission_name, create_time, deleted)
VALUES ('PRODUCT_ADMIN', 'UPDATE', NOW(), FALSE);
INSERT INTO role_permissions (role_name, permission_name, create_time, deleted)
VALUES ('PRODUCT_ADMIN', 'DELETE', NOW(), FALSE);
INSERT INTO role_permissions (role_name, permission_name, create_time, deleted)
VALUES ('PRODUCT_ADMIN', 'READ', NOW(), FALSE);
