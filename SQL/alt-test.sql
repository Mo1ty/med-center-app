INSERT INTO `alternative-db`.`addresses` VALUES
(1, "Praha", "69783", "Mendlová", 42), 
(2, "Brno", "88512", "Tinková", 137), 
(3, "Pardubice", "25667", "Tinková", 37), 
(4, "Pardubice", "50812", "Hlinky", 182), 
(5, "Praha", "50529", "Dobrovolná", 93), 
(6, "Ostrava", "99004", "Rooseveltova", 197), 
(7, "České Budějovice", "11644", "Novotná", 23), 
(8, "Praha", "52812", "Červinková", 20), 
(9, "Dresden", "93481", "Drahunova", 47), 
(10, "Dresden", "88657", "Rooseveltova", 15);

INSERT INTO `alternative-db`.`loyalty_levels` VALUES
(1, "Common Client", 0, "Regular client with no privileges"),
(2, "Loyal client", 5, "He likes us and prefers to visit our center."),
(3, "Doctor's relative", 10, "Doctor's relative or a doctor itself");

INSERT INTO `alternative-db`.`shift_types` VALUES
(1, "04:00:00", "12:00:00"),
(2, "09:30:00", "17:30:00"),
(3, "15:00:00", "23:00:00");

INSERT INTO `alternative-db`.`specialities` VALUES
(1, "Surgery", "description"), 
(2, "Pediatry", "description"), 
(3, "Dentistry", "description"); 

INSERT INTO `alternative-db`.`treatments` VALUES
(1, 1, "Endoscopic Suturing", 400, 1),
(2, 1, "High-Resolution Anoscopy", 400, 8),
(3, 3, "Laparoscopic Appendectomy", 800, 5),
(4, 2, "Open Appendectomy", 800, 2),
(5, 2, "Kyphoplasty", 600, 9);

INSERT INTO `alternative-db`.`login_data` VALUES
("1", "czteiiqpqf@email.com", "$2a$12$SVAvNae/KNZ7hIk9vtF.9uyAx6Nq1Yvz64nMfhVYOQ2CZZOi279Bm", "ROLE_CLIENT"),
("2", "wnffmuvgvx@email.com", "$2a$12$SVAvNae/KNZ7hIk9vtF.9uyAx6Nq1Yvz64nMfhVYOQ2CZZOi279Bm", "ROLE_CLIENT"),
("3", "yebnnsraft@email.com", "$2a$12$SVAvNae/KNZ7hIk9vtF.9uyAx6Nq1Yvz64nMfhVYOQ2CZZOi279Bm", "ROLE_CLIENT"),
("4", "pvvmpgimnr@email.com", "$2a$12$SVAvNae/KNZ7hIk9vtF.9uyAx6Nq1Yvz64nMfhVYOQ2CZZOi279Bm", "ROLE_CLIENT"),
("5", "ftvbmumhiw@email.com", "$2a$12$SVAvNae/KNZ7hIk9vtF.9uyAx6Nq1Yvz64nMfhVYOQ2CZZOi279Bm", "ROLE_CLIENT"),
("6", "ubkbmxfqto@email.com", "$2a$12$SVAvNae/KNZ7hIk9vtF.9uyAx6Nq1Yvz64nMfhVYOQ2CZZOi279Bm", "ROLE_CLIENT"),
("7", "cyznejodfq@email.com", "$2a$12$SVAvNae/KNZ7hIk9vtF.9uyAx6Nq1Yvz64nMfhVYOQ2CZZOi279Bm", "ROLE_CLIENT"),
("8", "pasfvfjuep@email.com", "$2a$12$SVAvNae/KNZ7hIk9vtF.9uyAx6Nq1Yvz64nMfhVYOQ2CZZOi279Bm", "ROLE_CLIENT"),
("9", "snivazsyro@email.com", "$2a$12$SVAvNae/KNZ7hIk9vtF.9uyAx6Nq1Yvz64nMfhVYOQ2CZZOi279Bm", "ROLE_DOCTOR"),
("10", "jonnqzgtiz@email.com", "$2a$12$SVAvNae/KNZ7hIk9vtF.9uyAx6Nq1Yvz64nMfhVYOQ2CZZOi279Bm", "ROLE_DOCTOR");

