# 数据库 
#创建数据库
DROP DATABASE IF EXISTS yayaoadvertise_db;
CREATE DATABASE yayaoadvertise_db;

#使用数据库 
use yayaoadvertise_db;
#创建角色表
CREATE TABLE role_tb(
role_id int(11) NOT NULL AUTO_INCREMENT COMMENT '角色id',
name varchar(255) COMMENT '角色名',
duty varchar(255) COMMENT '角色职责',
update_date datetime COMMENT '角色更新时间',
PRIMARY KEY (role_id)
)ENGINE = InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='角色表';

#创建管理员表 
CREATE TABLE admin_tb(
admin_id int(11) NOT NULL AUTO_INCREMENT COMMENT '管理员id',
name varchar(255) COMMENT '管理员姓名',
cell_phone varchar(255) COMMENT '手机号',
email varchar(255) COMMENT '邮箱',
password varchar(255) COMMENT '密码',
money decimal(11,2) COMMENT '金钱',
recharge decimal(11,2) COMMENT '充值金钱',
withdrawals decimal(11,2) COMMENT '已提现金钱',
identity_cards varchar(255) COMMENT '身份证',
qq varchar(255) COMMENT 'QQ',
wechat varchar(255) COMMENT '微信号',
bank_user_name varchar(255) COMMENT '开户人',
bank_name varchar(255) COMMENT '开户银行',
bank_account varchar(255) COMMENT '银行账号',
bank_address varchar(255) COMMENT '开户银行地址',
status varchar(255) COMMENT '账号状态',
create_date datetime COMMENT '账号创建时间',
last_login_date datetime COMMENT '最新登录时间',
role_id int(11) COMMENT '角色id外键',
parent_id int(11) COMMENT '父id',
PRIMARY KEY (admin_id),
CONSTRAINT FK_ROLE_ADMIN FOREIGN KEY (role_id) REFERENCES role_tb (role_id) ON DELETE RESTRICT ON UPDATE RESTRICT,
INDEX idx_role_id (role_id) USING BTREE
)ENGINE = InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='管理员表';

#创建流水信息表 
CREATE TABLE water_information_tb(
water_information_id int(11) NOT NULL AUTO_INCREMENT COMMENT '流水信息id',
name varchar(255) COMMENT '名称',
type varchar(255) COMMENT '类型',
money decimal(11,2) COMMENT '金钱',
create_date datetime COMMENT '创建时间',
admin_id int(11) COMMENT '管理员id外键',
PRIMARY KEY (water_information_id),
CONSTRAINT FK_ADMIN_WATER_INFORMATION FOREIGN KEY (admin_id) REFERENCES admin_tb (admin_id) ON DELETE RESTRICT ON UPDATE RESTRICT,
INDEX idx_admin_id (admin_id) USING BTREE
)ENGINE = InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='流水信息表';

#创建权限表 
CREATE TABLE jurisdiction_tb(
jurisdiction_id int(11) NOT NULL AUTO_INCREMENT COMMENT '权限id',
name varchar(255) COMMENT '权限工作流名称',
addtion tinyint(4) COMMENT '增加',
deletion tinyint(4) COMMENT '删除',
updation tinyint(4) COMMENT '修改',
queries tinyint(4) COMMENT '查询',
update_date datetime COMMENT '权限更新时间',
role_id int(11) COMMENT '角色id外键',
PRIMARY KEY (jurisdiction_id),
CONSTRAINT FK_ROLE_JURISDICTION FOREIGN KEY (role_id) REFERENCES role_tb (role_id) ON DELETE RESTRICT ON UPDATE RESTRICT,
INDEX idx_role_id (role_id) USING BTREE
)ENGINE = InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='权限表';

#创建公告表 
CREATE TABLE notice_tb(
notice_id int(11) NOT NULL AUTO_INCREMENT COMMENT '公告id',
title varchar(255) COMMENT '标题',
type varchar(255) COMMENT '标签类型',
content longtext COMMENT '内容',
update_date datetime COMMENT '更新日期',
PRIMARY KEY (notice_id)
)ENGINE = InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='公告表';

