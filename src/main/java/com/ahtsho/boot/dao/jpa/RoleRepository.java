package com.ahtsho.boot.dao.jpa;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ahtsho.boot.domain.Role;

@Repository
public class RoleRepository{
	private static final String ROLES_TABLE = "roles";

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Transactional(readOnly=true)
	public List<Role> findAll() {
		return jdbcTemplate.query("select * from "+ROLES_TABLE, new RolesRowMapper());
	}
}
class RolesRowMapper implements RowMapper<Role>{
	@Override
	public Role mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new Role(
				rs.getString("role_name"),
				rs.getString("description")
				);
	}
}