INSERT INTO `alternative-db`.`contact` VALUES
(1, 1, "Lenka", "Kučerová", "+420 235 124 314", 1),
(2, 2, "Ondřej", "Veselý", "+420 324 234 321", 2),
(3, 3, "Svetlana", "Králová", "+420 987 887 342", 3),
(4, 4, "Marek", "Němec", "+420 324 433 123", 4),
(5, 5, "Marie", "Dvořáková", "+420 987 582 294", 5),
(6, 6, "Michal", "Marek", "+420 023 823 231", 6),
(7, 7, "Ekaterina", "Pokorná", "+420 432 123 342", 7),
(8, 8, "Ladislav", "Novotný", "+420 234 234 246", 8),
(9, 9, "Lucie", "Sedláčková", "+420 986 545 234", 9),
(10, 10, "David", "Jelínek", "+420 123 097 265", 10);

INSERT INTO `alternative-db`.`clients` VALUES
(1, 1, 900, 1),
(2, 2, 900, 1),
(3, 3, 900, 1),
(4, 4, 10900, 2),
(5, 5, 1900, 2),
(6, 6, 1900, 2),
(7, 7, 900, 3),
(8, 8, 900, 1);

INSERT INTO `alternative-db`.`doctors` VALUES
(1, 9, "description_slot", "2", "3", "1"),
(2, 10, "description_slot", "2", "6", "2");

INSERT INTO `alternative-db`.`visits` VALUES
(1, 3, 2, 3, 300, "2022-09-18 15:00:00", "Comment"), 
(2, 3, 1, 1, 100, "2022-11-14 16:00:00", "Comment"), 
(3, 7, 1, 5, 200, "2022-12-09 14:00:00", "Comment"), 
(4, 7, 1, 2, 900, "2022-11-23 15:00:00", "Comment"), 
(5, 4, 1, 5, 200, "2022-09-28 15:00:00", "Comment"), 
(6, 2, 2, 2, 900, "2022-09-13 15:00:00", "Comment"), 
(7, 5, 2, 4, 600, "2022-11-19 09:00:00", "Comment"), 
(8, 1, 2, 1, 100, "2022-09-15 14:00:00", "Comment"), 
(9, 5, 1, 3, 300, "2022-11-24 09:00:00", "Comment"), 
(10, 5, 1, 4, 600, "2022-10-20 16:00:00", "Comment"), 
(11, 2, 1, 4, 600, "2022-09-02 09:00:00", "Comment"), 
(12, 5, 1, 3, 300, "2022-10-20 10:00:00", "Comment"), 
(13, 1, 1, 5, 200, "2022-09-08 09:00:00", "Comment"), 
(14, 6, 1, 5, 200, "2022-10-23 13:00:00", "Comment"), 
(15, 3, 2, 1, 100, "2022-11-06 17:00:00", "Comment"), 
(16, 3, 2, 3, 300, "2022-12-04 16:00:00", "Comment"), 
(17, 5, 2, 5, 200, "2022-10-04 17:00:00", "Comment"), 
(18, 1, 1, 2, 900, "2022-09-13 16:00:00", "Comment"), 
(19, 6, 1, 2, 900, "2022-09-13 11:00:00", "Comment"), 
(20, 2, 1, 4, 600, "2022-12-01 17:00:00", "Comment"), 
(21, 3, 1, 1, 100, "2022-09-26 11:00:00", "Comment"), 
(22, 6, 1, 3, 300, "2022-09-16 11:00:00", "Comment"), 
(23, 7, 1, 1, 100, "2022-09-11 11:00:00", "Comment"), 
(24, 4, 2, 5, 200, "2022-12-24 13:00:00", "Comment"), 
(25, 2, 1, 2, 900, "2022-11-14 17:00:00", "Comment"), 
(26, 6, 2, 2, 900, "2022-12-14 16:00:00", "Comment"), 
(27, 1, 1, 4, 600, "2022-09-07 13:00:00", "Comment"), 
(28, 4, 2, 3, 300, "2022-09-29 17:00:00", "Comment"), 
(29, 7, 2, 5, 200, "2022-12-10 17:00:00", "Comment"), 
(30, 5, 1, 2, 900, "2022-10-23 15:00:00", "Comment"); 