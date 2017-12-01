CREATE TABLE book_authors(
book_id VARCHAR references book(id),
author_name VARCHAR  references author(name),
PRIMARY KEY(book_id, author_name)
);