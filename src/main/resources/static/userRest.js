const data = document.getElementById("data-user");
const url = 'http://localhost:8080/api/barUserInfo';

function userAuthInfo() {
    fetch(url)
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
            data.innerHTML = temp;
        });
}
userAuthInfo()