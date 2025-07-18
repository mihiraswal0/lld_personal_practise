CREATE TABLE url_details (
short_url VARCHAR(20) PRIMARY KEY,
long_url VARCHAR(50) UNIQUE NOT NULL,
creation_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
expiry_time TIMESTAMP
);


CREATE TABLE available_ids (
    id  BIGINT AUTO_INCREMENT PRIMARY KEY,
    base_64_id VARCHAR(20) NOT NULL UNIQUE,
    status VARCHAR(10) NOT NULL DEFAULT 'UNUSED'
);
