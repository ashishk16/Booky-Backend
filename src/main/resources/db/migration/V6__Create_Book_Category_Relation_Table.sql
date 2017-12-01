CREATE TABLE book_categories(
book_id VARCHAR references book(id),
category_name VARCHAR references category(name),
PRIMARY KEY(book_id, category_name)
);