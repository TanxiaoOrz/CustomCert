-- 判断customcert数据库是否存在，不存在则创建
IF DB_ID(N'customcert') IS NULL
BEGIN
    CREATE DATABASE customcert;
    PRINT N'customcert 数据库创建成功'; -- 修正打印信息，与数据库名一致
END
ELSE
BEGIN
    PRINT N'customcert 数据库已存在，无需重复创建'; -- 修正打印信息
END
Go
-- 切换到customcert数据库（后续操作针对该库）
USE customcert;

-- 正确判断xml_log数据表是否存在（两种方式二选一，以下提供更简洁的OBJECT_ID方式）
IF OBJECT_ID(N'xml_log', N'U') IS NULL -- N'U' 表示判断用户表（User Table）
BEGIN
    -- 创建XML日志表（对应实体类XmlLog，补充自增列语法IDENTITY(1,1)）
CREATE TABLE xml_log (
    -- 主键ID：自增列（IDENTITY(1,1)），BIGINT类型，非空主键（对应MyBatis-Plus的IdType.AUTO）
                         id BIGINT NOT NULL  PRIMARY KEY,
    -- 类型名称：可变字符串，长度50，允许为空
                         type_name VARCHAR(50) NULL,
    -- 主ID：可变字符串，长度64，允许为空
                         main_id VARCHAR(64) NULL,
    -- 文件路径根目录：可变字符串，长度255，允许为空
                         file_path_root VARCHAR(255) NULL,
    -- 输入文件名称：可变字符串，长度255，允许为空
                         input_file VARCHAR(255) NULL,
    -- 输入文件内容：大文本类型，允许为空
                         input_file_context VARCHAR(MAX) NULL,
        -- 输入时间：日期时间类型，允许为空
        input_date_time DATETIME NULL,
        -- 处理状态：可变字符串，长度20，允许为空
        status VARCHAR(20) NULL,
        -- 结果文件名称：可变字符串，长度255，允许为空
        answer_file VARCHAR(255) NULL,
        -- 结果文件内容：大文本类型，允许为空
        answer_file_context VARCHAR(MAX) NULL,
        -- 结果时间：日期时间类型，允许为空
        answer_date_time DATETIME NULL
    );

-- 为常用查询字段创建索引（提升查询效率）
CREATE INDEX idx_xml_log_main_id ON xml_log(main_id);
CREATE INDEX idx_xml_log_input_date_time ON xml_log(input_date_time);
CREATE INDEX idx_xml_log_status ON xml_log(status);
CREATE INDEX idx_xml_log_type_name ON xml_log(type_name);
CREATE INDEX idx_xml_log_type_name_join_status ON xml_log(type_name, status);

-- 修正打印信息，明确是数据表创建成功
PRINT N'数据表 xml_log 创建成功！';
END
ELSE
BEGIN
    -- 修正打印信息，明确是数据表已存在
    PRINT N'数据表 xml_log 已存在，无需重复创建！';
END

Go