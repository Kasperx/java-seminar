package de.linuxhotel.jf.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import javax.enterprise.inject.Alternative;

import de.linuxhotel.jf.persistence.Gender;
import de.linuxhotel.jf.persistence.Person;

@Alternative
public class JDBCPersonDaoImpl implements PersonDao {

	@Override
	public List<Person> getAll() {

		try {
			Connection c = DriverManager.getConnection("jdbc:mariadb://localhost:3306/lh", "newuser", "changeme");
			ResultSet rs = c.createStatement().executeQuery("select * from person");
			List l_persons = new Vector();
			
			while (rs.next()) {
				String vname = rs.getString("vorname");
				String nname = rs.getString("nachname");
				int groese = rs.getInt("groesse");
				Gender gender = Gender.valueOf(rs.getString("gender"));
				Person p = new Person(vname, nname, groese, gender);
				
				l_persons.add(p);
				
//				l_persons.add(
//						new Person(
//								rs.getString("vorname"),
//								rs.getString("nachname"),
//								rs.getFloat("groese"),
//								Gender.valueOf(rs.getString("gender"))
//								)
//						);
			}
			return l_persons;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public Person getById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveOrUpdate(Person person) {
		
		try {
		
			Connection c = DriverManager.getConnection("jdbc:mariadb://localhost:3306/lh", "newuser", "changeme");
			// TODO Auto-generated method stub
			String sql = null;
			if(person.getId() == null) {
				sql = "insert into person (vorname, nachname, groesse, gender) values (?,?,?,?)";
			} else {
				sql = "update person set vorname = ?, nachname = ?, groesse = ?, gender = ?";
			}
			PreparedStatement pst = c.prepareStatement(sql);
			pst.setString(1, person.getVorname());
			pst.setString(2, person.getNachname());
			pst.setInt(3, person.getGroesse());
			pst.setString(4, person.getGender().name());
			if (person.getId() != null)
				pst.setString(5, person.getId().toString());
			pst.executeUpdate();
		
		}catch (Exception e) {	
			e.printStackTrace();
		}
	}

	@Override
	public Person deleteById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	public List <Person> search(String name, String p) {
		
		try {
			Connection c = DriverManager.getConnection("jdbc:mariadb://localhost:3306/lh", "newuser", "changeme");
			
			// Problem!! example: this would get all data, because ..."or 1 = 1 equals true
			name = "' or 1 = 1";
			ResultSet rs1 = c.createStatement().executeQuery("select * from person where name = '"+name+"' and password = MD5('"+p+"', id)");
			//
			
			PreparedStatement pst = c.prepareStatement("select * from person where vorname ?");
			pst.setString(1, name); // sql.setstring -> '?' immer index=1
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				String vorname = rs.getString("vorname");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
	}
}
