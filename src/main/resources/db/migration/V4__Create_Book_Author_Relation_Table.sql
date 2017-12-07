CREATE TABLE book_authors(
book_id VARCHAR references book(id),
author_id INTEGER   references author(id),
PRIMARY KEY(book_id, author_id)
);