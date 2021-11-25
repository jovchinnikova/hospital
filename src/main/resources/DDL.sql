create database hospital;
use hospital;

create table if not exists Suppliers(
id serial,
name varchar(45) not null,
country varchar(45) not null,
primary key(id)
);

create table if not exists Equipments(
id serial,
name varchar(45) not null,
price decimal(5,1) unsigned,
primary key(id)
);

create table if not exists Equipment_suppliers(
id serial,
supplier_id bigint unsigned not null,
equipment_id bigint unsigned not null,
primary key(id),
constraint fk_equipment_suppliers_equipment_id foreign key(equipment_id) references Equipments(id)
on update no action on delete cascade,
constraint fk_equipment_suppliers_supplier_id foreign key(supplier_id) references Suppliers(id)
on update no action on delete cascade
);

create table if not exists Medications(
id serial,
name varchar(45) not null unique,
form varchar(45) not null,
dosage double unsigned,
price decimal(5,1) unsigned,
primary key(id)
);

create table if not exists Medication_Suppliers(
id serial,
medication_id bigint unsigned not null,
supplier_id bigint unsigned not null,
primary key(id),
constraint fk_medication_suppliers_medication_id foreign key(medication_id) references Medications(id)
on update no action on delete cascade,
constraint fk_medications_suppliers_supplier_id foreign key(supplier_id) references Suppliers(id)
on update no action on delete cascade
);

create table if not exists Addresses(
id serial,
city varchar(45) not null,
street varchar(45) not null,
building_number varchar(45) not null,
primary key(id)
);

create table if not exists Specializations(
id serial,
name varchar(45) not null unique,
salary decimal(5,1) unsigned not null,
primary key(id)
);

create table if not exists Departments(
id serial,
title varchar(45) not null,
department_head_id bigint unsigned not null,
hospital_id bigint unsigned not null,
primary key(id)
);

create table if not exists Employees(
id serial,
first_name varchar(45) not null,
last_name varchar(45) not null,
specialization_id bigint unsigned not null,
department_id bigint unsigned,
position varchar(45),
qualification int unsigned,
primary key(id),
constraint fk_Employees_specialization_id foreign key(specialization_id) references Specializations(id)
on update no action on delete cascade,
constraint fk_Employees_department_id foreign key(department_id) references Departments(id)
on update no action on delete cascade
);

alter table Departments add constraint fk_Departments_department_head_id foreign key(department_head_id) references Employees(id)
on update no action on delete cascade;

create table if not exists Hospitals(
id serial,
title varchar(45) not null,
chief_doctor_id bigint unsigned not null,
address_id bigint unsigned not null,
phone_number int unsigned,
primary key(id),
constraint fk_Hospitals_address_id foreign key(address_id) references Addresses(id)
on update no action on delete cascade,
constraint fk_Hospitals_chief_doctor_id foreign key(chief_doctor_id) references Employees(id)
on update no action on delete cascade
);

alter table Departments add constraint fk_Departments_hospital_id foreign key(hospital_id) references Hospitals(id)
on update no action on delete cascade;

create table if not exists Wards(
id serial,
number int unsigned not null,
floor int unsigned not null,
department_id bigint unsigned not null,
primary key(id),
constraint fk_Wards_department_id foreign key(department_id) references Departments(id)
on update no action on delete cascade
);

create table if not exists Patients(
id serial,
first_name varchar(45) not null,
last_name varchar(45) not null,
age int unsigned not null,
ward_id bigint unsigned not null,
diagnosis varchar(45),
primary key(id),
constraint fk_Patients_ward_id foreign key(ward_id) references Wards(id)
on update no action on delete cascade
);

create table if not exists Department_equipments(
id serial,
department_id bigint unsigned not null,
equipment_id bigint unsigned not null,
quantity int unsigned,
primary key(id),
constraint fk_Department_equipments_department_id foreign key(department_id) references Departments(id)
on update no action on delete cascade,
constraint fk_Department_equipments_equipment_id foreign key(equipment_id) references Equipments(id)
on update no action on delete cascade
);

create table if not exists Department_medications(
id serial,
department_id bigint unsigned not null,
medication_id bigint unsigned not null,
quantity int unsigned,
primary key(id),
constraint fk_Department_medications_department_id foreign key(department_id) references Departments(id)
on update no action on delete cascade,
constraint fk_Department_medications_medication_id foreign key(medication_id) references Medications(id)
on update no action on delete cascade
);