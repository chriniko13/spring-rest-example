package spring.rest.example.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import spring.rest.example.domain.Department;
import spring.rest.example.service.DepartmentService;

import java.util.Collection;

@RestController
@RequestMapping("/resources")
public class DepartmentResource {

    private final DepartmentService departmentService;

    @Autowired
    public DepartmentResource(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @RequestMapping(value = "/departments", method = RequestMethod.POST)
    public void save(@RequestBody Department department) {
        departmentService.save(department);
    }

    @RequestMapping(value = "/departments", method = RequestMethod.GET)
    public Collection<Department> list() {
        return departmentService.list();
    }

    @RequestMapping(value = "/departments/{departmentId}", method = RequestMethod.GET)
    public Department find(@PathVariable("departmentId") Long id) {
        return departmentService.find(id);
    }

    @RequestMapping(value = "/departments", method = RequestMethod.PUT)
    public void update(@RequestBody Department department) {
        departmentService.update(department);
    }


    @RequestMapping(value = "/departments/{departmentId}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("departmentId") Long id) {
        departmentService.delete(id);
    }

}
