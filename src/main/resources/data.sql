INSERT INTO patient_tbl
(name, birth_date, gender, email, blood_group_type, created_at, patient_insurance_id)
VALUES ('Ravi Kumar', '1995-06-12', 'Male', 'ravi.kumar@example.com', 'A_Positive', NOW(), null);


INSERT INTO doctor_tbl (email, name, specialization) VALUES ('doctor@example.com', 'Dr. John Doe', 'Cardiology');
