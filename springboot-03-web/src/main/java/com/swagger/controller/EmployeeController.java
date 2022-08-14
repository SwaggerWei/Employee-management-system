package com.swagger.controller;

import com.swagger.dao.DepartmentDAO;
import com.swagger.dao.EmployeeDAO;
import com.swagger.pojo.Department;
import com.swagger.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Controller
public class EmployeeController {

    //
    @Autowired
    EmployeeDAO employeeDAO;

    @Autowired
    DepartmentDAO departmentDAO;


    @RequestMapping("/emps")
    public String list(Model model) {
        Collection<Employee> employees = employeeDAO.getAll();
        model.addAttribute("emps", employees);
        return "emp/list";
    }

    @GetMapping("/emp")
    public String toAddPage(Model model) {
        // 查出所有的部门信息
        Collection<Department> departments = departmentDAO.getDepartments();
        model.addAttribute("departments", departments);
        return "emp/add";
    }

    @PostMapping("/emp")
    public String addEmp(Employee employee) {

        // 添加操作 forward 服务器内转发重定向
        // 保存员工信息
        // System.out.println("add ->" + employee);
        employeeDAO.add(employee);

        // 重定向到所有员工页面
        return "redirect:/emps";
    }

    // 去到员工的修改页面
    @GetMapping("/emp/{id}")
    public String toUpdateEmp(@PathVariable("id") Integer id, Model model) {
        // 查询员工原来的信息

        Employee employee = employeeDAO.getEmployeeById(id);
        model.addAttribute("emp", employee);
        Collection<Department> departments = departmentDAO.getDepartments();
        model.addAttribute("departments", departments);

        return "emp/update";
    }

    @PostMapping("/updateEmp")
    public String updateEmp(Employee employee) {

        employeeDAO.add(employee);
        return "redirect:/emps";
    }

    // 删除员工
    @GetMapping("/delemp/{id}")
    public String deleteEmp(@PathVariable("id") Integer id) {
        employeeDAO.delete(id);
        return "redirect:/emps";
    }

}
