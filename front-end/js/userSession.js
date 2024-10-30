class UserSession{
    constructor(){
        this.UserSession = getUserFromStorage;
    }

    //gets user from local storage and parses it
    getUserFromStorage(){
        const userData = localStorage.getItem('user');
        return userData ? JSON.parse(userData) : null;
    }

    //Checks if there's a user logged in
    isUserLoggedIn(){
        return this.user !== null;
    }

    getUserId(){
        return this.isUserLoggedIn() ? this.user.id : null;
    }

    getUserName(){
        return this.isUserLoggedIn() ? this.user.username : null;
    }


}