package de.linuxhotel.jf.report;

import java.lang.reflect.Field;

public class ExportConf {
	protected Field field;
	protected Export export;

	public ExportConf(Field field, Export export) {
		super();
		this.field = field;
		this.export = export;
	}
}
