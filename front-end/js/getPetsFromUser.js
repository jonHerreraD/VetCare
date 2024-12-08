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
    <div class="card custom-card mb-3" style="width: 35rem;">
        <div class="card-img-container">
            <img src="../../img/Perro-con-lentes.jpg" alt="">
        </div>
        <div class="card-header">
            <strong>${pet.name}</strong>
        </div>
        <div class="card-body pet-details">
            <div class="row mb-3">
                <div class="col-6">
                    <p class="card-text mb-1"><strong>Especie:</strong> ${pet.specie}</p>
                    <p class="card-text mb-1"><strong>Raza:</strong> ${pet.breed}</p>
                    <p class="card-text mb-1"><strong>Sexo:</strong> ${pet.sex}</p>
                </div>
                <div class="col-6">
                    <p class="card-text mb-1"><strong>Edad:</strong> ${pet.age}</p>
                    <p class="card-text mb-1"><strong>Peso:</strong> ${pet.weight}</p>
                </div>
            </div>
            <div class="d-flex justify-content-between align-items-center">
                <span class="badge ${
            pet.specie.toLowerCase() === 'perro' ? 'bg-primary' :
                pet.specie.toLowerCase() === 'gato' ? 'bg-secondary' :
                    'bg-info'
        }">
                    ${pet.specie}
                </span>
                <div>
                    <button class="btn btn-sm btn-outline-primary me-2" onclick="editPet(${pet.id})">Editar</button>
                    <button class="btn btn-sm btn-outline-danger" onclick="deletePet(${pet.id})">Eliminar</button>
                </div>
            </div>
            <hr class="my-3">
            <p class="card-text text-muted" style="font-size: 0.9rem;">
                <strong>Características:</strong> ${pet.characteristics.length > 100 ? pet.characteristics.substring(0, 100) + '...' : pet.characteristics}
            </p>
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
    localStorage.setItem('petId',petId);
    // Aquí podrías redirigir a una página de edición o mostrar un formulario de edición
    window.location.replace("http://localhost:63342/vetcare/front-end/html/client/updatePet.html?_ijt=9qir044svnagrt746tvdvur6ec&_ij_reload=RELOAD_ON_SAVE");
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
