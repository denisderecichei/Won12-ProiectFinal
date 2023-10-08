$(document).ready(() => {

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
        //apelam metoda pt a face post-ul
        addCountry(newCountry);
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
})