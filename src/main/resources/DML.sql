use hospital;

insert into Suppliers (name,country) values
	("Belmedtechnic","Belarus"),
	("Belmedpreparaty","Belarus"),
	("DealMed","Russia"),
	("Tagler","Russia"),
	("Sigma","Germany"),
    ("Vermeiren","Belgium"),
    ("Glavpharm", "Russia"),
    ("Atalantique repartition","France");
    
insert into Equipments (name,price) values
	("dropper", 20.4),
	("deffibrilator", 100.8),
    ("ultrasonic dissector", 300.7),
    ("feeding tube", 3.0),
    ("operation table", 30.6);
    
insert into Medications (name,form,dosage,price) values
	("Ibypophen","pills", 5.0, 4.2),
    ("Cetorolac","pills", 3.4, 2.6),
    ("Ambroksol","pills", 2.1, 1.2),
    ("Furasol","powder", 5, 0.9),
    ("Levomekol","ointment", 7, 2.3);
    
insert into Equipment_suppliers (equipment_id,supplier_id) values
	(4,1),
    (1,5),
    (2,4),
    (3,3),
    (5,6),
    (1,1),
    (2,8);
    
insert into Medication_suppliers (medication_id, supplier_id) values
	(1,2),
    (2,4),
    (3,6),
    (4,7),
    (4,4),
    (5,7),
    (3,5);
    
insert into Addresses (city,street,building_number) values
	("Minsk","Uralskaya","5/1"),
	("Minsk","Filatava","9"),
	("Vitebsk","Frunze","71");

insert into Specializations (name,salary) values
	("anesthetist",1000.0),
	("cardiologist",1500.2),
    ("oncologist",1600.3),
    ("surgeon",1100.5),
    ("traumatologist",990.7),
    ("neuropathologist",800.0),
    ("dentist",2000.7),
    ("therapist",850.3);
    
insert into Employees (first_name, last_name, specialization_id, position) values
	("Ivan","Ivanov",2,"chief doctor"),
    ("Petr", "Petrov",3,"chief doctor"),
    ("Maria", "Loseva",5, "chief doctor"),
    ("Vladimir","Vladimirov",1,"head of department"),
    ("Petr","Ivanov",2,"head of department"),
    ("Zinaida","Morozova",3,"head of department"),
    ("Irina","Novik",4,"head of department"),
    ("Ivan","Vasiliev",5,"head of department"),
    ("Dmitriy","Oskin",6,"head of department"),
    ("Elena","Shavruk",7,"head of department"),
    ("Vasiliy","Novikov",8,"head of department"),
    ("Viktor","Mironenko",8,"head of department"),
    ("Andrey", "Sedyn",4,"head of department"),
    ("Nastasya","Novik",5,"head of department"),
    ("Alexandr","Bylova",2,"head of department"),
    ("Olga","Piluga",1,"head of department"),
    ("Ellina","Dashuk",1,"head of department"),
    ("Galina","Podgornaya",2,"head of department"),
	("Nikolay","Punchik",4,"head of department"),
    ("Vasiliy","Korchik",5,"head of department");
    
insert into Hospitals (title, chief_doctor_id, address_id, phone_number) values
	("City clinical hospital №6",1,1,12345),
    ("City clinical hospital №5",2,2,67891),
    ("Ambulance hospital",3,3,13579);

insert into Departments (title, department_head_id, hospital_id) values
	("therapeutic",11,1), 
	("cardiological",5,1), 
    ("oncological",6,1), 
    ("neurological",9,1), 
    ("anesthetic and intensive care",4,1), 
    ("surgical",7,1), 
    ("traumatological",8,1), 
    ("dental",10,1), 
    ("therapeutic",12,2), 
    ("surgical",13,2), 
    ("traumatological",14,2), 
    ("cardiological",15,2), 
    ("anesthetic and intensive care",16,2), 
    ("anesthetic and intensive care",17,3), 
    ("cardiological",18,3), 
    ("surgical",19,3), 
    ("traumatological",20,3); 
    
insert into Employees (first_name,last_name,specialization_id,department_id,qualification) values
	("Alexandr","Konyukh", 8, 1, 2),
    ("Alexandr","Vorobyev", 2, 2, 1),
    ("Valeriy","Litvinets", 5, 11, 2),
    ("Yakub","Kolas",7,12,2),
    ("Yanka","Kupala",2,15,2),
    ("Dmitriy","Marushka",4,16,2);
    
insert into Wards (number,floor,department_id) values
	(100,1,7),
	(230,2,15),
	(310,3,3),
	(470,4,11),
	(529,5,16),
	(114,1,9);

insert into Patients (first_name, last_name, age, ward_id, diagnosis) values
	("Diana","Melnikova",48,3,"asthma"),
    ("Valeria","Sidyako",22,2,"flu"),
    ("Vladislav","Kolesnik",56,1,"pneumonia"),
    ("Kristina","Kolesnik",43,1,"pneumonia");

insert into Patients (first_name, last_name, age, ward_id) values
	("Maria","Subbotko",87,5),
    ("Polina","Ivanova",13,6);
    
insert into Department_equipments (department_id, equipment_id, quantity) values
	(4,5,32),
    (15,3,100),
    (10,1,490),
    (9,2,10),
    (7,4,51),
    (13,2,76),
    (1,5,90);
    
insert into Department_medications (department_id, medication_id, quantity) values
	(5,1,76),
    (16,2,54),
    (7,3,71),
    (8,5,300),
    (12,4,179),
    (13,4,270),
    (16,1,560);