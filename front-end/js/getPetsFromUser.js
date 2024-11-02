const userSession = new UserSession();
const userId = userSession.getUserId();

let petList = async () => {
    try {
        const request = await fetch(`http://localhost:8080/api/v2/pets/client/${userId}/pets`, {
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
        renderPets(pets);
    } catch (error) {
        console.error('Hubo un problema con la solicitud:', error);
    }
};

let renderPets = (pets) => {
    let cardContainer = document.getElementById('card-container');
    cardContainer.innerHTML = ''; // Limpiar el contenedor antes de agregar tarjetas

    pets.forEach(pet => {
        let HTMLCard = `
            <div class="card mb-3" style="width: 50rem; margin-bottom: 20px;">
                <h5 class="card-header">${pet.name}</h5>
                <div class="card-body">
                    <p class="card-text">Specie: ${pet.specie}</p>
                    <p class="card-text">Breed: ${pet.breed}</p>
                    <p class="card-text">Sex: ${pet.sex}</p>
                    <p class="card-text">Age: ${pet.age}</p>
                    <p class="card-text">Weight: ${pet.weight}</p>
                    <p class="card-text">Characteristics: ${pet.characteristics}</p>
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
