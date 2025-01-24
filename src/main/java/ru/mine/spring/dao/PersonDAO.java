package ru.mine.spring.dao;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.mine.spring.models.Person;

import javax.validation.Valid;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public PersonDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional(readOnly = true)
    public List<Person> index() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select p from Person p", Person.class).getResultList();
    }

    @Transactional(readOnly = true)
    public Person show(int id) {
        return sessionFactory.getCurrentSession().get(Person.class, id);
    }

    @Transactional
    public void save(Person person) {
        Session session = sessionFactory.getCurrentSession();
        session.save(person);
    }

    @Transactional
    public void update(int id, Person person) {
        Session session = sessionFactory.getCurrentSession();
        Person existingPerson = session.get(Person.class, id);
        if (existingPerson != null) {
            existingPerson.setName(person.getName());
            existingPerson.setAge(person.getAge());
            existingPerson.setEmail(person.getEmail());
            session.update(existingPerson);
        }
    }

    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        Person person = session.get(Person.class, id);
        if (person != null) {
            session.remove(person);
        }
    }


//    private JdbcTemplate jdbcTemplate;
//
//    @Autowired
//    public PersonDAO(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }
//
//    public List<Person> index() {
//        String sql = "select * from person";
//        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Person.class));
//    }
//
//    public Person show(int id) {
//        String sql = "select * from person where id = ?";
//        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper<>(Person.class));
//    }
//
//    public void save(Person person) {
//        String sql = "insert into person(name, age, email) values(?, ?, ?)";
//        jdbcTemplate.update(sql, person.getName(), person.getAge(), person.getEmail());
//    }
//
//    public void update(int id, Person person) {
//        String sql = "update person set name = ?, age = ?, email = ? where id = ?";
//        jdbcTemplate.update(sql, person.getName(), person.getAge(), person.getEmail(), id);
//    }
//
//    public void delete(int id) {
//        String sql = "delete from person where id = ?";
//        jdbcTemplate.update(sql, id);
//    }


//    private static final String URL = "jdbc:mysql://localhost:3306/first_db";
//    private static final String USER = "root";
//    private static final String PASS = "ingadfg842655@";
//    private static Connection connection;
//
//    static {
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//        } catch (ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//        try {
//            connection = DriverManager.getConnection(URL, USER, PASS);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    public List<Person> index() {
//        List<Person> people = new ArrayList<>();
//        String query = "select * from person";
//        try (Statement statement = connection.createStatement();
//             ResultSet resultSet = statement.executeQuery(query)) {
//            while (resultSet.next()) {
//                Person person = new Person();
//                person.setId(resultSet.getInt("id"));
//                person.setName(resultSet.getString("name"));
//                person.setAge(resultSet.getInt("age"));
//                person.setEmail(resultSet.getString("email"));
//                people.add(person);
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        return people;
//    }
//
//    public Person show(int id) {
//        Person person = null;
//        String query = "select * from person where id = ?";
//        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
//            preparedStatement.setInt(1, id);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            if (resultSet.next()) {
//                person = new Person();
//                person.setId(resultSet.getInt("id"));
//                person.setName(resultSet.getString("name"));
//                person.setAge(resultSet.getInt("age"));
//                person.setEmail(resultSet.getString("email"));
//            }
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }
//        return person;
//    }
//
//    public void save(Person person) {
//        String query = "insert into person (name, age, email) values (?, ?, ?)";
//        try (PreparedStatement statement = connection.prepareStatement(query)) {
//            statement.setString(1, person.getName());
//            statement.setInt(2, person.getAge());
//            statement.setString(3, person.getEmail());
//            statement.executeUpdate();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    public void update(int id, Person updatePperson) {
//        String query = "update person set name = ?, age = ?, email = ? where id = ?";
//        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
//            preparedStatement.setString(1, updatePperson.getName());
//            preparedStatement.setInt(2, updatePperson.getAge());
//            preparedStatement.setString(3, updatePperson.getEmail());
//            preparedStatement.setInt(4, id);
//            preparedStatement.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void delete(int id) {
//        String query = "delete from person where id = ?";
//        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
//            preparedStatement.setInt(1, id);
//            preparedStatement.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
}
