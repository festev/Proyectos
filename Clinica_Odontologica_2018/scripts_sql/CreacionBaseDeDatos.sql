-- -----------------------------------------------------
-- Schema clinicaodontologica
-- -----------------------------------------------------

CREATE SCHEMA IF NOT EXISTS `clinicaodontologica` DEFAULT CHARACTER SET utf8 ;
USE `clinicaodontologica` ;

-- -----------------------------------------------------
-- Table `clinicaodontologica`.`obrasocial`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `clinicaodontologica`.`obrasocial` (
  `idObraSocial` INT(11) NOT NULL AUTO_INCREMENT,
  `nombreObra` VARCHAR(20) NOT NULL,
  `coberturaImplanteMax` INT(11) NOT NULL,
  `coberturaProtesisMax` INT(11) NOT NULL,
  PRIMARY KEY (`idObraSocial`));
  
  -- -----------------------------------------------------
-- Table `clinicaodontologica`.`afiliado`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `clinicaodontologica`.`afiliado` (
  `documento` INT(11) NOT NULL,
  `nombre` VARCHAR(45) NOT NULL,
  `apellido` VARCHAR(45) NOT NULL,
  `fechaNacimiento` DATE NOT NULL,
  `fechaIntegracion` DATE NOT NULL,
  `fechaDesafiliacion` DATE NULL DEFAULT NULL,
  `domicilio` VARCHAR(50) NOT NULL,
  `localidad` VARCHAR(50) NOT NULL,
  `telefono` INT(11) NOT NULL,
  `ocupacion` VARCHAR(50) NOT NULL,
  `coberturaImplante` INT(11) NOT NULL,
  `coberturaProtesis` INT(11) NOT NULL,
  `idObraSocial` INT(11) NOT NULL,
  PRIMARY KEY (`documento`),
  CONSTRAINT `FK_Afiliado_ObraSocial`
    FOREIGN KEY (`idObraSocial`)
    REFERENCES `clinicaodontologica`.`obrasocial` (`idObraSocial`));
    
-- -----------------------------------------------------
-- Table `clinicaodontologica`.`tratamiento`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `clinicaodontologica`.`tratamiento` (
  `idTratamiento` INT(11) NOT NULL AUTO_INCREMENT,
  `nombreTratamiento` VARCHAR(80) NOT NULL,
  `precio` INT(11) NOT NULL,
  PRIMARY KEY (`idTratamiento`));
  
-- -----------------------------------------------------
-- Table `clinicaodontologica`.`turno`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `clinicaodontologica`.`turno` (
  `nroTurno` INT(11) NOT NULL AUTO_INCREMENT,
  `fechaTurno` DATE NOT NULL,
  `horaTurno` TIME NOT NULL,
  `medicacion` VARCHAR(100) NULL DEFAULT NULL,
  `dienteCara` INT(11) NULL DEFAULT NULL,
  `realizado` TINYINT(4) NOT NULL,
  `idTratamiento` INT(11) NOT NULL,
  `documento` INT(11) NOT NULL,
  PRIMARY KEY (`nroTurno`),
  CONSTRAINT `FK_Turno_Afiliado`
    FOREIGN KEY (`documento`)
    REFERENCES `clinicaodontologica`.`afiliado` (`documento`),
  CONSTRAINT `FK_Turno_Tratamiento`
    FOREIGN KEY (`idTratamiento`)
    REFERENCES `clinicaodontologica`.`tratamiento` (`idTratamiento`));
    
-- -----------------------------------------------------
-- Table `clinicaodontologica`.`factura`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `clinicaodontologica`.`factura` (
  `nroFactura` INT(11) NOT NULL AUTO_INCREMENT,
  `fechaCreacion` DATE NOT NULL,
  `horaCreacion` TIME NOT NULL,
  `totalPagar` INT(11) NOT NULL,
  `nroTurno` INT(11) NOT NULL,
  PRIMARY KEY (`nroFactura`),
  CONSTRAINT `FK_Factura_Turno`
    FOREIGN KEY (`nroTurno`)
    REFERENCES `clinicaodontologica`.`turno` (`nroTurno`));
    
-- -----------------------------------------------------
-- Table `clinicaodontologica`.`historialclinico`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `clinicaodontologica`.`historialclinico` (
  `documento` INT(11) NOT NULL,
  `enferCardiovascular` TINYINT(4) NOT NULL,
  `marcapasos` TINYINT(4) NOT NULL,
  `problePresion` TINYINT(4) NOT NULL,
  `probleGastrico` TINYINT(4) NOT NULL,
  `probleRenal` TINYINT(4) NOT NULL,
  `asma` TINYINT(4) NOT NULL,
  `diabetes` TINYINT(4) NOT NULL,
  `tratOnco` TINYINT(4) NOT NULL,
  `dieta` TINYINT(4) NOT NULL,
  `alergiaMedicamento` TINYINT(4) NOT NULL,
  `enfInfecto` TINYINT(4) NOT NULL,
  `transSanguinea` TINYINT(4) NOT NULL,
  `probleCoagulacion` TINYINT(4) NOT NULL,
  `embarazo` TINYINT(4) NOT NULL,
  `opeReciente` TINYINT(4) NOT NULL,
  `fiebreReumatica` TINYINT(4) NOT NULL,
  `mediTratMedico` TINYINT(4) NOT NULL,
  `convulDesmayos` TINYINT(4) NOT NULL,
  `observaciones` VARCHAR(300) NOT NULL,
  CONSTRAINT `FK_HistorialClinico_Afiliado`
    FOREIGN KEY (`documento`)
    REFERENCES `clinicaodontologica`.`afiliado` (`documento`));
    
-- -----------------------------------------------------
-- Table `clinicaodontologica`.`historialfactura`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `clinicaodontologica`.`historialfactura` (
  `nroFactura` INT(11) NOT NULL,
  `fechaCreacion` DATE NOT NULL,
  `horaCreacion` TIME NOT NULL,
  `totalPagar` INT(11) NOT NULL,
  `nroTurno` INT(11) NOT NULL,
  `documento` INT(11) NOT NULL,
  PRIMARY KEY (`nroFactura`));