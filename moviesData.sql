-- movies table
create TABLE movies (
    tconst VARCHAR(255) PRIMARY KEY,
	title_type VARCHAR(50),
    primary_title VARCHAR(255),
    runtime_minutes INT,
    genres VARCHAR(255)
);

-- ratings table
CREATE TABLE ratings (
    tconst VARCHAR(255) PRIMARY KEY,
    averageRating DECIMAL(3, 1),
    numVotes INT
);


-- Import data from CSV files into the tables
BULK INSERT movies
FROM 'E:\New folder (4)\movies.csv'
WITH (
    FORMAT = 'CSV',
    FIRSTROW = 2, -- Skip the header row if present
    FIELDTERMINATOR = ',',
    ROWTERMINATOR = '\n'
);

BULK INSERT ratings
FROM 'E:\New folder (4)\ratings.csv'
WITH (
    FORMAT = 'CSV',
    FIRSTROW = 2, -- Skip the header row if present
    FIELDTERMINATOR = ',',
    ROWTERMINATOR = '\n'
);


select * from ratings

select * from movies 
