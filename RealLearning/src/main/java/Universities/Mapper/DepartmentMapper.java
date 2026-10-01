package Universities.Mapper;

import Universities.DTO.DepartmentRequest;
import Universities.DTO.DepartmentResponse;
import Universities.Entity.Department;

public final class DepartmentMapper {
    public static DepartmentResponse toDepartmentResponse(Department department){
        DepartmentResponse departmentResponse = new DepartmentResponse();
        departmentResponse.setName(department.getName());
        departmentResponse.setId(department.getId());
        return departmentResponse;
    }

    public static Department toDepartment(DepartmentRequest departmentRequest){
        Department department = new Department();
        department.setName(departmentRequest.getName());
        return department;
    }
}
