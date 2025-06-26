import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final String DB_URL = "jdbc:sqlite:todo.db";
    private static final String SQL_FILE_PATH = "src/main/resources/sql/schema.sql";

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(DB_URL)) {
            System.out.println("Connection to SQLite established.");
            executeSqlScript(connection, SQL_FILE_PATH);
        } catch (SQLException e) {
            System.err.println("Database connection failed: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void executeSqlScript(Connection connection, String filePath) {
        try {
            // 读取SQL文件内容
            String sqlContent = new String(Files.readAllBytes(Paths.get(filePath)), "UTF-8");
            
            // 移除所有注释
            String sqlWithoutComments = removeComments(sqlContent);
            
            // 按分号分割SQL语句
            String[] sqlStatements = sqlWithoutComments.split(";");
            
            try (Statement statement = connection.createStatement()) {
                connection.setAutoCommit(false);
                
                for (int i = 0; i < sqlStatements.length; i++) {
                    String sql = sqlStatements[i].trim();
                    if (sql.isEmpty()) {
                        continue;
                    }
                    
                    System.out.println("\n===== 执行SQL [" + (i+1) + "] =====");
                    System.out.println(sql);
                    
                    try {
                        statement.execute(sql);
                        System.out.println("✅ SQL执行成功");
                    } catch (SQLException e) {
                        System.err.println("❌ SQL执行失败: " + e.getMessage());
                        connection.rollback();
                        throw new SQLException("SQL语句 [" + (i+1) + "] 执行失败: " + sql, e);
                    }
                }
                
                connection.commit();
                System.out.println("\n===== 所有SQL语句执行完毕 =====");
            }
        } catch (IOException e) {
            System.err.println("读取SQL文件失败: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("数据库操作失败: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 移除SQL脚本中的所有注释
     */
    private static String removeComments(String sql) {
        // 分割为行
        String[] lines = sql.split("\n");
        StringBuilder result = new StringBuilder();
        
        boolean inMultiLineComment = false;
        for (String line : lines) {
            String trimmedLine = line.trim();
            
            // 处理多行注释开始
            if (trimmedLine.startsWith("/*") && !trimmedLine.endsWith("*/")) {
                inMultiLineComment = true;
                continue;
            }
            
            // 处理多行注释中间
            if (inMultiLineComment) {
                if (trimmedLine.endsWith("*/")) {
                    inMultiLineComment = false;
                }
                continue;
            }
            
            // 处理多行注释结束
            if (trimmedLine.startsWith("/*") && trimmedLine.endsWith("*/")) {
                continue;
            }
            
            // 处理单行注释
            if (trimmedLine.startsWith("--")) {
                continue;
            }
            
            // 保留非注释行
            int singleQuoteIndex = line.indexOf("'");
            int doubleQuoteIndex = line.indexOf("\"");
            int commentIndex = line.indexOf("--");
            
            // 确保注释不在引号内
            if (commentIndex != -1) {
                if ((singleQuoteIndex == -1 || commentIndex < singleQuoteIndex) && 
                    (doubleQuoteIndex == -1 || commentIndex < doubleQuoteIndex)) {
                    line = line.substring(0, commentIndex);
                }
            }
            
            result.append(line).append("\n");
        }
        
        return result.toString();
    }
}