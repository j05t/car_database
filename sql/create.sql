-- SEQUENCES

-- Sequence: car_number
CREATE SEQUENCE Car_Id_Sequence
      INCREMENT BY 1
      NO MINVALUE
      NO MAXVALUE
      START WITH 1 
      NO CYCLE
;

-- Sequence: category_number
CREATE SEQUENCE Category_Id_Sequence
      INCREMENT BY 1
      NO MINVALUE
      NO MAXVALUE
      START WITH 1 
      NO CYCLE
;

-- Sequence: feature_number
CREATE SEQUENCE Feature_Id_Sequence
      INCREMENT BY 1
      NO MINVALUE
      NO MAXVALUE
      START WITH 1 
      NO CYCLE
;

-- Sequence: manufacturer_number
CREATE SEQUENCE Manufacturer_Id_Sequence
      INCREMENT BY 1
      NO MINVALUE
      NO MAXVALUE
      START WITH 1 
      NO CYCLE
;

-- Sequence: user_number
CREATE SEQUENCE Buyer_Id_Sequence
      INCREMENT BY 1
      NO MINVALUE
      NO MAXVALUE
      START WITH 1 
      NO CYCLE
;

-- Sequence: user_number
CREATE SEQUENCE Seller_Id_Sequence
      INCREMENT BY 1
      NO MINVALUE
      NO MAXVALUE
      START WITH 1 
      NO CYCLE
;

-- Sequence: user_number
CREATE SEQUENCE Person_Sequence   
INCREMENT BY 1  
      NO MINVALUE
      NO MAXVALUE
      START WITH 1 
      NO CYCLE
;

-- TABLES

-- Table: CAR

CREATE TABLE CAR (
    ID INTEGER  NOT NULL PRIMARY KEY DEFAULT nextval('Car_Id_Sequence'),
    MANUFACTURER_ID INTEGER  NOT NULL,
    CATEGORY_ID INTEGER  NOT NULL,
    KM INTEGER  NOT NULL,
    NAME VARCHAR  NOT NULL,
    PRICE decimal(12,2)  NOT NULL,
    DESCRIPTION VARCHAR  NOT NULL,
    REGISTRATION_YEAR date  NOT NULL
);

-- Table: FEATURE

CREATE TABLE FEATURE (
    ID INTEGER  NOT NULL PRIMARY KEY DEFAULT nextval('Feature_Id_Sequence'),
    DESCRIPTION VARCHAR  NOT NULL
);

-- Table: CARFEATURE

CREATE TABLE CAR_FEATURE (
    CARS_ID INTEGER  NOT NULL,
    FEATURES_ID INTEGER  NOT NULL  
);

-- Table: MANUFACTURER

CREATE TABLE MANUFACTURER (
    ID INTEGER  NOT NULL PRIMARY KEY DEFAULT nextval('Manufacturer_Id_Sequence'),
    NAME VARCHAR  NOT NULL
);

-- Table: CATEGORY

CREATE TABLE CATEGORY (
    ID INTEGER  NOT NULL PRIMARY KEY  DEFAULT nextval('Category_Id_Sequence'),
    NAME VARCHAR  NOT NULL
);

-- Table: PERSON

CREATE TABLE PERSON (
    ID SERIAL  NOT NULL PRIMARY KEY,
    FIRST_NAME VARCHAR  NOT NULL,
    LAST_NAME VARCHAR  NOT NULL,
	DTYPE VARCHAR
);

-- Table: BUYER

CREATE TABLE BUYER (
    ID INTEGER  NOT NULL PRIMARY KEY
);

-- Table: SELLER

CREATE TABLE SELLER (
    ID INTEGER  NOT NULL PRIMARY KEY
);

-- Table: ADDRESS

CREATE TABLE ADDRESS (
    STREET VARCHAR  NOT NULL,
    POSTALCODE INTEGER  NOT NULL,
    LOCATION VARCHAR  NOT NULL,
	PERSON_ID INTEGER
);

-- Table: TRANSACTION

CREATE TABLE TRANSACTION (
    CAR_ID INTEGER  NOT NULL,
	BUYER_ID INTEGER  NOT NULL,
	SELLER_ID INTEGER  NOT NULL,
    DATE DATE  NOT NULL
);

-- PRIMARY KEYS

ALTER TABLE ADDRESS
    ADD CONSTRAINT PK_ADDRESS PRIMARY KEY (STREET, POSTALCODE);
	
	ALTER TABLE TRANSACTION
    ADD CONSTRAINT PK_TRANSACTION PRIMARY KEY (CAR_ID, BUYER_ID,SELLER_ID,DATE);
	
	ALTER TABLE CAR_FEATURE
    ADD CONSTRAINT PK_CARFEATURE PRIMARY KEY (FEATURES_ID, CARS_ID);
	
--FOREIGN KEYS 


--TABLE BUYER

	ALTER TABLE BUYER
    ADD CONSTRAINT FK_ID FOREIGN KEY (ID)
    REFERENCES PERSON;


--TABLE SELLER 

	ALTER TABLE SELLER
    ADD CONSTRAINT FK_ID FOREIGN KEY (ID)
    REFERENCES PERSON;


--TABLE CAR

	ALTER TABLE CAR
    ADD CONSTRAINT FK_MANUFACTURER FOREIGN KEY (MANUFACTURER_ID)
    REFERENCES MANUFACTURER;
	
	ALTER TABLE CAR
    ADD CONSTRAINT FK_CATEGORY FOREIGN KEY (CATEGORY_ID)
    REFERENCES CATEGORY;
			
	--TABLE TRANSACTION
	
	ALTER TABLE TRANSACTION
    ADD CONSTRAINT FK_CAR FOREIGN KEY (CAR_ID)
    REFERENCES CAR;
	
	ALTER TABLE TRANSACTION
    ADD CONSTRAINT FK_BUYER FOREIGN KEY (BUYER_ID)
    REFERENCES BUYER;
	
	ALTER TABLE TRANSACTION
    ADD CONSTRAINT FK_SELLER FOREIGN KEY (SELLER_ID)
    REFERENCES SELLER;
	
	--TABLE CARFEATURE
	
	ALTER TABLE CAR_FEATURE
    ADD CONSTRAINT FK_FEATURE FOREIGN KEY (FEATURES_ID)
    REFERENCES FEATURE;
	
	ALTER TABLE CAR_FEATURE
    ADD CONSTRAINT FK_CAR FOREIGN KEY (CARS_ID)
    REFERENCES CAR;
	
	--TABLE ADDRESS 
	
	ALTER TABLE ADDRESS
	ADD CONSTRAINT FK_PERSON FOREIGN KEY(PERSON_ID)
	REFERENCES PERSON;