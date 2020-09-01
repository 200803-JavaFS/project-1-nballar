INSERT INTO ers_user_roles 
	VALUES (1, 'employee'),
	(2, 'financemgr');
	
INSERT INTO ers_reimbursement_type 
	VALUES (1, 'food'),
	(2, 'lodging'),
	(3, 'travel'),
	(4, 'other');
	
INSERT INTO ers_reimbursement_status 
	VALUES (1, 'pending'),
	(2, 'approved'),
	(3, 'denied');
	
INSERT INTO ers_users 
	VALUES (1, 'the_eddie', '-576159084', 'Edward', 'Ballar', 'rawwpork@gmail.com', 1);