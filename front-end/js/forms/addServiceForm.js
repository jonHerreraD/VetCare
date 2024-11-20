const userSession = new UserSession();
console.log(userSession);

const clientId = userSession.getClientId();
const specieForm = document.getElementById('specieForm');
const breedForm = document.getElementById('breedForm');
const ageForm = document.getElementById('ageForm');
let nextBttn = document.getElementById('nextBttn');
let petId = 0;




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
    let formSelector = document.getElementById('selector');
    formSelector.innerHTML = '<option selected>Mascota</option>'; // Agrega la opción inicial

    if (pets.length === 0) {
        formSelector.innerHTML += '<option>No hay mascotas registradas</option>';
        return;
    }
    for (const pet of pets) {

        // Construir la tarjeta según si tiene historial clínico o no
        let HTMLOption = `<option value="${pet.id}">${pet.name}</option>`;
        formSelector.innerHTML += HTMLOption;
    }

    // Escuchar cambios en el selector para actualizar el campo de especie
    formSelector.addEventListener('change', () => {
        const selectedPetId = formSelector.value;
        const selectedPet = pets.find(pet => pet.id == selectedPetId);

        // Si se seleccionó una mascota válida, muestra su especie
        if (selectedPet) {
            specieForm.value = selectedPet.specie;
            breedForm.value = selectedPet.breed;
            ageForm.value = selectedPet.age;
            petId = selectedPetId;
        } else {
            specieForm.value = ''; // Limpia el campo si no hay mascota seleccionada
            breedForm.value = '';
            ageForm.value = '';
        }
    });

};

// Llamar a petList cuando el DOM esté listo
document.addEventListener('DOMContentLoaded', petList);


nextBttn.addEventListener("click", addService)

async function addService(){

    let serviceForm = {};

    serviceForm.requiredService = document.getElementById('serviceSelector').value;


    const loadService = {
        service: {
            requiredService: serviceForm.requiredService,
            servicePrice: 100.00
        }
    };

    const add = await fetch('http://localhost:8080/api/v2/vetServices/add', { // Endpoint corrected to match your Spring Boot controller
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(loadService) // Send form data as JSON
    });

    const serviceData = await add.json();
    const serviceId = serviceData.data?.id;
    const dateForm = document.getElementById('dateForm').value;
    const timeForm = document.getElementById('timeForm').value;

    if (add.ok){

        const getHealthRecordForPet = await fetch(`http://localhost:8080/api/v2/healthRecords/pet/${petId}`,{
            method: 'GET',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        });

        const healthRecord = await getHealthRecordForPet.json();
        const healthRecordId = healthRecord.data?.id;
        const loadAppointment = {
            appointment:{
                date: dateForm,
                hour: timeForm,
                status: "Pendiente"
            },
            pet_id: petId,
            vetService_id: serviceId,
            healthRecord_id: healthRecordId
        }

        const createAppointment = await fetch('http://localhost:8080/api/v2/appointments/add',{
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(loadAppointment) // Send form data as JSON
        })

        if (createAppointment.ok){
            switch (serviceForm.requiredService) {
                case "Estetica":
                    window.location.replace("http://localhost:63342/vetcare/front-end/html/client/groomingService.html?_ijt=bmcvuqq3rpcv7c9qb7hpnfm625&_ij_reload=RELOAD_ON_SAVE");
                    break;
                case "Consulta" :
                    window.location.replace("http://localhost:63342/vetcare/front-end/html/client/consultService.html?_ijt=bmcvuqq3rpcv7c9qb7hpnfm625&_ij_reload=RELOAD_ON_SAVE");
                    break;
                case "Vacunacion":
                    window.location.replace("http://localhost:63342/vetcare/front-end/html/client/vaccineService.html?_ijt=e8pb3igmrlpbji3fk11r0dsirt&_ij_reload=RELOAD_ON_SAVE");
                    break;
                case "Pension" :
                    window.location.replace("http://localhost:63342/vetcare/front-end/html/client/pensionService.html?_ijt=e8pb3igmrlpbji3fk11r0dsirt&_ij_reload=RELOAD_ON_SAVE");
                    break;
                case "Cirujia":
                    window.location.replace("http://localhost:63342/vetcare/front-end/html/client/surgeryService.html?_ijt=e8pb3igmrlpbji3fk11r0dsirt&_ij_reload=RELOAD_ON_SAVE");
                    break;
                case "Eutanasia" :
                    window.location.replace("http://localhost:63342/vetcare/front-end/html/client/euthanasiaService.html?_ijt=bmcvuqq3rpcv7c9qb7hpnfm625&_ij_reload=RELOAD_ON_SAVE");
                    break;
                default:
                    console.error("Servicio no reconocido:", serviceForm.requiredService);
            }
        }


    }

}