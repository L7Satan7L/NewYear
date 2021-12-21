const presentBox = document.getElementById('presentBox');
const closePresent = document.getElementById('closePresent');

function showMyPresent(user) {
    presentBox.style.display = "grid"
    closePresent.addEventListener("click", function () {
        presentBox.style.display = "none";
        table.parentNode.removeChild(table);
    })
    const u = user.getAttribute('value');
    const p = user.getAttribute('name');
    console.log(u);
    console.log(p);
    const us = JSON.parse(u);
    console.log(us);
    const pr = JSON.parse(p);
    console.log(pr.length);
    document.getElementById('username').value = us['name'];

    let table = document.createElement('table');
    table.setAttribute("class", "table1 table-hover");
    let thead = document.createElement('thead');
    let tbody = document.createElement('tbody');

    table.appendChild(thead);
    table.appendChild(tbody);

    document.getElementById('table').appendChild(table);

    let row_1 = document.createElement('tr');
    let heading1 = document.createElement('th');
    heading1.innerHTML = "Name";
    let heading2 = document.createElement('th');
    heading2.innerHTML = "Present";
    let heading3 = document.createElement('th');
    heading3.innerHTML = "Action";

    row_1.appendChild(heading1);
    row_1.appendChild(heading2);
    row_1.appendChild(heading3);
    thead.appendChild(row_1)
    tableCycle(pr);

    function tableCycle(pr) {
        for (i = 0; i < pr.length; i++) {
            let butt = document.createElement("button");
            let img = document.createElement("i");
            img.setAttribute('class', "bi bi-trash");
            butt.appendChild(img);
            butt.setAttribute('class', "btn btn-block buttonGet");
            let id = pr[i]['id'];
            butt.addEventListener("click", function () {
                showPresentRemoveBox(id)
            });
            let row_2 = document.createElement('tr');
            let row_2_data_1 = document.createElement('td');
            row_2_data_1.innerHTML = pr[i]["name"];
            let row_2_data_2 = document.createElement('td');
            row_2_data_2.innerHTML = pr[i]["wish"];
            let row_2_data_3 = document.createElement('td');
            row_2_data_3.appendChild(butt);
            row_2.appendChild(row_2_data_1);
            row_2.appendChild(row_2_data_2);
            row_2.appendChild(row_2_data_3);
            tbody.appendChild(row_2);
        }
    }
}

const removePresent = document.getElementById("removePresent");
const removePresentBut = document.getElementById("removePresentBut");
const cancelRemove = document.getElementById("cancelRemove");

function showPresentRemoveBox(id) {
    console.log(id)
    removePresent.style.display = "grid";
    cancelRemove.addEventListener("click", function () {
        removePresent.style.display = "none";
    });
    removePresentBut.addEventListener("click", function () {
        const xhttp = new XMLHttpRequest();
        xhttp.open("DELETE", "http://localhost:8080/profile/deletePresent");
        xhttp.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
        xhttp.send(JSON.stringify({
            "id": id
        }));
        xhttp.onreadystatechange = function () {
            if (this.readyState == 4 && this.status == 200) {
                removePresent.style.display = "none";
                window.location.reload();
            }
        };
    });
}


const field = document.getElementById("fieldName");
const edit_button = document.getElementById("edit-button");
const ok_button = document.getElementById("ok-button");

edit_button.addEventListener("click", function () {
    field.removeAttribute('readonly');
    edit_button.style.display = "none";
    ok_button.style.display = "block";
});

ok_button.addEventListener("click", function () {
    field.setAttribute('readonly', true);
    ok_button.style.display = "none";
    edit_button.style.display = "block";
})

const addBox = document.getElementById("AddBox");
const close_button = document.getElementById("close");

function showWishCreateBox() {
    addBox.style.display = "grid";
    close_button.addEventListener("click", function () {
        addBox.style.display = "none";
    })
}

const create_button = document.getElementById("create");

function WishCreate() {
    addBox.style.display = "none";
    const name = document.getElementById("name").value;
    const wish = document.getElementById("wish").value;

    const xhttp = new XMLHttpRequest();
    xhttp.open("POST", "http://localhost:8080/profile/addWish");
    xhttp.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
    xhttp.send(JSON.stringify({
        "name": name, "wish": wish
    }));
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            create_button.style.display = "grid";
            create_button.addEventListener("click", function () {
                create_button.style.display = "none";
            })
        }
    };
}

const closed_button = document.getElementById("closed");
const updateBox = document.getElementById("UpdateBox");

function showWishEditBox(str) {
    updateBox.style.display = "grid";
    closed_button.addEventListener("click", function () {
        updateBox.style.display = "none";
    })
    console.log(str);
    const id = str.getAttribute('value');
    console.log(id);
    const objects = JSON.parse(id);
    console.log(objects);
    const w = objects['wish'];
    console.log(w);
    const n = objects['name'];
    console.log(n);
    const i = objects['id'];
    console.log(i);
    document.getElementById('idUpdate').value = i;
    document.getElementById('nameUpdate').value = n;
    document.getElementById('wishUpdate').value = w;
}

function wishEdit() {
    updateBox.style.display = "none";
    const id = document.getElementById("idUpdate").value;
    const name = document.getElementById("nameUpdate").value;
    const wish = document.getElementById("wishUpdate").value;
    const update = document.getElementById("update");
    const update_close = document.getElementById("update_close");
    const xhttp = new XMLHttpRequest();
    xhttp.open("PUT", "http://localhost:8080/profile/update");
    xhttp.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
    xhttp.send(JSON.stringify({
        "id": id, "name": name, "wish": wish
    }));
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            update.style.display = "grid";
            update_close.addEventListener("click", function () {
                update.style.display = "none";
            })
        }
    };
}

const cancel_button = document.getElementById("cancel");
const delete_div = document.getElementById("delete");
const delete_button = document.getElementById("deleted");

function showWishDeleteBox(str) {
    const id = str.getAttribute('value');
    delete_div.style.display = "grid";
    cancel_button.addEventListener("click", function () {
        delete_div.style.display = "none";
    })
    delete_button.addEventListener("click", function () {
        const xhttp = new XMLHttpRequest();
        xhttp.open("DELETE", "http://localhost:8080/profile/delete");
        xhttp.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
        xhttp.send(JSON.stringify({
            "id": id
        }));
        xhttp.onreadystatechange = function () {
            if (this.readyState == 4 && this.status == 200) {
                delete_div.style.display = "none";
                window.location.reload();
            }
        };
    })
}