#创建广告表 
CREATE TABLE advertise_tb(
advertise_id int(11) NOT NULL AUTO_INCREMENT COMMENT '广告id',
name varchar(255) COMMENT '名称',
type varchar(255) COMMENT '类型',
subtype varchar(255) COMMENT '子类型',
billing_mode varchar(255) COMMENT '计费模式',
region varchar(255) COMMENT '地域',
title varchar(255) COMMENT '标题',
img varchar(255) COMMENT '广告图片',
link varchar(255) COMMENT '链接地址',
unit_price decimal(11,2) COMMENT '单价',
unit_delivery_number int(11) COMMENT '投放次数',
now_unit_delivery_number int(11) COMMENT '消耗的投放次数',
unit_money decimal(11,2) COMMENT '广告金额',
now_unit_money decimal(11,2) COMMENT '消耗的广告金额',
status varchar(255) COMMENT '广告状态',
start_delivery_date datetime COMMENT '投放开始时间',
end_delivery_date datetime COMMENT '投放结束时间',
update_date datetime COMMENT '更新时间',
admin_id int(11) COMMENT '管理员id外键',
PRIMARY KEY (advertise_id),
CONSTRAINT FK_ADMIN_ADVERTISE FOREIGN KEY (admin_id) REFERENCES admin_tb (admin_id) ON DELETE RESTRICT ON UPDATE RESTRICT,
INDEX idx_admin_id (admin_id) USING BTREE
)ENGINE = InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='广告表';

#创建广告数据表 
CREATE TABLE advertise_data_tb(
advertise_data_id int(11) NOT NULL AUTO_INCREMENT COMMENT '广告数据id',
pvs int(11) COMMENT 'pvs',
uvs int(11) COMMENT 'uvs',
ips int(11) COMMENT 'ips',
forward int(11) COMMENT '转发数',
daily_day datetime COMMENT '每日日期',
advertise_id int(11) COMMENT '广告id',
PRIMARY KEY (advertise_data_id),
CONSTRAINT FK_ADVERTISE_ADVERTISE_DATA FOREIGN KEY (advertise_id) REFERENCES advertise_tb (advertise_id) ON DELETE RESTRICT ON UPDATE RESTRICT,
INDEX idx_advertise_id (advertise_id) USING BTREE
)ENGINE = InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='广告数据表';

#创建广告位表 
CREATE TABLE advertise_space_tb(
advertise_space_id int(11) NOT NULL AUTO_INCREMENT COMMENT '广告位id',
name varchar(255) COMMENT '名称',
platform varchar(255) COMMENT '投放平台',
type varchar(255) COMMENT '展示类型',
business_type varchar(255) COMMENT '业务类型',
billing_mode varchar(255) COMMENT '计费模式',
region varchar(255) COMMENT '地域',
location varchar(255) COMMENT '广告位置',
unit_price decimal(11,2) COMMENT '单价',
now_unit_delivery_number int(11) COMMENT '获取的点击次数',
now_unit_money decimal(11,2) COMMENT '获取的广告位金额',
status varchar(255) COMMENT '广告位状态',
update_date datetime COMMENT '更新时间',
admin_id int(11) COMMENT '管理员id外键',
PRIMARY KEY (advertise_space_id),
CONSTRAINT FK_ADMIN_ADVERTISE_SPACE FOREIGN KEY (admin_id) REFERENCES admin_tb (admin_id) ON DELETE RESTRICT ON UPDATE RESTRICT,
INDEX idx_admin_id (admin_id) USING BTREE
)ENGINE = InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='广告位表';

#创建广告位数据表 
CREATE TABLE advertise_space_data_tb(
advertise_space_data_id int(11) NOT NULL AUTO_INCREMENT COMMENT '广告位数据id',
pvs int(11) COMMENT 'pvs',
uvs int(11) COMMENT 'uvs',
ips int(11) COMMENT 'ips',
forward int(11) COMMENT '转发数',
daily_day datetime COMMENT '每日日期',
advertise_space_id int(11) COMMENT '广告位id',
PRIMARY KEY (advertise_space_data_id),
CONSTRAINT FK_ADVERTISE_SPACE_ADVERTISE_SPACE_DATA FOREIGN KEY (advertise_space_id) REFERENCES advertise_space_tb (advertise_space_id) ON DELETE RESTRICT ON UPDATE RESTRICT,
INDEX idx_advertise_space_id (advertise_space_id) USING BTREE
)ENGINE = InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='广告位数据表';

