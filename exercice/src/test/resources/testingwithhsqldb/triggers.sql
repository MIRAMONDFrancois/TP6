/*
 *  $Id: sampledata.sql 3348 2009-12-15 14:24:19Z unsaved $
 *
 *  Creates and populates database objects with sample data.
 *  This file was created by grabbing the commands made by creating
 *  sample data with the DatabaseManager utility.
 */

CREATE TRIGGER insertItem AFTER INSERT ON Item
	REFERENCING NEW ROW AS newrow
	FOR EACH ROW
		UPDATE Invoice 
		SET Total = Total + (newrow.Cost * newrow.Quantity) 
		WHERE InvoiceID = newrow.InvoiceID;

CREATE TRIGGER updateItem AFTER UPDATE ON Item
	REFERENCING NEW ROW AS newrow OLD ROW as oldrow
	FOR EACH ROW			
		UPDATE Invoice 
		SET Total = SELECT SUM(Cost*Quantity) FROM Item WHERE InvoiceID=Invoice.ID 
		WHERE (InvoiceID = oldrow.InvoiceID) OR (InvoiceID = newrow.InvoiceID);


CREATE TRIGGER deleteItem AFTER DELETE ON Item
	REFERENCING OLD ROW AS oldrow
	FOR EACH ROW
		UPDATE Invoice 
		SET Total = Total - (oldrow.Cost * oldrow.Quantity) 
		WHERE InvoiceID = oldrow.InvoiceID;

COMMIT;
