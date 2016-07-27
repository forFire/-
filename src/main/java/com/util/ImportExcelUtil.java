/**
 * 
 */
package com.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;



/**
 * @author zf 待改进
 * 导入excel的格式   第一行是列，第二行以后是数据
 * @return List<String[]>  list.get(0) => sheet String[0] 是列名
 */
public class ImportExcelUtil {
	
	/** 
     * Excel 2003 
     */  
    private final static String XLS = "xls";  
    /** 
     * Excel 2007 
     */  
    private final static String XLSX = "xlsx";  
    /** 
     * 分隔符 
     */  
    private final static String SEPARATOR = "|";  
	
	/** 
     * 由Excel文件的Sheet导出至List 
     * @param file 
     * @param sheetNum 
     * @return 
     */  
    public static List<String[]> exportListFromExcel(MultipartFile file)   throws IOException {  
        return exportListFromExcel(file.getInputStream(), FilenameUtils.getExtension(file.getOriginalFilename()));  
    }  
  
	
    /** 
     * 由Excel流的Sheet导出至List 
     * @param is 
     * @param extensionName 
     * @param sheetNum 
     * @return 
     * @throws IOException 
     */  
    public static List<String[]> exportListFromExcel(InputStream is, String extensionName) throws IOException {  
        Workbook workbook = null;  
        XSSFWorkbook xSSFWorkbook = null;
        if (extensionName.toLowerCase().equals(XLS)) {  
            workbook = new HSSFWorkbook(is); 
//            return exportListFromExcel(workbook, sheetNum);  
            return null;
        } else if (extensionName.toLowerCase().equals(XLSX)) {  
        	xSSFWorkbook = new XSSFWorkbook(is); 
        	return exportXSSFListFromExcel(xSSFWorkbook); 
        }  else{
        	return null;
        }
       
    }  
  
    
	public static List<String[]> exportXSSFListFromExcel(XSSFWorkbook wb) {
		
		int sheet_numbers = wb.getNumberOfSheets();// 获取表的总数
		String[] sheetnames = new String[sheet_numbers];
		// 用来存储某个表
		List<String[]> ls_a = new ArrayList<String[]>(); 
		
		//遍历多个sheet表
		for (int i = 0; i < sheet_numbers; i++) {// 遍历所有表
			XSSFSheet sheet = wb.getSheetAt(i); // 获取 某个表
			sheetnames[i] = sheet.getSheetName();// 获取表名，存入数组
			System.out.println("------>>>---正在读取Excel表数据，当前表：" + sheetnames[i]);
			//遍历行--------------从第三行开始读取数据
			for (int rows = 1; rows < sheet.getLastRowNum(); rows++) {// 有多少行
				XSSFRow row = sheet.getRow(rows);// 取得某一行 对象
				
				String[] s = new String[row.getLastCellNum()];// 初始化数组长度
				
                //遍历列
				for (int columns = 0; columns < row.getLastCellNum(); columns++) {// 读取所有列
					
					//取得某单元格内容，字符串类型
					XSSFCell cell = row.getCell(columns);
					if (cell != null) {
						
						switch (cell.getCellType()) {
						
							case XSSFCell.CELL_TYPE_STRING: // 字符串
								
								s[columns] = cell.getStringCellValue();
								if (s[columns] == null) {
									s[columns] = " ";
								}
								break;
								
							case XSSFCell.CELL_TYPE_NUMERIC: // 数字
								double strCell = cell.getNumericCellValue();
								if (String.valueOf(strCell) == null) {
									s[columns] = " ";
								}
							case XSSFCell.CELL_TYPE_BOOLEAN:
								
								s[columns] = String.valueOf(cell.getNumericCellValue());
								if (s[columns] == null) {
									s[columns] = " ";
								}
								break;
							
							case XSSFCell.CELL_TYPE_FORMULA:
								s[columns] = String.valueOf(cell.getCellFormula());
								if (s[columns] == null) {
									s[columns] = " ";
								}
								break;
							case XSSFCell.CELL_TYPE_BLANK: // 空值
								s[columns] = " ";
								break;
							default:
								System.out.print("\n---单元格格式不支持---");
								break;
						}
					}
				 }
				
				ls_a.add(s);
				
			  }
		  }
		return ls_a;
	}

	@SuppressWarnings("static-access")
	public static void main(String[] args) {
//		FileInputStream input = null;
//		try {
//			input = new FileInputStream(new File("E:/消防设备点表.xlsx"));
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}// 读取的文件路径
		ImportExcelUtil im = new ImportExcelUtil();
		List<String[]> list =new ArrayList<String[]>();
//		try {
//			list = im.exportListFromExcel(new MultipartFile("E:/消防设备点表.xlsx"));
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		for(String[] s :list){
			for(String str :s){
				System.out.println(str);
			}
		}
	}

}
