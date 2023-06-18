/*
SQLyog 
MySQL - 5.7.29-log : Database - gmall_realtime
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
/*Table structure for table `table_process` */

CREATE TABLE `table_process` (
  `source_table` varchar(200) NOT NULL COMMENT '来源表',
  `sink_table` varchar(200) DEFAULT NULL COMMENT '输出表',
  `sink_columns` varchar(2000) DEFAULT NULL COMMENT '输出字段',
  `sink_pk` varchar(200) DEFAULT NULL COMMENT '主键字段',
  `sink_extend` varchar(200) DEFAULT NULL COMMENT '建表扩展',
  PRIMARY KEY (`source_table`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `table_process` */
INSERT INTO `table_process`(`source_table`, `sink_table`, `sink_columns`, `sink_pk`, `sink_extend`) VALUES ('activity_info', 'dim_activity_info', 'id,activity_name,activity_type,activity_desc,start_time,end_time,create_time', 'id', NULL);
INSERT INTO `table_process`(`source_table`, `sink_table`, `sink_columns`, `sink_pk`, `sink_extend`) VALUES ('activity_rule', 'dim_activity_rule', 'id,activity_id,activity_type,condition_amount,condition_num,benefit_amount,benefit_discount,benefit_level', 'id', NULL);
INSERT INTO `table_process`(`source_table`, `sink_table`, `sink_columns`, `sink_pk`, `sink_extend`) VALUES ('activity_sku', 'dim_activity_sku', 'id,activity_id,sku_id,create_time', 'id', NULL);
INSERT INTO `table_process`(`source_table`, `sink_table`, `sink_columns`, `sink_pk`, `sink_extend`) VALUES ('base_category1', 'dim_base_category1', 'id,name', 'id', NULL);
INSERT INTO `table_process`(`source_table`, `sink_table`, `sink_columns`, `sink_pk`, `sink_extend`) VALUES ('base_category2', 'dim_base_category2', 'id,name,category1_id', 'id', NULL);
INSERT INTO `table_process`(`source_table`, `sink_table`, `sink_columns`, `sink_pk`, `sink_extend`) VALUES ('base_category3', 'dim_base_category3', 'id,name,category2_id', 'id', NULL);
INSERT INTO `table_process`(`source_table`, `sink_table`, `sink_columns`, `sink_pk`, `sink_extend`) VALUES ('base_province', 'dim_base_province', 'id,name,region_id,area_code,iso_code,iso_3166_2', NULL, NULL);
INSERT INTO `table_process`(`source_table`, `sink_table`, `sink_columns`, `sink_pk`, `sink_extend`) VALUES ('base_region', 'dim_base_region', 'id,region_name', NULL, NULL);
INSERT INTO `table_process`(`source_table`, `sink_table`, `sink_columns`, `sink_pk`, `sink_extend`) VALUES ('base_trademark', 'dim_base_trademark', 'id,tm_name', 'id', NULL);
INSERT INTO `table_process`(`source_table`, `sink_table`, `sink_columns`, `sink_pk`, `sink_extend`) VALUES ('coupon_info', 'dim_coupon_info', 'id,coupon_name,coupon_type,condition_amount,condition_num,activity_id,benefit_amount,benefit_discount,create_time,range_type,limit_num,taken_count,start_time,end_time,operate_time,expire_time,range_desc', 'id', NULL);
INSERT INTO `table_process`(`source_table`, `sink_table`, `sink_columns`, `sink_pk`, `sink_extend`) VALUES ('coupon_range', 'dim_coupon_range', 'id,coupon_id,range_type,range_id', 'id', NULL);
INSERT INTO `table_process`(`source_table`, `sink_table`, `sink_columns`, `sink_pk`, `sink_extend`) VALUES ('financial_sku_cost', 'dim_financial_sku_cost', 'id,sku_id,sku_name,busi_date,is_lastest,sku_cost,create_time', 'id', NULL);
INSERT INTO `table_process`(`source_table`, `sink_table`, `sink_columns`, `sink_pk`, `sink_extend`) VALUES ('sku_info', 'dim_sku_info', 'id,spu_id,price,sku_name,sku_desc,weight,tm_id,category3_id,sku_default_img,is_sale,create_time', 'id', ' SALT_BUCKETS = 4');
INSERT INTO `table_process`(`source_table`, `sink_table`, `sink_columns`, `sink_pk`, `sink_extend`) VALUES ('spu_info', 'dim_spu_info', 'id,spu_name,description,category3_id,tm_id','id', 'SALT_BUCKETS = 3');
INSERT INTO `table_process`(`source_table`, `sink_table`, `sink_columns`, `sink_pk`, `sink_extend`) VALUES ('user_info', 'dim_user_info', 'id,login_name,name,user_level,birthday,gender,create_time,operate_time', 'id', ' SALT_BUCKETS = 3');