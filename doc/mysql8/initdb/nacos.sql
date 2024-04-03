CREATE USER 'nacos'@'%' IDENTIFIED BY 'nacosjedeiah';
GRANT ALL PRIVILEGES ON nacos_config.* TO 'nacos'@'%';
FLUSH
PRIVILEGES;

CREATE DATABASE IF NOT EXISTS `nacos_config` CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE `nacos_config`;

/******************************************/
/*   表名称 = config_info                  */
/******************************************/
CREATE TABLE `config_info`
(
    `id`                 bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
    `data_id`            varchar(255) NOT NULL COMMENT 'data_id',
    `group_id`           varchar(128)          DEFAULT NULL COMMENT 'group_id',
    `content`            longtext     NOT NULL COMMENT 'content',
    `md5`                varchar(32)           DEFAULT NULL COMMENT 'md5',
    `gmt_create`         datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `gmt_modified`       datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
    `src_user`           text COMMENT 'source user',
    `src_ip`             varchar(50)           DEFAULT NULL COMMENT 'source ip',
    `app_name`           varchar(128)          DEFAULT NULL COMMENT 'app_name',
    `tenant_id`          varchar(128)          DEFAULT '' COMMENT '租户字段',
    `c_desc`             varchar(256)          DEFAULT NULL COMMENT 'configuration description',
    `c_use`              varchar(64)           DEFAULT NULL COMMENT 'configuration usage',
    `effect`             varchar(64)           DEFAULT NULL COMMENT '配置生效的描述',
    `type`               varchar(64)           DEFAULT NULL COMMENT '配置的类型',
    `c_schema`           text COMMENT '配置的模式',
    `encrypted_data_key` text         NOT NULL COMMENT '密钥',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_configinfo_datagrouptenant` (`data_id`,`group_id`,`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='config_info';

INSERT INTO `config_info` (`id`, `data_id`, `group_id`, `content`, `md5`, `gmt_create`, `gmt_modified`, `src_user`, `src_ip`, `app_name`, `tenant_id`, `c_desc`, `c_use`, `effect`, `type`, `c_schema`, `encrypted_data_key`) VALUES (2, 'product-dev.yml', 'DEFAULT_GROUP', '# Logger Config\n# logging:\n#   level:\n#     com.jedeiah: debug\n#     com.zaxxer.hikari: info\n\n', '88a08e03abdada0e813ba4d0afe12db8', '2024-03-29 17:27:53', '2024-03-30 14:45:16', 'nacos', '192.168.65.1', '', 'dev', '', '', '', 'yaml', '', '');
INSERT INTO `config_info` (`id`, `data_id`, `group_id`, `content`, `md5`, `gmt_create`, `gmt_modified`, `src_user`, `src_ip`, `app_name`, `tenant_id`, `c_desc`, `c_use`, `effect`, `type`, `c_schema`, `encrypted_data_key`) VALUES (4, 'application-dev.yml', 'DEFAULT_GROUP', 'springdoc:\n  swagger-ui:\n    # 持久化认证数据，如果设置为 true，它会保留授权数据并且不会在浏览器关闭/刷新时丢失\n    persistAuthorization: true\nspring:\n  datasource:\n    driver-class-name: com.mysql.cj.jdbc.Driver\n    url: jdbc:mysql://192.168.0.151:3306/luban?serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=utf8&useSSL=false\n    username: root\n    password: jedeiah\n    hikari:\n      connection-timeout: 30000  # 获取连接的超时时间（单位：毫秒），默认值为 30000 30秒\n      idle-timeout: 180000  # 连接空闲时的超时时间（单位：毫秒），默认值为 600000 10分钟\n      max-lifetime: 180000  # 连接的最大生命周期（单位：毫秒），默认值为 1800000 30分钟\n      maximum-pool-size: 10  # 连接池中允许的最大连接数，默认值为 10\n      minimum-idle: 5  # 连接池中保持的最小空闲连接数，默认值为 maximum-pool-size 的一半\n      connection-test-query: SELECT 1  # 用于测试连接的 SQL 查询语句，默认值为 null\n      validation-timeout: 3000  # 连接验证超时时间（单位：毫秒），默认值为 5000\n      leak-detection-threshold: 0  # 连接泄漏检测阈值（单位：毫秒），默认值为 0\n      initialization-fail-timeout: 1000  # 初始化失败超时时间（单位：毫秒），默认值为 1000\n      isolate-internal-queries: false  # 是否隔离内部查询，默认值为 false\n      allow-pool-suspension: false  # 是否允许连接池暂停，默认值为 false\n      read-only: false  # 连接是否只读，默认值为 false\n      register-mbeans: false  # 是否注册 JMX MBeans，默认值为 false\n      auto-commit: true  # 是否自动提交事务，默认值为 true\n    #Redis配置\n  data:\n    redis:\n      host: ${REDIS_HOST:192.168.0.151}\n      port: 6379\n      password: jedeiah\n\n# actuator\nmanagement:\n  endpoints:\n    web:\n      exposure:\n        include: \'*\'\n  endpoint:\n    health:\n      enabled: true\n      show-details: ALWAYS\n    gateway:\n      enabled: true\n\nmybatis-plus:\n  typeEnumsPackage: com.jedeiah.commons.enums\n\nlogging:\n  level:\n    root: info\n    com.jedeiah.commons.handler: debug', '5b5e78b7b03a96858aab70c46b42ef6c', '2024-03-29 17:30:46', '2024-04-03 16:32:53', 'nacos', '192.168.65.1', '', 'dev', '', '', '', 'yaml', '', '');
INSERT INTO `config_info` (`id`, `data_id`, `group_id`, `content`, `md5`, `gmt_create`, `gmt_modified`, `src_user`, `src_ip`, `app_name`, `tenant_id`, `c_desc`, `c_use`, `effect`, `type`, `c_schema`, `encrypted_data_key`) VALUES (14, 'uaa-dev.yml', 'DEFAULT_GROUP', 'github:\n  clientId: d9352007e09ee5ddbb99\n  clientSecret: e0c4667eeb79008d31364ae7a1793376e00cd4e7\n  authorizeUrl: https://github.com/login/oauth/authorize\n  redirectUrl : http://192.168.0.151:8090/githubloding.html\n  accessTokenUrl: https://github.com/login/oauth/access_token\n  userInfoUrl: https://api.github.com/user\nspring:\n  ldap:\n    urls: ldap://192.168.0.151:389\n    base: dc=jedeiah,dc=com\n    username: cn=admin,dc=jedeiah,dc=com\n    password: jedeiah\n\n', 'ac4e940246990d5a0209ad04154d5d87', '2024-03-30 01:04:59', '2024-04-04 01:44:15', 'nacos', '192.168.65.1', '', 'dev', '', '', '', 'yaml', '', '');
INSERT INTO `config_info` (`id`, `data_id`, `group_id`, `content`, `md5`, `gmt_create`, `gmt_modified`, `src_user`, `src_ip`, `app_name`, `tenant_id`, `c_desc`, `c_use`, `effect`, `type`, `c_schema`, `encrypted_data_key`) VALUES (23, 'gateway-dev.yml', 'DEFAULT_GROUP', 'spring:\n  cloud:\n    gateway:\n      globalcors:\n        add-to-simple-url-handler-mapping: true # 解决options请求被拦截问题\n        corsConfigurations:\n          \'[/**]\': # 匹配所有请求\n            allowedOriginPatterns: \"*\" #跨域处理 允许所有的域\n            allowedMethods: # 支持的方法\n              - GET\n              - POST\n              - PUT\n              - DELETE\n              - OPTIONS\n            allowedHeaders: \"*\" # 允许在请求中携带的头信息\n            allowCredentials: true # 是否允许携带cookie\n            maxAge: 360000 # 这次跨域检测的有效期\n      routes:\n        - id: uaa\n          uri: lb://uaa\n          predicates:\n            - Path=/api/uaa/**\n          filters:\n            - StripPrefix=2\n            - name: RequestRateLimiter #局部限流过滤器，请求数限流 ，使用默认的facatory\n              args:\n                key-resolver: \"#{@ipKeyResolver}\"# 用户身份唯一标识（与代码里的注释写法一致）\n                redis-rate-limiter.replenishRate: 1 #每秒只允许一个请求\n                redis-rate-limiter.burstCapacity: 3 #宽限的请求数量（即实际允许并发4个请求）\n        - id: uaa-oauth2 \n          uri: lb://uaa\n          predicates:\n            - Path=/oauth2/**\n        - id: product\n          uri: lb://product\n          predicates:\n            - Path=/api/product/**\n          filters:\n            - StripPrefix=2\n\n', '0f5632a1c23581527ec539bfcc652ff4', '2024-03-30 14:55:37', '2024-04-01 15:45:31', 'nacos', '192.168.65.1', '', 'dev', '', '', '', 'yaml', '', '');

/******************************************/
/*   表名称 = config_info_aggr             */
/******************************************/
CREATE TABLE `config_info_aggr`
(
    `id`           bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
    `data_id`      varchar(255) NOT NULL COMMENT 'data_id',
    `group_id`     varchar(128) NOT NULL COMMENT 'group_id',
    `datum_id`     varchar(255) NOT NULL COMMENT 'datum_id',
    `content`      longtext     NOT NULL COMMENT '内容',
    `gmt_modified` datetime     NOT NULL COMMENT '修改时间',
    `app_name`     varchar(128) DEFAULT NULL COMMENT 'app_name',
    `tenant_id`    varchar(128) DEFAULT '' COMMENT '租户字段',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_configinfoaggr_datagrouptenantdatum` (`data_id`,`group_id`,`tenant_id`,`datum_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='增加租户字段';


/******************************************/
/*   表名称 = config_info_beta             */
/******************************************/
CREATE TABLE `config_info_beta`
(
    `id`                 bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
    `data_id`            varchar(255) NOT NULL COMMENT 'data_id',
    `group_id`           varchar(128) NOT NULL COMMENT 'group_id',
    `app_name`           varchar(128)          DEFAULT NULL COMMENT 'app_name',
    `content`            longtext     NOT NULL COMMENT 'content',
    `beta_ips`           varchar(1024)         DEFAULT NULL COMMENT 'betaIps',
    `md5`                varchar(32)           DEFAULT NULL COMMENT 'md5',
    `gmt_create`         datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `gmt_modified`       datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
    `src_user`           text COMMENT 'source user',
    `src_ip`             varchar(50)           DEFAULT NULL COMMENT 'source ip',
    `tenant_id`          varchar(128)          DEFAULT '' COMMENT '租户字段',
    `encrypted_data_key` text         NOT NULL COMMENT '密钥',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_configinfobeta_datagrouptenant` (`data_id`,`group_id`,`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='config_info_beta';

/******************************************/
/*   表名称 = config_info_tag              */
/******************************************/
CREATE TABLE `config_info_tag`
(
    `id`           bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
    `data_id`      varchar(255) NOT NULL COMMENT 'data_id',
    `group_id`     varchar(128) NOT NULL COMMENT 'group_id',
    `tenant_id`    varchar(128)          DEFAULT '' COMMENT 'tenant_id',
    `tag_id`       varchar(128) NOT NULL COMMENT 'tag_id',
    `app_name`     varchar(128)          DEFAULT NULL COMMENT 'app_name',
    `content`      longtext     NOT NULL COMMENT 'content',
    `md5`          varchar(32)           DEFAULT NULL COMMENT 'md5',
    `gmt_create`   datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `gmt_modified` datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
    `src_user`     text COMMENT 'source user',
    `src_ip`       varchar(50)           DEFAULT NULL COMMENT 'source ip',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_configinfotag_datagrouptenanttag` (`data_id`,`group_id`,`tenant_id`,`tag_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='config_info_tag';

/******************************************/
/*   表名称 = config_tags_relation         */
/******************************************/
CREATE TABLE `config_tags_relation`
(
    `id`        bigint(20) NOT NULL COMMENT 'id',
    `tag_name`  varchar(128) NOT NULL COMMENT 'tag_name',
    `tag_type`  varchar(64)  DEFAULT NULL COMMENT 'tag_type',
    `data_id`   varchar(255) NOT NULL COMMENT 'data_id',
    `group_id`  varchar(128) NOT NULL COMMENT 'group_id',
    `tenant_id` varchar(128) DEFAULT '' COMMENT 'tenant_id',
    `nid`       bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'nid, 自增长标识',
    PRIMARY KEY (`nid`),
    UNIQUE KEY `uk_configtagrelation_configidtag` (`id`,`tag_name`,`tag_type`),
    KEY         `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='config_tag_relation';

/******************************************/
/*   表名称 = group_capacity               */
/******************************************/
CREATE TABLE `group_capacity`
(
    `id`                bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `group_id`          varchar(128) NOT NULL DEFAULT '' COMMENT 'Group ID，空字符表示整个集群',
    `quota`             int(10) unsigned NOT NULL DEFAULT '0' COMMENT '配额，0表示使用默认值',
    `usage`             int(10) unsigned NOT NULL DEFAULT '0' COMMENT '使用量',
    `max_size`          int(10) unsigned NOT NULL DEFAULT '0' COMMENT '单个配置大小上限，单位为字节，0表示使用默认值',
    `max_aggr_count`    int(10) unsigned NOT NULL DEFAULT '0' COMMENT '聚合子配置最大个数，，0表示使用默认值',
    `max_aggr_size`     int(10) unsigned NOT NULL DEFAULT '0' COMMENT '单个聚合数据的子配置大小上限，单位为字节，0表示使用默认值',
    `max_history_count` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '最大变更历史数量',
    `gmt_create`        datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `gmt_modified`      datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_group_id` (`group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='集群、各Group容量信息表';

/******************************************/
/*   表名称 = his_config_info              */
/******************************************/
CREATE TABLE `his_config_info`
(
    `id`                 bigint(20) unsigned NOT NULL COMMENT 'id',
    `nid`                bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'nid, 自增标识',
    `data_id`            varchar(255) NOT NULL COMMENT 'data_id',
    `group_id`           varchar(128) NOT NULL COMMENT 'group_id',
    `app_name`           varchar(128)          DEFAULT NULL COMMENT 'app_name',
    `content`            longtext     NOT NULL COMMENT 'content',
    `md5`                varchar(32)           DEFAULT NULL COMMENT 'md5',
    `gmt_create`         datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `gmt_modified`       datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
    `src_user`           text COMMENT 'source user',
    `src_ip`             varchar(50)           DEFAULT NULL COMMENT 'source ip',
    `op_type`            char(10)              DEFAULT NULL COMMENT 'operation type',
    `tenant_id`          varchar(128)          DEFAULT '' COMMENT '租户字段',
    `encrypted_data_key` text         NOT NULL COMMENT '密钥',
    PRIMARY KEY (`nid`),
    KEY                  `idx_gmt_create` (`gmt_create`),
    KEY                  `idx_gmt_modified` (`gmt_modified`),
    KEY                  `idx_did` (`data_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='多租户改造';


/******************************************/
/*   表名称 = tenant_capacity              */
/******************************************/
CREATE TABLE `tenant_capacity`
(
    `id`                bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `tenant_id`         varchar(128) NOT NULL DEFAULT '' COMMENT 'Tenant ID',
    `quota`             int(10) unsigned NOT NULL DEFAULT '0' COMMENT '配额，0表示使用默认值',
    `usage`             int(10) unsigned NOT NULL DEFAULT '0' COMMENT '使用量',
    `max_size`          int(10) unsigned NOT NULL DEFAULT '0' COMMENT '单个配置大小上限，单位为字节，0表示使用默认值',
    `max_aggr_count`    int(10) unsigned NOT NULL DEFAULT '0' COMMENT '聚合子配置最大个数',
    `max_aggr_size`     int(10) unsigned NOT NULL DEFAULT '0' COMMENT '单个聚合数据的子配置大小上限，单位为字节，0表示使用默认值',
    `max_history_count` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '最大变更历史数量',
    `gmt_create`        datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `gmt_modified`      datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='租户容量信息表';

CREATE TABLE `tenant_info`
(
    `id`            bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
    `kp`            varchar(128) NOT NULL COMMENT 'kp',
    `tenant_id`     varchar(128) default '' COMMENT 'tenant_id',
    `tenant_name`   varchar(128) default '' COMMENT 'tenant_name',
    `tenant_desc`   varchar(256) DEFAULT NULL COMMENT 'tenant_desc',
    `create_source` varchar(32)  DEFAULT NULL COMMENT 'create_source',
    `gmt_create`    bigint(20) NOT NULL COMMENT '创建时间',
    `gmt_modified`  bigint(20) NOT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_tenant_info_kptenantid` (`kp`,`tenant_id`),
    KEY             `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='tenant_info';
INSERT INTO `tenant_info` (`kp`, `tenant_id`, `tenant_name`, `tenant_desc`, `create_source`, `gmt_create`, `gmt_modified`) VALUES ('1', 'dev', 'dev', '开发环境', 'nacos', 1711640015359, 1711640015359);
INSERT INTO `tenant_info` (`kp`, `tenant_id`, `tenant_name`, `tenant_desc`, `create_source`, `gmt_create`, `gmt_modified`) VALUES ('1', 'test', 'test', '测试环境', 'nacos', 1711640026204, 1711640026204);



CREATE TABLE `users`
(
    `username` varchar(50)  NOT NULL PRIMARY KEY COMMENT 'username',
    `password` varchar(500) NOT NULL COMMENT 'password',
    `enabled`  boolean      NOT NULL COMMENT 'enabled'
);
-- ----------------------------
INSERT INTO `users` (`username`, `password`, `enabled`) VALUES ('kaifadev', '$2a$10$qYlJW/BD80g6O3lN36RI2OXn.QcIDEi8aQOBB0gwuGtMuQcQnvuTm', 1);
INSERT INTO `users` (`username`, `password`, `enabled`) VALUES ('kaifatest', '$2a$10$WiUqrXF6dxCvPzjNbg1pT.TWQvwrnTLYgkqftxPOxfdy6f.PfvRM6', 1);
INSERT INTO `users` (`username`, `password`, `enabled`) VALUES ('leaderdev', '$2a$10$45PgfD0hjuUYGUVvE9uMuewit9fZgwb38LjSFBKzZfhkt6AKoNDCi', 1);
INSERT INTO `users` (`username`, `password`, `enabled`) VALUES ('leadertest', '$2a$10$/verw6GOIPzutINkvEKXveNVVX7ViZCmqEvC24PK2iwIEZeqrxXua', 1);
INSERT INTO `users` (`username`, `password`, `enabled`) VALUES ('nacos', '$2a$10$dTWQWzzcSpEj8v75DlSVw.P/WM2BfZuWG5rwxb3DCisAy7HcUyWxO', 1);


CREATE TABLE `roles`
(
    `username` varchar(50) NOT NULL COMMENT 'username',
    `role`     varchar(50) NOT NULL COMMENT 'role',
    UNIQUE INDEX `idx_user_role` (`username` ASC, `role` ASC) USING BTREE
);
INSERT INTO `roles` (`username`, `role`) VALUES ('kaifadev', 'kaifa_dev');
INSERT INTO `roles` (`username`, `role`) VALUES ('kaifatest', 'kaifa_test');
INSERT INTO `roles` (`username`, `role`) VALUES ('leaderdev', 'leader_dev');
INSERT INTO `roles` (`username`, `role`) VALUES ('leadertest', 'leader_text');
INSERT INTO `roles` (`username`, `role`) VALUES ('nacos', 'ROLE_ADMIN');

CREATE TABLE `permissions`
(
    `role`     varchar(50)  NOT NULL COMMENT 'role',
    `resource` varchar(255) NOT NULL COMMENT 'resource',
    `action`   varchar(8)   NOT NULL COMMENT 'action',
    UNIQUE INDEX `uk_role_permission` (`role`,`resource`,`action`) USING BTREE
);
INSERT INTO `permissions` (`role`, `resource`, `action`) VALUES ('kaifa_dev', 'dev:*:*', 'r');
INSERT INTO `permissions` (`role`, `resource`, `action`) VALUES ('kaifa_test', 'test:*:*', 'r');
INSERT INTO `permissions` (`role`, `resource`, `action`) VALUES ('leader_dev', 'dev:*:*', 'rw');
INSERT INTO `permissions` (`role`, `resource`, `action`) VALUES ('leader_text', 'test:*:*', 'rw');


