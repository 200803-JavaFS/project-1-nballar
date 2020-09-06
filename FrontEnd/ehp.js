let uId = sessionStorage.getItem("uId");

document.getElementById("getReimbsByUserBtn").addEventListener("click", getReimbsByUserFunc);

document.getElementById("addReimbBtn").addEventListener("click", addReimbFunc);

async function getReimbsByUserFunc() {

    document.getElementById("reimbbody").innerText = "";
    let uId = sessionStorage.getItem("uId");

    let resp = await fetch(url+"reimbursementsbyauthor"+uId, {
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

async function addReimbFunc(){

    let rAmt = document.getElementById("reimbamt").value;
    let rDesc = document.getElementById("reimbdesc").value;

    const rTypes = document.querySelectorAll('input[name="gridRadios"]');
    let rTypeChoice;
    for (const rType of rTypes) {
        if (rType.checked) {
            rTypeChoice = rType.value;
            break;
        }
    }

    let reimb = {
        amt: rAmt,
        description: rDesc,
        rAuthorId: uId,
        rType: rTypeChoice
    }

    let resp = await fetch(url+"reimbursements", {
        method: 'POST',
        body: JSON.stringify(reimb),
        credentials: "include"
    });

    if(resp.status===201) {
        getReimbsByUserFunc();
    } else {
        document.getElementById("table-row").innerHTML = "Reimbursement couldn't be added";
    }

}