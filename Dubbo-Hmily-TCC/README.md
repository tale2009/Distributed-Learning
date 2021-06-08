根据[hmily-repository-database-mysql](https://github.com/dromara/hmily/blob/14d3d8d591505bb5bae5d06d26381563f280fce6/hmily-repository/hmily-repository-database/hmily-repository-database-mysql/src/main/resources/mysql/schema.sql)提示

创建Hmily使用Mysql对应的事务记录表
```
CREATE DATABASE  IF NOT EXISTS  `hmily`  DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci ;

USE `hmily`;

CREATE TABLE IF NOT EXISTS `hmily_lock`
(
    `trans_id`          bigint(20) not null comment '全局事务id',
    `participant_id`    bigint(20) not null comment 'hmily参与者id',
    `resource_id`       varchar(256) not null comment '资源id',
    `target_table_name` varchar(64)  not null comment '锁定目标表名',
    `target_table_pk`   varchar(64)  not null comment '锁定表主键',
    `create_time`       datetime     not null comment '创建时间',
    `update_time`       datetime     not null DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '更新时间',
    PRIMARY KEY (`resource_id`, `target_table_name`, `target_table_pk`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci comment 'hmily全局lock表';

create table if not exists `hmily_participant_undo`
(
    `undo_id`         bigint(20) not null comment '主键id' primary key,
    `participant_id`  bigint(20) not null comment '参与者id',
    `trans_id`        bigint(20) not null comment '全局事务id',
    `resource_id`     varchar(256) not null comment '资源id，tac模式下为jdbc url',
    `data_snapshot`   longblob     not null comment '回滚数据快照',
    `status`          tinyint      not null comment '状态',
    `create_time`     datetime     not null comment '创建时间',
    `update_time`     datetime     not null DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '更新时间'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci comment 'hmily事务参与者undo记录，用在AC模式';

create table if not exists `hmily_transaction_global`
(
    `trans_id`    bigint(20) not null comment '全局事务id' primary key,
    `app_name`    varchar(128) not null comment '应用名称',
    `status`      tinyint      not null comment '事务状态',
    `trans_type`  varchar(16)  not null comment '事务模式',
    `retry`       int                   default 0 not null comment '重试次数',
    `version`     int          not null comment '版本号',
    `create_time` datetime     not null comment '创建时间',
    `update_time` datetime     not null DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '更新时间'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci comment 'hmily事务表（发起者）';

create table if not exists `hmily_transaction_participant`
(
    `participant_id`     bigint(20)  not null comment '参与者事务id' primary key,
    `participant_ref_id` bigint(20)           comment '参与者关联id且套调用时候会存在',
    `trans_id`           bigint(20)  not null comment '全局事务id',
    `trans_type`         varchar(16)   not null comment '事务类型',
    `status`             tinyint       not null comment '分支事务状态',
    `app_name`           varchar(64)   not null comment '应用名称',
    `role`               tinyint       not null comment '事务角色',
    `retry`              int default 0 not null comment '重试次数',
    `target_class`       varchar(512)  null comment '接口名称',
    `target_method`      varchar(128)  null comment '接口方法名称',
    `confirm_method`     varchar(128)  null comment 'confirm方法名称',
    `cancel_method`      varchar(128)  null comment 'cancel方法名称',
    `confirm_invocation` longblob      null comment 'confirm调用点',
    `cancel_invocation`  longblob      null comment 'cancel调用点',
    `version`            int default 0 not null,
    `create_time`        datetime      not null comment '创建时间',
    `update_time`        datetime      not null DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '更新时间'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci comment 'hmily事务参与者';
```
2个库需要新建账户表和冻结表
```
CREATE TABLE `mytest`.`account`  (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `name` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL COMMENT '姓名',
  `cn_wallet` decimal(12, 2) NOT NULL COMMENT '人民币钱包',
  `us_wallet` decimal(12, 2) NOT NULL COMMENT '美元钱包',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci COMMENT = '账户' ROW_FORMAT = Dynamic;


CREATE TABLE `mytest`.`freeze_account`  (
  `id` bigint(20) NOT NULL COMMENT '用户id',
  `cn_freeze` decimal(12, 2) NULL DEFAULT NULL COMMENT '人民币冻结',
  `us_freeze` decimal(12, 2) NULL DEFAULT NULL COMMENT '美元冻结',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;


CREATE TABLE `mytest2`.`account`  (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `name` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL COMMENT '姓名',
  `cn_wallet` decimal(12, 2) NOT NULL COMMENT '人民币钱包',
  `us_wallet` decimal(12, 2) NOT NULL COMMENT '美元钱包',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci COMMENT = '账户' ROW_FORMAT = Dynamic;


CREATE TABLE `mytest2`.`freeze_account`  (
  `id` bigint(20) NOT NULL COMMENT '用户id',
  `cn_freeze` decimal(12, 2) NULL DEFAULT NULL COMMENT '人民币冻结',
  `us_freeze` decimal(12, 2) NULL DEFAULT NULL COMMENT '美元冻结',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;
```
使用docker创建zookeeper连接dubbo
```
docker run -p 21810:2181 --name some-zookeeper -d  zookeeper:3.4.14
```