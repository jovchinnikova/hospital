use hospital;

#All heads of departments will have qualification 3
update Employees set qualification = 3 where position = "head of department";

#All chief doctors will have qualification 4
update Employees set qualification = 4 where position="chief doctor";

#Neuropathologist's salary will be changed to 980
update Specializations set salary = 980 where name = "neuropathologist";

#Deffibrilators will be delivired in quantity 560 to all departments
update Department_equipments set quantity = 560 where equipment_id = (select id from Equipments where name = "deffibrilator");

#Phone number in City clinical hospital №6 will be changed to 12354
update Hospitals set phone_number = 12354 where title = "City clinical hospital №6";

#Deletes Tagler supplier
delete from Suppliers where name = "Tagler";

#Deletes patients with age 22
delete from Patients where age = 22;

#Shows all the info about hospital: title, chief doctor's firat name and last name, full address and phone number
select h.title as hospital, e.first_name, e.last_name, a.city, a.street, a.building_number, h.phone_number from Hospitals h 
inner join Employees e on h.chief_doctor_id = e.id inner join Addresses a on h.address_id = a.id;

/*These queries show list of departments and firat names and last names of their heads
according to a hospital (first,second,third hospitals)*/
select d.title as department, e.first_name, e.last_name from Departments d inner join Employees e on d.department_head_id = e.id
where d.hospital_id = 1;
select d.title as department, e.first_name, e.last_name from Departments d inner join Employees e on d.department_head_id = e.id
where d.hospital_id = 2;
select d.title as department, e.first_name, e.last_name from Departments d inner join Employees e on d.department_head_id = e.id
where d.hospital_id = 3;

#These queries show the list of equipment/medications with the info about suppliers
select e.name as equipment, s.name as supplier, s.country, e.price from Equipment_Suppliers es inner join Equipments e
on es.equipment_id = e.id inner join Suppliers s on es.supplier_id = s.id;
select m.name as medication, m.form, m.dosage, s.name as supplier, s.country, m.price from Medication_Suppliers ms inner join Medications m
on ms.medication_id = m.id inner join Suppliers s on ms.supplier_id = s.id;

#These queries show quantity of equipment/medcations by department
select d.title as department, count(e.id) as equipment_quantity from Department_equipments de inner join Departments d
on de.department_id = d.id inner join Equipments e on de.equipment_id = e.id group by d.title;
select d.title as department, count(m.id) as medication_quantity from Department_medications dm inner join Departments d
on dm.department_id = d.id inner join Medications m on dm.medication_id = m.id group by d.title;

#Shows hospitals' titles and quantity of departments for each of them
select h.title as hospital, count(d.id) as department_quantity from Departments d inner join Hospitals h
on d.hospital_id = h.id group by h.title;

/*Shows first name, last name, specializatio and salary of the employee that earns more than 1000
ordered by salary descending*/
select first_name, last_name, s.name, s.salary from Employees e inner join Specializations s 
on e.specialization_id = s.id where s.salary > 1000 order by s.salary desc;

#Shows average salary of employees by departments inn City clinical hospital №6
select d.title as department, avg(s.salary) as average_salary from Departments d inner join Employees e on d.id = e.department_id
inner join Specializations s on e.specialization_id = s.id 
where d.hospital_id = (select id from Hospitals where title = "City clinical hospital №6") group by d.title;

#Shows quantity of patients in cardiological department
select d.title as department, count(p.id) as patients_quantity from Departments d inner join Wards w
on d.id = w.department_id inner join Patients p on w.id = p.ward_id group by d.title;

#Shows all the info about wards and patients in them ordered by ward number ascending
select w.number as ward, w.floor, p.first_name, p.last_name, p.diagnosis from Wards w left join Patients p
on w.id = p.ward_id order by w.number;

#Shows first name and last name of patients whose diagnosis wasn't established
select first_name, last_name from Patients where diagnosis is null order by first_name;

#Shows all the info about the employees with surgeon specialization and qualification 2
select * from Employees where specialization_id = (select id from Specializations where name = "surgeon") and qualification = 2;

#Shows first name and last name of the oldest patient
select first_name, last_name, age from Patients where age = (select max(age) from Patients);

#Shows a list of equipments and medications
select name from Equipments union select name from Medications;

#Shows all employees and clients areder by first name ascending
select first_name, last_name from Employees e union select first_name, last_name
 from Patients order by first_name;
 
#Deletes medications cheaper than 2
delete from Medications where price < 2;
 
#Deletes Cetorolac from all departments
delete from Department_medications 
where medication_id = (select id from Medications where name = "Cetorolac");

#Deletes surgeons with qualification 2
delete from Employees where specialization_id = (select id from Specializations where name = "surgeon") and qualification = 2;
