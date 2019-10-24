package de.linuxhotel.jf.persistence;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import de.linuxhotel.jf.report.Export;
import de.linuxhotel.jf.report.ReportColor;

@Entity
public class Person extends BaseObject implements Serializable{

	@Export (title="firstname", order=1, color=ReportColor.RED)
	private String vorname;
	@Export (title="lastname", order=2, color=ReportColor.RED)
	private String nachname;
	private int groesse;
	private Gender gender;
	
	public Person() {
	}

	@ManyToOne (cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Company company;
	
	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public Person(String vorname, String nachname, int groesse, Gender gender) {
		this.vorname = vorname;
		this.nachname = nachname;
		this.groesse = groesse;
		this.gender = gender;
	}

	public String getVorname() {
		return vorname;
	}

	public void setVorname(String vorname) {
		this.vorname = vorname;
	}

	public String getNachname() {
		return nachname;
	}

	public void setNachname(String nachname) {
		this.nachname = nachname;
	}

	public int getGroesse() {
		return groesse;
	}

	public void setGroesse(int groesse) {
		this.groesse = groesse;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}
	
	@Override
	public String toString() {
		return "Person [vorname=" + vorname + ", nachname=" + nachname + ", groesse=" + groesse + ", gender=" + gender
				+ "]";
	}
}
