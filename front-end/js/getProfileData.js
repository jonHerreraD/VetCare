const userSession = new UserSession();
console.log(userSession);

const clientId = userSession.getClientId();

let getClientData = async() =>{
    try {
        const requestClientData = await fetch(`http://localhost:8080/api/v2/clients/client/${clientId}/clients`, {
            method: 'GET',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        });

        if (!requestClientData.ok) {
            throw new Error(`Error en la solicitud: ${requestClientData.status}`);
        }

        const client = await requestClientData.json();

        renderClient(client.data || []);
    } catch (error) {
        console.error('Hubo un problema con la solicitud:', error);
    }
};

let renderClient = (client) => {
    let cardContainer = document.getElementById('card-container');
    cardContainer.innerHTML = ''; // Limpiar el contenedor antes de agregar tarjetas

        const pets = client.pets;


        let HTMLCard = `
            <div class="card mb-3" style="width: 35rem; border-radius: 8px; overflow: hidden;">
                <h5 class="card-header bg-primary text-white text-center" style="font-size: 1.3rem; padding: 8px;">
                    <strong>Perfil</strong>
                </h5>
                <div class="card-body" style="padding: 10px;">
                    <div style="display: flex; justify-content: space-between; margin-bottom: 5px;">
                        <div  style="flex: 1; margin-right: 5px;">
                            <p class="card-text mb-1" style="font-size: 0.9rem;"><strong>Nombre:</strong> ${client.name}</p>
                            <p class="card-text mb-1" style="font-size: 0.9rem;"><strong>Paterno:</strong> ${client.paternal}</p>
                            <p class="card-text mb-1" style="font-size: 0.9rem;"><strong>Materno:</strong> ${client.maternal}</p>
                            <p class="card-text mb-1" style="font-size: 0.9rem;"><strong>Mascotas:</strong> ${pets.length}</p>
                        </div>
                        <div style="flex: 1;">
                            <p class="card-text mb-1" style="font-size: 0.9rem;"><strong>Telefono:</strong> ${client.phoneNumber}</p>
                            <p class="card-text mb-1" style="font-size: 0.9rem;"><strong>Correo:</strong> ${client.email}</p>
                            <p class="card-text mb-1" style="font-size: 0.9rem;"><strong>Direccion:</strong> ${client.address}</p>
                            <p class="card-text mb-1" style="font-size: 0.9rem;"><strong>Usuario:</strong> ${client.vetUser.username}</p>
                            
                        </div>
                    </div>
                    <hr style="margin: 5px 0;">
                    <div class="d-flex justify-content-center mt-1">
                        <button class="btn btn-primary btn-sm mx-1" onclick="editPet(${client.id})">Editar</button>
                        <button class="btn btn-danger btn-sm mx-1" onclick="deletePet(${client.id})">Eliminar</button>
                    </div>
                </div>
            </div>
        `;
        cardContainer.innerHTML += HTMLCard;

};

getClientData();