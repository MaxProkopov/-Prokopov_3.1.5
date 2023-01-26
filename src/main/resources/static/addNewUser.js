const addForm = document.getElementById("add-form");
addForm.addEventListener('submit', (e) => {
    e.preventDefault();
    let nameValue = document.getElementById("name").value;
    let surNameValue = document.getElementById("surName").value;
    let ageValue = document.getElementById("age").value;
    let salaryValue = document.getElementById("salary").value;
    let passwordValue = document.getElementById("password").value;
    let roles = getRoles(Array.from(document.getElementById("addRoles").selectedOptions).map(role => role.value));
    let newUser = {
        name: nameValue,
        surName: surNameValue,
        age: ageValue,
        salary: salaryValue,
        password: passwordValue,
        roles: roles
    }
    fetch(url, {
        method: "POST",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json;charset=UTF-8'
        },
        body: JSON.stringify(newUser)
    })
        .then(data => {
            const dataAr = [];
            dataAr.push(data);
            getAllUsers(data);
        }).then(() => {
        addForm.reset()
        document.getElementById("nav-home-tab").click();})
})
function getRoles(role) {
    let roles = [];
    if (role.indexOf("ADMIN") >= 0) {
        roles.push({"name": 'ADMIN'});
    }
    if (role.indexOf("USER") >= 0) {
        roles.push({"name": 'USER'});
    }
    return roles;
}