function editModal(id) {
    fetch(url + id, {
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json;charset=UTF-8'
        }
    }).then(res => {
        res.json().then(user => {
            document.getElementById('idEdit').value = user.id;
            document.getElementById('nameEdit').value = user.name;
            document.getElementById('surNameEdit').value = user.surName;
            document.getElementById('ageEdit').value = user.age;
            document.getElementById('salaryEdit').value = user.salary;
            document.getElementById('passwordEdit').value = user.password;
        })
    });
}

const editUser = document.getElementById("editUser");
editUser.addEventListener('submit', (e) => {
    e.preventDefault();
    let idValue = document.getElementById("idEdit").value;
    let nameValue = document.getElementById("nameEdit").value;
    let surNameValue = document.getElementById("surNameEdit").value;
    let ageValue = document.getElementById("ageEdit").value;
    let salaryValue = document.getElementById("salaryEdit").value;
    let passwordValue = document.getElementById("passwordEdit").value;
    let roles = editRoles(Array.from(document.getElementById("rolesEdit").selectedOptions).map(role => role.value));
    let newUser = {
        id: idValue,
        name: nameValue,
        surName: surNameValue,
        age: ageValue,
        salary: salaryValue,
        password: passwordValue,
        roles: roles
    }
    fetch(url + document.getElementById('idEdit').value, {
        method: "PATCH",
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(newUser)
    }).then(() => {
        document.getElementById("closeEditForm").click();
        getAllUsers()
    })
})
function editRoles(rols) {
    let roles = [];
    if (rols.indexOf("ADMIN") >= 0) {
        roles.push({"name": 'ADMIN'});
    }
    if (rols.indexOf("USER") >= 0) {
        roles.push({"name": 'USER'});
    }
    return roles;
}