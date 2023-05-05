
CREATE TABLE IF NOT EXISTS tomador (
	nmid SERIAL,
	documento varchar(15) NOT NULL,
	tipo_doc varchar(20) NOT NULL,
	nombre varchar(75) NOT NULL,
	apellido varchar(75) NOT NULL,
	direccion varchar(40) NOT NULL,
	telefono varchar(20) NOT NULL,
	ocupacion varchar(100) NOT NULL,
	correo varchar(50) NOT NULL,
	f_naci date default null,
	PRIMARY KEY (nmid)
	
);

CREATE TABLE IF NOT EXISTS beneficiario (
	nmid SERIAL,
	tipo_doc varchar(20) NOT NULL,
	documento varchar(15) NOT NULL,
	nombre varchar(75) NOT NULL,
	apellido varchar(75) NOT NULL,
	f_naci date default null,
	parentezco varchar(25) ,
	ocupacion varchar(100) not null,
	direccion varchar(20) NOT NULL,
	telefono varchar(15) not null,
	correo varchar(50) NOT NULL,
	porcentaje_afi double precision not null,
	nombre_banco varchar(50) not null,
	numero_cuenta varchar(20) not null,
	nmid_tomador INT not null,
	PRIMARY KEY (nmid)
);

CREATE TABLE IF NOT EXISTS siniestro (
	nmid SERIAL,
	tipo_siniestro varchar(200) NOT NULL,
	f_siniestro date default null,
	lugar varchar(100) NOT NULL,
	nmid_tomador INT NOT NULL,
	PRIMARY KEY (nmid)
);

CREATE TABLE IF NOT EXISTS seguro (
	nmid SERIAL,
	tipo_seguro varchar(150),
	valor double precision not null,
	descripcion varchar(500),
	f_inicial date default null,
	f_final date default null,
	nmid_tomador INT NOT NULL,
	nmid_reaseguradora INT NOT NULL,
	PRIMARY KEY (nmid)
);

CREATE TABLE IF NOT EXISTS reaseguradora (
	nmid SERIAL,
	nit varchar(18) not null,
	razon_social varchar(150) NOT NULL,
	monto_seguro double precision not null,
	porcentaje_cober double precision not null,
	PRIMARY KEY (nmid)
);

CREATE TABLE IF NOT EXISTS pagos (
	nmid SERIAL,
	f_pago date default null,
	cuotas int not null,
	valor_cmes double precision not null,
	nmid_seguro int not null,
	PRIMARY KEY (nmid)
);
-- Relacion beneficiario
ALTER TABLE beneficiario ADD FOREIGN KEY (nmid_tomador) REFERENCES tomador(nmid);

--Relacion seguro
ALTER TABLE seguro ADD FOREIGN KEY (nmid_tomador) REFERENCES tomador(nmid);

--Relacion siniestro
ALTER TABLE siniestro ADD FOREIGN KEY (nmid_tomador) REFERENCES tomador(nmid);

--Relacion  pagos
ALTER TABLE pagos ADD FOREIGN KEY (nmid_seguro) REFERENCES seguro(nmid);

--Relacion seguro
ALTER TABLE seguro ADD FOREIGN KEY (nmid_reaseguradora) REFERENCES reaseguradora(nmid);