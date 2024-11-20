const userSession = new UserSession();
const clientId = userSession.getClientId();

const petName = {
    setPetName: function(name) {
        this.name = name;
    }
};

let petData = {};
let petId = 0;

let bttn = document.getElementById('registerBttn');
bttn.addEventListener("click", async  event => {
    await addPet();
    await findPetData();
});

let addPet = async () => {
    let petForm = {};

    // Obtener los valores del formulario
    petForm.name = document.getElementById('fname').value;
    petForm.sex = document.getElementById('fsex').value;
    petForm.specie = document.getElementById('fspecie').value;
    petForm.age = document.getElementById('fage').value;
    petForm.breed = document.getElementById('fbreed').value;
    petForm.weight = document.getElementById('fweight').value;
    petForm.characteristics = document.getElementById('fcharacteristics').value;
    petForm.clientId = clientId;

    // Opciones para el sexo
    const sexOptions = {
        1: 'Macho',
        2: 'Hembra'
    };
    petForm.sex = sexOptions[petForm.sex];

    // Validación de campos
    function areAllFieldsFilled(form) {
        return Object.values(form).every(value => value !== null && value !== '');
    }
    if (!areAllFieldsFilled(petForm)) {
        alert("Debes llenar todos los campos");
        return;
    }

    // Cargar datos de la mascota
    const loadPet = {
        pet: {
            name: petForm.name,
            specie: petForm.specie,
            breed: petForm.breed,
            sex: petForm.sex,
            characteristics: petForm.characteristics,
            age: petForm.age,
            weight: petForm.weight,
        },
        client_id: petForm.clientId
    };

    try {
        // Enviar solicitud para registrar la mascota
        const addPetRequest = await fetch('http://localhost:8080/api/v2/pets/addPet', {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(loadPet)
        });

        if (addPetRequest.ok) {
            console.log("Mascota registrada con éxito");

            petName.setPetName(petForm.name);

        } else {
            const error = await addPetRequest.text();
            console.error('Error al registrar la mascota:', error);
        }
    } catch (error) {
        console.error('Error en la solicitud:', error);
    }
};



let findPetData = async () => {

    console.log("El nombre de la mascota es: " + petName.name);


    // Obtener la información de la mascota recién registrada
    const getPetData = await fetch(`http://localhost:8080/api/v2/pets/pet/${petName.name}/client/${clientId}`, {
        method: 'GET',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        }
    });

    petData = await getPetData.json();
    petId = petData.data?.id;  // Obtén el ID de la mascota

    console.log("Pet id " + petId);
};

/*
let createClinicHistory = async () => {


    // Crear el historial clínico
    const loadClinicHistory = {
        clinicHistory: {
            name: `${petName.name} Health Record`,
            notes: "",
        },
        pet_id: petId
    };

    console.log("Datos a enviar para el historial clínico:", JSON.stringify(loadClinicHistory));

    // Enviar solicitud para registrar el historial clínico
    const addClinicHistoryRequest = await fetch('http://localhost:8080/api/v2/healthRecords/add', {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(loadClinicHistory)
    });

    if (addClinicHistoryRequest.ok) {
        // Redireccionar o actualizar la interfaz de usuario
        window.location.replace('http://localhost:63342/vetcare/front-end/html/client/pets.html');
    } else {
        console.error('Error al registrar el historial clínico.');
    }
};

 */
