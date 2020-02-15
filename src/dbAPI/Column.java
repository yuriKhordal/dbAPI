package dbAPI;

import dbAPI.Constraint;
import dbAPI.ConstraintsEnum;
import dbAPI.DatabaseDataType;
import dbAPI.IColumn;

public class Column implements IColumn {
	private String name;
	private int index;
	private DatabaseDataType type;
	private Constraint[] constraints;
	
	public Column(String name, int index, DatabaseDataType type) {
		this.name = name;
		this.index = index;
		this.type = type;
	}
	
	public Column(String name, int index, DatabaseDataType type, Constraint[] constraints) {
		this(name, index, type);
		this.constraints = constraints.clone();
	}
	
	public Column(String name, int index, DatabaseDataType type, ConstraintsEnum ... constraints) {
		this(name, index, type);
		this.constraints = new Constraint[constraints.length];
		int i = 0;
		for (ConstraintsEnum c : constraints) {
			this.constraints[i++] = new Constraint(c, this);
		}
	} 

	@Override
	public Constraint[] getConstraints() {
		return constraints.clone();
	}

	@Override
	public int getIndex() {
		return index;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public DatabaseDataType getType() {
		return type;
	}

	@Override
	public boolean hasConstraint(ConstraintsEnum constraint) {
		for (Constraint c : constraints) {
			if (c.getType() == constraint) {
				return true;
			}
		}
		return false;
	}

}
