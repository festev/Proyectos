INSERT INTO `obrasocial` VALUES (1,'SinObra',0,0),(2,'OSDE210',0,1465),(3,'OSDE310',0,2794),(4,'OSDE410',8644,5650),(5,'OSDE450',15412,14450),(6,'OSDE510',18460,16200);

INSERT INTO `afiliado` VALUES (41356752,'Carlos','Gonzales','2003-12-11','2018-12-04',NULL,'Mexico 111','CABA',1143671258,'Jardinero',3644,5650,4),(41546215,'Atila','Mendez','1999-11-13','2018-12-04',NULL,'Av. Corrientes 123','CABA',1143682143,'Florista',0,0,1),(42415637,'Martín','Huanca','2000-05-18','2018-12-04',NULL,'Yatay 333','Morón',1143672417,'Veterinario',18460,16200,6),(12345678,'Federico','Gutierrez','2000-02-20','2018-12-03',NULL,'Pringles 555','CABA',1159475106,'Ingeniero en sistemas',18460,16200,6);

INSERT INTO `historialclinico` VALUES (41356752,0,1,0,1,0,0,0,0,1,0,0,0,0,0,0,0,0,0,''),(41546215,0,1,0,1,0,0,1,0,1,1,0,0,0,0,0,1,0,1,''),(42415637,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,'Alérgico al Latex'),(12345678,0,1,0,0,0,1,0,0,1,0,0,1,0,0,0,1,1,0,'');

INSERT INTO `tratamiento` VALUES (1,'ConsultaDiagnostico',1000),(2,'ConsultaUrgencia',2000),(3,'Conducto',3000),(4,'Extraccion',4000),(5,'Limpieza',4000),(6,'Arreglo',5000),(7,'CirugiaBucal',6000),(8,'RadiologiaDental',5000),(9,'Ortodoncia',5000),(10,'Implante',5000),(11,'Protesis',6000);

INSERT INTO `turno` VALUES (39,'2018-12-04','09:00:00','',NULL,0,4,12345678),(40,'2018-12-02','09:30:00','',NULL,0,6,41356752),(41,'2018-12-01','07:00:00','',31,1,10,41356752),(42,'2018-12-28','07:30:00',NULL,NULL,0,2,41356752),(43,'2018-12-28','07:30:00',NULL,NULL,0,2,41356752),(44,'2018-12-01','07:00:00','',NULL,0,3,12345678),(52,'2018-12-01','07:00:00','',12,1,10,41546215);

INSERT INTO `factura` VALUES (51,'2018-12-04','01:35:00',0,41),(52,'2018-12-04','01:35:00',0,41),(53,'2018-12-04','01:35:00',0,41),(58,'2018-12-04','07:04:00',5000,52);

INSERT INTO `historialfactura` VALUES (54,'2018-12-04','04:10:00',0,49,12454353),(55,'2018-12-04','04:10:00',0,49,12454353),(56,'2018-12-04','04:10:00',350,50,12454353),(57,'2018-12-04','04:11:00',0,51,12454353);