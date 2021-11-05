package components.excel;

import java.io.FileOutputStream;
import java.io.Serializable;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelCreatorImp implements ExcelCreator, Serializable {

	@Override
	public void createExcel(String fileName, Object[][] bookData) {

		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("FIR");

		int rowCount = 0;

		for (Object[] aBook : bookData) {
			Row row = sheet.createRow(++rowCount);

			int columnCount = 0;

			for (Object field : aBook) {
				Cell cell = row.createCell(++columnCount);
				if (field instanceof String) {
					cell.setCellValue((String) field);
				} else if (field instanceof Integer) {
					cell.setCellValue((Integer) field);
				}
			}

		}

		try (FileOutputStream outputStream = new FileOutputStream(fileName + ".xlsx")) {
			workbook.write(outputStream);
			System.out.println("excel created.....");
		} catch (Exception e) {
			System.out.println("Got error in saving excel... Please handle it....");
		}
	}

}
