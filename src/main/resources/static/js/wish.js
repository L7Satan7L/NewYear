
const wishBox = document.getElementById('wishBox');
const closeWish = document.getElementById('closeWish');
function showWishBox(user) {
    wishBox.style.display = "grid"
    closeWish.addEventListener("click", function () {
        wishBox.style.display = "none";
        table.parentNode.removeChild(table);
    })
    const u = user.getAttribute('value');
    const w = user.getAttribute('name');
    console.log(u);
    console.log(w);
    const us = JSON.parse(u);
    console.log(us);
    const wi = JSON.parse(w);
    console.log(wi.length);
    document.getElementById('username').value = us['name'];

    let table = document.createElement('table');
    table.setAttribute("class", "table table-hover");
    let thead = document.createElement('thead');
    let tbody = document.createElement('tbody');

    table.appendChild(thead);
    table.appendChild(tbody);

    document.getElementById('table').appendChild(table);

    let row_1 = document.createElement('tr');
    let heading_1 = document.createElement('th');
    heading_1.innerHTML = "Name";
    let heading_2 = document.createElement('th');
    heading_2.innerHTML = "Wish";
    let heading_3 = document.createElement('th');
    heading_3.innerHTML = "Action";

    row_1.appendChild(heading_1);
    row_1.appendChild(heading_2);
    row_1.appendChild(heading_3);
    thead.appendChild(row_1)
    tableCycle(wi);
    function tableCycle(wi) {
        for (i = 0; i < wi.length; i++) {
            let but = document.createElement("button");
            let img = document.createElement("i");
            img.setAttribute('class', "bi bi-clipboard-check");
            but.appendChild(img);
            but.setAttribute('class',"btn btn-block buttonGet");
            let id =  wi[i]['id'];
            but.addEventListener("click", function () {
                showWishGetBox(id)
            });
            if(wi[i]['get'] === true){
                but.style.display = "none";
            }
            else{
                but.style.display = "inline-block";
            }
            let row_2 = document.createElement('tr');
            let row_2_data_1 = document.createElement('td');
            row_2_data_1.innerHTML = wi[i]["name"];
            let row_2_data_2 = document.createElement('td');
            row_2_data_2.innerHTML = wi[i]["wish"];
            let row_2_data_3 = document.createElement('td');
            row_2_data_3.appendChild(but);
            row_2.appendChild(row_2_data_1);
            row_2.appendChild(row_2_data_2);
            row_2.appendChild(row_2_data_3);
            tbody.appendChild(row_2);
        }
    }
}
const getWishBox = document.getElementById("getWish");
const getWishBut = document.getElementById("getWishBut");
const closeWishBut = document.getElementById("cancelGet");

function showWishGetBox(id) {
    console.log(id)
    getWishBox.style.display = "grid";
    closeWishBut.addEventListener("click", function () {
        getWishBox.style.display = "none";
    });
    getWishBut.addEventListener("click", function () {
        const xhttp = new XMLHttpRequest();
        xhttp.open("PUT", "http://localhost:8080/getWish");
        xhttp.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
        xhttp.send(JSON.stringify({
            "id": id
        }));
        xhttp.onreadystatechange = function () {
            if (this.readyState == 4 && this.status == 200) {
                getWishBox.style.display = "none";
                window.location.reload();
            }
        };
    });
}