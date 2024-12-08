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

let renderPets = async (pets) => {
    let cardContainer = document.getElementById('card-container');
    cardContainer.innerHTML = ''; // Limpiar el contenedor antes de agregar tarjetas

    if (pets.length === 0) {
        cardContainer.innerHTML = '<p>No hay mascotas registradas.</p>';
        return;
    }

    for (const pet of pets) {
        // Realizar una solicitud para obtener el historial clínico de cada mascota
        let hasHealthRecord = false;

        try {
            const response = await fetch(`http://localhost:8080/api/v2/healthRecords/pet/${pet.id}`);
            if (response.ok) {
                const healthRecord = await response.json();
                hasHealthRecord = healthRecord !== null && healthRecord.data; // Asume que 'data' contiene el historial
            }
        } catch (error) {
            console.error(`Error al verificar historial clínico para la mascota ${pet.name}:`, error);
        }


        // Construir la tarjeta según si tiene historial clínico o no
        let HTMLCard = `
    <div class="card shadow-sm rounded-3 border-0" style="width: 50rem; margin-bottom: 20px; background-color: rgba(248,249,250,0.45);">
        <div class="card-header d-flex justify-content-between align-items-center" style="background-color: #e5bc0b; color: white; padding: 15px;">
            <h5 class="mb-0 d-flex align-items-center">
                <i class="fas fa-paw me-2" style="font-size: 1.2rem;"></i>
                <strong>${pet.name}</strong>
            </h5>
            ${hasHealthRecord ? `
                <a href="#" class="btn btn-light text-primary ms-auto d-flex align-items-center" onclick="viewPetRecord(${pet.id})">
                    <i class="fas fa-notes-medical me-2"></i>
                    Ver Historial Clínico
                </a>
            ` : `
                <a href="#" class="btn btn-outline-light ms-auto d-flex align-items-center" onclick="createPetRecord(${pet.id},'${pet.name}')">
                    <i class="fas fa-plus-circle me-2"></i>
                    Crear Historial Clínico
                </a>
            `}
        </div>
        <div class="card-body p-3">
            <div class="row">
                <div class="col-12">
                    <small class="text-muted">
                        <i class="fas fa-info-circle me-1"></i>
                        ${hasHealthRecord ? 'Historial médico disponible' : 'Historial médico pendiente'}
                    </small>
                </div>
            </div>
        </div>
    </div>
`;
        cardContainer.innerHTML += HTMLCard;
    }

};


/*
// Agrega event listeners a los botones de editar y eliminar
    document.querySelectorAll('.viewRecord-btn').forEach(button => {
        button.addEventListener('click', (event) => {
            event.preventDefault();
            const petId = event.target.getAttribute('data-id');
            const petName = event.target.getAttribute('data-name');
            viewPetRecord(petId); // Llama a la función de edición con el id de la mascota
            createPetRecord(petId,petName);
        });
    });

*/

let viewPetRecord = async (petId) => {
    console.log(`Ver historial de mascota con ID: ${petId}`);
    localStorage.setItem('petId',petId);

        // Aquí podrías redirigir a una página de edición o mostrar un formulario de edición
        window.location.replace("http://localhost:63342/vetcare/front-end/html/client/petClinicHistory.html?_ijt=qr7n9j243keptu0v33dg6ue3bu&_ij_reload=RELOAD_ON_SAVE");
        // usando el petId para recuperar los datos de la mascota.
};

let createPetRecord = async (petId,petName) => {
    console.log(`Crear historial de mascota con ID: ${petId}`);
    localStorage.setItem('petId',petId);

    const loadHealthRecord = {
        healthRecord: {
            name: `${petName} Historial Clinico`,
            notes: "",
        },
        pet_id: petId
    };

    console.log("Datos a enviar para el historial clínico:", JSON.stringify(loadHealthRecord));

    // Enviar solicitud para registrar el historial clínico
    const addHealthRecordRequest = await fetch('http://localhost:8080/api/v2/healthRecords/add', {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(loadHealthRecord)
    });

    if (addHealthRecordRequest.ok) {
        // Aquí podrías redirigir a una página de edición o mostrar un formulario de edición
        window.location.replace("http://localhost:63342/vetcare/front-end/html/client/petClinicHistory.html?_ijt=qr7n9j243keptu0v33dg6ue3bu&_ij_reload=RELOAD_ON_SAVE");
        // usando el petId para recuperar los datos de la mascota.
    }

};

// Llamar a la función para obtener y renderizar la lista de mascotas
petList();

