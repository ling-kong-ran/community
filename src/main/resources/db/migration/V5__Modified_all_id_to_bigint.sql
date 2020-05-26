alter table question modify id bigint auto_increment;
alter table user modify id bigint auto_increment;
alter table tquestion modify creator bigint null comment '问题创建人id';
