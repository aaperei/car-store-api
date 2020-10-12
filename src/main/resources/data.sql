DROP TABLE IF EXISTS car;
 
CREATE TABLE car(
  id INT AUTO_INCREMENT  PRIMARY KEY,
  model VARCHAR(20) NOT NULL,
  brand VARCHAR(20) NOT NULL,
  year int NOT NULL,
  description VARCHAR(255) NOT NULL,
  sold int NOT NULL,
  created DATE NOT NULL,
  updated DATE NOT NULL
);

ALTER TABLE car ADD CONSTRAINT brand_const CHECK (brand IN ('VOLKSWAGEN', 'FORD', 'CHEVROLET', 'TOYOTA', 'HYUNDAI', 'JEEP', 'FIAT', 'RENAULT'));

INSERT INTO car (model, brand, year, description, sold, created, updated) VALUES
	('Onix', 'CHEVROLET', 2020, 'Cor Branca, completo', 0, SYSDATE, SYSDATE),
	('Punto', 'FIAT', 2019, 'Cor Preta, completo', 0, SYSDATE, SYSDATE),
	('Ranger', 'FORD', 2019, 'Cor Preta, completo', 0, SYSDATE, SYSDATE),
	('HB20', 'HYUNDAI', 2020, 'Cor Branca, completo', 0, SYSDATE, SYSDATE),
	('Renegade', 'JEEP', 2019, 'Cor Preta, completo', 0, SYSDATE, SYSDATE),
	('Captur', 'RENAULT', 2020, 'Cor Branca, completo', 0, SYSDATE, SYSDATE),
	('Corolla', 'TOYOTA', 2019, 'Cor Preta, completo', 0, SYSDATE, SYSDATE),
	('Gol', 'VOLKSWAGEN', 2019, 'Cor Preta, completo', 0, SYSDATE, SYSDATE),
	('Cobalt', 'CHEVROLET', 2020, 'Cor Branca, completo', 0, SYSDATE, SYSDATE),
	('Strada', 'FIAT', 2019, 'Cor Preta, completo', 0, SYSDATE, SYSDATE),
	('Fusion', 'FORD', 2019, 'Cor Preta, completo', 0, SYSDATE, SYSDATE),
	('Caoa', 'HYUNDAI', 2020, 'Cor Branca, completo', 0, SYSDATE, SYSDATE),
	('Compass', 'JEEP', 2019, 'Cor Preta, completo', 0, SYSDATE, SYSDATE),
	('Zoe', 'RENAULT', 2020, 'Cor Branca, completo', 0, SYSDATE, SYSDATE),
	('Yaris', 'TOYOTA', 2019, 'Cor Preta, completo', 0, SYSDATE, SYSDATE),
	('Golf', 'VOLKSWAGEN', 2019, 'Cor Preta, completo', 0, SYSDATE, SYSDATE),
	('Groove', 'CHEVROLET', 2020, 'Cor Branca, completo', 0, SYSDATE, SYSDATE),
	('Toro', 'FIAT', 2019, 'Cor Preta, completo', 0, SYSDATE, SYSDATE),
	('Ka', 'FORD', 2019, 'Cor Preta, completo', 0, SYSDATE, SYSDATE),
	('Creta', 'HYUNDAI', 2020, 'Cor Branca, completo', 0, SYSDATE, SYSDATE),
	('Grand Cherokee', 'JEEP', 2019, 'Cor Preta, completo', 0, SYSDATE, SYSDATE),
	('Kwid', 'RENAULT', 2020, 'Cor Branca, completo', 0, SYSDATE, SYSDATE),
	('RAV4', 'TOYOTA', 2019, 'Cor Preta, completo', 0, SYSDATE, SYSDATE),
	('Jetta', 'VOLKSWAGEN', 2019, 'Cor Preta, completo', 0, SYSDATE, SYSDATE),
	('Tracker', 'CHEVROLET', 2020, 'Cor Branca, completo', 0, SYSDATE, SYSDATE),
	('Mobi', 'FIAT', 2019, 'Cor Preta, completo', 0, SYSDATE, SYSDATE),
	('Evos', 'FORD', 2019, 'Cor Preta, completo', 0, SYSDATE, SYSDATE),
	('Ioniq', 'HYUNDAI', 2020, 'Cor Branca, completo', 0, SYSDATE, SYSDATE),
	('Wrangler', 'JEEP', 2019, 'Cor Preta, completo', 0, SYSDATE, SYSDATE),
	('Duster', 'RENAULT', 2020, 'Cor Branca, completo', 0, SYSDATE, SYSDATE),
	('Etios', 'TOYOTA', 2019, 'Cor Preta, completo', 0, SYSDATE, SYSDATE),
	('Fox', 'VOLKSWAGEN', 2019, 'Cor Preta, completo', 0, SYSDATE, SYSDATE);
 