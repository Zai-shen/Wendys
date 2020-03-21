CREATE TABLE IF NOT EXISTS owner
(
  id         BIGINT AUTO_INCREMENT PRIMARY KEY,
  name       VARCHAR(255) NOT NULL,
  created_at DATETIME     NOT NULL,
  updated_at DATETIME     NOT NULL
);

CREATE TABLE IF NOT EXISTS breeds
(
    breed_enum VARCHAR_IGNORECASE(10) NOT NULL PRIMARY KEY
);

MERGE INTO breeds (breed_enum)
VALUES ('ARABIAN'),
       ('MORGAN'),
       ('PAINT'),
       ('APPALOOSA');

--Delete below statement for consistent DB
--DROP TABLE IF EXISTS horse;
CREATE TABLE IF NOT EXISTS horse
(
  id          BIGINT AUTO_INCREMENT PRIMARY KEY,
  owner_id    BIGINT,
  FOREIGN KEY (owner_id) REFERENCES owner (id),
  name        VARCHAR(255) NOT NULL,
  description VARCHAR(MAX),
  rating      SMALLINT     NOT NULL,
  birth_day   DATETIME     NOT NULL,
  breed       VARCHAR_IGNORECASE(10), --ENUM ('ARABIAN', 'MORGAN', 'PAINT', 'APPALOOSA'), --COLLATE case_insensitive, H2 doesnt support enums well enough
  FOREIGN KEY (breed) REFERENCES breeds (breed_enum),
  image       VARCHAR(MAX),
  created_at  DATETIME     NOT NULL,
  updated_at  DATETIME     NOT NULL
);