//import UserSession from '/userSession';

const userSession = new UserSession();
console.log(userSession);

const clientId = userSession.getClientId();
let petList = async () => {
    try {
        const request = await fetch(`http://localhost:8080/api/v2/pets/client/${clientId}/pets`, {
            method: 'GET',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        });

        if (!request.ok) {
            throw new Error(`Error en la solicitud: ${request.status}`);
        }

        const pets = await request.json();
        renderPets(pets.data || []);
    } catch (error) {
        console.error('Hubo un problema con la solicitud:', error);
    }
};

let renderPets = (pets) => {
    let cardContainer = document.getElementById('card-container');
    cardContainer.innerHTML = ''; // Limpiar el contenedor antes de agregar tarjetas

    if(pets.length === 0){
        cardContainer.innerHTML = '<p>No hay mascotas registradas.</p>';
        return;
    }
    pets.forEach(pet => {
        let HTMLCard = `
            <div class="card mb-3" style="width: 50rem; margin-bottom: 20px;">
                <h5 class="card-header"><strong>${pet.name}</strong></h5>
                <div class="card-body">
                    <p class="card-text"><strong>Specie:</strong> ${pet.specie} <br>
                                         <strong>Breed:</strong> ${pet.breed} <br>
                                         <strong>Sex:</strong> ${pet.sex} <br>
                                         <strong>Age:</strong> ${pet.age} <br>
                                         <strong>Weight:</strong> ${pet.weight}</p> 
                    <p class="card-text"><strong>Characteristics:</strong> ${pet.characteristics}</p>
                    <a href="#" class="btn btn-primary">Editar</a>
                    <a href="#" class="btn btn-danger">Eliminar</a>
                </div>
            </div>
        `;
        cardContainer.innerHTML += HTMLCard;
    });
};

// Llamar a la función para obtener y renderizar la lista de mascotas
petList();
