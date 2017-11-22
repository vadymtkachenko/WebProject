package com.org;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Elat on 22.04.2017.
 */
public class Validation {

    private String message;
    private String empName;
    private String empSurname;
    private String empEmail;
    private String empDate;
    private String empId;
    private String depName;
    private String depNumber;
    private String nameValid = "[A-Z][a-zA-Z]{1,19} *";
    private String dateValid = "^\\d{4}-\\d{2}-\\d{2}$";
    private String emailValid = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
    private String numberValid = "[0-9]{1,6} *";

    public boolean validateEmployee(HttpServletRequest request, String name, String surname, String email, String date) {
        if (name.length() == 0 || !name.matches(nameValid) || surname.length() == 0 || !surname.matches(nameValid) ||
                email.length() == 0 || !email.matches(emailValid) ||
                date.length() == 0 || !date.matches(dateValid)) {
            if (name.length() == 0 && surname.length() == 0 && email.length() == 0 && date.length() == 0) {
                message = "All is null!";
                request.setAttribute("message", message);
            }
//                CHECKING FOR VALID
            if (!name.matches(nameValid)) {
                empName = "Name is incorrect. Name must start with big letter and contain maximum 20 characters!";
                request.setAttribute("empName", empName);
            }
            if (!surname.matches(nameValid)) {
                empSurname = "Surname is incorrect! Surname must start with big letter and contain maximum 20 characters!";
                request.setAttribute("empSurname", empSurname);
            }
            if (!email.matches(emailValid)) {
                empEmail = "Email is incorrect!";
                request.setAttribute("empEmail", empEmail);
            }
            if (!date.matches(dateValid)) {
                empDate = "Date is incorrect!";
                request.setAttribute("empDate", empDate);
            }
//               CHECKING FOR NULLS
            if (name.length() == 0) {
                empName = "Name can not be null!";
                request.setAttribute("empName", empName);
            }
            if (surname.length() == 0) {
                empSurname = "Surname can not be null!";
                request.setAttribute("empSurname", empSurname);
            }
            if (email.length() == 0) {
                empEmail = "Email can not be null!";
                request.setAttribute("empEmail", empEmail);
            }
            if (date.length() == 0) {
                empDate = "Date can not be null!";
                request.setAttribute("empDate", empDate);
            }
            return true;
        } else return false;
    }

    public boolean validateEmployeeEdit(HttpServletRequest request, String name, String surname, String email, String date, String depid) {

        if (name.length() == 0 || !name.matches(nameValid) || surname.length() == 0 || !surname.matches(nameValid) ||
                email.length() == 0 || !email.matches(emailValid) ||
                date.length() == 0 || !date.matches(dateValid) || depid.length() == 0 || !depid.matches(numberValid)) {
            if (name.length() == 0 && surname.length() == 0 && email.length() == 0 && date.length() == 0 && depid.length() == 0) {
                message = "All is null!";
                request.setAttribute("message", message);
            }
//                CHECKING FOR VALID
            if (!name.matches(nameValid)) {
                empName = "Name is incorrect. Name must start with big letter and consist maximum 20 characters!";
                request.setAttribute("empName", empName);
            }
            if (!surname.matches(nameValid)) {
                empSurname = "Surname is incorrect! Surname must start with big letter and consist maximum 20 characters!";
                request.setAttribute("empSurname", empSurname);
            }
            if (!email.matches(emailValid)) {
                empEmail = "Email is incorrect!";
                request.setAttribute("empEmail", empEmail);
            }
            if (!date.matches(dateValid)) {
                empDate = "Date is incorrect!";
                request.setAttribute("empDate", empDate);
            }
            if (!depid.matches(numberValid)) {
                empId = "Id of department is incorrect!";
                request.setAttribute("empId", empId);
            }
//               CHECKING FOR NULLS
            if (name.length() == 0) {
                empName = "Name can not be null!";
                request.setAttribute("empName", empName);
            }
            if (surname.length() == 0) {
                empSurname = "Surname can not be null!";
                request.setAttribute("empSurname", empSurname);
            }
            if (email.length() == 0) {
                empEmail = "Email can not be null!";
                request.setAttribute("empEmail", empEmail);
            }
            if (date.length() == 0) {
                empDate = "Date can not be null!";
                request.setAttribute("empDate", empDate);
            }
            if (depid.length() == 0) {
                empId = "Id of department can not be null!";
                request.setAttribute("empId", empId);
            }
            return true;
        } else return false;
    }

    public boolean validateDepartment(HttpServletRequest request, String name, String number) {
        if (name.length() == 0 || !name.matches(nameValid)
                || number.length() == 0 ||
                number.equals("0") ||
                !number.matches(numberValid)) {
            if (name.length() == 0 && (number.length() == 0 || number.equals("0"))) {
//                CHECKING FOR NULLS
                message = "All is null!";
                request.setAttribute("message", message);
            }
            if (number.length() == 0 || number.equals("0")) {
                depNumber = "Number can not be null!";
                request.setAttribute("depNumber", depNumber);
            }
            if (name.length() == 0) {
                depName = "Name can not be null";
                request.setAttribute("depName", depName);
            }
//            CHECKING FOR VALID
            if (!name.matches(nameValid)) {
                depName = "Name is incorrect. Name must start with big letter and contain maximum 20 characters!";
                request.setAttribute("depName", depName);
            }
            if (!number.matches(numberValid)) {
                depNumber = "Number is incorrect! Number must contain maximum 6 digits!";
                request.setAttribute("depNumber", depNumber);
            }

            return true;
        } else return false;
    }
}

