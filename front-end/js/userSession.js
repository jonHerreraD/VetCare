class UserSession {
    constructor() {
        this.clientData = this.getClientFromStorage();
    }

    // Obtiene los datos del cliente de localStorage y los parsea
    getClientFromStorage() {
        const clientData = localStorage.getItem('clientData');
        return clientData ? JSON.parse(clientData) : null;
    }

    // Verifica si hay un cliente logueado
    isClientLoggedIn() {
        return this.clientData !== null;
    }

    // Obtiene el ID del cliente
    getClientId() {
        return this.isClientLoggedIn() ? this.clientData.id : null;
    }
    
    getClientName(){
        return this.isClientLoggedIn() ? this.clientData.name : null;
    }

    // Obtiene el nombre de usuario del cliente
    getUserName() {
        return this.isClientLoggedIn() ? this.clientData.vetUser.username : null;
    }
}
