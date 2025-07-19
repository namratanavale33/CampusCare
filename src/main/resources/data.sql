DELETE FROM admin;
INSERT INTO admin (email, password) VALUES ('admin@campuscare.com', 'admin123');

UPDATE complaint SET status = 'PENDING' WHERE status = 'Pending';

