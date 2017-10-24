package spring.rest.example.resource;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import spring.rest.example.domain.Department;
import spring.rest.example.dto.DepartmentDto;
import spring.rest.example.dto.DepartmentInsertDto;
import spring.rest.example.dto.DepartmentUpdateDto;
import spring.rest.example.service.DepartmentService;
import spring.rest.example.validator.Validator;

import java.lang.reflect.Type;
import java.util.Collection;

@RestController
@RequestMapping("/resources")
public class DepartmentResource {

    private final DepartmentService departmentService;
    private final ModelMapper modelMapper;
    private final Validator<DepartmentInsertDto> departmentInsertDtoValidator;
    private final Validator<DepartmentUpdateDto> departmentUpdateDtoValidator;

    @Autowired
    public DepartmentResource(DepartmentService departmentService,
                              ModelMapper modelMapper,
                              Validator<DepartmentInsertDto> departmentInsertDtoValidator,
                              Validator<DepartmentUpdateDto> departmentUpdateDtoValidator) {
        this.departmentService = departmentService;
        this.modelMapper = modelMapper;
        this.departmentInsertDtoValidator = departmentInsertDtoValidator;
        this.departmentUpdateDtoValidator = departmentUpdateDtoValidator;
    }

    @RequestMapping(value = "/departments", method = RequestMethod.POST)
    public void save(@RequestBody DepartmentInsertDto departmentInsertDto) {
        departmentInsertDtoValidator.validate(departmentInsertDto);
        departmentService.save(departmentInsertDto);
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

    @RequestMapping(value = "/departments/{departmentId}", method = RequestMethod.PUT)
    public void update(@PathVariable("departmentId") Long departmentId,
                       @RequestBody DepartmentUpdateDto departmentUpdateDto) {
        departmentUpdateDtoValidator.validate(departmentUpdateDto);
        departmentService.update(departmentId, departmentUpdateDto);
    }

    @RequestMapping(value = "/departments/{departmentId}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("departmentId") Long id) {
        departmentService.delete(id);
    }

}
