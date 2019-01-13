package com.ahtsho.boot.dao.jpa;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ahtsho.boot.domain.Contact;
import com.ahtsho.boot.domain.User;
import com.ahtsho.boot.domain.UserInfo;

@Repository
public class UserRepository{
	private static final String USERS_TABLE = " remplace.users ";
	private static final String CONTACTS_TABLE = " remplace.contacts ";
	private static final String USER_INFO_TABLE = " remplace.user_info ";

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Transactional(readOnly=true)
	public List<User> findAll() {
		return jdbcTemplate.query("select * from "+USERS_TABLE, new UserRowMapper());
	}

	public void create(User user) {
		jdbcTemplate.update("INSERT INTO "+USERS_TABLE+" "
				+ "(username, mister, name, surname, role) "
				+ "VALUES (?,?,?,?,?)", 
				new Object[] {user.getUsername(),user.getMister(),user.getName(),user.getSurname(),user.getRole()},
				new int[] { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR});
	}

	public List<User> find(User user) {
		List<User>users = new ArrayList<User>();
		List<String> conditions = new ArrayList<>();
		if(user.getName()!=null) {
			conditions.add(" name like '"+applyMatchAny(user.getName())+"' ");
		} if(user.getSurname()!=null) {
			conditions.add(" surname like '"+applyMatchAny(user.getSurname())+"' ");
		} if(user.getUsername()!=null) {
			conditions.add(" username like '"+applyMatchAny(user.getUsername())+"' ");
		}
		users.addAll(jdbcTemplate.query("select * from "+USERS_TABLE 
				+ " WHERE "+conditions.stream().collect(Collectors.joining(" and ")), 
				new UserRowMapper()));
		return users;
	}
	public User findUser(int id) {
		return jdbcTemplate.queryForObject("select * from users where id=?",
				new Object[]{id}, new UserRowMapper());
	}

	private String applyMatchAny(String name) {
		return "%"+name+"%";
	}

	public List<UserInfo> findUserInfo(int userid) {
		return jdbcTemplate.query("select * from "+USER_INFO_TABLE+" where user_id=?", 
				new Object[] {userid},new BeanPropertyRowMapper<UserInfo>(UserInfo.class));
	}

	public List<Contact> findContacts(int userid) {
		return jdbcTemplate.query("select * from "+CONTACTS_TABLE+" where user_id=?", 
		new Object[] {userid},new BeanPropertyRowMapper<Contact>(Contact.class));
	}

	public void insertContact(Contact contact) {
		jdbcTemplate.update("insert into "+CONTACTS_TABLE+" (user_id, phone_no, email, facebook) "
				+ "values (?,?,?,?)", 
				new Object[] {
						contact.getUser_id(), 
						contact.getPhone_no(),
						contact.getEmail(),
						contact.getFacebook()},
				new int[] {Types.INTEGER, Types.VARCHAR,Types.VARCHAR,Types.VARCHAR});
	}

	public void insertUserInfo(UserInfo info) {
		jdbcTemplate.update("insert into "+USER_INFO_TABLE+" (user_id, cv, photo) "
				+ "values (?,?,?)",
				new Object[] {
						info.getUser_id(),
						info.getCv(),
						info.getPhoto()},
				new int[] {Types.INTEGER, Types.BLOB, Types.BLOB});
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

	public void update(User user) {
		jdbcTemplate.update("UPDATE "+USERS_TABLE+" SET" + 
				" name = ?, " +
				" surname = ?, " +
				" role = ?" +
				" WHERE id=?",new Object[] {
						user.getName(),
						user.getSurname(),
						user.getRole(),
						user.getId()
				},
				new int[] {Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.INTEGER});
	}
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