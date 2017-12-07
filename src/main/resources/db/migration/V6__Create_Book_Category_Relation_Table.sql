CREATE TABLE book_categories(
book_id VARCHAR references book(id),
category_id INTEGER references category(id),
PRIMARY KEY(book_id, category_id)
);