测试用此脚本建表
```
CREATE TABLE `eshop_address` (
  `address_id` bigint  NOT NULL COMMENT '地址id',
  `user_id` bigint  NOT NULL COMMENT '用户id',
  `address_name` varchar (50) NOT NULL COMMENT '收货人姓名',
  `country_code` char(5) COMMENT '国际区号',
  `phone` varchar (11) NOT NULL COMMENT '手机号码',
  `province_code` char(2) not null COMMENT '省码',
  `city_code` bigint(20)  COMMENT '市码',
  `region_code` char(2) COMMENT '区码',
  `detail` varchar(256) not null comment '详细地址',
  `create_user_id` bigint(20) NOT NULL COMMENT '创建人id',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `modify_user_id` bigint(20) NOT NULL COMMENT '编辑人id',
  `modify_time` datetime NOT NULL COMMENT '编辑时间',
  PRIMARY KEY (`address_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='收货地址';
```