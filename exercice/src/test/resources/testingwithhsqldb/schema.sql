/*
 *  Creates database objects.
 */

DROP TABLE Item IF EXISTS;
DROP TABLE Invoice IF EXISTS;
DROP TABLE Product IF EXISTS;
DROP TABLE Customer IF EXISTS;

CREATE TABLE Customer(ID INTEGER PRIMARY KEY, FirstName VARCHAR(20), LastName VARCHAR(30), Street VARCHAR(50), City VARCHAR(25));
CREATE TABLE Product(ID INTEGER PRIMARY KEY, Name VARCHAR(30), Price DECIMAL(10,2));
CREATE TABLE Invoice(ID INTEGER PRIMARY KEY, CustomerID INTEGER, Total DECIMAL(10,2), 
	FOREIGN KEY (CustomerId) REFERENCES Customer(ID) ON DELETE CASCADE);
CREATE TABLE Item(InvoiceID INTEGER, Item INTEGER, ProductID INTEGER, Quantity INTEGER, Cost DECIMAL(10,2), PRIMARY KEY(InvoiceID,Item), 
	FOREIGN KEY (InvoiceId) REFERENCES Invoice (ID) ON DELETE CASCADE, 
	FOREIGN KEY (ProductId) REFERENCES Product(ID));

-- Contrainte d'intégrité : le prix d'un article doit être positif ou nul
ALTER TABLE Product ADD CONSTRAINT PositivePrice CHECK (Price >= 0.0);

-- Contrainte d'intégrité : le coût d'un item doit être positif ou nul
ALTER TABLE Item ADD CONSTRAINT PositiveCost CHECK ( Cost >= 0.0);

COMMIT;
