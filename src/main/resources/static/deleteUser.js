function deleteModal(id) {
    fetch(url + id, {
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json;charset=UTF-8'
        }
    }).then(res => {
        res.json().then(user => {
            document.getElementById('idDelete').value = user.id;
            document.getElementById('nameDelete').value = user.name;
            document.getElementById('surNameDelete').value = user.surName;
            document.getElementById('ageDelete').value = user.age;
            document.getElementById('salaryDelete').value = user.salary;
            document.getElementById('passwordDelete').value = user.password;
        })
    });
}

const deleteUser = document.getElementById("deleteUser");
deleteUser.addEventListener('submit', (e) => {
    e.preventDefault()
    fetch(url + document.getElementById('idDelete').value, {
        method: 'DELETE',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json;charset=UTF-8'
        }
    }).then(() => {
        document.getElementById("closeDeleteForm").click();
        getAllUsers()
    })
})
