CREATE TABLE Copy(
copyId VARCHAR NOT NULL PRIMARY KEY,
status VARCHAR,
id VARCHAR references Book(id)
);