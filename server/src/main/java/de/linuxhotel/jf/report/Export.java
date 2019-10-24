package de.linuxhotel.jf.report;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME) // set time of run
@Target(ElementType.FIELD) // set validity for this annotation
@Documented // filter, to document this class for sure
@Inherited // filter, to make this possible on extension
//@Repeatable(Export.class) // make multiple ... possible
public @interface Export {
	int order();
	String title();
	ReportColor color() default ReportColor.BLUE;
}
