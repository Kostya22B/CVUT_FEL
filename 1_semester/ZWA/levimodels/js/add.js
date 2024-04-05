// add check js
const mname = document.getElementById('mname');
const mlink = document.getElementById('mlinka');
const mtype = document.getElementById('mtype');
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
form.addEventListener('submit', (e)=> {
    if(!validateInputs()){     //if validate
        e.preventDefault();    //stop form from submitting
    }
});
//main function which check if data valid or not
function validateInputs() {
    const mnameValue = mname.value.trim();
    const mlinkValue = mlink.value.trim();
    const mtypeValue = mtype.value.trim();
    var linkcheck = /^https?:\/\/[\w\/?.&-=]+$/;
    var typebet = /^[a-z]*$/;
/*-----------------------------------------------------------------------------*/ 
    // Check name
    if(mnameValue === '') {
        setError(mname, 'Name is required!');
        return false;
    } else if (mnameValue.length < 4 ) { 
        setError(mname, 'Name must be at least 4 characters!');
        return false;
    } else {
        setSuccess(mname);
    }
    // Check link
    if(mlinkValue === '') {
        setError(mlink, 'Link is required!');
        return false;
    } else if (!mlink.value.match(linkcheck)) {
        setError(mlink, 'Invalid link');
        return false;
    } else {
        setSuccess(mlink);
    }
    // Check type
    if(mtypeValue === '') {
        setError(mtype, 'Type is required!');
        return false;
    } else if (mtypeValue.length > 4 && mtypeValue.length < 3) { 
        setError(mtype, 'Type must be only 3 or 4 symbols');
        return false;
    } else if (!mtype.value.match(typebet)) {
        setError(mtype, 'Type must not contain special symbols');
        return false;
    } else {
        setSuccess(mtype);
    }
    return true; 
};