create table question
(
	id int auto_increment,
	title varchar(50),
	description text,
	gmt_create bigint,
	like_count int default 0,
	creator int,
	comment_count int default 0,
	view_count int default 0,
	gmt_modified bigint,
	tag varchar(256),
	constraint QUESTION_PK
		primary key (id)
);

