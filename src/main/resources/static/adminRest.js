const url = 'http://localhost:8080/api/admin/users/';
const renderTable = document.getElementById("showAllUsers");

const UsersTable = (showAllUsers) => {
    let temp = '';
    showAllUsers.forEach((user) => {
        temp += `<tr>
                    <td>${user.id}</td>
                    <td>${user.name}</td>
                    <td>${user.surName}</td>
                    <td>${user.age}</td>
                    <td>${user.salary}</td>
                    <td>${user.roles.map((el)=> el.name.toString())}</td>
                     <td>  
                     <button class="btn btn-info" type="button"
                     data-bs-toggle="modal" data-bs-target="#modalEdit"
                     onclick="editModal(${user.id})">Edit</button></td>
                     <td>
                     <button class="btn btn-danger" type="button"
                     data-bs-toggle="modal" data-bs-target="#modalDelete"
                     onclick="deleteModal(${user.id})">Delete</button></td>
                  </tr>`
    })
    renderTable.innerHTML = temp;
}

function getAllUsers() {
    fetch(url)
        .then(res => res.json())
        .then(data => {
            UsersTable(data)
        })
}
getAllUsers()

//Вкладка User в Admin Panel
const userTable = document.getElementById("About user");
const urlAuth = 'http://localhost:8080/api/barUserInfo';

function userAuthInfo() {
    fetch(urlAuth)
        .then((res) => res.json())
        .then((user) => {
            let temp = '';
            temp += `<tr>
            <td>${user.id}</td>
            <td>${user.name}</td>
            <td>${user.surName}</td>
            <td>${user.age}</td>
            <td>${user.salary}</td>
            <td>${user.roles.map((el)=> el.name.toString())}</td>
            </tr>`;
            userTable.innerHTML = temp;
        });
}

userAuthInfo()


