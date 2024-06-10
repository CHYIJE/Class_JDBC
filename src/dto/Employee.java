package dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Employee {
	private int employee_id;
	private String name;
	private String department;
	private int salary;

	@Override
	public String toString() {
		return "ID : " + employee_id + " || " + "NAME : " + name + " || " + "Department : " + department + " || "
				+ "Salary : " + salary;
	}

}
