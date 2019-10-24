package de.linuxhotel.jf.cdi;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerProducer {
	
	@Produces
	public Logger create(InjectionPoint p) {
		return LoggerFactory.getLogger(p.getMember().getDeclaringClass());
	}
}
