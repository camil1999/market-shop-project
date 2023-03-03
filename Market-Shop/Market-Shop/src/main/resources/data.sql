insert into users
(username,password,enabled,type)
values
('admin1','{noop}admin',1,'Admin'),
('kassir1','{noop}12',1,'Cashier');

insert into admins
(name,surname,email,phone,username)
values
('Jamil','Mammadov','mamedovcamil@gmail.com','0552310290','admin1');

insert into cashiers
(name,surname,email,phone,username)
values
('Ilkin','kassirinSoyadi','kassir@gmail.com','0558964751','kassir1');


insert into authorities
(username, authority)
values
('admin1','Admin'),
('kassir1','Cashier');

insert into products
(name,barcode,price,cost,description,register_date,update_date,quantity,percent)
values
('Koynek','19990630',30.0,15.0,'Qisaqol','2023-02-02','2023-02-03',7,100),
('Salvar','20000917',45.0,20.0,'Cins','2023-02-02','2023-02-03',8,125),
('Pencek','19800410',50.0,30.0,'Qara','2023-02-02','2023-02-03',6,66.6),
('Ayaqqabi','1981110',65.0,40.0,'Qisaqol','2023-02-02','2023-02-03',2,62.5);


insert into sales
(product_name,price,cost,quantity,sold_date,cashier_name,total_price,profit)
values
('Koynek',30.0,15.0,2,'2023-01-01','kassir1',60.0,30.0),
('Salvar',45.0,20.0,2,'2023-01-05','kassir1',90.0,50.0),
('Pencek',55.0,30.0,3,'2023-01-10','kassir1',165.0,75.0),
('Ayaqqabı',65.0,40.0,3,'2023-01-15','kassir1',195.0,75.0),
('Pencek',55.0,30.0,1,'2023-01-20','kassir1',55.0,25.0),
('Koynek',30.0,15.0,1,'2023-01-25','kassir1',30.0,15.0),
('Ayaqqabı',65.0,40.0,5,'2023-01-30','kassir1',325.0,125.0);
 
insert into reports
(total_turnover,consumption,profit)
values
(920,1050,395);