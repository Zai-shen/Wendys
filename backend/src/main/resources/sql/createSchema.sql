CREATE TABLE IF NOT EXISTS owner
(
  id         BIGINT AUTO_INCREMENT PRIMARY KEY,
  name       VARCHAR(255) NOT NULL,
  created_at DATETIME     NOT NULL,
  updated_at DATETIME     NOT NULL
);

CREATE TABLE IF NOT EXISTS horse
(
  id          BIGINT AUTO_INCREMENT PRIMARY KEY,
  name        VARCHAR(255) NOT NULL,
  description VARCHAR(MAX),
  rating      SMALLINT     NOT NULL,
  birth_day   DATETIME     NOT NULL,
  breed       ENUM('ARABIAN', 'MORGAN', 'PAINT', 'APPALOOSA') COLLATE case_insensitive,
  image       VARCHAR(MAX),
  created_at  DATETIME     NOT NULL,
  updated_at  DATETIME     NOT NULL
);