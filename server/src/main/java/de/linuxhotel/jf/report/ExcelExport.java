package de.linuxhotel.jf.report;

import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import de.linuxhotel.jf.persistence.Person;

public class ExcelExport {

//	public static void main(String[] args) {
//	
//		TestPersonDaoImpl test = new TestPersonDaoImpl();
//		List <Person> list = test.getAll();
//		generateReport(list);
//	}

	public static void generateReport(List<Person> list, OutputStream out) {
		
		if (list.size() == 0)
			return;
		
		Class bauplan = list.get(0).getClass();
		List <ExportConf> configs = new ArrayList();
		Field [] fields = bauplan.getDeclaredFields();
		for (Field field: fields) {
			Export[] export = field.getAnnotationsByType(Export.class);
			for (Export temp : export) {
				ExportConf ex = new ExportConf(field, temp);
				configs.add(ex);
			}
		}
		
		String vorname, nachname;
		vorname = "";
		nachname = "";
		Vector <String> name = new Vector();
		
		for (Person temp_person : list) {
			int index = 0;
			String temp = "";
			
			for (ExportConf temp_export : configs) {
				temp_export.field.setAccessible(true);

				try {
				
				if (index++ == 0) {
					vorname = temp_export.field.get(temp_person).toString();
					temp = temp_export.field.get(temp_person).toString();
					//System.out.print("Vorname: "+vorname + "\t");
				} else {
					nachname = temp_export.field.get(temp_person).toString();
					//temp += ","+temp_export.field.get(temp_person).toString();
					//System.out.print("Nachname: "+nachname);
					//System.out.println();
				}
					
				}catch (Exception e) {
					e.printStackTrace();
				}
			}
			//name.add(vorname + "," + nachname);
			name.add(vorname + "," + nachname);
		}
		
		try (Workbook wb = new XSSFWorkbook()) {
		CreationHelper createHelper = wb.getCreationHelper();
		Sheet sheet = wb.createSheet("new sheet");
		Row row;
		Cell cell;
		int index = 0;
		row = sheet.createRow(index++);
		cell = row.createCell(0);
		row.createCell(0).setCellValue(createHelper.createRichTextString("vorname"));
		row.createCell(1).setCellValue(createHelper.createRichTextString("nachname"));
		
		XSSFCellStyle style;
		style = (XSSFCellStyle) wb.createCellStyle();
        style.setFillForegroundColor(IndexedColors.LIGHT_CORNFLOWER_BLUE.getIndex());
        XSSFFont font = (XSSFFont) wb.createFont();
        font.setColor(IndexedColors.BLUE.getIndex());
		
		for (String temp: name) {
			row = sheet.createRow(index++);

			style.setFont(font);
			cell = row.createCell(0);
			cell.setCellStyle(style);
			cell.setCellValue(createHelper.createRichTextString(temp.split(",")[0]));
			cell = row.createCell(1);
			cell.setCellStyle(style);
			cell.setCellValue(createHelper.createRichTextString(temp.split(",")[1]));
			cell = row.createCell(2);
			cell.setCellStyle(style);
			cell.setCellValue(createHelper.createRichTextString(list.get(name.indexOf(temp)).getVorname()));
			cell = row.createCell(3);
			cell.setCellStyle(style);
			cell.setCellValue(createHelper.createRichTextString(list.get(name.indexOf(temp)).getNachname()));
		
			wb.write(out);
		
		}
		
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
