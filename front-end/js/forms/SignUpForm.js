let bttn = document.getElementById('signUpBttn');

bttn.addEventListener("click", event => {
    signUp();
});

let signUp = async () => {
    try {
        let clientForm = {};
        let vetUserForm = {};

        clientForm.name = document.getElementById('fname').value;
        clientForm.phoneNumber = document.getElementById('fphoneNumber').value;
        clientForm.paternal = document.getElementById('fpaternal').value;
        clientForm.maternal = document.getElementById('fmaternal').value;
        clientForm.address = document.getElementById('faddress').value;
        clientForm.email = document.getElementById('femail').value;
        vetUserForm.username = document.getElementById('fuser').value;
        vetUserForm.password = document.getElementById('fpassword').value;
        vetUserForm.role = 'client';

        const clientRequest = await fetch('http://localhost:8080/api/v2/clients/add', {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(clientForm)
        });

        if (clientRequest.ok) {
            const clientData = await clientRequest.json();
            const clientId = clientData.data?.id; // Verificación opcional

            if (!clientId) {
                throw new Error("ID de cliente no encontrado en la respuesta");
            }

            const loadUser = {
                vetUser: {
                    username: vetUserForm.username,
                    password: vetUserForm.password,
                    role: vetUserForm.role
                },
                client_id: clientId,
                employee_id: null
            };

            console.log("Cargando usuario:", loadUser);

            const userRequest = await fetch('http://localhost:8080/api/v2/vetUsers/add', {
                method: 'POST',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(loadUser)
            });

            if (userRequest.ok) {
                console.log("Usuario creado con éxito");
                window.location.replace("http://localhost:63342/vetcare/front-end/html/LogIn.html?_ijt=92oia6qk4geahg18r4ip2b2upu&_ij_reload=RELOAD_ON_SAVE");
            } else {
                console.error("Error al crear el usuario: ", await userRequest.text());
            }
        } else {
            console.error("Error al agregar cliente: ", await clientRequest.text());
        }
    } catch (error) {
        console.error("Error en signUp:", error);
    }
};
