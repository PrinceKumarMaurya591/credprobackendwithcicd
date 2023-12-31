//package com.au.credpro.report.service;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//import javax.persistence.EntityNotFoundException;
//import javax.persistence.Query;
//
//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.ss.usermodel.Sheet;
//import org.apache.poi.ss.usermodel.Workbook;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.stereotype.Service;
//
//import com.au.credpro.report.confguration.DatabaseProperties;
//import com.au.credpro.report.entity.QueryList;
//import com.au.credpro.report.exception.UnauthorizedException;
//import com.au.credpro.report.repository.QueryListRepository;
//
//@Service
//public class QueryExecutionService {
//
//	@Autowired
//	private QueryListRepository queryListRepository;
//
//@Autowired 
//private UserService userService;
//
//    private static final String DB_URL = "jdbc:mysql://localhost:3306/credpro";
//    private static final String DB_USER = "root";
//    private static final String DB_PASSWORD = "root";
//
////	 Assuming you have a method to connect to database1 and execute the given
////	 query
////	 Replace this method with the actual logic to connect to database1 and execute
////	 the query
//	private List<Map<String, Object>> executeQueryOnDatabase1(String query) {
////		String dbUrl = databaseProperties.getUrl();
////		String dbUser = databaseProperties.getUsername();
////		String dbPassword = databaseProperties.getPassword();
//		// Implement the logic to connect to database1 and execute the query
//		// Return the result as a List<Map<String, Object>> where each map represents a
//		// row of data
//		// with column names as keys and corresponding values
//		// You can use JDBC, JPA, or any other database access method to interact with
//		// database1
//		// For simplicity, let's assume you have implemented this method
//		// and the return type is List<Map<String, Object>>
//		List<Map<String, Object>> resultList = new ArrayList<>();
//		
//        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
////		try (Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
//
//				Statement statement = connection.createStatement();
//				ResultSet resultSet = statement.executeQuery(query)) {
//
//			int columnCount = resultSet.getMetaData().getColumnCount();
//
//			while (resultSet.next()) {
//				Map<String, Object> row = new HashMap<>();
//				for (int i = 1; i <= columnCount; i++) {
//					String columnName = resultSet.getMetaData().getColumnName(i);
//					Object columnValue = resultSet.getObject(i);
//					row.put(columnName, columnValue);
//				}
//				resultList.add(row);
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//			// You can handle the exception as required, such as logging or throwing a
//			// custom exception
//		}
//
//		return resultList;
//	}
//
//	
//	
//
//
////	    @Autowired
////	    @Qualifier("db2EntityManagerFactory")
////	    private EntityManagerFactory db2EntityManagerFactory;
////
////	    public List<Map<String, Object>> executeQueryOnDatabase1(String query) {
////	        List<Map<String, Object>> resultList = new ArrayList<>();
////
////	        EntityManager entityManager = db2EntityManagerFactory.createEntityManager();
////	        try {
////	            Query nativeQuery = entityManager.createNativeQuery(query);
////	            List<Object[]> rows = nativeQuery.getResultList();
////
////	            for (Object[] row : rows) {
////	                Map<String, Object> rowMap = new HashMap<>();
////	                for (int i = 0; i < row.length; i++) {
////	                    String columnName = nativeQuery.getResultList().get(0).getClass().getDeclaredFields()[i].getName();
////	                    Object columnValue = row[i];
////	                    rowMap.put(columnName, columnValue);
////	                }
////	                resultList.add(rowMap);
////	            }
////
////	        } finally {
////	            entityManager.close();
////	        }
////
////	        return resultList;
////	    }
//	
//	
//	
//	public Workbook executeQueryOnDatabase1AndGetExcel(String query) {
//		List<Map<String, Object>> resultList = executeQueryOnDatabase1(query);
//
//		Workbook workbook = new XSSFWorkbook();
//		Sheet sheet = workbook.createSheet("QueryResult");
//		Row headerRow = sheet.createRow(0);
//
//		// Create headers based on column names
//		int columnIdx = 0;
//		for (String columnName : resultList.get(0).keySet()) {
//			Cell cell = headerRow.createCell(columnIdx);
//			cell.setCellValue(columnName);
//			columnIdx++;
//		}
//
//		int rowIndex = 1;
//		for (Map<String, Object> rowMap : resultList) {
//			Row dataRow = sheet.createRow(rowIndex);
//			int columnIndex = 0;
//			for (Object cellValue : rowMap.values()) {
//				Cell cell = dataRow.createCell(columnIndex);
//				if (cellValue instanceof Number) {
//					cell.setCellValue(((Number) cellValue).doubleValue());
//				} else if (cellValue instanceof String) {
//					cell.setCellValue((String) cellValue);
//				} else if (cellValue instanceof Boolean) {
//					cell.setCellValue((Boolean) cellValue);
//				} else if (cellValue instanceof java.util.Date) {
//					cell.setCellValue((java.util.Date) cellValue);
//				} else {
//					cell.setCellValue(cellValue.toString());
//				}
//				columnIndex++;
//			}
//			rowIndex++;
//		}
//
//		return workbook;
//	}
//	
//	
//	
//	public Workbook executeQueryByAdminAndGetExcel(Long adminId, Long queryId) throws UnauthorizedException {
//	    QueryList queryList = queryListRepository.findById(queryId)
//	            .orElseThrow(() -> new EntityNotFoundException("Query not found"));
//
//	    String query = queryList.getQuery();
//	    return executeQueryOnDatabase1AndGetExcel(query);
//	}
//	
//
//	public Workbook executeAssignedQueryAndGetExcel(Long userId, Long queryId) throws UnauthorizedException {
//		QueryList queryList = queryListRepository.findById(queryId)
//				.orElseThrow(() -> new EntityNotFoundException("Query not found"));
//
//		// Check if the user is assigned to this query
////		if (queryList.getUser().stream().noneMatch(user -> user.getUserId().equals(userId))) {
////			throw new UnauthorizedException("You are not authorized to execute this query.");
////		}
//		
//		// Check if the user is assigned to this query or if the user is admin
//		  if (queryList.getUser().stream().noneMatch(user -> user.getUserId().equals(userId))
//		            && !userService.getUser(userId).getIsAdmin()) {
//		        throw new UnauthorizedException("You are not authorized to execute this query.");
//		    }
//		
//
//		String query = queryList.getQuery();
//		return executeQueryOnDatabase1AndGetExcel(query);
//	}
//}