package de.linuxhotel.jf.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.enterprise.inject.Alternative;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import de.linuxhotel.jf.persistence.Company;

public class JPACrudCompanyDaoImpl extends JPACrudDaoImpl<Company, Long>{	
}