#创建广告位广告关系表
CREATE TABLE advertise_relate_tb(
advertise_relate_id int(11) NOT NULL AUTO_INCREMENT COMMENT '广告位广告关系id',
update_date datetime COMMENT '更新时间',
advertise_space_id int(11) COMMENT '广告位id外键',
advertise_id int(11) COMMENT '广告id外键',
PRIMARY KEY (advertise_relate_id),
CONSTRAINT FK_ADVERTISE_SPACE_ADVERTISE_RELATE FOREIGN KEY (advertise_space_id) REFERENCES advertise_space_tb (advertise_space_id) ON DELETE CASCADE ON UPDATE CASCADE,
CONSTRAINT FK_ADVERTISE_ADVERTISE_RELATE FOREIGN KEY (advertise_id) REFERENCES advertise_tb (advertise_id) ON DELETE CASCADE ON UPDATE CASCADE,
INDEX idx_advertise_space_id (advertise_space_id) USING BTREE,
INDEX idx_advertise_id (advertise_id) USING BTREE
)ENGINE = InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='广告位广告关系表';

#创建网站表 
CREATE TABLE website_tb(
website_id int(11) NOT NULL AUTO_INCREMENT COMMENT '网站id',
name varchar(255) COMMENT '名称',
type varchar(255) COMMENT '类型',
url varchar(255) COMMENT '网站url',
status varchar(255) COMMENT '网站状态',
remark varchar(255) COMMENT '备注',
update_date datetime COMMENT '更新时间',
admin_id int(11) COMMENT '管理员id外键',
PRIMARY KEY (website_id),
CONSTRAINT FK_ADMIN_WEBSITE FOREIGN KEY (admin_id) REFERENCES admin_tb (admin_id) ON DELETE RESTRICT ON UPDATE RESTRICT,
INDEX idx_admin_id (admin_id) USING BTREE
)ENGINE = InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='网站表';

#设置初始角色
INSERT IGNORE INTO role_tb (name,duty,update_date) 
VALUES ("超级管理员","超级管理员",now());
INSERT IGNORE INTO role_tb (name,duty,update_date) 
VALUES ("财务","金钱进出账",now());  
INSERT IGNORE INTO role_tb (name,duty,update_date) 
VALUES ("渠道管理员","管理渠道主",now());  
INSERT IGNORE INTO role_tb (name,duty,update_date) 
VALUES ("广告管理员","管理广告主",now());  
INSERT IGNORE INTO role_tb (name,duty,update_date) 
VALUES ("渠道主","投放广告位",now());  
INSERT IGNORE INTO role_tb (name,duty,update_date) 
VALUES ("广告主","推广广告",now());  
INSERT IGNORE INTO role_tb (name,duty,update_date) 
VALUES ("二级渠道主","投放广告位二级代理",now());  
INSERT IGNORE INTO role_tb (name,duty,update_date) 
VALUES ("二级广告主","推广广告二级代理",now());  

#设置初始权限
INSERT IGNORE INTO jurisdiction_tb (name,addtion,deletion,updation,queries,update_date,role_id) 
VALUES ("全部","1","1","1","1",now(),"1000");
INSERT IGNORE INTO jurisdiction_tb (name,addtion,deletion,updation,queries,update_date,role_id) 
VALUES ("全部","1","0","0","1",now(),"1002");

#设置初始管理员密码MD5加密123456
INSERT IGNORE INTO admin_tb (name,cell_phone,email,password,create_date,last_login_date,role_id) 
VALUES ("聂跃","15111336587","278076304@qq.com","11874bb6149dd45428da628c9766b252",now(),now(),"1000");  

