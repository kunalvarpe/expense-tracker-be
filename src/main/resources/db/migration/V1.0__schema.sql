-- User Info
CREATE TABLE IF NOT EXISTS user_info (
	id VARCHAR(50) PRIMARY KEY,
	first_name VARCHAR(20),
	last_name VARCHAR(20),
	email VARCHAR(20),
	role VARCHAR(50),
	mobile VARCHAR(13),
	created_by VARCHAR(50),
    created_at TIMESTAMP,
    modified_by VARCHAR(50),
    modifiedAt TIMESTAMP
);

CREATE INDEX IF NOT EXISTS last_name_idx ON user_info (last_name);

--  Expense
CREATE TABLE IF NOT EXISTS expenses (
	id SERIAL PRIMARY KEY,
	name VARCHAR(30) NOT NULL,
	category VARCHAR(30),
	amount NUMERIC(10, 2) NOT NULL,
	currency VARCHAR(3),
	created_by VARCHAR(50),
	created_at TIMESTAMP,
	modified_by VARCHAR(50),
    modified_at TIMESTAMP
);
CREATE INDEX IF NOT EXISTS name_category_idx ON expenses (name, category);

