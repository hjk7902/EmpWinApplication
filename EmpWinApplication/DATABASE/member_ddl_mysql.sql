create table member (
	memberid varchar(20) primary key,
	name varchar(20) not null,
	phone varchar(20) unique,
	age int(3)
);

insert into member values ('user1', '홍길동', null, 30);

select * from member;
