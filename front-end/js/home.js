
const userSession = new UserSession();

console.log(userSession);

console.log(userSession.getUserId());  // Muestra el ID del cliente
console.log(userSession.getUserName());  // Muestra el nombre de usuario del cliente
console.log(userSession.getEmployeeId());
console.log(userSession.getEmployeeName());



/*
// Define una función asíncrona para manejar la solicitud
async function fetchCurrentUser() {
    try {
        const getCurrentUser = await fetch('http://localhost:8080/api/v2/login/current-user', {
            method: 'GET',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            credentials: 'include' // Incluye cookies en la solicitud
        });

        if (getCurrentUser.ok) {
            const result = await getCurrentUser.json(); // Si el servidor devuelve JSON, lo parseamos directamente
            console.log('Usuario logueado:', result); // Trabaja con los datos del usuario aquí
        } else {
            const error = await getCurrentUser.text();
            console.log('Error obteniendo usuario logueado: ' + error);
        }
    } catch (error) {
        console.error('Error de red o servidor:', error);
    }
}

// Llama a la función
fetchCurrentUser();


 */