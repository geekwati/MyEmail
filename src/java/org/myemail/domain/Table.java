package org.myemail.domain;

import java.sql.*;
public abstract class Table{
	// TO do table name variable
	abstract public Table generateObject(ResultSet rs);
	abstract public String generateInsertQuery();
	abstract public String generateSelectQuery();
	
}
