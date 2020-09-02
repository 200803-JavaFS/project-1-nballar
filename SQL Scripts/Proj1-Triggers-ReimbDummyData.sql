--Trigger Function for Insert
CREATE OR REPLACE FUNCTION trigger_set_time1() RETURNS TRIGGER 
AS $$
BEGIN 
	NEW.reimb_submitted = NOW();
	RETURN NEW;
END;
$$ LANGUAGE plpgsql;

--Trigger Function for Update
CREATE OR REPLACE FUNCTION trigger_set_time2() RETURNS TRIGGER 
AS $$
BEGIN 
	NEW.reimb_resolved = NOW();
	RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER set_time1 BEFORE INSERT ON ers_reimbursement FOR EACH ROW 
EXECUTE PROCEDURE trigger_set_time1();

CREATE TRIGGER set_time2 BEFORE UPDATE ON ers_reimbursement FOR EACH ROW 
EXECUTE PROCEDURE trigger_set_time2();

ALTER TABLE ers_reimbursement 
DROP COLUMN reimb_receipt;

INSERT INTO ers_reimbursement (reimb_id, reimb_amount, reimb_description, reimb_author, reimb_status_id_fk, reimb_type_id_fk)
	VALUES (1, 50.00, 'Bought dinner for me and my client', 5, 1, 1),
	(2, 25.00, 'Gas money for dinner with client', 5, 1, 3);