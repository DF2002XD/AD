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
('Carlos Sanchez', 'carlos.sanchez@gmail.com', '1978-11-30');
INSERT INTO tratamientos (nombre_tratamiento, descripcion) VALUES
('Fisioterapia', 'Tratamiento para mejorar la movilidad y reducir el dolor.'),
('Terapia Ocupacional', 'Ayuda a los pacientes a realizar actividades diarias.'),
('Rehabilitación Cardíaca', 'Programa para pacientes con enfermedades cardíacas.');
INSERT INTO citas (id_Paciente, date) VALUES
(1, '2024-07-01 10:00:00'),
(2, '2024-07-02 11:30:00'),
(3, '2024-07-03 09:15:00');
INSERT INTO pacientes_tratamientos (id_Paciente, id_Tratamiento, fecha_Inicio) VALUES
(1, 1, '2024-07-01'),
(2, 2, '2024-07-02'),
(3, 3, '2024-07-03');
