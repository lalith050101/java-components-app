package components.excel;

import java.io.Serializable;

public interface ExcelCreator extends Serializable {
	public void createExcel(String fileName, Object[][] content);
}
