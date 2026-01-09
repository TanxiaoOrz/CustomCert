IF DB_ID(N'customcert') IS NULL
BEGIN
    CREATE DATABASE customcert;
    PRINT N'TestDB 数据库创建成功';
END
ELSE
BEGIN
    PRINT N'TestDB 数据库已存在，无需重复创建';
END