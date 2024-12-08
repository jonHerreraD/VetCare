class UserSession {
    constructor() {
        this.userType = localStorage.getItem('userType');
        this.userData = this.getUserFromStorage();
    }

    // Get user data from localStorage and parse
    getUserFromStorage() {
        const userDetails = localStorage.getItem('userDetails');
        return userDetails ? JSON.parse(userDetails) : null;
    }

    // Check if a user is logged in
    isLoggedIn() {
        return this.userData !== null;
    }

    // Check if the logged-in user is a client
    isClientLoggedIn() {
        return this.userType === 'CLIENT';
    }

    // Check if the logged-in user is an employee
    isEmployeeLoggedIn() {
        return this.userType === 'EMPLOYEE';
    }

    // Get user ID based on type
    getUserId() {
        return this.isLoggedIn() ? this.userData.id : null;
    }

    // Get user name based on type
    getUserName() {
        if (!this.isLoggedIn()) return null;

        // For clients, access username through vetUser
        if (this.isClientLoggedIn()) {
            return this.userData.vetUser.username;
        }

        // For employees, you might need to adjust based on your exact employee model
        if (this.isEmployeeLoggedIn()) {
            // Assuming employees have a username field or through vetUser
            return this.userData.vetUser.username;
        }

        return null;
    }

    // Get user's first name based on type
    getUserFirstName() {
        return this.isLoggedIn() ? this.userData.name : null;
    }

    // Get full user details
    getUserDetails() {
        return this.userData;
    }

    // Get user type
    getUserType() {
        return this.userType;
    }

    getClientId() {
        return this.isClientLoggedIn() ? this.userData.id : null;
    }

    getEmployeeId() {
        return this.isEmployeeLoggedIn() ? this.userData.id : null;
    }

    getEmployeeName(){
        return this.userData.name;
    }
}