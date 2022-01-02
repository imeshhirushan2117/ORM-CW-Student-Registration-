package dao;

import entity.Program;

import java.util.List;

public interface QueryDAO extends SuperDAOUltra {
    List<Program>getStudentProgram(String value);
}
