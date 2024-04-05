function kontrola(){
    if(document.form.login.value=="") window.alert("You need to create a login");
    else if(document.form.email.value=="") window.alert("You have to input your email");
    else if(document.form.date.value=="") window.alert("You need to input birth date");
    else if(document.form.password.value=="") window.alert("You need to write down the password");
    else if(document.form.password_confirm.value=="") window.alert("Confirm the password");
    else if(document.form.name.value=="" && document.form.login.value=="" && document.form.email.value=="" && 
    document.form.date.value=="" && document.form.password.value=="" && 
    document.form.password_confirm.value=="") window.alert("nic jste nevyplnil!");
}