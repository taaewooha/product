drop table product;

create table product (
    product_id          number,         --상품 아이디
    product_name        varchar2(50),   --상품명
    product_quantity    number,   --상품수량
    product_price       number   --상품가격
);

--기본키생성
alter table product add Constraint product_product_id_pk primary key (product_id);

--제약조건
alter table product modify product_id constraint product_passwd_uk unique;
alter table product modify product_id constraint product_passwd_nn not null;

--시퀀스
drop sequence product_product_id_seq;
create sequence product_product_id_seq;

--테이블 구조확인
desc product;

--값 넣어보기
insert into product values(product_product_id_seq.nextval, '우유', '100', '1500');
--값 확인해보기
select * from product;
--rollback;
--commit;