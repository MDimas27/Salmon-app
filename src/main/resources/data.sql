INSERT INTO public.role (role_name) VALUES ('ROLE_ADMIN');
INSERT INTO public.role (role_name) VALUES ('ROLE_USER');

INSERT INTO public.role (role_name) VALUES ('ROLE_HR');
INSERT INTO public.role (role_name) VALUES ('ROLE_CFO');
INSERT INTO public.role (role_name) VALUES ('ROLE_SALESLEAD');

INSERT INTO public."user" (uuid, created_by, created_time, status, updated_by, updated_time, email, nama, password, jk, alamat, telepon, username) VALUES (1, 'init', '2020-03-12 00:00:00', 'ACTIVE', NULL, '2020-03-12 00:00:00', 'admin@email.com', 'Administrator', '$2a$04$lMlZP3e/6RlxOyFWQvXvY.yqI2Y4nJxRhy/wCvGVqfm.5qXeNMxxG', 'lk', 'Bekasi', '081234567890', 'admin');
INSERT INTO public."user" (uuid, created_by, created_time, status, updated_by, updated_time, email, nama, password, jk, alamat, telepon, username) VALUES (2, 'init', '2020-03-12 00:00:00', 'ACTIVE', NULL, '2020-03-12 00:00:00', 'user@email.com', 'Sales', '$2a$04$Q1tJAQ0UhNGSX9H5aL4A5uCaHW2wt7KEeuTpPyNg9KsSkjKmLf8iy', 'pr','Jakarta', '081987654321', 'sales');

INSERT INTO public."user" (uuid, created_by, created_time, status, updated_by, updated_time, email, nama, password, jk, alamat, telepon, username) VALUES (3, 'init', '2020-03-12 00:00:00', 'ACTIVE', NULL, '2020-03-12 00:00:00', 'hr@email.com', 'Hr', '$2y$12$4LGFj1yfZBcYjKvCzvszjuzSDSqHTLXECT20MKpC2LAb/B5qng9YK', 'lk', 'Bekasi', '08111111111', 'hr');
INSERT INTO public."user" (uuid, created_by, created_time, status, updated_by, updated_time, email, nama, password, jk, alamat, telepon, username) VALUES (4, 'init', '2020-03-12 00:00:00', 'ACTIVE', NULL, '2020-03-12 00:00:00', 'saleslead@email.com', 'Saleslead', '$2y$12$aPYMXgiRVEDKxmkJz93m9eUtF3Lu62lJBu37lvC8dcDR/Si5YiP.y', 'lk', 'Bekasi', '082222222222', 'saleslead');
INSERT INTO public."user" (uuid, created_by, created_time, status, updated_by, updated_time, email, nama, password, jk, alamat, telepon, username) VALUES (5, 'init', '2020-03-12 00:00:00', 'ACTIVE', NULL, '2020-03-12 00:00:00', 'cfo@email.com', 'Cfo', '$2y$12$UNGQmj4Uczrw3llvreIXD.J.IsEBAtMVexWjV7t3RgBAzO2B598le', 'lk', 'Bekasi', '083333333333', 'cfo');



INSERT INTO public.user_role (username, role_name) VALUES ('admin', 'ROLE_ADMIN');
INSERT INTO public.user_role (username, role_name) VALUES ('sales', 'ROLE_USER');

INSERT INTO public.user_role (username, role_name) VALUES ('sales', 'ROLE_HR');
INSERT INTO public.user_role (username, role_name) VALUES ('sales', 'ROLE_SALESLEAD');
INSERT INTO public.user_role (username, role_name) VALUES ('sales', 'ROLE_CFO');