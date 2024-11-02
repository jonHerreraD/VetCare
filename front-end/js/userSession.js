class UserSession{
    constructor(){
        this.user = this.getVetUserFromStorage();
    }

    //gets user from local storage and parses it
    getVetUserFromStorage(){
        const userData = localStorage.getItem('vetUser');
        return userData ? JSON.parse(userData) : null;
    }

    //Checks if there's a user logged in
    isVetUserLoggedIn(){
        return this.user !== null;
    }

    getUserId(){
        return this.isVetUserLoggedIn() ? this.user.id : null;
    }

    getUserName(){
        return this.isVetUserLoggedIn() ? this.user.username : null;
    }


}