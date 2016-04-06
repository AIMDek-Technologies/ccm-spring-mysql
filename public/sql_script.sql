#Import geographics master data
LOAD DATA LOCAL INFILE '/Downloads/zip_city_state_master_mysql.csv' INTO TABLE ccm.ccm_geographic FIELDS TERMINATED BY ',' OPTIONALLY ENCLOSED BY '"' LINES TERMINATED BY '\r\n' IGNORE 1 LINES;

#Insert admin user with decrepted password "admin"

INSERT INTO `ccm`.`ccm_users`
(`id`,
`address_id`,
`birthdate`,
`created_at`,
`created_by`,
`customer_id`,
`email`,
`firstname`,
`has_credit_card`,
`lastname`,
`password`,
`phone_number`,
`profile_picture`,
`role`,
`state`)
VALUES
('1', '0', NULL, NULL, '0', '0', 'admin@aimdek.com', 'admin', '0', 'admin', '$2a$10$JJHfWBeZglH8tuWrcpbq.OuEvw6LI5rujcMVKReWe3Zr069Jq4uia', NULL, NULL, 'HELPDESK', NULL);




