const userSession = new UserSession();

let bttn = document.getElementById('registerBttn');

bttn.addEventListener("click", event =>{
    addPet();
});



let addPet = async ()=>{
    let user = userSession.getUserFromStorage();
    let petForm = {};

    petForm.name = document.getElementById('fname').value;
    petForm.sex = document.getElementById('fsex').value;
    petForm.specie = document.getElementById('fspecie').value;
    petForm.age = document.getElementById('fage').value;
    petForm.breed = document.getElementById('fbreed').value;
    petForm.weight = document.getElementById('fweight').value;
    petForm.characteristics = document.getElementById('fcharacteristics').value;
    petForm.clientId = user.getUserId();

    const sexOptions = {
        1: 'Macho',
        2: 'Hembra'
    };
    
    //petForm.sex = sexOptions[petForm.sex] || alert("Debes llenar todos los campos");
    
    // Validar y asignar el valor de sexo
    petForm.sex = sexOptions[petForm.sex];
    if (!petForm.sex) {
        alert("Debes seleccionar el sexo");
        return; // Detener ejecución si el sexo no es válido
    }

    // Función para validar que todos los campos estén completos
    function areAllFieldsFilled(form) {
        return Object.values(form).every(value => value !== null && value !== '');
    }

    // Verificar campos antes de enviar
    if (!areAllFieldsFilled(petForm)) {
        alert("Debes llenar todos los campos");
        return; // Detener ejecución si algún campo está vacío
    }  
    
    // Realizar la solicitud al backend solo si todos los campos están llenos
try {

    const loadPet = {
        pet:{
            name: petForm.name,
            specie: petForm.specie,
            breed: petForm.breed,
            sex: petForm.sex,
            characteristics: petForm.characteristics,
            age: petForm.age,
            weight: petForm.weight,
        },
        clientId: petForm.clientId
    };

    console.log(loadPet);

    const addPetRequest = await fetch('http://localhost:8080/api/v2/pets/addPet',{
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },

        body: JSON.stringify(loadPet)
    });

    if (addPetRequest.ok) {
        console.log("Mascota registrada con éxito");
        // Redireccionar o actualizar la interfaz de usuario
    } else {
        const error = await addPetRequest.text();
        console.log('Error al registrar la mascota: ' + error);
    }
} catch (error) {
    console.error('Error en la solicitud:', error);
}
}