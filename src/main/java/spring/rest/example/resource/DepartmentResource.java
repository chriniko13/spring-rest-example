package spring.rest.example.resource;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import spring.rest.example.domain.Department;
import spring.rest.example.dto.DepartmentDto;
import spring.rest.example.service.DepartmentService;

import java.lang.reflect.Type;
import java.util.Collection;

@RestController
@RequestMapping("/resources")
public class DepartmentResource {

    private final DepartmentService departmentService;
    private final ModelMapper modelMapper;

    @Autowired
    public DepartmentResource(DepartmentService departmentService, ModelMapper modelMapper) {
        this.departmentService = departmentService;
        this.modelMapper = modelMapper;
    }

    @RequestMapping(value = "/departments", method = RequestMethod.POST)
    public void save(@RequestBody DepartmentDto departmentDto) {

        Department department = modelMapper.map(departmentDto, Department.class);

        departmentService.save(department);
    }

    @RequestMapping(value = "/departments", method = RequestMethod.GET)
    public Collection<DepartmentDto> list() {

        Collection<Department> departments = departmentService.list();
        Type listType = new TypeToken<Collection<DepartmentDto>>() {
        }.getType();

        return modelMapper.map(departments, listType);
    }

    @RequestMapping(value = "/departments/{departmentId}", method = RequestMethod.GET)
    public DepartmentDto find(@PathVariable("departmentId") Long id) {

        Department department = departmentService.find(id);
        return modelMapper.map(department, DepartmentDto.class);
    }

    @RequestMapping(value = "/departments", method = RequestMethod.PUT)
    public void update(@RequestBody DepartmentDto departmentDto) {
        Department department = modelMapper.map(departmentDto, Department.class);

        departmentService.update(department);
    }


    @RequestMapping(value = "/departments/{departmentId}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("departmentId") Long id) {
        departmentService.delete(id);
    }

}
