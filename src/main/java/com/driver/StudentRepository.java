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

    public void addStudentToDb(Student student){
    studentDb.put(student.getName(),student);
    }
    public void addTeacherToDb(Teacher teacher){
        teacherDb.put(teacher.getName(),teacher);
    }
    public void addStudentTeacherPair(String teacher,String student){
        if(studentDb.containsKey(student)&&teacherDb.containsKey(teacher)){
            if(pairsDb.containsKey(teacher)){
                List<String> ls = pairsDb.get(teacher);
                ls.add(student);
                pairsDb.put(teacher,ls);
            }else{
                List<String> ls = new ArrayList<>();
                ls.add(student);
                pairsDb.put(teacher,ls);
            }
        }
    }
    public Student getStudentByName(String name){
         return studentDb.get(name);
    }
    public Teacher getTeacherByName(String name){
        return teacherDb.get(name);
    }
    public List<String> getStudentsByTeacherName(String name){
        return pairsDb.get(name);
    }
    public List<String> getAllStudents(){
        List<String> temp= new ArrayList<>();
        temp.addAll(studentDb.keySet());
        return temp;
    }
    public void deleteTeacherByName(String teacher){
        if(teacherDb.containsKey(teacher)){
            if(pairsDb.containsKey(teacher)){
                List<String> sudent=pairsDb.get(teacher);
                for(String s:sudent)
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
    public void deleteAllTeachers() {
        for(String teacher:teacherDb.keySet()){
            if(pairsDb.containsKey(teacher))
            {
                List<String> StudentList=pairsDb.get(teacher);
                for(String ls:StudentList)
                {
                    if(studentDb.containsKey(ls))
                    {
                        studentDb.remove(ls);
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

