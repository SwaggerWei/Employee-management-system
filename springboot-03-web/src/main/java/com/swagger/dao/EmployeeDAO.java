package com.swagger.dao;

import com.swagger.pojo.Department;
import com.swagger.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

// 员工Dao
@Repository
public class EmployeeDAO {
    private static Map<Integer, Employee> employees = null;

    // 员工有所属的部门
    @Autowired
    private DepartmentDAO departmentDAO;
    static {
        // 穿件一个部门表
        employees = new HashMap<Integer, Employee>();
        employees.put(1001, new Employee(1001, "AA", "123456@qq.com", 0, new Department(101, "教学部")));
        employees.put(1002, new Employee(1002, "BB", "123456@qq.com", 1, new Department(102, "市场部")));
        employees.put(1003, new Employee(1003, "CC", "123456@qq.com", 0, new Department(103, "教研部")));
        employees.put(1004, new Employee(1004, "DD", "123456@qq.com", 1, new Department(104, "运营部")));
        employees.put(1005, new Employee(1005, "EE", "123456@qq.com", 0, new Department(105, "后勤部")));
    }

    // 主键自增
    private static Integer initId = 1006;

    // 增加一个员工
    public void add(Employee employee){
        if (employee.getId() == null){
            employee.setId(initId ++);
        }
        employee.setDepartment(departmentDAO.getDepartmentById(employee.getDepartment().getId()));
        employees.put(employee.getId(), employee);
    }

    // 查询全部员工
    public Collection<Employee> getAll(){
        return employees.values();
    }

    // 通过id 查询员工
    public Employee getEmployeeById(Integer id){
        return employees.get(id);
    }

    // 删除员工通过id
    public void delete(Integer id){
        employees.remove(id);
    }
}
