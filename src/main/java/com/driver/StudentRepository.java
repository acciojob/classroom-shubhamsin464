package com.driver;

import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository

public class StudentRepository {
    HashMap<String,Student> studentDb = new HashMap<>();
    HashMap<String,Teacher> teacherDb = new HashMap<>();
    HashMap<String,List<String>> pairsDb = new HashMap<>();

    public void addStudent(Student student) {
        studentDb.put(student.getName(), student);
    }
    //2
    public  void addTeacher(Teacher teacher) {
        teacherDb.put(teacher.getName(), teacher);
    }
    //3
    public  void addStudentTeacherPair(String studentName, String teacherName) {
        if (studentDb.containsKey(studentName) && teacherDb.containsKey(teacherName)) {
            if (pairsDb.containsKey(teacherName)) {
                List<String> l=pairsDb.get(teacherName);
                l.add(studentName);
                pairsDb.put(teacherName,l);
            } else {
                List<String> ls = new ArrayList<>();
                ls.add(studentName);
                pairsDb.put(teacherName, ls);
            }
        }
    }

    //4
    public Student getStudentByName(String name) {
        return studentDb.get(name);
    }

    //5
    public Teacher getTeacherByName(String name) {
        return teacherDb.get(name);
    }

    //6
    public List<String> getStudentsByTeacherName(String teacher) {
        return  pairsDb.get(teacher);
    }

    //7
    public List<String> getAllStudents() {
        List<String>ls= new ArrayList<>();
        for(String name:studentDb.keySet())
        {
            ls.add(name);
        }
        return new ArrayList<>(ls);
    }

    //8
    public void deleteTeacherByName(String teacher) {
        if(teacherDb.containsKey(teacher)){
            if(pairsDb.containsKey(teacher)){
                List<String>Str=pairsDb.get(teacher);
                for(String s:Str)
                {
                    if(studentDb.containsKey(s))
                    {
                        studentDb.remove(s);
                    }
                }
                teacherDb.remove(teacher);
                pairsDb.remove(teacher);
            }
        }
    }

    //9
    public void deleteAllTeachers() {
        for(String teacher:teacherDb.keySet()){
            if(pairsDb.containsKey(teacher))
            {
                List<String> Stu=pairsDb.get(teacher);
                for(String s:Stu)
                {
                    if(studentDb.containsKey(s))
                    {
                        studentDb.remove(s);
                    }
                }
            }
            pairsDb.clear();
            teacherDb.clear();;
        }
        pairsDb.clear();
        teacherDb.clear();
    }
}

