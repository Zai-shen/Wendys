-- insert initial test data
-- the id is hardcode to enable references between further test data
INSERT INTO owner (ID, NAME, CREATED_AT, UPDATED_AT)
VALUES (1, 'Fred', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
       (2, 'Julia', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
       (3, 'Kim', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
       (4, 'Bjorn', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
       (5, 'Tom', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
       (6, 'Kuku', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
       (7, 'Leck', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
       (8, 'Am', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
       (9, 'Unittest', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
       (10, 'Boi', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

INSERT INTO horse (ID, OWNER_ID, NAME, DESCRIPTION, RATING, BIRTH_DAY, BREED, IMAGE, CREATED_AT, UPDATED_AT)
VALUES (1,   1,   'Fuersthorse',  'hello, issa me', 5, '2011-01-25T21:34:55', 'paint',     'auhwudhwu288282', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
       (2,   1,   'Sicundhorse',  'NULL oder so',   4, '2012-01-25T21:34:55', 'ARABIAN',   '2937841208357028735028', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
       (3,   null,'Thor.D.horse', 'desc issa me',   3, '2013-01-25T21:34:55', 'morgan',    'testjeifz783h3jf93j0ur20jf9u', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
       (4,   2,   '4Horse',       NULL,             2, CURRENT_TIMESTAMP(),   'MORGAN',    '2937841208357028735028', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
       (5,   2,   '5Horse',       NULL,             1, CURRENT_TIMESTAMP(),   'morgan',    '2937841208357028735028', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
       (6,   3,   '6Horse',       NULL,             2, CURRENT_TIMESTAMP(),   'appaloosa', '2937841208357028735028', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
       (7,   6,   '7Horse',       NULL,             3, CURRENT_TIMESTAMP(),   'PAINT',     '2937841208357028735028', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
       (8,   9,   '8Horse',       NULL,             4, CURRENT_TIMESTAMP(),   'appaloosa', '2937841208357028735028', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
       (9,   null,'9Horse',       NULL,             5, CURRENT_TIMESTAMP(),   'APPALOOSA', '2937841208357028735028', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
       (10,  null,'10Horse',      NULL,             1, CURRENT_TIMESTAMP(),   'arabian',   '2937841208357028735028', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

