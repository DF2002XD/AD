CREATE DATABASE IF NOT EXISTS hospital_mysql;

USE hospital_mysql;

CREATE TABLE IF NOT EXISTS tratamientos(
    id_tratamiento INT AUTO_INCREMENT PRIMARY KEY,
    nombre_tratamiento VARCHAR(100) NOT NULL,
    descripcion TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS pacientes(
    id_Paciente INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    fecha_Nacimiento DATE NOT NULL
);

CREATE TABLE IF NOT EXISTS citas (
    id_Cita INT AUTO_INCREMENT PRIMARY KEY,
    id_Paciente INT NOT NULL,
    date DATETIME NOT NULL,
    CONSTRAINT fk_citas_paciente FOREIGN KEY (id_Paciente) REFERENCES pacientes(id_Paciente)
);

CREATE TABLE IF NOT EXISTS pacientes_tratamientos(
    id_Paciente INT NOT NULL,
    id_Tratamiento INT NOT NULL,
    fecha_Inicio DATE NOT NULL,
    PRIMARY KEY (id_Paciente, id_Tratamiento),
    CONSTRAINT fk_paciente_tratamiento FOREIGN KEY (id_Paciente) REFERENCES pacientes(id_Paciente),
    CONSTRAINT fk_tratamiento FOREIGN KEY (id_Tratamiento) REFERENCES tratamientos(id_Tratamiento)
);

INSERT INTO pacientes (nombre, email, fecha_Nacimiento) VALUES
('Juan Perez', 'juan.perez@gmail.com', '1985-05-15'),
('Maria Gomez', 'maria.gomez@gmail.com', '1990-08-22'),
('Carlos Sanchez', 'carlos.sanchez@gmail.com', '1978-11-30'),
('Lucia Torres', 'lucia.torres@gmail.com', '1995-04-10'),
('Javier Morales', 'javier.morales@gmail.com', '1982-09-17'),
('Laura Ruiz', 'laura.ruiz@gmail.com', '1993-12-02'),
('David Fernandez', 'david.fernandez@gmail.com', '1988-03-28'),
('Sonia Alvarez', 'sonia.alvarez@gmail.com', '1996-01-12'),
('Pablo Navarro', 'pablo.navarro@gmail.com', '1975-04-08'),
('Carmen Ortiz', 'carmen.ortiz@gmail.com', '1987-02-19');

INSERT INTO tratamientos (nombre_tratamiento, descripcion) VALUES
('Fisioterapia', 'Tratamiento para mejorar la movilidad y reducir el dolor.'),
('Terapia Ocupacional', 'Ayuda a los pacientes a realizar actividades diarias.'),
('Rehabilitación Cardíaca', 'Programa para pacientes con enfermedades cardíacas.'),
('Rehabilitación Cardíaca', 'Ejercicios controlados y monitorizados para recuperar la función cardiovascular.'),
('Terapia Neuromotora', 'Estimulación nerviosa para mejorar el control motor.'),
('Control Pediátrico', 'Revisión y seguimiento del crecimiento infantil.'),
('Rehabilitación Física', 'Recuperación funcional tras lesiones musculares o fracturas.'),
('Tratamiento Dermatológico', 'Atención a problemas cutáneos, acné, dermatitis, psoriasis, etc.'),
('Entrenamiento Cognitivo', 'Estimulación mental para pacientes neurológicos.'),
('Chequeo General', 'Revisión general con especial foco en salud cardíaca.'),
('Terapia Post-quirúrgica', 'Rehabilitación tras intervenciones traumatológicas.');

INSERT INTO citas (id_Paciente, date) VALUES
(1, '2024-07-01 10:00:00'),
(2, '2024-07-02 11:30:00'),
(3, '2024-07-03 09:15:00'),
(4, '2024-07-04 08:00:00'),
(5, '2024-07-05 14:30:00'),
(6, '2024-07-06 09:45:00'),
(7, '2024-07-07 10:15:00'),
(8, '2024-07-08 16:00:00'),
(9, '2024-07-09 12:30:00'),
(10, '2024-07-10 13:00:00');
INSERT INTO pacientes_tratamientos (id_Paciente, id_Tratamiento, fecha_Inicio) VALUES
(1, 1, '2024-07-01'),
(2, 2, '2024-07-02'),
(3, 3, '2024-07-03'),
(2, 1, '2024-07-02'),
(3, 2, '2024-07-03'),
(4, 3, '2024-07-04'),
(5, 4, '2024-07-05'),
(6, 5, '2024-07-06'),
(7, 5, '2024-07-07'),
(1, 6, '2024-07-08'),
(2, 7, '2024-07-09'),
(3, 8, '2024-07-10'),
(4, 4, '2024-07-11'),
(8, 1, '2024-07-12'),
(9, 2, '2024-07-13'),
(10, 5, '2024-07-14'),
(5, 6, '2024-07-15'),
(6, 2, '2024-07-16');
