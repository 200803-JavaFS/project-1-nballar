let uId = sessionStorage.getItem("uId");

document.getElementById("getAllBtn").addEventListener("click", getAllFunc);
document.getElementById("getByStatusBtn").addEventListener("click", getReimbsByStatusFunc);
document.getElementById("updateStatusBtn").addEventListener("click", updateReimbStatusFunc);
document.getElementById("logoutBtn").addEventListener("click", logoutFunc);

async function getAllFunc() {

    document.getElementById("reimbbody").innerText = "";

    let resp = await fetch(url+"reimbursements", {
        credentials: 'include'
    });

    if(resp.status===200){
        let data = await resp.json();
        for (let reimbursement of data){
            console.log(reimbursement);
            let row = document.createElement("tr");
            let cell = document.createElement("td");
            cell.innerHTML = reimbursement.reimbId;
            row.appendChild(cell);
            let cell2 = document.createElement("td");
            cell2.innerHTML = reimbursement.reimbAmt;
            row.appendChild(cell2);
            let cell3 = document.createElement("td");
            let stime = new Date(reimbursement.submitted);
            cell3.innerHTML = stime.toLocaleDateString();
            row.appendChild(cell3);
            if (reimbursement.resolved != null){
                let cell4 = document.createElement("td");
                let rtime = new Date(reimbursement.resolved);
                cell4.innerHTML = rtime.toLocaleDateString();
                row.appendChild(cell4);
            } else {
                let cell4 = document.createElement("td");
                row.appendChild(cell4);
            }
            let cell5 = document.createElement("td");
            cell5.innerHTML = reimbursement.description;
            row.appendChild(cell5);
            let cell6 = document.createElement("td");
            cell6.innerHTML = reimbursement.reimbAuthorId.userId;
            row.appendChild(cell6);
            if (reimbursement.reimbResolverId != null){
                let cell7 = document.createElement("td");
                cell7.innerHTML = reimbursement.reimbResolverId.userId;
                row.appendChild(cell7);
            } else {
                let cell7 = document.createElement("td");
                row.appendChild(cell7);
            }
            let cell8 = document.createElement("td");
            cell8.innerHTML = reimbursement.reimbStatusId.statusId;
            row.appendChild(cell8);
            let cell9 = document.createElement("td");
            cell9.innerHTML = reimbursement.reimbTypeId.typeId;
            row.appendChild(cell9);
            document.getElementById("reimbbody").appendChild(row);
            
        }
    }
}

async function getReimbsByStatusFunc() {

    document.getElementById("reimbbody2").innerText = "";

    let rStatus = document.getElementById("statusIdInput");
    let rsIdNumber = rStatus.value;

    let resp = await fetch(url+"reimbursementsbystatus/"+rsIdNumber, {
        credentials: "include"
    });

    if(resp.status===200){
        let data = await resp.json();
        for (let reimbursement of data){
            console.log(reimbursement);
            let row = document.createElement("tr");
            let cell = document.createElement("td");
            cell.innerHTML = reimbursement.reimbId;
            row.appendChild(cell);
            let cell2 = document.createElement("td");
            cell2.innerHTML = reimbursement.reimbAmt;
            row.appendChild(cell2);
            let cell3 = document.createElement("td");
            let stime = new Date(reimbursement.submitted);
            cell3.innerHTML = stime.toLocaleDateString();
            row.appendChild(cell3);
            if (reimbursement.resolved != null){
                let cell4 = document.createElement("td");
                let rtime = new Date(reimbursement.resolved);
                cell4.innerHTML = rtime.toLocaleDateString();
                row.appendChild(cell4);
            } else {
                let cell4 = document.createElement("td");
                row.appendChild(cell4);
            }
            let cell5 = document.createElement("td");
            cell5.innerHTML = reimbursement.description;
            row.appendChild(cell5);
            let cell6 = document.createElement("td");
            cell6.innerHTML = reimbursement.reimbAuthorId.userId;
            row.appendChild(cell6);
            if (reimbursement.reimbResolverId != null){
                let cell7 = document.createElement("td");
                cell7.innerHTML = reimbursement.reimbResolverId.userId;
                row.appendChild(cell7);
            } else {
                let cell7 = document.createElement("td");
                row.appendChild(cell7);
            }
            let cell8 = document.createElement("td");
            cell8.innerHTML = reimbursement.reimbStatusId.statusId;
            row.appendChild(cell8);
            let cell9 = document.createElement("td");
            cell9.innerHTML = reimbursement.reimbTypeId.typeId;
            row.appendChild(cell9);
            document.getElementById("reimbbody2").appendChild(row);
            
        }
    }
}

async function updateReimbStatusFunc(){

    let rId = document.getElementById("reimbIdInput");
    let rIdNumber = rId.value;

    const rStats = document.querySelectorAll('input[name="status"]');
    let rStatsChoice;
    for (const rStatus of rStats) {
        if (rStatus.checked) {
            rStatsChoice = rStatus.value;
            break;
        }
    }

    let reimbStatus = {
        id: rIdNumber,
        rAuthorId: uId,
        rStatus: rStatsChoice
    }

    let resp = await fetch(url+"updatereimbstatus", {
        method: 'POST',
        body: JSON.stringify(reimbStatus),
        credentials: "include"
    });

    if(resp.status===202) {
        document.getElementById("updateSuccess").innerHTML = "Reimbursement update successful";
    } else {
        document.getElementById("updateSuccess").innerHTML = "Reimbursement update failure";
    }
}

async function logoutFunc(){

    let resp = await fetch(url+"logout", {
        credentials: "include"
    });

    if(resp.status===200) {
        window.location.href = "index.html";
    }
}