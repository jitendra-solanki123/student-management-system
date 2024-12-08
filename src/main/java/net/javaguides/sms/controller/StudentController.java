package net.javaguides.sms.controller;

import jakarta.validation.Valid;
import net.javaguides.sms.dto.StudentDto;
import net.javaguides.sms.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class StudentController {

    private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // handle method to handle the List student request

    @GetMapping("/students")
    public String ListStudents(Model model)
    {
        List<StudentDto> dtoList = studentService.getAllStudents();
        model.addAttribute("students",dtoList);
        return "students";
    }

   //handler method to handle new Student request
    @GetMapping("/students/new")
     public String newStudent(Model model)
     {
         //student model object to store student form data
         StudentDto studentDto = new StudentDto();
         model.addAttribute("student",studentDto);
         return "create-student";
     }

     //handler method to handle the submit request
    @PostMapping("/students")
    public String saveStudent(@Valid  @ModelAttribute("student") StudentDto studentDto,

                              BindingResult result,
                              Model model  ){

        if(result.hasErrors()){

            model.addAttribute("student",studentDto);
            return "create-student";
        }

        studentService.createStudent(studentDto);
        return "redirect:/students";
    }



 //handler method to handle the edit request

   @GetMapping("/students/{studentId}/edit")
  public String editStudents(@PathVariable("studentId") Long studentId , Model model)
   {
       StudentDto studentDto = studentService.getStudentById(studentId);
       model.addAttribute("student",studentDto);
       return "edit_student";
   }


   //handler method to handle the edit student form submit request

    @PostMapping("/students/{studentId}")
    public String updateStudent(@PathVariable("studentId") Long studentId,
                                @Valid  @ModelAttribute("student") StudentDto studentDto,
                                BindingResult result,
                                Model model
                                )
    {
           if(result.hasErrors())
           {
               model.addAttribute("student",studentDto);
               return "edit_student";
           }
           studentDto.setId(studentId);

        studentService.updateStudent(studentDto);
        return "redirect:/students";
    }

    //handler method to handle delete student request

    @GetMapping("/students/{studentId}/delete")
    public String deleteStudent(@PathVariable("studentId") Long studentId)
    {
        studentService.deleteStudent(studentId);
        return "redirect:/students";
    }

    //handler method to handle view student request
    @GetMapping("/students/{studentId}/view")
    public String viewStudent(@PathVariable("studentId") Long studentId, Model model)
    {
       StudentDto studentDto =  studentService.getStudentById(studentId);
       model.addAttribute("student",studentDto);
       return "view_student";
    }
}