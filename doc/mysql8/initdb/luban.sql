CREATE DATABASE IF NOT EXISTS `luban` CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE `luban`;

-- 产品
CREATE TABLE products(
    id   INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);
INSERT INTO `products` (`id`, `name`) VALUES (1, 'Product1-moke');
INSERT INTO `products` (`id`, `name`) VALUES (2, 'Product2-moke');
INSERT INTO `products` (`id`, `name`) VALUES (3, 'Product3-moke');
INSERT INTO `products` (`id`, `name`) VALUES (4, 'Product4-moke');
INSERT INTO `products` (`id`, `name`) VALUES (5, 'Product5-moke');
INSERT INTO `products` (`id`, `name`) VALUES (6, 'Product6-moke');
INSERT INTO `products` (`id`, `name`) VALUES (7, 'Product7-moke');
INSERT INTO `products` (`id`, `name`) VALUES (8, 'Product8-moke');
INSERT INTO `products` (`id`, `name`) VALUES (9, 'Product9-moke');
INSERT INTO `products` (`id`, `name`) VALUES (10, 'Product10-moke');

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
INSERT INTO `users` (`id`, `user_id`, `username`, `password`, `create_time`, `update_time`, `deleted`) VALUES (1, 'bf043edd-fec2-40af-9d41-b34863ba6b52', 'user_1', 'user_1', '2024-03-31 14:47:26', '2024-03-31 14:47:26', 0);
INSERT INTO `users` (`id`, `user_id`, `username`, `password`, `create_time`, `update_time`, `deleted`) VALUES (2, '33d2afec-34d6-4852-b77a-007740a22bbd', 'editor_1', 'editor_1', '2024-03-31 14:47:58', '2024-03-31 14:47:58', 0);
INSERT INTO `users` (`id`, `user_id`, `username`, `password`, `create_time`, `update_time`, `deleted`) VALUES (3, 'dbc00ed4-53d7-4550-80a2-3a489d66ae53', 'adm_1', 'adm_1', '2024-03-31 14:48:12', '2024-03-31 14:48:12', 0);
INSERT INTO `users` (`id`, `user_id`, `username`, `password`, `create_time`, `update_time`, `deleted`) VALUES (4, '51ef5b39-6699-4243-b09b-5bfa25013676', 'user_2', 'user_2', '2024-03-31 21:38:58', '2024-03-31 21:38:58', 0);
INSERT INTO `users` (`id`, `user_id`, `username`, `password`, `create_time`, `update_time`, `deleted`) VALUES (5, 'f7a9fdad-e08a-4231-ae04-a719407ff7f8', 'a', 'a', '2024-04-01 00:22:56', '2024-04-01 00:22:56', 0);
INSERT INTO `users` (`id`, `user_id`, `username`, `password`, `create_time`, `update_time`, `deleted`) VALUES (6, '5a0ee17c-59a6-490e-a758-8f53fa944343', 'b', 'b', '2024-04-01 00:30:11', '2024-04-01 00:30:11', 0);
INSERT INTO `users` (`id`, `user_id`, `username`, `password`, `create_time`, `update_time`, `deleted`) VALUES (7, 'github_37944936', 'Jedeiah', '276d194b-fb65-4bc3-8c8f-1e062553b8a7', '2024-04-02 15:01:32', '2024-04-02 15:01:32', 0);


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
INSERT INTO `user_roles` (`user_id`, `role_name`, `create_time`, `deleted`) VALUES ('bf043edd-fec2-40af-9d41-b34863ba6b52', 'USER', '2024-03-31 14:47:26', 0);
INSERT INTO `user_roles` (`user_id`, `role_name`, `create_time`, `deleted`) VALUES ('33d2afec-34d6-4852-b77a-007740a22bbd', 'EDITOR', '2024-03-31 14:47:58', 0);
INSERT INTO `user_roles` (`user_id`, `role_name`, `create_time`, `deleted`) VALUES ('dbc00ed4-53d7-4550-80a2-3a489d66ae53', 'PRODUCT_ADMIN', '2024-03-31 14:48:12', 0);
INSERT INTO `user_roles` (`user_id`, `role_name`, `create_time`, `deleted`) VALUES ('51ef5b39-6699-4243-b09b-5bfa25013676', 'USER', '2024-03-31 21:38:58', 0);
INSERT INTO `user_roles` (`user_id`, `role_name`, `create_time`, `deleted`) VALUES ('f7a9fdad-e08a-4231-ae04-a719407ff7f8', 'USER', '2024-04-01 00:22:56', 0);
INSERT INTO `user_roles` (`user_id`, `role_name`, `create_time`, `deleted`) VALUES ('5a0ee17c-59a6-490e-a758-8f53fa944343', 'USER', '2024-04-01 00:30:11', 0);
INSERT INTO `user_roles` (`user_id`, `role_name`, `create_time`, `deleted`) VALUES ('github_37944936', 'EDITOR', '2024-04-02 15:01:32', 0);


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
