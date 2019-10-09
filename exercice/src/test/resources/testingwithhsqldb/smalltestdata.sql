/*
 *  $Id: sampledata.sql 3348 2009-12-15 14:24:19Z unsaved $
 *
 *  Creates and populates database objects with sample data.
 *  This file was created by grabbing the commands made by creating
 *  sample data with the DatabaseManager utility.
 */



INSERT INTO Customer VALUES(0,'Laura','Steel','429 Seventh Av.','Dallas');
INSERT INTO Customer VALUES(1,'Susanne','King','366 - 20th Ave.','Olten');

INSERT INTO Product VALUES(0,'Iron Iron',54);
INSERT INTO Product VALUES(1,'Chair Shoe',248);

INSERT INTO Invoice VALUES(0,0,0.0);
INSERT INTO Invoice VALUES(1,1,0.0);

INSERT INTO Item VALUES(0,0,1,11,1.5);
INSERT INTO Item VALUES(0,1,0,4,1.5);

INSERT INTO Item VALUES(1,0,0,8,1.5);
INSERT INTO Item VALUES(1,1,1,4,1.5);



UPDATE Item SET Cost=Cost*(SELECT Price FROM Product prod WHERE ProductID=prod.ID);
UPDATE Invoice SET Total=SELECT SUM(Cost*Quantity) FROM Item WHERE InvoiceID=Invoice.ID;

COMMIT;
