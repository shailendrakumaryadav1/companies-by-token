package com.sky;

/**
 * Created by SKY on 12/20/2017.
 */
public class Company {

	String entityName;
	String officeAddress;
	String startDate;
	String status;
	String link;

	public Company() {
	}

	public Company(String entityName, String officeAddress, String startDate, String status,
			String link) {
		this.entityName = entityName;
		this.officeAddress = officeAddress;
		this.startDate = startDate;
		this.status = status;
		this.link = link;
	}

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public String getOfficeAddress() {
		return officeAddress;
	}

	public void setOfficeAddress(String officeAddress) {
		this.officeAddress = officeAddress;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		Company company = (Company) o;

		if (entityName != null ? !entityName.equals(company.entityName) :
				company.entityName != null)
			return false;
		if (officeAddress != null ? !officeAddress.equals(company.officeAddress) :
				company.officeAddress != null)
			return false;
		if (startDate != null ? !startDate.equals(company.startDate) : company.startDate != null)
			return false;
		if (status != null ? !status.equals(company.status) : company.status != null)
			return false;
		return link != null ? link.equals(company.link) : company.link == null;

	}

	@Override
	public int hashCode() {
		int result = entityName != null ? entityName.hashCode() : 0;
		result = 31 * result + (officeAddress != null ? officeAddress.hashCode() : 0);
		result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
		result = 31 * result + (status != null ? status.hashCode() : 0);
		result = 31 * result + (link != null ? link.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "Company{" + "entityName='" + entityName + '\'' + ", officeAddress='" + officeAddress
				+ '\'' + ", startDate='" + startDate + '\'' + ", status='" + status + '\''
				+ ", link='" + link + '\'' + '}';
	}
}
