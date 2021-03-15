CREATE DATABASE testByte;

COMMIT;

CREATE USER 'testByteUsr'@'%' IDENTIFIED BY 'Byte001*';

COMMIT;

GRANT ALL PRIVILEGES ON testByte.* TO 'testByteUsr'@'%';

COMMIT;

DROP TABLE IF EXISTS testByte.company CASCADE;
CREATE TABLE IF NOT EXISTS testByte.company (
    id          	INT(10) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name       		VARCHAR(255) NOT NULL,
    nit        		VARCHAR(12) NOT NULL,
    address			VARCHAR(255) NOT NULL,
    founded_at    	TIMESTAMP,
    status 			VARCHAR(1) DEFAULT 'A' CHECK(status='A' OR status='I' OR status='P'),
    create_by   	VARCHAR(45),
    create_at   	TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_by  	VARCHAR(45),
    updated_at  	TIMESTAMP,
    INDEX idx_company_name(name),
    INDEX idx_company_nit(nit)
);

DROP TABLE IF EXISTS testByte.user CASCADE;
CREATE TABLE IF NOT EXISTS testByte.user (
    id          	INT(10) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name       		VARCHAR(255) NOT NULL,
    password   		VARCHAR(12) NOT NULL,
    status 			VARCHAR(1) DEFAULT 'A' CHECK(status='A' OR status='I' OR status='P'),
    create_by   	VARCHAR(45),
    create_at   	TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_by  	VARCHAR(45),
    updated_at  	TIMESTAMP,
    INDEX idx_user_name(name)
);

insert into user(name,password, create_by)values('Test','$2y$12$r2VgvPIb1eqjozw2SAvRUu.vYSsyYNhTgp.0No8VhRczE0W8wGw5K','initial charge');

SELECT * FROM testByte.company;
SELECT * FROM testByte.user;


COMMIT;