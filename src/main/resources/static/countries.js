$(document).ready(() => {
    //variabila in care tinem minte id-ul tarii pe care o editam, daca este cazul
    let idOfCountryToEdit;

    //actiunea de pe butonul de SAVE din interiorul modalului
    //asta e deci dupa ce avem date in modal
    $('#add-button-modal').click(() => {
        //extragem valorile introduse in modal
        const name = $('#modal-name').val();
        const capital = $('#modal-capital').val();
        const population = $('#modal-population').val();
        const area = $('#modal-area').val();
        const continent = $('#modal-continent').val();

        //construim tara noua cu datele din modal
        const newCountry = {
            name: name,
            capital: capital,
            population: population,
            area: area,
            continent: continent
        };
        //verificam daca avem id de tara de edit
        //daca nu avem, inseamna ca cream o tara noua
        if (idOfCountryToEdit == null) {
            //apelam metoda pt a face post-ul
            addCountry(newCountry);
        } else {
        //daca avem id de tara, atunci este un update, adica un PUT
            editCountry(idOfCountryToEdit, newCountry);
        }
    });

    //actiunea de pe butoanele de tip delete
    //practic, cum avem mai multe butoane de delete, facem generic pe clasa butonului, pe tipul de buton
    //daca click pe delete, citeste din parintele lui id-ul, si apoi fa delete cu acel delete
    $('.delete-icon').click(function () {
        const countryId = this.parentElement.id;

        fetch('countries/' + countryId, {
            method: 'DELETE'
        }).then(response => location.reload());
    });

    //actiunea de pe butoanele de tip edit
    //practic, cum avem mai multe butoane de edit, facem generic pe clasa butonului, pe tipul de buton
    //daca click pe delete, citeste din parintele lui id-ul, si seteaza acea variabila, idOfCountryToEdit
    $('.edit-icon').click(function () {
        idOfCountryToEdit = this.parentElement.id;
        const row = this.parentElement.parentElement;
        //citim de pe row (tr) informatiile
        const name = row.children[0].innerText;
        const capital = row.children[1].innerText;
        const population = row.children[2].innerText;
        const area = row.children[3].innerText;
        const continent = row.children[4].innerText;
        //setez valorile ce vin de pe acel rand in modal
        $('#modal-name').val(name);
        $('#modal-capital').val(capital);
        $('#modal-population').val(population);
        $('#modal-area').val(area);
        $('#modal-continent').val(continent);
    });

    function addCountry(newCountry) {
        //definim API call-ul. Nu trebuie sa adaugam localhost:8080, se prinde
        fetch('countries', {
            method: 'POST',
            body: JSON.stringify(newCountry),
            headers: {
                'Content-Type': 'application/json'
            }
        }).then(response => {
            //verificam daca e 200 OK statusul
            if (response.ok) {
                //a fost salvat cu succes, si aici dam reload la pagina, ca tabelul sa aiba entitatea noua.
                location.reload();
            } else {
                //trimitem alerta cu mesajul in caz de failure.
                alert("There are errors " + response.status);
            }
        });
    }

    function editCountry(idOfCountryToEdit, newCountry) {
    //efectuam call-ul de tip PUT, foarte similar cu POST
    //atat ca la URL adaugam si id-ul, si metoda de PUT
        fetch('countries/' + idOfCountryToEdit, {
            method: 'PUT',
            body: JSON.stringify(newCountry),
            headers: {
                'Content-Type': 'application/json'
            }
        }).then(response => {
              //verificam daca e 200 OK statusul
              if (response.ok) {
                  //a fost salvat cu succes, si aici dam reload la pagina, ca tabelul sa aiba entitatea noua.
                  location.reload();
              } else {
                  //trimitem alerta cu mesajul in caz de failure.
                  alert("There are errors " + response.status);
              }
          });
    };

    //acesta este butonul de add country, care deschide modalul cand intentionam sa adaugam o tara noua
    $('#add-country-button').click(function () {
        //golim modalul in cazul in care au ramas informatii vechi
        //deoarece in cazul de nou, vrem sa incepem cu un modal gol
        $('#modal-name').val('');
        $('#modal-capital').val('');
        $('#modal-population').val('');
        $('#modal-area').val('');
        $('#modal-continent').val('');
    })
})