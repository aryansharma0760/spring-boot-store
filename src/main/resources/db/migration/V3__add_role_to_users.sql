ALTER TABLE `store_api`.`users`
ADD COLUMN `role` VARCHAR(45) NOT NULL DEFAULT 'USER' AFTER `password`;