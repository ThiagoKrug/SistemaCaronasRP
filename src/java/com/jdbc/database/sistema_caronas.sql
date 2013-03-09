SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `sistema_caronas` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;

USE `sistema_caronas`;

CREATE  TABLE IF NOT EXISTS `sistema_caronas`.`veiculo` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `id_tipo_veiculo` INT(11) NOT NULL ,
  `placa` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NULL DEFAULT NULL ,
  `quilometragem` FLOAT(11) NULL DEFAULT NULL ,
  `capacidade_passageiro` INT(3) NULL DEFAULT NULL ,
  `cor` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_general_ci' NULL DEFAULT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_veiculo_tipoVeiculo_idx` (`id_tipo_veiculo` ASC) ,
  CONSTRAINT `fk_veiculo_tipoVeiculo`
    FOREIGN KEY (`id_tipo_veiculo` )
    REFERENCES `sistema_caronas`.`tipo_veiculo` (`id_tipo_veiculo` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;

CREATE  TABLE IF NOT EXISTS `sistema_caronas`.`tipo_veiculo` (
  `id_tipo_veiculo` INT(11) NOT NULL AUTO_INCREMENT ,
  `tipo_veiculo` VARCHAR(255) CHARACTER SET 'utf8' COLLATE 'utf8_general_ci' NULL DEFAULT NULL ,
  PRIMARY KEY (`id_tipo_veiculo`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;

CREATE  TABLE IF NOT EXISTS `sistema_caronas`.`passageiro` (
  `id_passageiro` INT(11) NOT NULL AUTO_INCREMENT ,
  `rg` VARCHAR(12) CHARACTER SET 'utf8' COLLATE 'utf8_general_ci' NULL DEFAULT NULL ,
  `nome` VARCHAR(255) CHARACTER SET 'utf8' COLLATE 'utf8_general_ci' NULL DEFAULT NULL ,
  `telefone` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_general_ci' NULL DEFAULT NULL ,
  `endereco` TEXT NULL DEFAULT NULL ,
  PRIMARY KEY (`id_passageiro`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;

CREATE  TABLE IF NOT EXISTS `sistema_caronas`.`tipo_usuario` (
  `id_tipo_usuario` INT(11) NOT NULL AUTO_INCREMENT ,
  `tipo_usuario` VARCHAR(255) CHARACTER SET 'utf8' COLLATE 'utf8_general_ci' NULL DEFAULT NULL ,
  PRIMARY KEY (`id_tipo_usuario`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;

CREATE  TABLE IF NOT EXISTS `sistema_caronas`.`usuario` (
  `id_usuario` INT(11) NOT NULL AUTO_INCREMENT ,
  `id_tipo_usuario` INT(11) NOT NULL ,
  `nome` TEXT CHARACTER SET 'utf8' COLLATE 'utf8_general_ci' NULL DEFAULT NULL ,
  `rg` VARCHAR(12) CHARACTER SET 'utf8' COLLATE 'utf8_general_ci' NULL DEFAULT NULL ,
  `nome_usuario` VARCHAR(255) CHARACTER SET 'utf8' COLLATE 'utf8_general_ci' NULL DEFAULT NULL ,
  `numero_servidor` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_general_ci' NULL DEFAULT NULL ,
  `senha` VARCHAR(255) CHARACTER SET 'utf8' COLLATE 'utf8_general_ci' NULL DEFAULT NULL ,
  `telefone` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_general_ci' NULL DEFAULT NULL ,
  `email` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_general_ci' NULL DEFAULT NULL ,
  `situacao` VARCHAR(45) NULL DEFAULT NULL ,
  PRIMARY KEY (`id_usuario`) ,
  INDEX `fk_usuario_tipo_usuario1_idx` (`id_tipo_usuario` ASC) ,
  CONSTRAINT `fk_usuario_tipo_usuario1`
    FOREIGN KEY (`id_tipo_usuario` )
    REFERENCES `sistema_caronas`.`tipo_usuario` (`id_tipo_usuario` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;

CREATE  TABLE IF NOT EXISTS `sistema_caronas`.`solicitacao_viagem` (
  `id_solicitacao_viagem` INT(11) NOT NULL AUTO_INCREMENT ,
  `numero_pedido` INT(11) NULL DEFAULT NULL ,
  `numero_transportados` INT(3) NULL DEFAULT NULL ,
  `servidores` TINYINT(1) NULL DEFAULT NULL ,
  `data_saida` DATE NULL DEFAULT NULL ,
  `hora_saida` TIMESTAMP NULL DEFAULT NULL ,
  `local_saida` TEXT NULL DEFAULT NULL ,
  `data_retorno` DATE NULL DEFAULT NULL ,
  `hora_retorno` TIMESTAMP NULL DEFAULT NULL ,
  `local_retorno` TEXT NULL DEFAULT NULL ,
  `percurso` TEXT NULL DEFAULT NULL ,
  `objetivo_viagem` TEXT NULL DEFAULT NULL ,
  `id_responsavel_solicitacao` INT(11) NOT NULL ,
  `status` VARCHAR(45) NULL DEFAULT NULL ,
  `motorista` TEXT NULL DEFAULT NULL ,
  `data_efetivacao_reserva` TIMESTAMP NULL DEFAULT NULL ,
  `id_tipo_veiculo` INT(11) NOT NULL ,
  `id_viagem` INT(11) NULL DEFAULT NULL ,
  PRIMARY KEY (`id_solicitacao_viagem`) ,
  INDEX `fk_solicitacaoViagem_usuario1_idx` (`id_responsavel_solicitacao` ASC) ,
  INDEX `fk_solicitacao_viagem_tipo_veiculo1` (`id_tipo_veiculo` ASC) ,
  INDEX `fk_solicitacao_viagem_viagem1_idx` (`id_viagem` ASC) ,
  CONSTRAINT `fk_solicitacaoViagem_usuario1`
    FOREIGN KEY (`id_responsavel_solicitacao` )
    REFERENCES `sistema_caronas`.`usuario` (`id_usuario` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_solicitacao_viagem_tipo_veiculo1`
    FOREIGN KEY (`id_tipo_veiculo` )
    REFERENCES `sistema_caronas`.`tipo_veiculo` (`id_tipo_veiculo` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_solicitacao_viagem_viagem1`
    FOREIGN KEY (`id_viagem` )
    REFERENCES `sistema_caronas`.`viagem` (`id_viagem` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;

CREATE  TABLE IF NOT EXISTS `sistema_caronas`.`passageiro_solicitacao_viagem` (
  `id_passageiro` INT(11) NOT NULL ,
  `id_solicitacao_viagem` INT(11) NOT NULL ,
  PRIMARY KEY (`id_passageiro`, `id_solicitacao_viagem`) ,
  INDEX `fk_passageiro_has_solicitacao_viagem_solicitacao_viagem1` (`id_solicitacao_viagem` ASC) ,
  INDEX `fk_passageiro_has_solicitacao_viagem_passageiro1` (`id_passageiro` ASC) ,
  CONSTRAINT `fk_passageiro_has_solicitacao_viagem_passageiro1`
    FOREIGN KEY (`id_passageiro` )
    REFERENCES `sistema_caronas`.`passageiro` (`id_passageiro` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_passageiro_has_solicitacao_viagem_solicitacao_viagem1`
    FOREIGN KEY (`id_solicitacao_viagem` )
    REFERENCES `sistema_caronas`.`solicitacao_viagem` (`id_solicitacao_viagem` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;

CREATE  TABLE IF NOT EXISTS `sistema_caronas`.`viagem` (
  `id_viagem` INT(11) NOT NULL AUTO_INCREMENT ,
  `id_autorizante` INT(11) NOT NULL ,
  `id_motorista` INT(11) NOT NULL ,
  `id_veiculo` INT(11) NOT NULL ,
  `data_efetivacao` DATE NULL DEFAULT NULL ,
  PRIMARY KEY (`id_viagem`) ,
  INDEX `fk_viagem_usuario1_idx` (`id_autorizante` ASC) ,
  INDEX `fk_viagem_usuario2_idx` (`id_motorista` ASC) ,
  INDEX `fk_viagem_veiculo1_idx` (`id_veiculo` ASC) ,
  CONSTRAINT `fk_viagem_usuario1`
    FOREIGN KEY (`id_autorizante` )
    REFERENCES `sistema_caronas`.`usuario` (`id_usuario` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_viagem_usuario2`
    FOREIGN KEY (`id_motorista` )
    REFERENCES `sistema_caronas`.`usuario` (`id_usuario` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_viagem_veiculo1`
    FOREIGN KEY (`id_veiculo` )
    REFERENCES `sistema_caronas`.`veiculo` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;

CREATE  TABLE IF NOT EXISTS `sistema_caronas`.`viagem_has_passageiro` (
  `id_viagem` INT(11) NOT NULL ,
  `id_passageiro` INT(11) NOT NULL ,
  PRIMARY KEY (`id_viagem`, `id_passageiro`) ,
  INDEX `fk_viagem_has_passageiro_passageiro1_idx` (`id_passageiro` ASC) ,
  INDEX `fk_viagem_has_passageiro_viagem1_idx` (`id_viagem` ASC) ,
  CONSTRAINT `fk_viagem_has_passageiro_viagem1`
    FOREIGN KEY (`id_viagem` )
    REFERENCES `sistema_caronas`.`viagem` (`id_viagem` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_viagem_has_passageiro_passageiro1`
    FOREIGN KEY (`id_passageiro` )
    REFERENCES `sistema_caronas`.`passageiro` (`id_passageiro` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
