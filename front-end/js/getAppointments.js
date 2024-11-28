const userSession = new UserSession();
console.log(userSession);

const clientId = userSession.getClientId();

const appointmentsEndpoint = `http://localhost:8080/api/v2/appointments/all/appointments/${clientId}/client`;

async function loadAppointments() {

    try {
        const getAppointments = await fetch(appointmentsEndpoint);
        if (!getAppointments.ok) {
            throw new Error("Error al obtener las citas");
        }
        const apData = await getAppointments.json();
        const appointments = apData.data;

        // Seleccionar el cuerpo de la tabla
        const tableBody = document.querySelector("#appointmentsTable tbody");
        tableBody.innerHTML = ""; // Limpiar el contenido previo

        // Generar filas de la tabla
        appointments.forEach((appointment, index) => {

            const pet = appointment.petName;
            console.log(pet);

            const row = `
                <tr>
                    <th scope="row">${index + 1}</th>
                    <td>${appointment.date}</td>
                    <td>${appointment.hour}</td>
                    <td>${appointment.clientName}</td>
                    <td>${pet}</td>
                    <td>${appointment.service}</td>
                    <td>${appointment.status}</td>
                    <td>
                        <div class="d-grid gap-2 d-md-flex justify-content-md-end button-container">
                            <button class="btn btn-info me-md-2 text-light" type="button" onclick="editAppointment(${appointment.id})">Editar</button>
                            <button class="btn btn-danger" type="button" onclick="deleteAppointment(${appointment.id})">Eliminar</button>
                        </div>
                    </td>
                </tr>
            `;
            tableBody.innerHTML += row;
        });
    } catch (error) {
        console.error("Hubo un problema al cargar las citas:", error);
    }

}


// Llamar a la función al cargar la página
document.addEventListener("DOMContentLoaded", loadAppointments);

// Funciones para editar/eliminar (placeholder)
function editAppointment(id) {
    console.log(`Editar cita con ID: ${id}`);
}

function deleteAppointment(id) {
    console.log(`Eliminar cita con ID: ${id}`);
}