create table sys_user (
                          id bigint not null auto_increment primary key comment '用户ID',
                          user_name varchar(50) comment '用户名',
                          user_password varchar(50) comment '密码',
                          user_email varchar(50) comment '邮箱',
                          user_info text comment '简介',
                          head_img blob comment '头像',
                          create_time datetime comment '创建时间'
);

alter table sys_user comment '用户表';

create table sys_role (
                          id bigint not null auto_increment primary key comment '角色ID',
                          role_name varchar(50) comment '角色名',
                          enabled int comment '有效标识',
                          create_by bigint comment '创建人',
                          create_time datetime comment '创建时间'
);

alter table sys_role comment '角色表';

create table sys_privilege (
                               id bigint not null auto_increment primary key comment '权限ID',
                               privilege_name varchar(50) comment '权限名',
                               privilege_url varchar(200) comment '权限URL'
);

alter table sys_privilege comment '权限表';

create table sys_user_role (
                               user_id bigint comment '用户ID',
                               role_id bigint comment '角色ID'
);
alter table sys_user_role comment '用户角色关联表';

create table sys_role_privilege (
                                    role_id bigint comment '角色ID',
                                    privilege_id bigint comment '权限ID'
);
alter table sys_role_privilege comment '角色权限关联表';

insert into sys_user
values (1, 'admin', '123456', 'admin@mybatis.tk', '管理员', null, now()),
       (1001, 'test', '123456', 'test@mybatis.tk', '测试用户', null, now());

insert into sys_role
values (1, '管理员', '1', '1', now()),
       (2, '普通用户', '1', '1', now());

insert into sys_user_role
    value (1,1),(1,2),(1001,2);

insert into sys_privilege
values (1, '用户管理', '/users'),(2, '角色管理', '/roles'),
       (3, '系统日志', '/logs'),(4, '人员维护', '/persons'),
       (5, '单位维护', '/companies');

insert into sys_role_privilege
values (1,1),(1,3),(1,2),(2,4),(2, 5);
