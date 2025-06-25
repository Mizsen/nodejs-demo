-- 用户表
CREATE TABLE IF NOT EXISTS user (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    real_name VARCHAR(50),
    role VARCHAR(20) NOT NULL,
    enabled BOOLEAN NOT NULL DEFAULT 1,
    created_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    last_login_time DATETIME,
    last_login_ip VARCHAR(45)
);
CREATE UNIQUE INDEX IF NOT EXISTS idx_user_username ON user(username);

-- 权限表
CREATE TABLE IF NOT EXISTS authority (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    user_id INTEGER REFERENCES user(id),
    authority VARCHAR(50) NOT NULL
);
CREATE UNIQUE INDEX IF NOT EXISTS idx_user_authority ON authority(user_id, authority);

-- 药方表
CREATE TABLE IF NOT EXISTS prescription (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    prescription_name VARCHAR(100) NOT NULL UNIQUE,
    indications TEXT NOT NULL,
    usage TEXT NOT NULL,
    treatment_cycle VARCHAR(50),
    created_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    created_by INTEGER REFERENCES user(id),
    updated_time DATETIME,
    updated_by INTEGER REFERENCES user(id)
);
CREATE INDEX IF NOT EXISTS idx_prescription_name ON prescription(prescription_name);
CREATE INDEX IF NOT EXISTS idx_prescription_indication ON prescription(indications);

-- 药品表
CREATE TABLE IF NOT EXISTS drug (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    drug_name VARCHAR(100) NOT NULL UNIQUE,
    specification VARCHAR(50),
    manufacturer VARCHAR(100),
    indications TEXT,
    created_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    created_by INTEGER REFERENCES user(id),
    updated_time DATETIME,
    updated_by INTEGER REFERENCES user(id)
);
CREATE INDEX IF NOT EXISTS idx_drug_name ON drug(drug_name);

-- 药方-药品关联表
CREATE TABLE IF NOT EXISTS prescription_drug (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    prescription_id INTEGER REFERENCES prescription(id),
    drug_id INTEGER REFERENCES drug(id),
    dosage VARCHAR(50) NOT NULL
);
CREATE UNIQUE INDEX IF NOT EXISTS idx_prescription_drug ON prescription_drug(prescription_id, drug_id);

-- 药方图片表
CREATE TABLE IF NOT EXISTS prescription_image (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    prescription_id INTEGER REFERENCES prescription(id),
    image_path VARCHAR(255) NOT NULL,
    image_type VARCHAR(20) NOT NULL,
    sort_order INTEGER DEFAULT 0,
    created_time DATETIME DEFAULT CURRENT_TIMESTAMP
);
CREATE INDEX IF NOT EXISTS idx_prescription_image ON prescription_image(prescription_id);

-- 药品图片表
CREATE TABLE IF NOT EXISTS drug_image (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    drug_id INTEGER REFERENCES drug(id),
    image_path VARCHAR(255) NOT NULL,
    image_type VARCHAR(20) NOT NULL,
    sort_order INTEGER DEFAULT 0,
    created_time DATETIME DEFAULT CURRENT_TIMESTAMP
);
CREATE INDEX IF NOT EXISTS idx_drug_image ON drug_image(drug_id);

