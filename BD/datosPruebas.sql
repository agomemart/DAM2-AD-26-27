-- Datos de prueba de la BD Biblioteca

INSERT INTO autores (nombre, nacionalidad) VALUES
('Gabriel García Márquez', 'Colombiana'),
('Rosalía de Castro', 'Española'),
('Julio Cortázar', 'Argentina'),
('Almudena Grandes', 'Española'),
('Autora Debutante', NULL);

INSERT INTO libros (isbn, titulo, anio_publicacion, precio, disponible, autor_id) VALUES
('9780000000001', 'Cien años de soledad', 1967, 18.50, TRUE, 1),
('9780000000002', 'El amor en los tiempos del cólera', 1985, 16.90, FALSE, 1),
('9780000000003', 'Follas Novas', 1880, 12.00, TRUE, 2),
('9780000000004', 'Rayuela', 1963, 21.00, TRUE, 3),
('9780000000005', 'Bestiario', 1951, 14.50, TRUE, 3),
('9780000000006', 'Los besos en el pan', 2015, 17.95, TRUE, 4),
('9780000000007', 'Inés y la alegría', 2010, 22.90, TRUE, 4),
('9780000000008', 'Cantares Gallegos', 1863, NULL, TRUE, 2),
('9780000000009', 'El Lazarillo de Tormes', 1554, 9.90, TRUE, NULL),
('9780000000010', 'Crónica de una muerte anunciada', 1981, 11.50, TRUE, 1);

INSERT INTO socios (nombre, email) VALUES
('Lucía Pérez', 'lucia@mail.com'),
('Marcos Gil', 'marcos@mail.com'),
('Ana Ruiz', 'ana@mail.com');

INSERT INTO prestamos (socio_id, libro_id, fecha_prestamo, fecha_devolucion) VALUES
(1, 2, '2026-10-20', NULL);
