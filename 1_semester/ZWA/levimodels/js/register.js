// Register check js
const form = document.getElementById('form');
const login = document.getElementById('login');
const email = document.getElementById('email');
const date = document.getElementById('date');
const password = document.getElementById('password');
const cpassword = document.getElementById('cpassword');
// Delete special chars
login.onkeyup = function() {
    login.value = login.value.replace(/[-)&*+<=>!@,:;('/{}|]/g, '');
}
email.onkeyup = function() {
    email.value = email.value.replace(/[&*<>']/g, '');
}
/*constants will take info from spans*/ 
const setError = (element, message) => {
    const inputControl = element.parentElement;
    const errorDisplay = inputControl.querySelector('.error');

    errorDisplay.innerText = message;
    inputControl.classList.add('error');
    inputControl.classList.remove('success')
}

const setSuccess = element => {
    const inputControl = element.parentElement;
    const errorDisplay = inputControl.querySelector('.error');

    errorDisplay.innerText = '';
    inputControl.classList.add('success');
    inputControl.classList.remove('error');
};
const isValidEmail = email => {
    const re = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return re.test(String(email).toLowerCase());
    /** Converts all the alphabetic characters in a string to lowercase. */
}
form.addEventListener('submit', (e)=> {
    if(!validateInputs()){     //if validate
        e.preventDefault();    //stop form from submitting
    }
});
//main function which check if data valid or not
function validateInputs() {
    const loginValue = login.value.trim();
    const emailValue = email.value.trim();
    const dateValue = date.value.trim();
    const passwordValue = password.value.trim();
    const cpasswordValue = cpassword.value.trim();
    var alphabet = /^[a-z]*$/;
/*-----------------------------------------------------------------------------*/ 
    // Check login
    if(loginValue === '') {
        setError(login, 'Login is required!');
        return false;
    } else if (loginValue.length < 4 ) { 
        setError(login, 'Login must be at least 4 characters!');
        return false;    
    } else if (loginValue.length >= 16 ) {
        setError(login, 'Login must be no more than 16 characters!');
        return false;
    } else if (!login.value.match(alphabet)) {
        setError(login, 'Login must contain only Lowercase Latin alphabets!');
        return false;
    } else {
        setSuccess(login);
    }
    // Check email
    if(emailValue === '') {
        setError(email, 'Email is required!');
        return false;
    } else if (emailValue.length > 40 ) {
        setError(email, 'Email must be no more than 40 characters!');
        return false; 
    } else if (!isValidEmail(emailValue)) {
        setError(email, 'Provide a valid email address!');
        return false;
    } else {
        setSuccess(email);
    }
    // Check date
    if(dateValue === '') {
        setError(date, 'Date is required!');
        return false;
    } else {
        setSuccess(date);
    }
    // Check password
    if(passwordValue === '') {
        setError(password, 'Password is required!');
        return false;
    } else if (passwordValue.length < 8 ) {
        setError(password, 'Password must be at least 6 characters!');
        return false;
    } else if (passwordValue.length > 32 ) {
        setError(password, 'Password must be no more than 60 characters!');
        return false;
    }
     else {
        setSuccess(password);
    }
    // Check second password
    if(cpasswordValue === '') {
        setError(cpassword, 'Please confirm your password!');
        return false;
    } else if (cpasswordValue !== passwordValue) {
        setError(cpassword, 'Passwords is not match!');
        return false;
    } else {
        setSuccess(cpassword);
    }
    return true; 
    //last check. code is running step by step. so if in code before that will be an error, in this case code wont get to this part
    };