package de.linuxhotel.jf.persistence;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import de.linuxhotel.jf.report.Export;

@Entity
public class Company extends BaseObject{

	@Export(title="company name", order=3)
	private String name;

	public Company() {
	}

	@OneToMany(mappedBy = "company")
	private Set <Person> mitarbeiter;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Person> getMitarbeiter() {
		return mitarbeiter;
	}

	public void setMitarbeiter(Set<Person> mitarbeiter) {
		this.mitarbeiter = mitarbeiter;
	}
}
