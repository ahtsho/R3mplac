package com.ahtsho.boot.dao.jpa;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ahtsho.boot.domain.Activity;

@Repository
public class CalendarRepository{
	private static final String CALENDAR_TABLE = " remplace.calendar ";

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Transactional(readOnly=true)
	public List<Activity> findAll() {
		return jdbcTemplate.query("select * from "+CALENDAR_TABLE, new CalendarRowMapper());
	}

	public void create(Activity activity) {
		jdbcTemplate.update("INSERT INTO "+CALENDAR_TABLE+" "
				+ "(activity, start, end, user_id) "
				+ "VALUES (?,?,?,?)", 
				new Object[] {activity.getActivity(),activity.getStart(),activity.getEnd(),activity.getUser_id()},
				new int[] { Types.VARCHAR, Types.DATE, Types.DATE, Types.INTEGER});
	}
	/*
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
		users.addAll(jdbcTemplate.query("select * from "+CALENDAR_TABLE 
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
    }

	public void update(User user) {
		jdbcTemplate.update("UPDATE "+USER_INFO_TABLE+" SET" + 
				"name = ?" +
				"surname = ?" +
				"WHERE id=?",new Object[] {
						user.getName(),
						user.getSurname(),
						user.getId()
				},
				new int[] {Types.VARCHAR, Types.VARCHAR,Types.INTEGER});
	}
	*/
}
class CalendarRowMapper implements RowMapper<Activity>{
	@Override
	public Activity mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new Activity(
				rs.getInt("user_id"),
				rs.getString("activity"),
				rs.getDate("start"),
				rs.getDate("end")
				);
	}
}