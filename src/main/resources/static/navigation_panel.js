function navigation_panel() {
    fetch('http://localhost:8080/api/barUserInfo')
        .then(res => {
        res.json().then(user => {
            document.getElementById('navbarName').innerText = user.name;
            document.getElementById('navbarRoles').innerText = user.roles.map((el)=> el.name.toString())
        })
    });
}
navigation_panel()