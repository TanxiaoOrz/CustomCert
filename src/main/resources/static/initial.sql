IF DB_ID(N'customcert') IS NULL
BEGIN
    CREATE DATABASE customcert;
    PRINT N'TestDB 数据库创建成功';
END
ELSE
BEGIN
    PRINT N'TestDB 数据库已存在，无需重复创建';
END

use customcert;

if ((select count(1) from sys.databases where name = 'xml_log') = 0)
BEGIN
        -- 创建XML日志表（对应实体类XmlLog，表名采用小写下划线命名，符合数据库惯例）
CREATE TABLE xml_log (
    -- 主键ID：自增列（对应MyBatis-Plus的IdType.AUTO），BIGINT类型（对应实体类Long）
                         id BIGINT NOT NULL PRIMARY KEY,
    -- 类型名称：可变字符串，长度50（可根据实际业务调整），允许为空
                         type_name VARCHAR(50) NULL,
    -- 主ID：可变字符串，长度64（适配常见唯一标识长度），允许为空
                         main_id VARCHAR(64) NULL,
    -- 输入文件名称：可变字符串，长度255（适配文件路径/名称长度），允许为空
                         input_file VARCHAR(255) NULL,
    -- 输入文件内容：大文本类型（若内容较长，推荐用VARCHAR(MAX)），允许为空
                         input_file_context VARCHAR(MAX) NULL,
            -- 输入时间：日期时间类型（对应实体类Date/LocalDateTime），允许为空
                                 input_date_time DATETIME NULL,
            -- 处理状态：可变字符串，长度20（适配如"SUCCESS"/"FAILED"/"PROCESSING"等状态），允许为空
                                 status VARCHAR(20) NULL,
            -- 结果文件名称：可变字符串，长度255，允许为空
                                 answer_file VARCHAR(255) NULL,
            -- 结果文件内容：大文本类型，允许为空
                                 answer_file_context VARCHAR(MAX) NULL
        );
-- 可选：为常用查询字段创建索引（提升查询效率，根据业务需求添加）
CREATE INDEX idx_xml_log_main_id ON xml_log(main_id);
CREATE INDEX idx_xml_log_input_date_time ON xml_log(input_date_time);
create index idx_xml_log_status on xml_log(status);
create index idx_xml_log_type_name on xml_log(type_name);
create index idx_xml_log_type_name_join_status on xml_log(type_name, status);

PRINT N'数据库 ' + 'xml_log' + N' 创建成功！';
end
else
begin
        PRINT N'数据库' + 'xml_log' + N' 已存在，无需重复创建！';
end

