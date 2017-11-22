/**
 * Created by Elat on 15.04.2017.
 */
function validateFormDep() {
    var name = document.forms["department"]["name"].value.trim();
    var number = document.forms["department"]["number"].value.trim();
    var stringValid = /^[A-Z][a-zA-Z]+$/;
    var numberValid = /^[0-9]+$/;
    if (((name.length == 0 || name.length == null || name.length > 20) || (number == 0 || !numberValid.test(number) || number.length > 6)
        || (!stringValid.test(name))) == true) {
        if ((name.length == 0 || name.length == null) && number == 0) {
            document.forms["department"]["name"].style.border = "1px solid red";
            document.forms["department"]["number"].style.border = "1px solid red";
            document.getElementById("Error").innerHTML = 'All fields are required!';
            document.getElementById("Error").style.color = "red";
        }
        else {
            document.getElementById("Error").innerHTML = '';
        }
        if (name.length == 0 || name.length == null || (!stringValid.test(name)) || name.length > 20) {
            if (name.length == 0 || name.length == null) {
                document.getElementById("NameError").innerHTML = 'Name can not be null!';
            }
            if (name.length > 20) {
                document.getElementById("NameError").innerHTML = 'Name can not be as long!';
            }
            if (!stringValid.test(name) && name.length > 0) {
                document.getElementById("NameError").innerHTML = 'Please enter only characters. Name must start with big letter';
            }
            document.getElementById("NameError").style.color = "red";
            document.forms["department"]["name"].style.border = "1px solid red";
        }
        else {
            document.getElementById("NameError").innerHTML = '';
            document.forms["department"]["name"].style.border = '';
        }
        if (number == 0 || !numberValid.test(number) || number.length > 6) {
            if (number == 0) {
                document.getElementById("NumberError").innerHTML = 'Number can not be null!';
            }
            if (number.length > 6) {
                document.getElementById("NumberError").innerHTML = 'Number can not be as long!';
            }
            if (!numberValid.test(number) && number.length > 0) {
                document.getElementById("NumberError").innerHTML = 'Please enter only number!';
            }
            document.getElementById("NumberError").style.color = "red";
            document.forms["department"]["number"].style.border = "1px solid red";
        } else {
            document.getElementById("NumberError").innerHTML = '';
            document.forms["department"]["number"].style.border = '';
        }
        return false;
    }

}
function validateFormEmployee() {
    var name = document.forms["employee"]["name"].value.trim();
    var surname = document.forms["employee"]["surname"].value.trim();
    var date = document.forms["employee"]["date"].value.trim();
    var email = document.forms["employee"]["email"].value.trim();
    var emailValid = /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    var stringValid = /^[A-Z][a-zA-Z]+$/;
    var dateValid = /^\d{4}-\d{1,2}-\d{1,2}$/;
    if (((name.length == 0 || name.length == null) || (email.length == 0 || email.length == null || !emailValid.test(email)) ||
        (date.length == 0 || date.length == null || (!dateValid.test(date)))
        || (surname.length == 0 || surname == null) || (!stringValid.test(name || surname))) == true) {
        if (name.length == 0 && email.length == 0 && date.length == 0 && surname.length == 0) {
            document.forms["employee"]["name"].style.border = "1px solid red";
            document.forms["employee"]["surname"].style.border = "1px solid red";
            document.forms["employee"]["email"].style.border = "1px solid red";
            document.forms["employee"]["date"].style.border = "1px solid red";
            document.getElementById("Error").innerHTML = 'All fields are required!';
            document.getElementById("Error").style.color = "red";
        }
        else {
            document.getElementById("Error").innerHTML = '';
        }
        if (name.length == 0 || name.length == null || (!stringValid.test(name))) {
            if (name.length == 0 || name.length == null) {
                document.getElementById("NameError").innerHTML = 'Name can not be null!';
            }
            if (!stringValid.test(name) && name.length > 0) {
                document.getElementById("NameError").innerHTML = 'Please Enter Only Characters';
            }
            document.getElementById("NameError").style.color = "red";
            document.forms["employee"]["name"].style.border = "1px solid red";
        }
        else {
            document.getElementById("NameError").innerHTML = '';
            document.forms["employee"]["name"].style.border = '';
        }
        if (surname.length == 0 || surname.length == null || (!stringValid.test(surname))) {
            if (surname.length == 0 || surname.length == null) {
                document.getElementById("SurnameError").innerHTML = 'Surname can not be null!';
            }
            if (!stringValid.test(surname) && surname.length > 0) {
                document.getElementById("SurnameError").innerHTML = 'Please Enter Only Characters';
            }
            document.getElementById("SurnameError").style.color = "red";
            document.forms["employee"]["surname"].style.border = "1px solid red";
        } else {
            document.getElementById("SurnameError").innerHTML = '';
            document.forms["employee"]["surname"].style.border = '';
        }
        if (email.length == 0 || email.length == null || !emailValid.test(email)) {
            if (email.length == 0 || email.length == null) {
                document.getElementById("EmailError").innerHTML = 'Email can not be null!';
            }
            if (!emailValid.test(email) && email.length > 0) {
                document.getElementById("EmailError").innerHTML = 'Email is not correct!';
            }
            document.getElementById("EmailError").style.color = "red";
            document.forms["employee"]["email"].style.border = "1px solid red";
        } else {
            document.getElementById("EmailError").innerHTML = '';
            document.forms["employee"]["email"].style.border = '';
        }
        if (date.length == 0 || date.length == null || !dateValid.test(date)) {
            if (date.length == 0 || date.length == null) {
                document.getElementById("DateError").innerHTML = 'Date can not be null!';
            }
            if (!dateValid.test(date) && date.length > 0) {
                document.getElementById("DateError").innerHTML = 'Date is not correct!';
            }
            document.forms["employee"]["date"].style.border = "1px solid red";
            document.getElementById("DateError").style.color = "red";
        } else {
            document.getElementById("DateError").innerHTML = '';
            document.forms["employee"]["date"].style.border = '';
        }
        return false;
    }
}
function validateFormEmployeeEdit() {
    var name = document.forms["employee"]["name"].value.trim();
    var surname = document.forms["employee"]["surname"].value.trim();
    var date = document.forms["employee"]["date"].value.trim();
    var email = document.forms["employee"]["email"].value.trim();
    var depId = document.forms["employee"]["depId"].value.trim();
    var emailValid = /^\w+([.-]?\w+)*@\w+([.-]?\w+)*(\.\w{2,3})+$/;
    var stringValid = /^[A-Z][a-zA-Z]+$/;
    var dateValid = /^\d{4}-\d{1,2}-\d{1,2}$/;
    var numberValid = /^[0-9]+$/;
    if (((name.length == 0 || name.length == null) || (email.length == 0 || email.length == null || !emailValid.test(email)) ||
        (date.length == 0 || date.length == null || (!dateValid.test(date)))
        || (surname.length == 0 || surname == null) || (!stringValid.test(name || surname)) || (depId == 0 || !numberValid.test(depId) || depId.length > 6)) == true) {
        if (name.length == 0 && email.length == 0 && date.length == 0 && surname.length == 0 && depId == 0) {
            document.forms["employee"]["name"].style.border = "1px solid red";
            document.forms["employee"]["surname"].style.border = "1px solid red";
            document.forms["employee"]["email"].style.border = "1px solid red";
            document.forms["employee"]["date"].style.border = "1px solid red";
            document.forms["employee"]["depId"].style.border = "1px solid red";
            document.getElementById("Error").innerHTML = 'All fields are required!';
            document.getElementById("Error").style.color = "red";
        }
        else {
            document.getElementById("Error").innerHTML = '';
        }
        if (name.length == 0 || name.length == null || (!stringValid.test(name))) {
            if (name.length == 0 || name.length == null) {
                document.getElementById("NameError").innerHTML = 'Name can not be null!';
            }
            if (!stringValid.test(name) && name.length > 0) {
                document.getElementById("NameError").innerHTML = 'Please Enter Only Characters';
            }
            document.getElementById("NameError").style.color = "red";
            document.forms["employee"]["name"].style.border = "1px solid red";
        }
        else {
            document.getElementById("NameError").innerHTML = '';
            document.forms["employee"]["name"].style.border = '';
        }
        if (surname.length == 0 || surname.length == null || (!stringValid.test(surname))) {
            if (surname.length == 0 || surname.length == null) {
                document.getElementById("SurnameError").innerHTML = 'Surname can not be null!';
            }
            if (!stringValid.test(surname) && surname.length > 0) {
                document.getElementById("SurnameError").innerHTML = 'Please Enter Only Characters';
            }
            document.getElementById("SurnameError").style.color = "red";
            document.forms["employee"]["surname"].style.border = "1px solid red";
        } else {
            document.getElementById("SurnameError").innerHTML = '';
            document.forms["employee"]["surname"].style.border = '';
        }
        if (email.length == 0 || email.length == null || !emailValid.test(email)) {
            if (email.length == 0 || email.length == null) {
                document.getElementById("EmailError").innerHTML = 'Email can not be null!';
            }
            if (!emailValid.test(email) && email.length > 0) {
                document.getElementById("EmailError").innerHTML = 'Email is not correct!';
            }
            document.getElementById("EmailError").style.color = "red";
            document.forms["employee"]["email"].style.border = "1px solid red";
        } else {
            document.getElementById("EmailError").innerHTML = '';
            document.forms["employee"]["email"].style.border = '';
        }
        if (date.length == 0 || date.length == null || !dateValid.test(date)) {
            if (date.length == 0 || date.length == null) {
                document.getElementById("DateError").innerHTML = 'Date can not be null!';
            }
            if (!dateValid.test(date) && date.length > 0) {
                document.getElementById("DateError").innerHTML = 'Date is not correct!';
            }
            document.forms["employee"]["date"].style.border = "1px solid red";
            document.getElementById("DateError").style.color = "red";
        } else {
            document.getElementById("DateError").innerHTML = '';
            document.forms["employee"]["date"].style.border = '';
        }
        if (depId == 0 || !numberValid.test(depId) || depId.length > 6) {
            if (depId == 0) {
                document.getElementById("DepIDError").innerHTML = 'ID can not be null!';
            }
            if (depId.length > 6) {
                document.getElementById("DepIDError").innerHTML = 'ID can not be as long!';
            }
            if (!numberValid.test(depId) && depId.length > 0) {
                document.getElementById("DepIDError").innerHTML = 'Please enter only digits!';
            }
            document.getElementById("DepIDError").style.color = "red";
            document.forms["employee"]["depId"].style.border = "1px solid red";
        } else {
            document.getElementById("DepIDError").innerHTML = '';
            document.forms["employee"]["depId"].style.border = '';
        }

        return false;
    }
}
