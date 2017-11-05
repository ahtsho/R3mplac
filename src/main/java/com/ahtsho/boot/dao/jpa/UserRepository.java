package com.ahtsho.boot.dao.jpa;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.ahtsho.boot.domain.User;

@Repository
public class UserRepository{
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Transactional(readOnly=true)
	public List<User> findAll() {
		return jdbcTemplate.query("select * from users", new UserRowMapper());
	}

/* 
    @Transactional(readOnly=true)
    public User findUserById(int id) {
        return jdbcTemplate.queryForObject(
            "select * from users where id=?",
            new Object[]{id}, new UserRowMapper());
    }
 
    public User create(final User user) 
    {
        final String sql = "insert into users(name,email) values(?,?)";
 
        KeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, user.getName());
                ps.setString(2, user.getEmail());
                return ps;
            }
        }, holder);
 
        int newUserId = holder.getKey().intValue();
        user.setId(newUserId);
        return user;
    }*/
}

class UserRowMapper implements RowMapper<User>{
	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new User(
				rs.getInt("id"),
				rs.getString("username"),
				rs.getString("mister"),
				rs.getString("name"),
				rs.getString("surname"),
				rs.getString("role")
				);
	}
}