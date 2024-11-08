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
                    <a href="#" class="btn btn-primary" onclick="editPet(${pet.id})">Editar</a>
                    <a href="#" class="btn btn-danger" onclick="deletePet(${pet.id})">Eliminar</a>
                </div>
            </div>
        `;
        cardContainer.innerHTML += HTMLCard;
    });


// Agrega event listeners a los botones de editar y eliminar
document.querySelectorAll('.edit-btn').forEach(button => {
    button.addEventListener('click', (event) => {
        event.preventDefault();
        const petId = event.target.getAttribute('data-id');
        editPet(petId); // Llama a la función de edición con el id de la mascota
    });
});

document.querySelectorAll('.delete-btn').forEach(button => {
    button.addEventListener('click', (event) => {
        event.preventDefault();
        const petId = event.target.getAttribute('data-id');
        deletePet(petId); // Llama a la función de eliminación con el id de la mascota
    });
});

};


// Función para editar una mascota específica
let editPet = (petId) => {
    console.log(`Editar mascota con ID: ${petId}`);
    // Aquí podrías redirigir a una página de edición o mostrar un formulario de edición
    // usando el petId para recuperar los datos de la mascota.
};

// Función para eliminar una mascota específica
let deletePet = async (petId) => {
    const isConfirmed = confirm("¿Está seguro de que desea eliminar esta mascota?");

    if (!isConfirmed) {
        return; // Si el usuario cancela, no se ejecuta la eliminación
    }
    try {
        const response = await fetch(`http://localhost:8080/api/v2/pets/delete/${petId}`, {
            method: 'DELETE',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        });

        if (!response.ok) {
            throw new Error(`Error al eliminar la mascota: ${response.status}`);
        }

        // Si la eliminación fue exitosa, actualiza la lista de mascotas
        console.log(`Mascota con ID ${petId} eliminada correctamente.`);
        petList(); // Vuelve a cargar la lista de mascotas
    } catch (error) {
        console.error('Hubo un problema al intentar eliminar la mascota:', error);
    }
};

// Llamar a la función para obtener y renderizar la lista de mascotas
petList();
