-- Создаём схему
CREATE SCHEMA IF NOT EXISTS templates;

-- ----------------------------
-- SEQUENCES
-- ----------------------------
CREATE SEQUENCE IF NOT EXISTS templates.channels_seq
    START WITH 1 INCREMENT BY 1 CACHE 1;

CREATE SEQUENCE IF NOT EXISTS templates.templates_seq
    START WITH 1 INCREMENT BY 1 CACHE 1;

CREATE SEQUENCE IF NOT EXISTS templates.channel_templates_seq
    START WITH 1 INCREMENT BY 1 CACHE 1;

-- ----------------------------
-- TABLES
-- ----------------------------
CREATE TABLE IF NOT EXISTS templates.channels (
    id INT PRIMARY KEY DEFAULT nextval('templates.channels_seq'),
    name VARCHAR(100) NOT NULL,
    type VARCHAR(50) NOT NULL
    );

CREATE TABLE IF NOT EXISTS templates.templates (
    id INT PRIMARY KEY DEFAULT nextval('templates.templates_seq'),
    title VARCHAR(150) NOT NULL,
    name VARCHAR(100) NOT NULL,
    content TEXT NOT NULL,
    variables TEXT[],
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    );


CREATE TABLE IF NOT EXISTS templates.channel_templates (
    channel_id INT NOT NULL REFERENCES templates.channels(id) ON DELETE CASCADE,
    template_id INT NOT NULL REFERENCES templates.templates(id) ON DELETE CASCADE,
    PRIMARY KEY (channel_id, template_id)
    );

-- ----------------------------
-- INITIAL DATA
-- ----------------------------
INSERT INTO templates.channels (name, type) VALUES
     ('Telegram', 'TELEGRAM'),
     ('Phone', 'SMS');

INSERT INTO templates.templates (title, name, content, variables) VALUES
      ('Приветствие Telegram', 'welcome_telegram', 'Привет, {{username}}!', ARRAY['username']),
      ('Приветствие SMS', 'welcome_sms', 'Привет, {{username}}! Телефон: {{phone}}', ARRAY['username','phone']);

INSERT INTO templates.channel_templates (channel_id, template_id) VALUES
      (1, 1),
      (2, 2);