const clientsEndpoint = `http://localhost:8080/api/v2/clients/all`;

async function loadClients() {

    try {
        const getClients = await fetch(clientsEndpoint);
        if (!getClients.ok) {
            throw new Error("Error al obtener los clientes");
        }
        const apData = await getClients.json();
        const clients = apData.data;

        // Seleccionar el cuerpo de la tabla
        const tableBody = document.querySelector("#clientsTable tbody");
        tableBody.innerHTML = ""; // Limpiar el contenido previo

        // Generar filas de la tabla
        clients.forEach((client, index) => {

            const row = `
    <tr>
        <th scope="row">${index + 1}</th>
        <td>${client.name}</td>
        <td>${client.paternal}</td>
        <td>${client.maternal}</td>
        <td>${client.phoneNumber}</td>
        <td>${client.address}</td>
        <td>${client.email}</td>
        <td>${client.vetUser.username}</td>
        
        <td class="action-buttons">
            <button class="btn btn-sm btn-outline-primary" type="button" onclick="editClient(${client.id})">Editar</button>
            <button class="btn btn-sm btn-outline-danger" type="button" onclick="deleteClient(${client.id})">Eliminar</button>
        </td>
    </tr>
`;
            tableBody.innerHTML += row;
        });
    } catch (error) {
        console.error("Hubo un problema al cargar los clientes:", error);
    }

}


// Llamar a la función al cargar la página
document.addEventListener("DOMContentLoaded", loadClients);

// Funciones para editar/eliminar (placeholder)
function editClient(id) {
    console.log(`Editar Cliente con ID: ${id}`);
}

function deleteClient(id) {
    console.log(`Eliminar Cliente con ID: ${id}`);
}