let bttn = document.getElementById('signUpBttn');

bttn.addEventListener("click",event =>{
    signUp();
});

let signUp = async ()=>{
    let clientForm = {};
    let userForm = {};

    clientForm.name = document.getElementById('fname').value;
    clientForm.phoneNumber = document.getElementById('fphoneNumber').value;
    clientForm.paternal = document.getElementById('fpaternal').value;
    clientForm.maternal = document.getElementById('fmaternal').value;
    clientForm.address = document.getElementById('faddress').value;
    clientForm.email = document.getElementById('femail').value;
    userForm.user = document.getElementById('fuser').value;
    userForm.password = document.getElementById('fpassword').value;
    userForm.role = 'client';


    const clientRequest = await fetch('http://localhost:8080/api/v2/clientes/registrar', {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },

        body: JSON.stringify(clientForm)
    });

    if (clientRequest.ok) {
        const clientData = await clientRequest.json();
        const clientId = clientData.data.id;

        const loadUser = {
            user: {
                username: userForm.username,
                password: userForm.password,
                role: userForm.role
            },
            clientId: clientId
        };

        console.log(loadUser);

        const userRequest = await fetch('http://localhost:8080/api/v2/usuarios/crearUsuario', {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(loadUser)
        });

        if (userRequest.ok) {
            console.log("Usuario creado con éxito");
        } else {
            console.error("Error al crear el usuario");
        }
    }

}