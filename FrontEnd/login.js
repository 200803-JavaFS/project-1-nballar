const url = "http://localhost:8080/project1/"

document.getElementById("loginbtn").addEventListener("click", loginFunc);

async function loginFunc() {
    let un = document.getElementById("username").value;
    let up = document.getElementById("password").value;

    let user = {
        userName : un,
        passWord : up
    }

    let resp = await fetch(url+"login", {
        method: 'POST',
        body: JSON.stringify(user),
        credentials: "include"
    });

    //DON'T FORGET resp.status!!!
    if(resp.status===200){
        document.getElementById("login-row").innerText = "YOU HAVE LOGGED IN.";
        redirectToHomePages();
    } else {
        document.getElementById("login-row").innerText = "Login Failed";
    }

}

async function redirectToHomePages() {

    let resp = await fetch(url+"success", {
        method: 'GET',
        credentials: "include"
    });

    if(resp.status===200) {
        let data = await resp.json();
        //Did this to see what the object's field names coming in looked like
        //Then realized they're exactly the same as field names as Java models
        //console.log(data);
        let uId = data.userId;
        sessionStorage.setItem("uId", uId);
        let uRole = data.userRoleId.roleId;

        if(uRole == 1) {
            window.location.href = "empHomePage.html";
        } else if(uRole == 2) {
            window.location.href = "finMgrHomePage.html";
        }

    } else {
        document.getElementById("login-row").innerText = "Login Failed";
    }
}