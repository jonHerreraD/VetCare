const employeesEndpoint = `http://localhost:8080/api/v2/employees/all`;

async function loadEmployees() {

    try {
        const getEmployees = await fetch(employeesEndpoint);
        if (!getEmployees.ok) {
            throw new Error("Error al obtener los trabajadores");
        }
        const apData = await getEmployees.json();
        const employees = apData.data;

        // Seleccionar el cuerpo de la tabla
        const tableBody = document.querySelector("#employeesTable tbody");
        tableBody.innerHTML = ""; // Limpiar el contenido previo

        // Generar filas de la tabla
        employees.forEach((employee, index) => {

            const row = `
    <tr>
        <th scope="row">${index + 1}</th>
        <td>${employee.name}</td>
        <td>${employee.paternal}</td>
        <td>${employee.maternal}</td>
        <td>${employee.phoneNumber}</td>
        <td>${employee.email}</td>
        <td>${employee.vetUser.username}</td>
        <td>
            <span class="badge ${
                employee.vetUser.role === 'ADMIN' ? 'bg-success' :
                    employee.vetUser.role === 'MANAGER' ? 'bg-primary' :
                        'bg-secondary'
            } role-badge">${employee.vetUser.role}</span>
        </td>
        <td class="action-buttons">
            <button class="btn btn-sm btn-outline-primary" type="button" onclick="editEmployee(${employee.id})">Editar</button>
            <button class="btn btn-sm btn-outline-danger" type="button" onclick="deleteEmployee(${employee.id})">Eliminar</button>
        </td>
    </tr>
`;
            tableBody.innerHTML += row;
        });
    } catch (error) {
        console.error("Hubo un problema al cargar los empleados:", error);
    }

}


// Llamar a la función al cargar la página
document.addEventListener("DOMContentLoaded", loadEmployees);

// Funciones para editar/eliminar (placeholder)
function editEmployee(id) {
    console.log(`Editar trabajador con ID: ${id}`);
}

function deleteEmployee(id) {
    console.log(`Eliminar trabajador con ID: ${id}`);
}