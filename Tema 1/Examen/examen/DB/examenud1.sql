	-- Crear un esquema para el concesionario
	CREATE SCHEMA concesionario;

	-- Definir tipos de datos personalizados
	CREATE TYPE concesionario.Cliente AS (
	    nombre VARCHAR(255),
	    edad INT
	);

	CREATE TYPE concesionario.Coche AS (
	    modelo VARCHAR(50),
	    año_fabricacion INT
	);

	CREATE TYPE concesionario.Vendedor AS (
	    cedula VARCHAR(20),
	    especialidad VARCHAR(100)
	);

	-- Crear una tabla de Vendedores
	CREATE TABLE concesionario.Vendedores (
	    vendedor_id serial PRIMARY KEY,
	    datos_personales concesionario.Vendedor
	);

	-- Crear una tabla de Coches (Relación 1 a Varios con Clientes y Vendedor)
	CREATE TABLE concesionario.Coches (
	    coche_id serial PRIMARY KEY,
	    detalles_coche concesionario.Coche,
	    vendedor_id INT REFERENCES concesionario.Vendedores(vendedor_id) ON DELETE SET NULL
	);

	-- Crear una tabla de Clientes
	CREATE TABLE concesionario.Clientes (
	    cliente_id serial PRIMARY KEY,
	    datos_personales concesionario.Cliente,
	    coche_preferido_id INT UNIQUE REFERENCES concesionario.Coches(coche_id) ON DELETE SET NULL
	);

	-- Crear una tabla de Coches (Relación Varios a Varios con Clientes y Coches)
	CREATE TABLE concesionario.Ventas (
	    venta_id serial PRIMARY KEY,
	    cliente_id INT REFERENCES concesionario.Clientes(cliente_id),
	    coche_id INT REFERENCES concesionario.Coches(coche_id),
	    fecha_venta DATE,
	    precio_venta DECIMAL(10, 2)
	);

	-- Insertar datos de ejemplo
	INSERT INTO concesionario.Vendedores (datos_personales)
	VALUES (ROW('Carlos Rodríguez', 'Venta de coches nuevos'));

	INSERT INTO concesionario.Vendedores (datos_personales)
	VALUES (ROW('Ana Martínez', 'Venta de coches usados'));

	INSERT INTO concesionario.Vendedores (datos_personales)
	VALUES (ROW('Laura González', 'Venta de coches eléctricos'));

	INSERT INTO concesionario.Vendedores (datos_personales)
	VALUES (ROW('Pedro López', 'Venta de coches deportivos'));

	INSERT INTO concesionario.Coches (detalles_coche, vendedor_id)
	VALUES (ROW('Toyota Camry', 2023), 1);

	INSERT INTO concesionario.Coches (detalles_coche, vendedor_id)
	VALUES (ROW('Ford Explorer', 2022), 2);

	INSERT INTO concesionario.Coches (detalles_coche, vendedor_id)
	VALUES (ROW('Honda Civic', 2023), 1);

	INSERT INTO concesionario.Coches (detalles_coche, vendedor_id)
	VALUES (ROW('Chevrolet Malibu', 2022), 2);

	INSERT INTO concesionario.Coches (detalles_coche, vendedor_id)
	VALUES (ROW('Mercedes-Benz C-Class', 2022), 3);

	INSERT INTO concesionario.Coches (detalles_coche, vendedor_id)
	VALUES (ROW('BMW X5', 2023), 1);

	INSERT INTO concesionario.Clientes (datos_personales, coche_preferido_id)
	VALUES (ROW('Juan Pérez', 30), 1);

	INSERT INTO concesionario.Clientes (datos_personales, coche_preferido_id)
	VALUES (ROW('María Gómez', 28), 2);

	INSERT INTO concesionario.Clientes (datos_personales, coche_preferido_id)
	VALUES (ROW('Luis Torres', 25), 3);

	INSERT INTO concesionario.Clientes (datos_personales, coche_preferido_id)
	VALUES (ROW('Ana García', 35), 4);

	INSERT INTO concesionario.Clientes (datos_personales, coche_preferido_id)
	VALUES (ROW('Sofía Ramírez', 28), 5);

	INSERT INTO concesionario.Clientes (datos_personales, coche_preferido_id)
	VALUES (ROW('Javier Castro', 32), 6);

	INSERT INTO concesionario.Ventas (cliente_id, coche_id, fecha_venta, precio_venta)
	VALUES (1, 1, '2023-02-15', 25000.00);

	INSERT INTO concesionario.Ventas (cliente_id, coche_id,  fecha_venta, precio_venta)
	VALUES (2, 2, '2023-02-20', 30000.00);

	INSERT INTO concesionario.Ventas (cliente_id, coche_id, fecha_venta, precio_venta)
	VALUES (3, 3, '2023-03-10', 22000.00);

	INSERT INTO concesionario.Ventas (cliente_id, coche_id, fecha_venta, precio_venta)
	VALUES (4, 4, '2023-03-15', 28000.00);

	INSERT INTO concesionario.Ventas (cliente_id, coche_id, fecha_venta, precio_venta)
	VALUES (5, 5, '2023-04-05', 35000.00);

	INSERT INTO concesionario.Ventas (cliente_id, coche_id, fecha_venta, precio_venta)
	VALUES (6, 6, '2023-04-10', 42000.00);